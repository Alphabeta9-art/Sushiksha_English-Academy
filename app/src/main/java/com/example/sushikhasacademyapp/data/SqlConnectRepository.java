package com.example.sushikhasacademyapp.data;

import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirestoreKt;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.tasks.TasksKt;

/* JADX INFO: compiled from: SqlConnectRepository.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010'\u001a\u00020(H\u0002J\u000e\u0010)\u001a\u00020(H\u0086@¢\u0006\u0002\u0010*J\"\u0010+\u001a\u00020(2\u0006\u0010,\u001a\u00020\u00132\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020(0.J\"\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020\u00172\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020(0.J\"\u00101\u001a\u00020(2\u0006\u00102\u001a\u00020\u000e2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020(0.J\"\u00103\u001a\u00020(2\u0006\u00104\u001a\u00020\u001a2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020(0.J\"\u00105\u001a\u00020(2\u0006\u00106\u001a\u00020\u000e2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020(0.J\"\u00107\u001a\u00020(2\u0006\u00108\u001a\u00020\u00102\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020(0.R\u001d\u0010\u0004\u001a\u0004\u0018\u00010\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\r0\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\r0\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0!¢\u0006\b\n\u0000\u001a\u0004\b \u0010\"R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020$0!¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\"¨\u00069"}, d2 = {"Lcom/example/sushikhasacademyapp/data/SqlConnectRepository;", "", "<init>", "()V", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "getDb", "()Lcom/google/firebase/firestore/FirebaseFirestore;", "db$delegate", "Lkotlin/Lazy;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "defaultSpecialties", "", "", "defaultContactInfo", "Lcom/example/sushikhasacademyapp/data/ContactInfo;", "teacherProfile", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/sushikhasacademyapp/data/TeacherProfile;", "getTeacherProfile", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "courses", "Lcom/example/sushikhasacademyapp/data/CourseInfo;", "getCourses", "batches", "Lcom/example/sushikhasacademyapp/data/BatchSchedule;", "getBatches", "contactInfo", "getContactInfo", "_isLoading", "", "isLoading", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "_lastSyncTimestamp", "", "lastSyncTimestamp", "getLastSyncTimestamp", "startFirestoreListeners", "", "fetchLatestFromServer", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTeacherProfile", Scopes.PROFILE, "onComplete", "Lkotlin/Function1;", "saveCourse", "course", "deleteCourse", "courseId", "saveBatch", "batch", "deleteBatch", "batchId", "updateContactInfo", "contact", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SqlConnectRepository {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: db$delegate, reason: from kotlin metadata */
    private final Lazy db = LazyKt.lazy(new Function0() { // from class: com.example.sushikhasacademyapp.data.SqlConnectRepository$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SqlConnectRepository.db_delegate$lambda$0();
        }
    });
    private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
    private final List<String> defaultSpecialties = CollectionsKt.listOf((Object[]) new String[]{"Spoken English", "Advanced Grammar & Literature", "IELTS & Public Speaking"});
    private final ContactInfo defaultContactInfo = new ContactInfo("3, Vivekananda Road, Nabagram, Konnagar, Hooghly - 712246 (Near Holy Mission School)", "+91 9836926092", "sushiksha.konnagar@gmail.com");
    private final MutableStateFlow<TeacherProfile> teacherProfile = StateFlowKt.MutableStateFlow(new TeacherProfile(null, null, null, 0.0d, 0, null, null, this.defaultSpecialties, 127, null));
    private final MutableStateFlow<List<CourseInfo>> courses = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
    private final MutableStateFlow<List<BatchSchedule>> batches = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
    private final MutableStateFlow<ContactInfo> contactInfo = StateFlowKt.MutableStateFlow(this.defaultContactInfo);
    private final MutableStateFlow<Boolean> _isLoading = StateFlowKt.MutableStateFlow(true);
    private final StateFlow<Boolean> isLoading = FlowKt.asStateFlow(this._isLoading);
    private final MutableStateFlow<Long> _lastSyncTimestamp = StateFlowKt.MutableStateFlow(0L);
    private final StateFlow<Long> lastSyncTimestamp = FlowKt.asStateFlow(this._lastSyncTimestamp);

    public SqlConnectRepository() {
        startFirestoreListeners();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirebaseFirestore getDb() {
        return (FirebaseFirestore) this.db.getValue();
    }

    static final FirebaseFirestore db_delegate$lambda$0() {
        try {
            return FirestoreKt.getFirestore(Firebase.INSTANCE);
        } catch (Exception e) {
            Log.w("SqlConnectRepository", "Firebase not initialized: " + e.getMessage());
            return null;
        }
    }

    public final MutableStateFlow<TeacherProfile> getTeacherProfile() {
        return this.teacherProfile;
    }

    public final MutableStateFlow<List<CourseInfo>> getCourses() {
        return this.courses;
    }

    public final MutableStateFlow<List<BatchSchedule>> getBatches() {
        return this.batches;
    }

    public final MutableStateFlow<ContactInfo> getContactInfo() {
        return this.contactInfo;
    }

    public final StateFlow<Boolean> isLoading() {
        return this.isLoading;
    }

    public final StateFlow<Long> getLastSyncTimestamp() {
        return this.lastSyncTimestamp;
    }

    private final void startFirestoreListeners() {
        FirebaseFirestore firestore = getDb();
        if (firestore == null) {
            this._isLoading.setValue(false);
            return;
        }
        firestore.collection("teacher_profile").addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.data.SqlConnectRepository$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                SqlConnectRepository.startFirestoreListeners$lambda$2(this.f$0, (QuerySnapshot) obj, firebaseFirestoreException);
            }
        });
        firestore.collection("courses").addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.data.SqlConnectRepository$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                SqlConnectRepository.startFirestoreListeners$lambda$4(this.f$0, (QuerySnapshot) obj, firebaseFirestoreException);
            }
        });
        firestore.collection("batches").addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.data.SqlConnectRepository$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                SqlConnectRepository.startFirestoreListeners$lambda$6(this.f$0, (QuerySnapshot) obj, firebaseFirestoreException);
            }
        });
        firestore.collection("contact_info").addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.data.SqlConnectRepository$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                SqlConnectRepository.startFirestoreListeners$lambda$7(this.f$0, (QuerySnapshot) obj, firebaseFirestoreException);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass5(null), 3, null);
    }

    static final void startFirestoreListeners$lambda$2(SqlConnectRepository this$0, QuerySnapshot snapshot, FirebaseFirestoreException error) {
        boolean z;
        ArrayList arrayList;
        if (error != null) {
            Log.w("FirestoreRepo", "Listen teacher_profile failed.", error);
            return;
        }
        if (snapshot == null || snapshot.isEmpty()) {
            z = false;
        } else {
            List<DocumentSnapshot> documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            DocumentSnapshot doc = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents);
            if (doc != null) {
                String string = doc.getString(HintConstants.AUTOFILL_HINT_NAME);
                if (string == null && (string = doc.getString("Name")) == null) {
                    string = "Suman Ghosh";
                }
                String name = string;
                String string2 = doc.getString("designation");
                if (string2 == null && (string2 = doc.getString("Title")) == null) {
                    string2 = "Lead English Teacher & Mentor";
                }
                String designation = string2;
                String string3 = doc.getString("Qualification");
                if (string3 == null && (string3 = doc.getString("qualification")) == null) {
                    string3 = "M.A English | 30+ Years of Teaching Experience";
                }
                String qualification = string3;
                String string4 = doc.getString("Rating");
                if (string4 == null) {
                    Object obj = doc.get("Rating");
                    string4 = obj != null ? obj.toString() : null;
                    if (string4 == null) {
                        string4 = "4.9";
                    }
                }
                String ratingStr = string4;
                Double doubleOrNull = StringsKt.toDoubleOrNull(ratingStr);
                double rating = doubleOrNull != null ? doubleOrNull.doubleValue() : 5.0d;
                String string5 = doc.getString("Student Taught");
                if (string5 == null) {
                    Object obj2 = doc.get("Student Taught");
                    string5 = obj2 != null ? obj2.toString() : null;
                    if (string5 == null) {
                        string5 = "1500";
                    }
                }
                String studentsStr = string5;
                Integer intOrNull = StringsKt.toIntOrNull(studentsStr);
                int studentsGuided = intOrNull != null ? intOrNull.intValue() : 1500;
                String bio = doc.getString("Philosophy");
                if (bio == null && (bio = doc.getString("bio")) == null) {
                    bio = "";
                }
                String string6 = doc.getString("Image");
                String dpUrl = (string6 == null && (string6 = doc.getString("dpUrl")) == null) ? "" : string6;
                Object obj3 = doc.get("specialties");
                Iterable iterable = obj3 instanceof List ? (List) obj3 : null;
                if (iterable != null) {
                    z = false;
                    Collection arrayList2 = new ArrayList();
                    Iterator it = iterable.iterator();
                    while (it.hasNext()) {
                        Object next = it.next();
                        String string7 = next != null ? next.toString() : null;
                        if (string7 != null) {
                            arrayList2.add(string7);
                        }
                    }
                    arrayList = (List) arrayList2;
                } else {
                    z = false;
                    arrayList = null;
                }
                ArrayList arrayList3 = arrayList;
                ArrayList arrayList4 = arrayList3;
                String dpUrl2 = dpUrl;
                String dpUrl3 = bio;
                this$0.teacherProfile.setValue(new TeacherProfile(name, designation, qualification, rating, studentsGuided, dpUrl2, dpUrl3, !((arrayList4 == null || arrayList4.isEmpty()) ? true : z) ? arrayList3 : this$0.defaultSpecialties));
            } else {
                z = false;
            }
        }
        this$0._isLoading.setValue(Boolean.valueOf(z));
        this$0._lastSyncTimestamp.setValue(Long.valueOf(System.currentTimeMillis()));
    }

    /* JADX WARN: Code duplicated, block: B:57:0x00ec A[PHI: r9
  0x00ec: PHI (r9v20 java.lang.Double) = (r9v19 java.lang.Double), (r9v24 java.lang.Double) binds: [B:49:0x00d4, B:56:0x00ea] A[DONT_GENERATE, DONT_INLINE]] */
    static final void startFirestoreListeners$lambda$4(SqlConnectRepository this$0, QuerySnapshot snapshot, FirebaseFirestoreException error) {
        double dDoubleValue;
        String string;
        if (error != null) {
            Log.w("FirestoreRepo", "Listen courses failed.", error);
            return;
        }
        if (snapshot != null) {
            Iterable documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            Iterable<DocumentSnapshot> iterable = documents;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (DocumentSnapshot documentSnapshot : iterable) {
                String string2 = documentSnapshot.getString("Title");
                if (string2 == null && (string2 = documentSnapshot.getString("title")) == null && (string2 = documentSnapshot.getString("Course Title")) == null) {
                    string2 = documentSnapshot.getId();
                    Intrinsics.checkNotNullExpressionValue(string2, "getId(...)");
                }
                String str = string2;
                String string3 = documentSnapshot.getString("Duration");
                String str2 = (string3 == null && (string3 = documentSnapshot.getString(TypedValues.TransitionType.S_DURATION)) == null) ? "" : string3;
                String string4 = documentSnapshot.getString("Description");
                String str3 = (string4 == null && (string4 = documentSnapshot.getString("description")) == null) ? "" : string4;
                String string5 = documentSnapshot.getString("Mode");
                String str4 = (string5 == null && (string5 = documentSnapshot.getString("mode")) == null) ? "" : string5;
                String string6 = documentSnapshot.getString("Level");
                String str5 = (string6 == null && (string6 = documentSnapshot.getString("level")) == null) ? "" : string6;
                String string7 = documentSnapshot.getString("Fee Structure");
                String str6 = (string7 == null && (string7 = documentSnapshot.getString("fee")) == null && (string7 = documentSnapshot.getString("Fee")) == null) ? "" : string7;
                Double doubleOrNull = documentSnapshot.getDouble("rating");
                if (doubleOrNull != null) {
                    dDoubleValue = doubleOrNull.doubleValue();
                } else {
                    Object obj = documentSnapshot.get("Rating");
                    doubleOrNull = (obj == null || (string = obj.toString()) == null) ? null : StringsKt.toDoubleOrNull(string);
                    if (doubleOrNull != null) {
                        dDoubleValue = doubleOrNull.doubleValue();
                    } else {
                        dDoubleValue = 5.0d;
                    }
                }
                String id = documentSnapshot.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                arrayList.add(new CourseInfo(id, str, str3, str2, str4, str5, str6, dDoubleValue, null, 256, null));
            }
            List loadedCourses = (List) arrayList;
            this$0.courses.setValue(loadedCourses);
        }
    }

    static final void startFirestoreListeners$lambda$6(SqlConnectRepository this$0, QuerySnapshot snapshot, FirebaseFirestoreException error) {
        String str;
        int iIntValue;
        String string;
        String string2;
        if (error != null) {
            Log.w("FirestoreRepo", "Listen batches failed.", error);
            return;
        }
        if (snapshot != null) {
            Iterable documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            Iterable<DocumentSnapshot> iterable = documents;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (DocumentSnapshot documentSnapshot : iterable) {
                String string3 = documentSnapshot.getString("Title");
                if (string3 == null && (string3 = documentSnapshot.getString("title")) == null && (string3 = documentSnapshot.getString("courseTitle")) == null) {
                    string3 = documentSnapshot.getId();
                    Intrinsics.checkNotNullExpressionValue(string3, "getId(...)");
                }
                String str2 = string3;
                String string4 = documentSnapshot.getString("Timing");
                String str3 = (string4 == null && (string4 = documentSnapshot.getString("timing")) == null) ? "" : string4;
                String string5 = documentSnapshot.getString("Days");
                String str4 = (string5 == null && (string5 = documentSnapshot.getString("days")) == null) ? "" : string5;
                Long l = documentSnapshot.getLong("Seats Left");
                if (l != null) {
                    str = str2;
                    iIntValue = (int) l.longValue();
                } else {
                    str = str2;
                    Long l2 = documentSnapshot.getLong("seatsLeft");
                    if (l2 != null) {
                        iIntValue = (int) l2.longValue();
                    } else {
                        String string6 = documentSnapshot.getString("Seats Left");
                        Integer intOrNull = null;
                        Integer intOrNull2 = string6 != null ? StringsKt.toIntOrNull(string6) : null;
                        if (intOrNull2 != null) {
                            iIntValue = intOrNull2.intValue();
                        } else {
                            Object obj = documentSnapshot.get("Seats Left");
                            Integer intOrNull3 = (obj == null || (string2 = obj.toString()) == null) ? null : StringsKt.toIntOrNull(string2);
                            if (intOrNull3 != null) {
                                iIntValue = intOrNull3.intValue();
                            } else {
                                Object obj2 = documentSnapshot.get("seatsLeft");
                                if (obj2 != null && (string = obj2.toString()) != null) {
                                    intOrNull = StringsKt.toIntOrNull(string);
                                }
                                iIntValue = intOrNull != null ? intOrNull.intValue() : 0;
                            }
                        }
                    }
                }
                String string7 = documentSnapshot.getString("Mode");
                if (string7 == null && (string7 = documentSnapshot.getString("mode")) == null) {
                    string7 = "Offline";
                }
                String str5 = string7;
                boolean z = StringsKt.contains((CharSequence) str5, (CharSequence) "Online", true) || Intrinsics.areEqual((Object) documentSnapshot.getBoolean("isOnline"), (Object) true);
                String id = documentSnapshot.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                arrayList.add(new BatchSchedule(id, str, str3, str4, iIntValue, z, str5));
            }
            List loadedBatches = (List) arrayList;
            this$0.batches.setValue(loadedBatches);
        }
    }

    static final void startFirestoreListeners$lambda$7(SqlConnectRepository this$0, QuerySnapshot snapshot, FirebaseFirestoreException error) {
        if (error != null) {
            Log.w("FirestoreRepo", "Listen contact_info failed.", error);
            return;
        }
        if (snapshot != null && !snapshot.isEmpty()) {
            List<DocumentSnapshot> documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            DocumentSnapshot doc = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents);
            if (doc != null) {
                String address = doc.getString("address");
                if (address == null && (address = doc.getString("Address")) == null) {
                    address = "3, Vivekananda Road, Nabagram, Konnagar, Hooghly - 712246 (Near Holy Mission School)";
                }
                String email = doc.getString("email");
                if (email == null && (email = doc.getString("Email")) == null) {
                    email = "sushiksha.konnagar@gmail.com";
                }
                String phone = doc.getString("phone_number");
                if (phone == null && (phone = doc.getString("phone")) == null && (phone = doc.getString("Phone")) == null && (phone = doc.getString("phone_Number")) == null) {
                    phone = "+91 9836926092";
                }
                this$0.contactInfo.setValue(new ContactInfo(address, phone, email));
            }
        }
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$startFirestoreListeners$5, reason: invalid class name */
    /* JADX INFO: compiled from: SqlConnectRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$startFirestoreListeners$5", f = "SqlConnectRepository.kt", i = {}, l = {192}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass5(Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SqlConnectRepository.this.new AnonymousClass5(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (SqlConnectRepository.this.fetchLatestFromServer(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$fetchLatestFromServer$2, reason: invalid class name */
    /* JADX INFO: compiled from: SqlConnectRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$fetchLatestFromServer$2", f = "SqlConnectRepository.kt", i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3}, l = {203, 231, 255, 282}, m = "invokeSuspend", n = {"$this$withContext", "firestore", "$this$withContext", "firestore", "teacherSnapshot", "doc", "$this$withContext", "firestore", "teacherSnapshot", "doc", "courseSnapshot", "loadedCourses", "$this$withContext", "firestore", "teacherSnapshot", "doc", "courseSnapshot", "loadedCourses", "batchSnapshot", "loadedBatches"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = SqlConnectRepository.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:100:0x020d  */
        /* JADX WARN: Code duplicated, block: B:102:0x0211 A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:103:0x0221  */
        /* JADX WARN: Code duplicated, block: B:106:0x0239  */
        /* JADX WARN: Code duplicated, block: B:112:0x024a  */
        /* JADX WARN: Code duplicated, block: B:114:0x024d  */
        /* JADX WARN: Code duplicated, block: B:115:0x0250 A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:117:0x0275  */
        /* JADX WARN: Code duplicated, block: B:120:0x02a4 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:121:0x02a5  */
        /* JADX WARN: Code duplicated, block: B:125:0x02d5 A[Catch: Exception -> 0x065d, TryCatch #1 {Exception -> 0x065d, blocks: (B:122:0x02a9, B:123:0x02cf, B:125:0x02d5, B:127:0x02eb, B:129:0x02f1, B:131:0x02fb, B:133:0x0307, B:135:0x0311, B:139:0x031e, B:141:0x0326, B:145:0x0333, B:147:0x0339, B:151:0x0344, B:153:0x034c, B:157:0x0359, B:159:0x0361, B:161:0x0369, B:165:0x0376, B:167:0x037e, B:169:0x0384, B:171:0x038a, B:176:0x039a, B:174:0x0393, B:177:0x03c3), top: B:290:0x02a9 }] */
        /* JADX WARN: Code duplicated, block: B:127:0x02eb A[Catch: Exception -> 0x065d, TryCatch #1 {Exception -> 0x065d, blocks: (B:122:0x02a9, B:123:0x02cf, B:125:0x02d5, B:127:0x02eb, B:129:0x02f1, B:131:0x02fb, B:133:0x0307, B:135:0x0311, B:139:0x031e, B:141:0x0326, B:145:0x0333, B:147:0x0339, B:151:0x0344, B:153:0x034c, B:157:0x0359, B:159:0x0361, B:161:0x0369, B:165:0x0376, B:167:0x037e, B:169:0x0384, B:171:0x038a, B:176:0x039a, B:174:0x0393, B:177:0x03c3), top: B:290:0x02a9 }] */
        /* JADX WARN: Code duplicated, block: B:132:0x0305 A[PHI: r29
  0x0305: PHI (r29v5 java.lang.String) = (r29v4 java.lang.String), (r29v7 java.lang.String) binds: [B:126:0x02e9, B:128:0x02ef] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:135:0x0311 A[Catch: Exception -> 0x065d, TryCatch #1 {Exception -> 0x065d, blocks: (B:122:0x02a9, B:123:0x02cf, B:125:0x02d5, B:127:0x02eb, B:129:0x02f1, B:131:0x02fb, B:133:0x0307, B:135:0x0311, B:139:0x031e, B:141:0x0326, B:145:0x0333, B:147:0x0339, B:151:0x0344, B:153:0x034c, B:157:0x0359, B:159:0x0361, B:161:0x0369, B:165:0x0376, B:167:0x037e, B:169:0x0384, B:171:0x038a, B:176:0x039a, B:174:0x0393, B:177:0x03c3), top: B:290:0x02a9 }] */
        /* JADX WARN: Code duplicated, block: B:138:0x031c A[PHI: r4
  0x031c: PHI (r4v15 java.lang.String) = (r4v14 java.lang.String), (r4v48 java.lang.String) binds: [B:134:0x030f, B:136:0x0317] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:141:0x0326 A[Catch: Exception -> 0x065d, TryCatch #1 {Exception -> 0x065d, blocks: (B:122:0x02a9, B:123:0x02cf, B:125:0x02d5, B:127:0x02eb, B:129:0x02f1, B:131:0x02fb, B:133:0x0307, B:135:0x0311, B:139:0x031e, B:141:0x0326, B:145:0x0333, B:147:0x0339, B:151:0x0344, B:153:0x034c, B:157:0x0359, B:159:0x0361, B:161:0x0369, B:165:0x0376, B:167:0x037e, B:169:0x0384, B:171:0x038a, B:176:0x039a, B:174:0x0393, B:177:0x03c3), top: B:290:0x02a9 }] */
        /* JADX WARN: Code duplicated, block: B:144:0x0331 A[PHI: r4
  0x0331: PHI (r4v18 java.lang.String) = (r4v17 java.lang.String), (r4v46 java.lang.String) binds: [B:140:0x0324, B:142:0x032c] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:147:0x0339 A[Catch: Exception -> 0x065d, TryCatch #1 {Exception -> 0x065d, blocks: (B:122:0x02a9, B:123:0x02cf, B:125:0x02d5, B:127:0x02eb, B:129:0x02f1, B:131:0x02fb, B:133:0x0307, B:135:0x0311, B:139:0x031e, B:141:0x0326, B:145:0x0333, B:147:0x0339, B:151:0x0344, B:153:0x034c, B:157:0x0359, B:159:0x0361, B:161:0x0369, B:165:0x0376, B:167:0x037e, B:169:0x0384, B:171:0x038a, B:176:0x039a, B:174:0x0393, B:177:0x03c3), top: B:290:0x02a9 }] */
        /* JADX WARN: Code duplicated, block: B:150:0x0342 A[PHI: r4
  0x0342: PHI (r4v20 java.lang.String) = (r4v19 java.lang.String), (r4v44 java.lang.String) binds: [B:146:0x0337, B:148:0x033d] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:153:0x034c A[Catch: Exception -> 0x065d, TryCatch #1 {Exception -> 0x065d, blocks: (B:122:0x02a9, B:123:0x02cf, B:125:0x02d5, B:127:0x02eb, B:129:0x02f1, B:131:0x02fb, B:133:0x0307, B:135:0x0311, B:139:0x031e, B:141:0x0326, B:145:0x0333, B:147:0x0339, B:151:0x0344, B:153:0x034c, B:157:0x0359, B:159:0x0361, B:161:0x0369, B:165:0x0376, B:167:0x037e, B:169:0x0384, B:171:0x038a, B:176:0x039a, B:174:0x0393, B:177:0x03c3), top: B:290:0x02a9 }] */
        /* JADX WARN: Code duplicated, block: B:156:0x0357 A[PHI: r4
  0x0357: PHI (r4v23 java.lang.String) = (r4v22 java.lang.String), (r4v43 java.lang.String) binds: [B:152:0x034a, B:154:0x0352] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:159:0x0361 A[Catch: Exception -> 0x065d, TryCatch #1 {Exception -> 0x065d, blocks: (B:122:0x02a9, B:123:0x02cf, B:125:0x02d5, B:127:0x02eb, B:129:0x02f1, B:131:0x02fb, B:133:0x0307, B:135:0x0311, B:139:0x031e, B:141:0x0326, B:145:0x0333, B:147:0x0339, B:151:0x0344, B:153:0x034c, B:157:0x0359, B:159:0x0361, B:161:0x0369, B:165:0x0376, B:167:0x037e, B:169:0x0384, B:171:0x038a, B:176:0x039a, B:174:0x0393, B:177:0x03c3), top: B:290:0x02a9 }] */
        /* JADX WARN: Code duplicated, block: B:164:0x0374 A[PHI: r4
  0x0374: PHI (r4v26 java.lang.String) = (r4v25 java.lang.String), (r4v39 java.lang.String), (r4v41 java.lang.String) binds: [B:158:0x035f, B:160:0x0367, B:162:0x036f] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:167:0x037e A[Catch: Exception -> 0x065d, TryCatch #1 {Exception -> 0x065d, blocks: (B:122:0x02a9, B:123:0x02cf, B:125:0x02d5, B:127:0x02eb, B:129:0x02f1, B:131:0x02fb, B:133:0x0307, B:135:0x0311, B:139:0x031e, B:141:0x0326, B:145:0x0333, B:147:0x0339, B:151:0x0344, B:153:0x034c, B:157:0x0359, B:159:0x0361, B:161:0x0369, B:165:0x0376, B:167:0x037e, B:169:0x0384, B:171:0x038a, B:176:0x039a, B:174:0x0393, B:177:0x03c3), top: B:290:0x02a9 }] */
        /* JADX WARN: Code duplicated, block: B:169:0x0384 A[Catch: Exception -> 0x065d, TryCatch #1 {Exception -> 0x065d, blocks: (B:122:0x02a9, B:123:0x02cf, B:125:0x02d5, B:127:0x02eb, B:129:0x02f1, B:131:0x02fb, B:133:0x0307, B:135:0x0311, B:139:0x031e, B:141:0x0326, B:145:0x0333, B:147:0x0339, B:151:0x0344, B:153:0x034c, B:157:0x0359, B:159:0x0361, B:161:0x0369, B:165:0x0376, B:167:0x037e, B:169:0x0384, B:171:0x038a, B:176:0x039a, B:174:0x0393, B:177:0x03c3), top: B:290:0x02a9 }] */
        /* JADX WARN: Code duplicated, block: B:172:0x038f  */
        /* JADX WARN: Code duplicated, block: B:174:0x0393 A[Catch: Exception -> 0x065d, PHI: r4
  0x0393: PHI (r4v29 java.lang.Double) = (r4v28 java.lang.Double), (r4v35 java.lang.Double) binds: [B:166:0x037c, B:173:0x0391] A[DONT_GENERATE, DONT_INLINE], TryCatch #1 {Exception -> 0x065d, blocks: (B:122:0x02a9, B:123:0x02cf, B:125:0x02d5, B:127:0x02eb, B:129:0x02f1, B:131:0x02fb, B:133:0x0307, B:135:0x0311, B:139:0x031e, B:141:0x0326, B:145:0x0333, B:147:0x0339, B:151:0x0344, B:153:0x034c, B:157:0x0359, B:159:0x0361, B:161:0x0369, B:165:0x0376, B:167:0x037e, B:169:0x0384, B:171:0x038a, B:176:0x039a, B:174:0x0393, B:177:0x03c3), top: B:290:0x02a9 }] */
        /* JADX WARN: Code duplicated, block: B:175:0x0398  */
        /* JADX WARN: Code duplicated, block: B:179:0x040d A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:180:0x040e  */
        /* JADX WARN: Code duplicated, block: B:184:0x043e A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:186:0x0454 A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:191:0x046e A[PHI: r29
  0x046e: PHI (r29v12 java.lang.String) = (r29v11 java.lang.String), (r29v17 java.lang.String) binds: [B:185:0x0452, B:187:0x0458] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:194:0x047a A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:197:0x0485 A[PHI: r6
  0x0485: PHI (r6v34 java.lang.String) = (r6v33 java.lang.String), (r6v47 java.lang.String) binds: [B:193:0x0478, B:195:0x0480] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:200:0x048f A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:203:0x049a A[PHI: r6
  0x049a: PHI (r6v37 java.lang.String) = (r6v36 java.lang.String), (r6v45 java.lang.String) binds: [B:199:0x048d, B:201:0x0495] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:206:0x04a2 A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:207:0x04b0 A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:209:0x04bc A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:210:0x04c4 A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:212:0x04cb A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:213:0x04d0  */
        /* JADX WARN: Code duplicated, block: B:215:0x04d4 A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:217:0x04da A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:220:0x04e5  */
        /* JADX WARN: Code duplicated, block: B:222:0x04e9 A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:224:0x04ef A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:227:0x04fa  */
        /* JADX WARN: Code duplicated, block: B:229:0x04fe A[Catch: Exception -> 0x065a, PHI: r9
  0x04fe: PHI (r9v6 java.lang.Integer) = (r9v5 java.lang.Integer), (r9v10 java.lang.Integer), (r9v13 java.lang.Integer) binds: [B:214:0x04d2, B:221:0x04e7, B:228:0x04fc] A[DONT_GENERATE, DONT_INLINE], TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:230:0x0505  */
        /* JADX WARN: Code duplicated, block: B:233:0x050e A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:238:0x0529 A[Catch: Exception -> 0x065a, TryCatch #2 {Exception -> 0x065a, blocks: (B:181:0x0413, B:182:0x0438, B:184:0x043e, B:186:0x0454, B:188:0x045a, B:190:0x0464, B:192:0x0470, B:194:0x047a, B:198:0x0487, B:200:0x048f, B:204:0x049c, B:206:0x04a2, B:231:0x0507, B:233:0x050e, B:236:0x0516, B:238:0x0529, B:242:0x053d, B:246:0x0551, B:207:0x04b0, B:209:0x04bc, B:210:0x04c4, B:212:0x04cb, B:215:0x04d4, B:217:0x04da, B:219:0x04e0, B:222:0x04e9, B:224:0x04ef, B:226:0x04f5, B:229:0x04fe, B:247:0x056b), top: B:292:0x0413 }] */
        /* JADX WARN: Code duplicated, block: B:241:0x053c  */
        /* JADX WARN: Code duplicated, block: B:244:0x054c  */
        /* JADX WARN: Code duplicated, block: B:245:0x054f  */
        /* JADX WARN: Code duplicated, block: B:249:0x05c7 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:250:0x05c8  */
        /* JADX WARN: Code duplicated, block: B:253:0x05e2 A[Catch: Exception -> 0x0658, TryCatch #4 {Exception -> 0x0658, blocks: (B:251:0x05d1, B:253:0x05e2, B:255:0x05ea, B:258:0x05f4, B:260:0x05fc, B:263:0x0606, B:265:0x060e, B:267:0x0616, B:269:0x061e, B:272:0x0628, B:273:0x0639), top: B:296:0x05d1 }] */
        /* JADX WARN: Code duplicated, block: B:255:0x05ea A[Catch: Exception -> 0x0658, TryCatch #4 {Exception -> 0x0658, blocks: (B:251:0x05d1, B:253:0x05e2, B:255:0x05ea, B:258:0x05f4, B:260:0x05fc, B:263:0x0606, B:265:0x060e, B:267:0x0616, B:269:0x061e, B:272:0x0628, B:273:0x0639), top: B:296:0x05d1 }] */
        /* JADX WARN: Code duplicated, block: B:260:0x05fc A[Catch: Exception -> 0x0658, TryCatch #4 {Exception -> 0x0658, blocks: (B:251:0x05d1, B:253:0x05e2, B:255:0x05ea, B:258:0x05f4, B:260:0x05fc, B:263:0x0606, B:265:0x060e, B:267:0x0616, B:269:0x061e, B:272:0x0628, B:273:0x0639), top: B:296:0x05d1 }] */
        /* JADX WARN: Code duplicated, block: B:265:0x060e A[Catch: Exception -> 0x0658, TryCatch #4 {Exception -> 0x0658, blocks: (B:251:0x05d1, B:253:0x05e2, B:255:0x05ea, B:258:0x05f4, B:260:0x05fc, B:263:0x0606, B:265:0x060e, B:267:0x0616, B:269:0x061e, B:272:0x0628, B:273:0x0639), top: B:296:0x05d1 }] */
        /* JADX WARN: Code duplicated, block: B:38:0x0112 A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:55:0x0152 A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:57:0x0158 A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:58:0x015d  */
        /* JADX WARN: Code duplicated, block: B:60:0x0161  */
        /* JADX WARN: Code duplicated, block: B:63:0x0169 A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:64:0x016e  */
        /* JADX WARN: Code duplicated, block: B:67:0x0179 A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:69:0x017f A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:70:0x0186  */
        /* JADX WARN: Code duplicated, block: B:72:0x018a  */
        /* JADX WARN: Code duplicated, block: B:75:0x0194 A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:76:0x0199  */
        /* JADX WARN: Code duplicated, block: B:82:0x01b2 A[PHI: r0
  0x01b2: PHI (r0v17 java.lang.String) = (r0v16 java.lang.String), (r0v40 java.lang.String) binds: [B:78:0x01a5, B:80:0x01ad] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:88:0x01c7 A[PHI: r0
  0x01c7: PHI (r0v20 java.lang.String) = (r0v19 java.lang.String), (r0v38 java.lang.String) binds: [B:84:0x01ba, B:86:0x01c2] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:91:0x01d5 A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:92:0x01d8  */
        /* JADX WARN: Code duplicated, block: B:94:0x01dc A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:97:0x01fa A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        /* JADX WARN: Code duplicated, block: B:99:0x0208 A[Catch: Exception -> 0x0660, TryCatch #0 {Exception -> 0x0660, blocks: (B:36:0x0101, B:38:0x0112, B:40:0x011a, B:43:0x0124, B:45:0x012e, B:48:0x0136, B:50:0x0140, B:53:0x014a, B:55:0x0152, B:57:0x0158, B:61:0x0163, B:63:0x0169, B:65:0x0173, B:67:0x0179, B:69:0x017f, B:73:0x018c, B:75:0x0194, B:77:0x019b, B:79:0x01a7, B:83:0x01b4, B:85:0x01bc, B:89:0x01c9, B:91:0x01d5, B:94:0x01dc, B:95:0x01f4, B:97:0x01fa, B:99:0x0208, B:102:0x0211, B:105:0x022e, B:107:0x023b, B:109:0x0241, B:116:0x0258, B:118:0x0277, B:115:0x0250, B:33:0x00ea), top: B:288:0x00ea }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            FirebaseFirestore firestore;
            CoroutineScope $this$withContext;
            Object objAwait;
            QuerySnapshot teacherSnapshot;
            DocumentSnapshot doc;
            Object objAwait2;
            DocumentSnapshot doc2;
            FirebaseFirestore firestore2;
            QuerySnapshot teacherSnapshot2;
            String string;
            String string2;
            String string3;
            String ratingStr;
            Double doubleOrNull;
            double rating;
            String string4;
            Integer intOrNull;
            int iIntValue;
            String string5;
            String bio;
            String string6;
            String dpUrl;
            Object obj;
            Iterable iterable;
            List specialtiesFromDoc;
            List list;
            boolean z;
            List specialties;
            Iterable iterable2;
            int i;
            Collection arrayList;
            String string7;
            Collection collection;
            Object obj2;
            Object obj3;
            QuerySnapshot courseSnapshot;
            Iterable iterable3;
            DocumentSnapshot doc3;
            QuerySnapshot teacherSnapshot3;
            Collection arrayList2;
            Iterable<DocumentSnapshot> iterable4;
            List<CourseInfo> list2;
            Object objAwait3;
            FirebaseFirestore firestore3;
            QuerySnapshot courseSnapshot2;
            QuerySnapshot teacherSnapshot4;
            String string8;
            Iterable iterable5;
            String string9;
            String str;
            String string10;
            String str2;
            String string11;
            String str3;
            String string12;
            String str4;
            String string13;
            String str5;
            Double doubleOrNull2;
            double dDoubleValue;
            Object obj4;
            String string14;
            Collection arrayList3;
            Iterable<DocumentSnapshot> iterable6;
            Object objAwait4;
            String string15;
            QuerySnapshot courseSnapshot3;
            String string16;
            String str6;
            String string17;
            String str7;
            Long l;
            String str8;
            QuerySnapshot teacherSnapshot5;
            String str9;
            Long l2;
            String string18;
            Integer intOrNull2;
            int iIntValue2;
            Object obj5;
            Object obj6;
            String string19;
            String string20;
            String string21;
            String str10;
            boolean z2;
            boolean z3;
            DocumentSnapshot contactDoc;
            String address;
            String email;
            String phone;
            String str11 = "seatsLeft";
            String str12 = "Seats Left";
            CoroutineScope $this$withContext2 = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            String str13 = "title";
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    firestore = SqlConnectRepository.this.getDb();
                    if (firestore == null) {
                        SqlConnectRepository.this._isLoading.setValue(Boxing.boxBoolean(false));
                        return Unit.INSTANCE;
                    }
                    try {
                        Task<QuerySnapshot> task = firestore.collection("teacher_profile").get();
                        Intrinsics.checkNotNullExpressionValue(task, "get(...)");
                        $this$withContext = $this$withContext2;
                        try {
                            this.L$0 = SpillingKt.nullOutSpilledVariable($this$withContext);
                            this.L$1 = firestore;
                            this.label = 1;
                            objAwait = TasksKt.await(task, this);
                            if (objAwait == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            teacherSnapshot = (QuerySnapshot) objAwait;
                            List<DocumentSnapshot> documents = teacherSnapshot.getDocuments();
                            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
                            doc = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents);
                            if (doc == null) {
                                string = doc.getString(HintConstants.AUTOFILL_HINT_NAME);
                                if (string == null && (string = doc.getString("Name")) == null) {
                                    string = "Suman Ghosh";
                                }
                                String name = string;
                                string2 = doc.getString("designation");
                                if (string2 == null && (string2 = doc.getString("Title")) == null) {
                                    string2 = "Lead English Teacher & Mentor";
                                }
                                String designation = string2;
                                string3 = doc.getString("Qualification");
                                if (string3 == null && (string3 = doc.getString("qualification")) == null) {
                                    string3 = "M.A English | 30+ Years of Teaching Experience";
                                }
                                String qualification = string3;
                                ratingStr = doc.getString("Rating");
                                if (ratingStr == null) {
                                    obj3 = doc.get("Rating");
                                    if (obj3 != null) {
                                        ratingStr = obj3.toString();
                                    } else {
                                        ratingStr = null;
                                    }
                                    if (ratingStr == null) {
                                        ratingStr = "4.9";
                                    }
                                }
                                doubleOrNull = StringsKt.toDoubleOrNull(ratingStr);
                                if (doubleOrNull != null) {
                                    rating = doubleOrNull.doubleValue();
                                } else {
                                    rating = 4.9d;
                                }
                                string4 = doc.getString("Student Taught");
                                if (string4 == null) {
                                    obj2 = doc.get("Student Taught");
                                    if (obj2 != null) {
                                        string4 = obj2.toString();
                                    } else {
                                        string4 = null;
                                    }
                                    if (string4 == null) {
                                        string4 = "1500";
                                    }
                                }
                                String studentsStr = string4;
                                intOrNull = StringsKt.toIntOrNull(studentsStr);
                                if (intOrNull != null) {
                                    iIntValue = intOrNull.intValue();
                                } else {
                                    iIntValue = 1500;
                                }
                                int studentsGuided = iIntValue;
                                string5 = doc.getString("Philosophy");
                                if (string5 == null || (string5 = doc.getString("bio")) != null) {
                                    bio = string5;
                                } else {
                                    bio = "";
                                }
                                string6 = doc.getString("Image");
                                if (string6 == null || (string6 = doc.getString("dpUrl")) != null) {
                                    dpUrl = string6;
                                } else {
                                    dpUrl = "";
                                }
                                obj = doc.get("specialties");
                                if (obj instanceof List) {
                                    iterable = (List) obj;
                                } else {
                                    iterable = null;
                                }
                                if (iterable != null) {
                                    iterable2 = iterable;
                                    i = 0;
                                    arrayList = new ArrayList();
                                    for (Object obj7 : iterable2) {
                                        if (obj7 != null) {
                                            string7 = obj7.toString();
                                        } else {
                                            string7 = null;
                                        }
                                        if (string7 != null) {
                                            collection = arrayList;
                                            collection.add(string7);
                                        } else {
                                            collection = arrayList;
                                        }
                                        i = i;
                                        arrayList = collection;
                                        iterable2 = iterable2;
                                    }
                                    specialtiesFromDoc = (List) arrayList;
                                } else {
                                    specialtiesFromDoc = null;
                                }
                                list = specialtiesFromDoc;
                                if (list != null || list.isEmpty()) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (z) {
                                    specialties = SqlConnectRepository.this.defaultSpecialties;
                                } else {
                                    specialties = specialtiesFromDoc;
                                }
                                SqlConnectRepository.this.getTeacherProfile().setValue(new TeacherProfile(name, designation, qualification, rating, studentsGuided, dpUrl, bio, specialties));
                            }
                            Task<QuerySnapshot> task2 = firestore.collection("courses").get();
                            Intrinsics.checkNotNullExpressionValue(task2, "get(...)");
                            this.L$0 = SpillingKt.nullOutSpilledVariable($this$withContext);
                            this.L$1 = firestore;
                            this.L$2 = SpillingKt.nullOutSpilledVariable(teacherSnapshot);
                            this.L$3 = SpillingKt.nullOutSpilledVariable(doc);
                            this.label = 2;
                            objAwait2 = TasksKt.await(task2, this);
                            if (objAwait2 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            doc2 = doc;
                            firestore2 = firestore;
                            teacherSnapshot2 = teacherSnapshot;
                            try {
                                courseSnapshot = (QuerySnapshot) objAwait2;
                                Iterable documents2 = courseSnapshot.getDocuments();
                                Intrinsics.checkNotNullExpressionValue(documents2, "getDocuments(...)");
                                iterable3 = documents2;
                                doc3 = doc2;
                                teacherSnapshot3 = teacherSnapshot2;
                                arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                                iterable4 = iterable3;
                                for (DocumentSnapshot documentSnapshot : iterable4) {
                                    Iterable iterable7 = iterable4;
                                    string8 = documentSnapshot.getString("Title");
                                    if (string8 == null || (string8 = documentSnapshot.getString("title")) != null) {
                                        iterable5 = iterable3;
                                    } else {
                                        iterable5 = iterable3;
                                        string8 = documentSnapshot.getString("Course Title");
                                        if (string8 == null) {
                                            String id = documentSnapshot.getId();
                                            Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                                            string8 = id;
                                        }
                                    }
                                    String str14 = string8;
                                    string9 = documentSnapshot.getString("Duration");
                                    if (string9 == null || (string9 = documentSnapshot.getString(TypedValues.TransitionType.S_DURATION)) != null) {
                                        str = string9;
                                    } else {
                                        str = "";
                                    }
                                    string10 = documentSnapshot.getString("Description");
                                    if (string10 == null || (string10 = documentSnapshot.getString("description")) != null) {
                                        str2 = string10;
                                    } else {
                                        str2 = "";
                                    }
                                    string11 = documentSnapshot.getString("Mode");
                                    if (string11 == null || (string11 = documentSnapshot.getString("mode")) != null) {
                                        str3 = string11;
                                    } else {
                                        str3 = "";
                                    }
                                    string12 = documentSnapshot.getString("Level");
                                    if (string12 == null || (string12 = documentSnapshot.getString("level")) != null) {
                                        str4 = string12;
                                    } else {
                                        str4 = "";
                                    }
                                    string13 = documentSnapshot.getString("Fee Structure");
                                    if (string13 != null && (string13 = documentSnapshot.getString("fee")) == null && (string13 = documentSnapshot.getString("Fee")) == null) {
                                        str5 = "";
                                    } else {
                                        str5 = string13;
                                    }
                                    doubleOrNull2 = documentSnapshot.getDouble("rating");
                                    if (doubleOrNull2 != null) {
                                        dDoubleValue = doubleOrNull2.doubleValue();
                                    } else {
                                        obj4 = documentSnapshot.get("Rating");
                                        if (obj4 != null || (string14 = obj4.toString()) == null) {
                                            doubleOrNull2 = null;
                                        } else {
                                            doubleOrNull2 = StringsKt.toDoubleOrNull(string14);
                                        }
                                        if (doubleOrNull2 != null) {
                                            dDoubleValue = doubleOrNull2.doubleValue();
                                        } else {
                                            dDoubleValue = 5.0d;
                                        }
                                    }
                                    String id2 = documentSnapshot.getId();
                                    Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
                                    arrayList2.add(new CourseInfo(id2, str14, str2, str, str3, str4, str5, dDoubleValue, null, 256, null));
                                    iterable4 = iterable7;
                                    iterable3 = iterable5;
                                }
                                list2 = (List) arrayList2;
                                SqlConnectRepository.this.getCourses().setValue(list2);
                                Task<QuerySnapshot> task3 = firestore2.collection("batches").get();
                                Intrinsics.checkNotNullExpressionValue(task3, "get(...)");
                                this.L$0 = SpillingKt.nullOutSpilledVariable($this$withContext);
                                this.L$1 = firestore2;
                                this.L$2 = SpillingKt.nullOutSpilledVariable(teacherSnapshot3);
                                this.L$3 = SpillingKt.nullOutSpilledVariable(doc3);
                                this.L$4 = SpillingKt.nullOutSpilledVariable(courseSnapshot);
                                this.L$5 = SpillingKt.nullOutSpilledVariable(list2);
                                this.label = 3;
                                objAwait3 = TasksKt.await(task3, this);
                                if (objAwait3 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                firestore3 = firestore2;
                                courseSnapshot2 = courseSnapshot;
                                teacherSnapshot4 = teacherSnapshot3;
                                try {
                                    QuerySnapshot batchSnapshot = (QuerySnapshot) objAwait3;
                                    Iterable documents3 = batchSnapshot.getDocuments();
                                    Intrinsics.checkNotNullExpressionValue(documents3, "getDocuments(...)");
                                    Iterable iterable8 = documents3;
                                    List<CourseInfo> list3 = list2;
                                    arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable8, 10));
                                    iterable6 = iterable8;
                                    for (DocumentSnapshot documentSnapshot2 : iterable6) {
                                        Iterable iterable9 = iterable6;
                                        string15 = documentSnapshot2.getString("Title");
                                        if (string15 == null || (string15 = documentSnapshot2.getString(str13)) != null) {
                                            courseSnapshot3 = courseSnapshot2;
                                        } else {
                                            courseSnapshot3 = courseSnapshot2;
                                            string15 = documentSnapshot2.getString("courseTitle");
                                            if (string15 == null) {
                                                String id3 = documentSnapshot2.getId();
                                                Intrinsics.checkNotNullExpressionValue(id3, "getId(...)");
                                                string15 = id3;
                                            }
                                        }
                                        String str15 = string15;
                                        string16 = documentSnapshot2.getString("Timing");
                                        if (string16 == null || (string16 = documentSnapshot2.getString("timing")) != null) {
                                            str6 = string16;
                                        } else {
                                            str6 = "";
                                        }
                                        string17 = documentSnapshot2.getString("Days");
                                        if (string17 == null || (string17 = documentSnapshot2.getString("days")) != null) {
                                            str7 = string17;
                                        } else {
                                            str7 = "";
                                        }
                                        l = documentSnapshot2.getLong(str12);
                                        if (l != null) {
                                            str8 = str13;
                                            teacherSnapshot5 = teacherSnapshot4;
                                            iIntValue2 = (int) l.longValue();
                                            str9 = str11;
                                        } else {
                                            str8 = str13;
                                            teacherSnapshot5 = teacherSnapshot4;
                                            str9 = str11;
                                            l2 = documentSnapshot2.getLong(str9);
                                            if (l2 != null) {
                                                iIntValue2 = (int) l2.longValue();
                                            } else {
                                                string18 = documentSnapshot2.getString(str12);
                                                if (string18 != null) {
                                                    intOrNull2 = StringsKt.toIntOrNull(string18);
                                                } else {
                                                    intOrNull2 = null;
                                                }
                                                if (intOrNull2 != null) {
                                                    iIntValue2 = intOrNull2.intValue();
                                                } else {
                                                    obj5 = documentSnapshot2.get(str12);
                                                    if (obj5 != null || (string20 = obj5.toString()) == null) {
                                                        intOrNull2 = null;
                                                    } else {
                                                        intOrNull2 = StringsKt.toIntOrNull(string20);
                                                    }
                                                    if (intOrNull2 != null) {
                                                        iIntValue2 = intOrNull2.intValue();
                                                    } else {
                                                        obj6 = documentSnapshot2.get(str9);
                                                        if (obj6 != null || (string19 = obj6.toString()) == null) {
                                                            intOrNull2 = null;
                                                        } else {
                                                            intOrNull2 = StringsKt.toIntOrNull(string19);
                                                        }
                                                        if (intOrNull2 != null) {
                                                            iIntValue2 = intOrNull2.intValue();
                                                        } else {
                                                            iIntValue2 = 0;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        string21 = documentSnapshot2.getString("Mode");
                                        if (string21 == null && (string21 = documentSnapshot2.getString("mode")) == null) {
                                            string21 = "Offline";
                                        }
                                        str10 = string21;
                                        String str16 = str12;
                                        if (!StringsKt.contains((CharSequence) str10, (CharSequence) "Online", true) || Intrinsics.areEqual(documentSnapshot2.getBoolean("isOnline"), Boxing.boxBoolean(true))) {
                                            z2 = true;
                                        } else {
                                            z2 = false;
                                        }
                                        String id4 = documentSnapshot2.getId();
                                        Intrinsics.checkNotNullExpressionValue(id4, "getId(...)");
                                        if (z2) {
                                            z3 = true;
                                        } else {
                                            z3 = false;
                                        }
                                        arrayList3.add(new BatchSchedule(id4, str15, str6, str7, iIntValue2, z3, str10));
                                        str12 = str16;
                                        str13 = str8;
                                        iterable6 = iterable9;
                                        teacherSnapshot4 = teacherSnapshot5;
                                        str11 = str9;
                                        courseSnapshot2 = courseSnapshot3;
                                    }
                                    List loadedBatches = (List) arrayList3;
                                    SqlConnectRepository.this.getBatches().setValue(loadedBatches);
                                    Task<QuerySnapshot> task4 = firestore3.collection("contact_info").get();
                                    Intrinsics.checkNotNullExpressionValue(task4, "get(...)");
                                    this.L$0 = SpillingKt.nullOutSpilledVariable($this$withContext);
                                    this.L$1 = SpillingKt.nullOutSpilledVariable(firestore3);
                                    this.L$2 = SpillingKt.nullOutSpilledVariable(teacherSnapshot4);
                                    this.L$3 = SpillingKt.nullOutSpilledVariable(doc3);
                                    this.L$4 = SpillingKt.nullOutSpilledVariable(courseSnapshot2);
                                    this.L$5 = SpillingKt.nullOutSpilledVariable(list3);
                                    this.L$6 = SpillingKt.nullOutSpilledVariable(batchSnapshot);
                                    this.L$7 = SpillingKt.nullOutSpilledVariable(loadedBatches);
                                    this.label = 4;
                                    objAwait4 = TasksKt.await(task4, this);
                                    if (objAwait4 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    try {
                                        QuerySnapshot contactSnapshot = (QuerySnapshot) objAwait4;
                                        List<DocumentSnapshot> documents4 = contactSnapshot.getDocuments();
                                        Intrinsics.checkNotNullExpressionValue(documents4, "getDocuments(...)");
                                        contactDoc = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents4);
                                        if (contactDoc != null) {
                                            address = contactDoc.getString("address");
                                            if (address == null && (address = contactDoc.getString("Address")) == null) {
                                                address = "3, Vivekananda Road, Nabagram, Konnagar, Hooghly - 712246 (Near Holy Mission School)";
                                            }
                                            email = contactDoc.getString("email");
                                            if (email == null && (email = contactDoc.getString("Email")) == null) {
                                                email = "sushiksha.konnagar@gmail.com";
                                            }
                                            phone = contactDoc.getString("phone_number");
                                            if (phone == null && (phone = contactDoc.getString("phone")) == null && (phone = contactDoc.getString("Phone")) == null && (phone = contactDoc.getString("phone_Number")) == null) {
                                                phone = "+91 9836926092";
                                            }
                                            SqlConnectRepository.this.getContactInfo().setValue(new ContactInfo(address, phone, email));
                                        }
                                        SqlConnectRepository.this._isLoading.setValue(Boxing.boxBoolean(false));
                                        SqlConnectRepository.this._lastSyncTimestamp.setValue(Boxing.boxLong(System.currentTimeMillis()));
                                        break;
                                    } catch (Exception e) {
                                        e = e;
                                        Log.w("FirestoreRepo", "fetchLatestFromServer error: " + e.getMessage());
                                    }
                                    return Unit.INSTANCE;
                                } catch (Exception e2) {
                                    e = e2;
                                    Log.w("FirestoreRepo", "fetchLatestFromServer error: " + e.getMessage());
                                    return Unit.INSTANCE;
                                }
                            } catch (Exception e3) {
                                e = e3;
                                Log.w("FirestoreRepo", "fetchLatestFromServer error: " + e.getMessage());
                                return Unit.INSTANCE;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            Log.w("FirestoreRepo", "fetchLatestFromServer error: " + e.getMessage());
                            return Unit.INSTANCE;
                        }
                    } catch (Exception e5) {
                        e = e5;
                        Log.w("FirestoreRepo", "fetchLatestFromServer error: " + e.getMessage());
                        return Unit.INSTANCE;
                    }
                case 1:
                    firestore = (FirebaseFirestore) this.L$1;
                    try {
                        ResultKt.throwOnFailure($result);
                        $this$withContext = $this$withContext2;
                        objAwait = $result;
                        teacherSnapshot = (QuerySnapshot) objAwait;
                        List<DocumentSnapshot> documents5 = teacherSnapshot.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents5, "getDocuments(...)");
                        doc = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents5);
                        if (doc == null) {
                            string = doc.getString(HintConstants.AUTOFILL_HINT_NAME);
                            if (string == null) {
                                string = "Suman Ghosh";
                            }
                            String name2 = string;
                            string2 = doc.getString("designation");
                            if (string2 == null) {
                                string2 = "Lead English Teacher & Mentor";
                            }
                            String designation2 = string2;
                            string3 = doc.getString("Qualification");
                            if (string3 == null) {
                                string3 = "M.A English | 30+ Years of Teaching Experience";
                            }
                            String qualification2 = string3;
                            ratingStr = doc.getString("Rating");
                            if (ratingStr == null) {
                                obj3 = doc.get("Rating");
                                if (obj3 != null) {
                                    ratingStr = obj3.toString();
                                } else {
                                    ratingStr = null;
                                }
                                if (ratingStr == null) {
                                    ratingStr = "4.9";
                                }
                            }
                            doubleOrNull = StringsKt.toDoubleOrNull(ratingStr);
                            if (doubleOrNull != null) {
                                rating = doubleOrNull.doubleValue();
                            } else {
                                rating = 4.9d;
                            }
                            string4 = doc.getString("Student Taught");
                            if (string4 == null) {
                                obj2 = doc.get("Student Taught");
                                if (obj2 != null) {
                                    string4 = obj2.toString();
                                } else {
                                    string4 = null;
                                }
                                if (string4 == null) {
                                    string4 = "1500";
                                }
                            }
                            String studentsStr2 = string4;
                            intOrNull = StringsKt.toIntOrNull(studentsStr2);
                            if (intOrNull != null) {
                                iIntValue = intOrNull.intValue();
                            } else {
                                iIntValue = 1500;
                            }
                            int studentsGuided2 = iIntValue;
                            string5 = doc.getString("Philosophy");
                            if (string5 == null) {
                                bio = string5;
                            } else {
                                bio = string5;
                            }
                            string6 = doc.getString("Image");
                            if (string6 == null) {
                                dpUrl = string6;
                            } else {
                                dpUrl = string6;
                            }
                            obj = doc.get("specialties");
                            if (obj instanceof List) {
                                iterable = (List) obj;
                            } else {
                                iterable = null;
                            }
                            if (iterable != null) {
                                iterable2 = iterable;
                                i = 0;
                                arrayList = new ArrayList();
                                while (r37.hasNext()) {
                                    if (obj7 != null) {
                                        string7 = obj7.toString();
                                    } else {
                                        string7 = null;
                                    }
                                    if (string7 != null) {
                                        collection = arrayList;
                                        collection.add(string7);
                                    } else {
                                        collection = arrayList;
                                    }
                                    i = i;
                                    arrayList = collection;
                                    iterable2 = iterable2;
                                }
                                specialtiesFromDoc = (List) arrayList;
                            } else {
                                specialtiesFromDoc = null;
                            }
                            list = specialtiesFromDoc;
                            if (list != null) {
                                z = true;
                            } else {
                                z = true;
                            }
                            if (z) {
                                specialties = specialtiesFromDoc;
                            } else {
                                specialties = SqlConnectRepository.this.defaultSpecialties;
                            }
                            SqlConnectRepository.this.getTeacherProfile().setValue(new TeacherProfile(name2, designation2, qualification2, rating, studentsGuided2, dpUrl, bio, specialties));
                        }
                        Task<QuerySnapshot> task5 = firestore.collection("courses").get();
                        Intrinsics.checkNotNullExpressionValue(task5, "get(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable($this$withContext);
                        this.L$1 = firestore;
                        this.L$2 = SpillingKt.nullOutSpilledVariable(teacherSnapshot);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(doc);
                        this.label = 2;
                        objAwait2 = TasksKt.await(task5, this);
                        if (objAwait2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        doc2 = doc;
                        firestore2 = firestore;
                        teacherSnapshot2 = teacherSnapshot;
                        courseSnapshot = (QuerySnapshot) objAwait2;
                        Iterable documents6 = courseSnapshot.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents6, "getDocuments(...)");
                        iterable3 = documents6;
                        doc3 = doc2;
                        teacherSnapshot3 = teacherSnapshot2;
                        arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                        iterable4 = iterable3;
                        while (r26.hasNext()) {
                            Iterable iterable10 = iterable4;
                            string8 = documentSnapshot.getString("Title");
                            if (string8 == null) {
                                iterable5 = iterable3;
                            } else {
                                iterable5 = iterable3;
                            }
                            String str17 = string8;
                            string9 = documentSnapshot.getString("Duration");
                            if (string9 == null) {
                                str = string9;
                            } else {
                                str = string9;
                            }
                            string10 = documentSnapshot.getString("Description");
                            if (string10 == null) {
                                str2 = string10;
                            } else {
                                str2 = string10;
                            }
                            string11 = documentSnapshot.getString("Mode");
                            if (string11 == null) {
                                str3 = string11;
                            } else {
                                str3 = string11;
                            }
                            string12 = documentSnapshot.getString("Level");
                            if (string12 == null) {
                                str4 = string12;
                            } else {
                                str4 = string12;
                            }
                            string13 = documentSnapshot.getString("Fee Structure");
                            if (string13 != null) {
                                str5 = string13;
                            } else {
                                str5 = string13;
                            }
                            doubleOrNull2 = documentSnapshot.getDouble("rating");
                            if (doubleOrNull2 != null) {
                                dDoubleValue = doubleOrNull2.doubleValue();
                            } else {
                                obj4 = documentSnapshot.get("Rating");
                                if (obj4 != null) {
                                    doubleOrNull2 = null;
                                } else {
                                    doubleOrNull2 = null;
                                }
                                if (doubleOrNull2 != null) {
                                    dDoubleValue = doubleOrNull2.doubleValue();
                                } else {
                                    dDoubleValue = 5.0d;
                                }
                            }
                            String id5 = documentSnapshot.getId();
                            Intrinsics.checkNotNullExpressionValue(id5, "getId(...)");
                            arrayList2.add(new CourseInfo(id5, str17, str2, str, str3, str4, str5, dDoubleValue, null, 256, null));
                            iterable4 = iterable10;
                            iterable3 = iterable5;
                        }
                        list2 = (List) arrayList2;
                        SqlConnectRepository.this.getCourses().setValue(list2);
                        Task<QuerySnapshot> task6 = firestore2.collection("batches").get();
                        Intrinsics.checkNotNullExpressionValue(task6, "get(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable($this$withContext);
                        this.L$1 = firestore2;
                        this.L$2 = SpillingKt.nullOutSpilledVariable(teacherSnapshot3);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(doc3);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(courseSnapshot);
                        this.L$5 = SpillingKt.nullOutSpilledVariable(list2);
                        this.label = 3;
                        objAwait3 = TasksKt.await(task6, this);
                        if (objAwait3 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        firestore3 = firestore2;
                        courseSnapshot2 = courseSnapshot;
                        teacherSnapshot4 = teacherSnapshot3;
                        QuerySnapshot batchSnapshot2 = (QuerySnapshot) objAwait3;
                        Iterable documents7 = batchSnapshot2.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents7, "getDocuments(...)");
                        Iterable iterable11 = documents7;
                        List<CourseInfo> list4 = list2;
                        arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable11, 10));
                        iterable6 = iterable11;
                        while (r26.hasNext()) {
                            Iterable iterable12 = iterable6;
                            string15 = documentSnapshot2.getString("Title");
                            if (string15 == null) {
                                courseSnapshot3 = courseSnapshot2;
                            } else {
                                courseSnapshot3 = courseSnapshot2;
                            }
                            String str18 = string15;
                            string16 = documentSnapshot2.getString("Timing");
                            if (string16 == null) {
                                str6 = string16;
                            } else {
                                str6 = string16;
                            }
                            string17 = documentSnapshot2.getString("Days");
                            if (string17 == null) {
                                str7 = string17;
                            } else {
                                str7 = string17;
                            }
                            l = documentSnapshot2.getLong(str12);
                            if (l != null) {
                                str8 = str13;
                                teacherSnapshot5 = teacherSnapshot4;
                                iIntValue2 = (int) l.longValue();
                                str9 = str11;
                            } else {
                                str8 = str13;
                                teacherSnapshot5 = teacherSnapshot4;
                                str9 = str11;
                                l2 = documentSnapshot2.getLong(str9);
                                if (l2 != null) {
                                    iIntValue2 = (int) l2.longValue();
                                } else {
                                    string18 = documentSnapshot2.getString(str12);
                                    if (string18 != null) {
                                        intOrNull2 = StringsKt.toIntOrNull(string18);
                                    } else {
                                        intOrNull2 = null;
                                    }
                                    if (intOrNull2 != null) {
                                        iIntValue2 = intOrNull2.intValue();
                                    } else {
                                        obj5 = documentSnapshot2.get(str12);
                                        if (obj5 != null) {
                                            intOrNull2 = null;
                                        } else {
                                            intOrNull2 = null;
                                        }
                                        if (intOrNull2 != null) {
                                            iIntValue2 = intOrNull2.intValue();
                                        } else {
                                            obj6 = documentSnapshot2.get(str9);
                                            if (obj6 != null) {
                                                intOrNull2 = null;
                                            } else {
                                                intOrNull2 = null;
                                            }
                                            if (intOrNull2 != null) {
                                                iIntValue2 = intOrNull2.intValue();
                                            } else {
                                                iIntValue2 = 0;
                                            }
                                        }
                                    }
                                }
                            }
                            string21 = documentSnapshot2.getString("Mode");
                            if (string21 == null) {
                                string21 = "Offline";
                            }
                            str10 = string21;
                            String str19 = str12;
                            if (StringsKt.contains((CharSequence) str10, (CharSequence) "Online", true)) {
                                z2 = true;
                            } else {
                                z2 = true;
                            }
                            String id6 = documentSnapshot2.getId();
                            Intrinsics.checkNotNullExpressionValue(id6, "getId(...)");
                            if (z2) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            arrayList3.add(new BatchSchedule(id6, str18, str6, str7, iIntValue2, z3, str10));
                            str12 = str19;
                            str13 = str8;
                            iterable6 = iterable12;
                            teacherSnapshot4 = teacherSnapshot5;
                            str11 = str9;
                            courseSnapshot2 = courseSnapshot3;
                        }
                        List loadedBatches2 = (List) arrayList3;
                        SqlConnectRepository.this.getBatches().setValue(loadedBatches2);
                        Task<QuerySnapshot> task7 = firestore3.collection("contact_info").get();
                        Intrinsics.checkNotNullExpressionValue(task7, "get(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable($this$withContext);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(firestore3);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(teacherSnapshot4);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(doc3);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(courseSnapshot2);
                        this.L$5 = SpillingKt.nullOutSpilledVariable(list4);
                        this.L$6 = SpillingKt.nullOutSpilledVariable(batchSnapshot2);
                        this.L$7 = SpillingKt.nullOutSpilledVariable(loadedBatches2);
                        this.label = 4;
                        objAwait4 = TasksKt.await(task7, this);
                        if (objAwait4 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        QuerySnapshot contactSnapshot2 = (QuerySnapshot) objAwait4;
                        List<DocumentSnapshot> documents8 = contactSnapshot2.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents8, "getDocuments(...)");
                        contactDoc = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents8);
                        if (contactDoc != null) {
                            address = contactDoc.getString("address");
                            if (address == null) {
                                address = "3, Vivekananda Road, Nabagram, Konnagar, Hooghly - 712246 (Near Holy Mission School)";
                            }
                            email = contactDoc.getString("email");
                            if (email == null) {
                                email = "sushiksha.konnagar@gmail.com";
                            }
                            phone = contactDoc.getString("phone_number");
                            if (phone == null) {
                                phone = "+91 9836926092";
                            }
                            SqlConnectRepository.this.getContactInfo().setValue(new ContactInfo(address, phone, email));
                        }
                        SqlConnectRepository.this._isLoading.setValue(Boxing.boxBoolean(false));
                        SqlConnectRepository.this._lastSyncTimestamp.setValue(Boxing.boxLong(System.currentTimeMillis()));
                        return Unit.INSTANCE;
                    } catch (Exception e6) {
                        e = e6;
                        Log.w("FirestoreRepo", "fetchLatestFromServer error: " + e.getMessage());
                        return Unit.INSTANCE;
                    }
                case 2:
                    doc2 = (DocumentSnapshot) this.L$3;
                    teacherSnapshot2 = (QuerySnapshot) this.L$2;
                    firestore2 = (FirebaseFirestore) this.L$1;
                    try {
                        ResultKt.throwOnFailure($result);
                        str11 = "seatsLeft";
                        $this$withContext = $this$withContext2;
                        objAwait2 = $result;
                        courseSnapshot = (QuerySnapshot) objAwait2;
                        Iterable documents9 = courseSnapshot.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents9, "getDocuments(...)");
                        iterable3 = documents9;
                        doc3 = doc2;
                        teacherSnapshot3 = teacherSnapshot2;
                        arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                        iterable4 = iterable3;
                        while (r26.hasNext()) {
                            Iterable iterable13 = iterable4;
                            string8 = documentSnapshot.getString("Title");
                            if (string8 == null) {
                                iterable5 = iterable3;
                            } else {
                                iterable5 = iterable3;
                            }
                            String str110 = string8;
                            string9 = documentSnapshot.getString("Duration");
                            if (string9 == null) {
                                str = string9;
                            } else {
                                str = string9;
                            }
                            string10 = documentSnapshot.getString("Description");
                            if (string10 == null) {
                                str2 = string10;
                            } else {
                                str2 = string10;
                            }
                            string11 = documentSnapshot.getString("Mode");
                            if (string11 == null) {
                                str3 = string11;
                            } else {
                                str3 = string11;
                            }
                            string12 = documentSnapshot.getString("Level");
                            if (string12 == null) {
                                str4 = string12;
                            } else {
                                str4 = string12;
                            }
                            string13 = documentSnapshot.getString("Fee Structure");
                            if (string13 != null) {
                                str5 = string13;
                            } else {
                                str5 = string13;
                            }
                            doubleOrNull2 = documentSnapshot.getDouble("rating");
                            if (doubleOrNull2 != null) {
                                dDoubleValue = doubleOrNull2.doubleValue();
                            } else {
                                obj4 = documentSnapshot.get("Rating");
                                if (obj4 != null) {
                                    doubleOrNull2 = null;
                                } else {
                                    doubleOrNull2 = null;
                                }
                                if (doubleOrNull2 != null) {
                                    dDoubleValue = doubleOrNull2.doubleValue();
                                } else {
                                    dDoubleValue = 5.0d;
                                }
                            }
                            String id7 = documentSnapshot.getId();
                            Intrinsics.checkNotNullExpressionValue(id7, "getId(...)");
                            arrayList2.add(new CourseInfo(id7, str110, str2, str, str3, str4, str5, dDoubleValue, null, 256, null));
                            iterable4 = iterable13;
                            iterable3 = iterable5;
                        }
                        list2 = (List) arrayList2;
                        SqlConnectRepository.this.getCourses().setValue(list2);
                        Task<QuerySnapshot> task8 = firestore2.collection("batches").get();
                        Intrinsics.checkNotNullExpressionValue(task8, "get(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable($this$withContext);
                        this.L$1 = firestore2;
                        this.L$2 = SpillingKt.nullOutSpilledVariable(teacherSnapshot3);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(doc3);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(courseSnapshot);
                        this.L$5 = SpillingKt.nullOutSpilledVariable(list2);
                        this.label = 3;
                        objAwait3 = TasksKt.await(task8, this);
                        if (objAwait3 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        firestore3 = firestore2;
                        courseSnapshot2 = courseSnapshot;
                        teacherSnapshot4 = teacherSnapshot3;
                        QuerySnapshot batchSnapshot3 = (QuerySnapshot) objAwait3;
                        Iterable documents10 = batchSnapshot3.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents10, "getDocuments(...)");
                        Iterable iterable14 = documents10;
                        List<CourseInfo> list5 = list2;
                        arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable14, 10));
                        iterable6 = iterable14;
                        while (r26.hasNext()) {
                            Iterable iterable15 = iterable6;
                            string15 = documentSnapshot2.getString("Title");
                            if (string15 == null) {
                                courseSnapshot3 = courseSnapshot2;
                            } else {
                                courseSnapshot3 = courseSnapshot2;
                            }
                            String str111 = string15;
                            string16 = documentSnapshot2.getString("Timing");
                            if (string16 == null) {
                                str6 = string16;
                            } else {
                                str6 = string16;
                            }
                            string17 = documentSnapshot2.getString("Days");
                            if (string17 == null) {
                                str7 = string17;
                            } else {
                                str7 = string17;
                            }
                            l = documentSnapshot2.getLong(str12);
                            if (l != null) {
                                str8 = str13;
                                teacherSnapshot5 = teacherSnapshot4;
                                iIntValue2 = (int) l.longValue();
                                str9 = str11;
                            } else {
                                str8 = str13;
                                teacherSnapshot5 = teacherSnapshot4;
                                str9 = str11;
                                l2 = documentSnapshot2.getLong(str9);
                                if (l2 != null) {
                                    iIntValue2 = (int) l2.longValue();
                                } else {
                                    string18 = documentSnapshot2.getString(str12);
                                    if (string18 != null) {
                                        intOrNull2 = StringsKt.toIntOrNull(string18);
                                    } else {
                                        intOrNull2 = null;
                                    }
                                    if (intOrNull2 != null) {
                                        iIntValue2 = intOrNull2.intValue();
                                    } else {
                                        obj5 = documentSnapshot2.get(str12);
                                        if (obj5 != null) {
                                            intOrNull2 = null;
                                        } else {
                                            intOrNull2 = null;
                                        }
                                        if (intOrNull2 != null) {
                                            iIntValue2 = intOrNull2.intValue();
                                        } else {
                                            obj6 = documentSnapshot2.get(str9);
                                            if (obj6 != null) {
                                                intOrNull2 = null;
                                            } else {
                                                intOrNull2 = null;
                                            }
                                            if (intOrNull2 != null) {
                                                iIntValue2 = intOrNull2.intValue();
                                            } else {
                                                iIntValue2 = 0;
                                            }
                                        }
                                    }
                                }
                            }
                            string21 = documentSnapshot2.getString("Mode");
                            if (string21 == null) {
                                string21 = "Offline";
                            }
                            str10 = string21;
                            String str112 = str12;
                            if (StringsKt.contains((CharSequence) str10, (CharSequence) "Online", true)) {
                                z2 = true;
                            } else {
                                z2 = true;
                            }
                            String id8 = documentSnapshot2.getId();
                            Intrinsics.checkNotNullExpressionValue(id8, "getId(...)");
                            if (z2) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            arrayList3.add(new BatchSchedule(id8, str111, str6, str7, iIntValue2, z3, str10));
                            str12 = str112;
                            str13 = str8;
                            iterable6 = iterable15;
                            teacherSnapshot4 = teacherSnapshot5;
                            str11 = str9;
                            courseSnapshot2 = courseSnapshot3;
                        }
                        List loadedBatches3 = (List) arrayList3;
                        SqlConnectRepository.this.getBatches().setValue(loadedBatches3);
                        Task<QuerySnapshot> task9 = firestore3.collection("contact_info").get();
                        Intrinsics.checkNotNullExpressionValue(task9, "get(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable($this$withContext);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(firestore3);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(teacherSnapshot4);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(doc3);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(courseSnapshot2);
                        this.L$5 = SpillingKt.nullOutSpilledVariable(list5);
                        this.L$6 = SpillingKt.nullOutSpilledVariable(batchSnapshot3);
                        this.L$7 = SpillingKt.nullOutSpilledVariable(loadedBatches3);
                        this.label = 4;
                        objAwait4 = TasksKt.await(task9, this);
                        if (objAwait4 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        QuerySnapshot contactSnapshot3 = (QuerySnapshot) objAwait4;
                        List<DocumentSnapshot> documents11 = contactSnapshot3.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents11, "getDocuments(...)");
                        contactDoc = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents11);
                        if (contactDoc != null) {
                            address = contactDoc.getString("address");
                            if (address == null) {
                                address = "3, Vivekananda Road, Nabagram, Konnagar, Hooghly - 712246 (Near Holy Mission School)";
                            }
                            email = contactDoc.getString("email");
                            if (email == null) {
                                email = "sushiksha.konnagar@gmail.com";
                            }
                            phone = contactDoc.getString("phone_number");
                            if (phone == null) {
                                phone = "+91 9836926092";
                            }
                            SqlConnectRepository.this.getContactInfo().setValue(new ContactInfo(address, phone, email));
                        }
                        SqlConnectRepository.this._isLoading.setValue(Boxing.boxBoolean(false));
                        SqlConnectRepository.this._lastSyncTimestamp.setValue(Boxing.boxLong(System.currentTimeMillis()));
                        return Unit.INSTANCE;
                    } catch (Exception e7) {
                        e = e7;
                        Log.w("FirestoreRepo", "fetchLatestFromServer error: " + e.getMessage());
                        return Unit.INSTANCE;
                    }
                case 3:
                    List<CourseInfo> list6 = (List) this.L$5;
                    courseSnapshot2 = (QuerySnapshot) this.L$4;
                    DocumentSnapshot doc4 = (DocumentSnapshot) this.L$3;
                    teacherSnapshot4 = (QuerySnapshot) this.L$2;
                    FirebaseFirestore firestore4 = (FirebaseFirestore) this.L$1;
                    try {
                        ResultKt.throwOnFailure($result);
                        str11 = "seatsLeft";
                        doc3 = doc4;
                        list2 = list6;
                        $this$withContext = $this$withContext2;
                        firestore3 = firestore4;
                        objAwait3 = $result;
                        QuerySnapshot batchSnapshot4 = (QuerySnapshot) objAwait3;
                        Iterable documents12 = batchSnapshot4.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents12, "getDocuments(...)");
                        Iterable iterable16 = documents12;
                        List<CourseInfo> list7 = list2;
                        arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable16, 10));
                        iterable6 = iterable16;
                        while (r26.hasNext()) {
                            Iterable iterable17 = iterable6;
                            string15 = documentSnapshot2.getString("Title");
                            if (string15 == null) {
                                courseSnapshot3 = courseSnapshot2;
                            } else {
                                courseSnapshot3 = courseSnapshot2;
                            }
                            String str113 = string15;
                            string16 = documentSnapshot2.getString("Timing");
                            if (string16 == null) {
                                str6 = string16;
                            } else {
                                str6 = string16;
                            }
                            string17 = documentSnapshot2.getString("Days");
                            if (string17 == null) {
                                str7 = string17;
                            } else {
                                str7 = string17;
                            }
                            l = documentSnapshot2.getLong(str12);
                            if (l != null) {
                                str8 = str13;
                                teacherSnapshot5 = teacherSnapshot4;
                                iIntValue2 = (int) l.longValue();
                                str9 = str11;
                            } else {
                                str8 = str13;
                                teacherSnapshot5 = teacherSnapshot4;
                                str9 = str11;
                                l2 = documentSnapshot2.getLong(str9);
                                if (l2 != null) {
                                    iIntValue2 = (int) l2.longValue();
                                } else {
                                    string18 = documentSnapshot2.getString(str12);
                                    if (string18 != null) {
                                        intOrNull2 = StringsKt.toIntOrNull(string18);
                                    } else {
                                        intOrNull2 = null;
                                    }
                                    if (intOrNull2 != null) {
                                        iIntValue2 = intOrNull2.intValue();
                                    } else {
                                        obj5 = documentSnapshot2.get(str12);
                                        if (obj5 != null) {
                                            intOrNull2 = null;
                                        } else {
                                            intOrNull2 = null;
                                        }
                                        if (intOrNull2 != null) {
                                            iIntValue2 = intOrNull2.intValue();
                                        } else {
                                            obj6 = documentSnapshot2.get(str9);
                                            if (obj6 != null) {
                                                intOrNull2 = null;
                                            } else {
                                                intOrNull2 = null;
                                            }
                                            if (intOrNull2 != null) {
                                                iIntValue2 = intOrNull2.intValue();
                                            } else {
                                                iIntValue2 = 0;
                                            }
                                        }
                                    }
                                }
                            }
                            string21 = documentSnapshot2.getString("Mode");
                            if (string21 == null) {
                                string21 = "Offline";
                            }
                            str10 = string21;
                            String str114 = str12;
                            if (StringsKt.contains((CharSequence) str10, (CharSequence) "Online", true)) {
                                z2 = true;
                            } else {
                                z2 = true;
                            }
                            String id9 = documentSnapshot2.getId();
                            Intrinsics.checkNotNullExpressionValue(id9, "getId(...)");
                            if (z2) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            arrayList3.add(new BatchSchedule(id9, str113, str6, str7, iIntValue2, z3, str10));
                            str12 = str114;
                            str13 = str8;
                            iterable6 = iterable17;
                            teacherSnapshot4 = teacherSnapshot5;
                            str11 = str9;
                            courseSnapshot2 = courseSnapshot3;
                        }
                        List loadedBatches4 = (List) arrayList3;
                        SqlConnectRepository.this.getBatches().setValue(loadedBatches4);
                        Task<QuerySnapshot> task10 = firestore3.collection("contact_info").get();
                        Intrinsics.checkNotNullExpressionValue(task10, "get(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable($this$withContext);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(firestore3);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(teacherSnapshot4);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(doc3);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(courseSnapshot2);
                        this.L$5 = SpillingKt.nullOutSpilledVariable(list7);
                        this.L$6 = SpillingKt.nullOutSpilledVariable(batchSnapshot4);
                        this.L$7 = SpillingKt.nullOutSpilledVariable(loadedBatches4);
                        this.label = 4;
                        objAwait4 = TasksKt.await(task10, this);
                        if (objAwait4 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        QuerySnapshot contactSnapshot4 = (QuerySnapshot) objAwait4;
                        List<DocumentSnapshot> documents13 = contactSnapshot4.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents13, "getDocuments(...)");
                        contactDoc = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents13);
                        if (contactDoc != null) {
                            address = contactDoc.getString("address");
                            if (address == null) {
                                address = "3, Vivekananda Road, Nabagram, Konnagar, Hooghly - 712246 (Near Holy Mission School)";
                            }
                            email = contactDoc.getString("email");
                            if (email == null) {
                                email = "sushiksha.konnagar@gmail.com";
                            }
                            phone = contactDoc.getString("phone_number");
                            if (phone == null) {
                                phone = "+91 9836926092";
                            }
                            SqlConnectRepository.this.getContactInfo().setValue(new ContactInfo(address, phone, email));
                        }
                        SqlConnectRepository.this._isLoading.setValue(Boxing.boxBoolean(false));
                        SqlConnectRepository.this._lastSyncTimestamp.setValue(Boxing.boxLong(System.currentTimeMillis()));
                        return Unit.INSTANCE;
                    } catch (Exception e8) {
                        e = e8;
                        Log.w("FirestoreRepo", "fetchLatestFromServer error: " + e.getMessage());
                        return Unit.INSTANCE;
                    }
                case 4:
                    try {
                        ResultKt.throwOnFailure($result);
                        objAwait4 = $result;
                        QuerySnapshot contactSnapshot5 = (QuerySnapshot) objAwait4;
                        List<DocumentSnapshot> documents14 = contactSnapshot5.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents14, "getDocuments(...)");
                        contactDoc = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents14);
                        if (contactDoc != null) {
                            address = contactDoc.getString("address");
                            if (address == null) {
                                address = "3, Vivekananda Road, Nabagram, Konnagar, Hooghly - 712246 (Near Holy Mission School)";
                            }
                            email = contactDoc.getString("email");
                            if (email == null) {
                                email = "sushiksha.konnagar@gmail.com";
                            }
                            phone = contactDoc.getString("phone_number");
                            if (phone == null) {
                                phone = "+91 9836926092";
                            }
                            SqlConnectRepository.this.getContactInfo().setValue(new ContactInfo(address, phone, email));
                        }
                        SqlConnectRepository.this._isLoading.setValue(Boxing.boxBoolean(false));
                        SqlConnectRepository.this._lastSyncTimestamp.setValue(Boxing.boxLong(System.currentTimeMillis()));
                        break;
                    } catch (Exception e9) {
                        e = e9;
                        Log.w("FirestoreRepo", "fetchLatestFromServer error: " + e.getMessage());
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object fetchLatestFromServer(Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final void updateTeacherProfile(TeacherProfile profile, Function1<? super Boolean, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        this.teacherProfile.setValue(profile);
        FirebaseFirestore firestore = getDb();
        if (firestore != null) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C04341(profile, firestore, onComplete, null), 3, null);
        } else {
            onComplete.invoke(false);
        }
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$updateTeacherProfile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SqlConnectRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$updateTeacherProfile$1", f = "SqlConnectRepository.kt", i = {0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4}, l = {321, 324, 326, 328, 331}, m = "invokeSuspend", n = {"data", "data", "snapshot", "docId", "data", "snapshot", "docId", "data", "snapshot", "docId", "e"}, s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0"})
    static final class C04341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FirebaseFirestore $firestore;
        final /* synthetic */ Function1<Boolean, Unit> $onComplete;
        final /* synthetic */ TeacherProfile $profile;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04341(TeacherProfile teacherProfile, FirebaseFirestore firebaseFirestore, Function1<? super Boolean, Unit> function1, Continuation<? super C04341> continuation) {
            super(2, continuation);
            this.$profile = teacherProfile;
            this.$firestore = firebaseFirestore;
            this.$onComplete = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04341(this.$profile, this.$firestore, this.$onComplete, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04341) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:25:0x010c A[Catch: Exception -> 0x01a8, TryCatch #0 {Exception -> 0x01a8, blocks: (B:8:0x002d, B:11:0x003e, B:37:0x0179, B:14:0x004f, B:17:0x0058, B:23:0x00f9, B:25:0x010c, B:28:0x0114, B:33:0x0149, B:20:0x0063), top: B:50:0x000d }] */
        /* JADX WARN: Code duplicated, block: B:26:0x0111  */
        /* JADX WARN: Code duplicated, block: B:28:0x0114 A[Catch: Exception -> 0x01a8, TryCatch #0 {Exception -> 0x01a8, blocks: (B:8:0x002d, B:11:0x003e, B:37:0x0179, B:14:0x004f, B:17:0x0058, B:23:0x00f9, B:25:0x010c, B:28:0x0114, B:33:0x0149, B:20:0x0063), top: B:50:0x000d }] */
        /* JADX WARN: Code duplicated, block: B:30:0x0144  */
        /* JADX WARN: Code duplicated, block: B:31:0x0145  */
        /* JADX WARN: Code duplicated, block: B:33:0x0149 A[Catch: Exception -> 0x01a8, TryCatch #0 {Exception -> 0x01a8, blocks: (B:8:0x002d, B:11:0x003e, B:37:0x0179, B:14:0x004f, B:17:0x0058, B:23:0x00f9, B:25:0x010c, B:28:0x0114, B:33:0x0149, B:20:0x0063), top: B:50:0x000d }] */
        /* JADX WARN: Code duplicated, block: B:36:0x0176  */
        /* JADX WARN: Code duplicated, block: B:39:0x01a5 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:40:0x01a6  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Map data;
            Object objAwait;
            QuerySnapshot snapshot;
            DocumentSnapshot documentSnapshot;
            String docId;
            Task<DocumentReference> taskAdd;
            Map data2;
            QuerySnapshot snapshot2;
            String docId2;
            Task<Void> task;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        data = MapsKt.mapOf(TuplesKt.to(HintConstants.AUTOFILL_HINT_NAME, this.$profile.getName()), TuplesKt.to("designation", this.$profile.getTitle()), TuplesKt.to("Qualification", this.$profile.getCredentials()), TuplesKt.to("Rating", String.valueOf(this.$profile.getRating())), TuplesKt.to("Student Taught", String.valueOf(this.$profile.getStudentsGuided())), TuplesKt.to("Philosophy", this.$profile.getBio()), TuplesKt.to("Image", this.$profile.getDpUrl()));
                        Task<QuerySnapshot> task2 = this.$firestore.collection("teacher_profile").get();
                        Intrinsics.checkNotNullExpressionValue(task2, "get(...)");
                        this.L$0 = data;
                        this.label = 1;
                        objAwait = TasksKt.await(task2, this);
                        if (objAwait == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        snapshot = (QuerySnapshot) objAwait;
                        List<DocumentSnapshot> documents = snapshot.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
                        documentSnapshot = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents);
                        if (documentSnapshot != null) {
                            docId = documentSnapshot.getId();
                        } else {
                            docId = null;
                        }
                        if (docId != null) {
                            task = this.$firestore.collection("teacher_profile").document(docId).set(data);
                            Intrinsics.checkNotNullExpressionValue(task, "set(...)");
                            this.L$0 = SpillingKt.nullOutSpilledVariable(data);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(docId);
                            this.label = 2;
                            if (TasksKt.await(task, this) == coroutine_suspended) {
                                data2 = data;
                                snapshot2 = snapshot;
                                docId2 = docId;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                                this.label = 4;
                                if (BuildersKt.withContext(Dispatchers.getMain(), new C01051(this.$onComplete, null), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                        } else {
                            taskAdd = this.$firestore.collection("teacher_profile").add(data);
                            Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                            this.L$0 = SpillingKt.nullOutSpilledVariable(data);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(docId);
                            this.label = 3;
                            if (TasksKt.await(taskAdd, this) != coroutine_suspended) {
                                data2 = data;
                                snapshot2 = snapshot;
                                docId2 = docId;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                                this.label = 4;
                                if (BuildersKt.withContext(Dispatchers.getMain(), new C01051(this.$onComplete, null), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                        }
                        return coroutine_suspended;
                    case 1:
                        data = (Map) this.L$0;
                        ResultKt.throwOnFailure($result);
                        objAwait = $result;
                        snapshot = (QuerySnapshot) objAwait;
                        List<DocumentSnapshot> documents2 = snapshot.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents2, "getDocuments(...)");
                        documentSnapshot = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents2);
                        if (documentSnapshot != null) {
                            docId = documentSnapshot.getId();
                        } else {
                            docId = null;
                        }
                        if (docId != null) {
                            task = this.$firestore.collection("teacher_profile").document(docId).set(data);
                            Intrinsics.checkNotNullExpressionValue(task, "set(...)");
                            this.L$0 = SpillingKt.nullOutSpilledVariable(data);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(docId);
                            this.label = 2;
                            if (TasksKt.await(task, this) == coroutine_suspended) {
                                data2 = data;
                                snapshot2 = snapshot;
                                docId2 = docId;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                                this.label = 4;
                                if (BuildersKt.withContext(Dispatchers.getMain(), new C01051(this.$onComplete, null), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                        } else {
                            taskAdd = this.$firestore.collection("teacher_profile").add(data);
                            Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                            this.L$0 = SpillingKt.nullOutSpilledVariable(data);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(docId);
                            this.label = 3;
                            if (TasksKt.await(taskAdd, this) != coroutine_suspended) {
                                data2 = data;
                                snapshot2 = snapshot;
                                docId2 = docId;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                                this.label = 4;
                                if (BuildersKt.withContext(Dispatchers.getMain(), new C01051(this.$onComplete, null), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                        }
                        return coroutine_suspended;
                    case 2:
                        docId2 = (String) this.L$2;
                        snapshot2 = (QuerySnapshot) this.L$1;
                        data2 = (Map) this.L$0;
                        ResultKt.throwOnFailure($result);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                        this.label = 4;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new C01051(this.$onComplete, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    case 3:
                        docId2 = (String) this.L$2;
                        snapshot2 = (QuerySnapshot) this.L$1;
                        data2 = (Map) this.L$0;
                        ResultKt.throwOnFailure($result);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                        this.label = 4;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new C01051(this.$onComplete, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    case 4:
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    case 5:
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Exception e) {
                Log.e("FirestoreRepo", "Error updating teacher profile: " + e.getMessage());
                this.L$0 = SpillingKt.nullOutSpilledVariable(e);
                this.L$1 = null;
                this.L$2 = null;
                this.label = 5;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.$onComplete, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$updateTeacherProfile$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$updateTeacherProfile$1$1", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01051(Function1<? super Boolean, Unit> function1, Continuation<? super C01051> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01051(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(true));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$updateTeacherProfile$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$updateTeacherProfile$1$2", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Function1<? super Boolean, Unit> function1, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(false));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public final void saveCourse(CourseInfo course, Function1<? super Boolean, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(course, "course");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        FirebaseFirestore firestore = getDb();
        if (firestore != null) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C04321(course, firestore, onComplete, null), 3, null);
        } else {
            onComplete.invoke(false);
        }
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$saveCourse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SqlConnectRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$saveCourse$1", f = "SqlConnectRepository.kt", i = {0, 1, 2, 3}, l = {353, 355, 357, 360}, m = "invokeSuspend", n = {"data", "data", "data", "e"}, s = {"L$0", "L$0", "L$0", "L$0"})
    static final class C04321 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CourseInfo $course;
        final /* synthetic */ FirebaseFirestore $firestore;
        final /* synthetic */ Function1<Boolean, Unit> $onComplete;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04321(CourseInfo courseInfo, FirebaseFirestore firebaseFirestore, Function1<? super Boolean, Unit> function1, Continuation<? super C04321> continuation) {
            super(2, continuation);
            this.$course = courseInfo;
            this.$firestore = firebaseFirestore;
            this.$onComplete = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04321(this.$course, this.$firestore, this.$onComplete, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04321) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x011b, code lost:
        
            if (kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getMain(), new com.example.sushikhasacademyapp.data.SqlConnectRepository.C04321.C01031(r9.$onComplete, null), r9) == r0) goto L29;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 368
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.sushikhasacademyapp.data.SqlConnectRepository.C04321.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$saveCourse$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$saveCourse$1$1", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01031(Function1<? super Boolean, Unit> function1, Continuation<? super C01031> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01031(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(true));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$saveCourse$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$saveCourse$1$2", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Function1<? super Boolean, Unit> function1, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(false));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public final void deleteCourse(String courseId, Function1<? super Boolean, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(courseId, "courseId");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        FirebaseFirestore firestore = getDb();
        if (firestore != null) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C04301(firestore, courseId, onComplete, null), 3, null);
        } else {
            onComplete.invoke(false);
        }
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteCourse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SqlConnectRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteCourse$1", f = "SqlConnectRepository.kt", i = {2}, l = {373, 374, 377}, m = "invokeSuspend", n = {"e"}, s = {"L$0"})
    static final class C04301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $courseId;
        final /* synthetic */ FirebaseFirestore $firestore;
        final /* synthetic */ Function1<Boolean, Unit> $onComplete;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04301(FirebaseFirestore firebaseFirestore, String str, Function1<? super Boolean, Unit> function1, Continuation<? super C04301> continuation) {
            super(2, continuation);
            this.$firestore = firebaseFirestore;
            this.$courseId = str;
            this.$onComplete = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04301(this.$firestore, this.$courseId, this.$onComplete, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0064, code lost:
        
            if (kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getMain(), new com.example.sushikhasacademyapp.data.SqlConnectRepository.C04301.C01011(r6.$onComplete, null), r6) == r0) goto L16;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 0
                switch(r1) {
                    case 0: goto L23;
                    case 1: goto L1f;
                    case 2: goto L1b;
                    case 3: goto L12;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L12:
                java.lang.Object r0 = r6.L$0
                java.lang.Exception r0 = (java.lang.Exception) r0
                kotlin.ResultKt.throwOnFailure(r7)
                goto La8
            L1b:
                kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L68
                goto L67
            L1f:
                kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L68
                goto L4b
            L23:
                kotlin.ResultKt.throwOnFailure(r7)
                com.google.firebase.firestore.FirebaseFirestore r1 = r6.$firestore     // Catch: java.lang.Exception -> L68
                java.lang.String r3 = "courses"
                com.google.firebase.firestore.CollectionReference r1 = r1.collection(r3)     // Catch: java.lang.Exception -> L68
                java.lang.String r3 = r6.$courseId     // Catch: java.lang.Exception -> L68
                com.google.firebase.firestore.DocumentReference r1 = r1.document(r3)     // Catch: java.lang.Exception -> L68
                com.google.android.gms.tasks.Task r1 = r1.delete()     // Catch: java.lang.Exception -> L68
                java.lang.String r3 = "delete(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)     // Catch: java.lang.Exception -> L68
                r3 = r6
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch: java.lang.Exception -> L68
                r4 = 1
                r6.label = r4     // Catch: java.lang.Exception -> L68
                java.lang.Object r1 = kotlinx.coroutines.tasks.TasksKt.await(r1, r3)     // Catch: java.lang.Exception -> L68
                if (r1 != r0) goto L4b
                goto L66
            L4b:
                kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.lang.Exception -> L68
                kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1     // Catch: java.lang.Exception -> L68
                com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteCourse$1$1 r3 = new com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteCourse$1$1     // Catch: java.lang.Exception -> L68
                kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> r4 = r6.$onComplete     // Catch: java.lang.Exception -> L68
                r3.<init>(r4, r2)     // Catch: java.lang.Exception -> L68
                kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3     // Catch: java.lang.Exception -> L68
                r4 = r6
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch: java.lang.Exception -> L68
                r5 = 2
                r6.label = r5     // Catch: java.lang.Exception -> L68
                java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r3, r4)     // Catch: java.lang.Exception -> L68
                if (r1 != r0) goto L67
            L66:
                return r0
            L67:
                goto La9
            L68:
                r1 = move-exception
                java.lang.String r3 = r1.getMessage()
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "Error deleting course: "
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.StringBuilder r3 = r4.append(r3)
                java.lang.String r3 = r3.toString()
                java.lang.String r4 = "FirestoreRepo"
                android.util.Log.e(r4, r3)
                kotlinx.coroutines.MainCoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getMain()
                kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
                com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteCourse$1$2 r4 = new com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteCourse$1$2
                kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> r5 = r6.$onComplete
                r4.<init>(r5, r2)
                kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                r2 = r6
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
                r6.L$0 = r5
                r5 = 3
                r6.label = r5
                java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r3, r4, r2)
                if (r2 != r0) goto La7
                return r0
            La7:
                r0 = r1
            La8:
            La9:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.sushikhasacademyapp.data.SqlConnectRepository.C04301.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteCourse$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteCourse$1$1", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01011(Function1<? super Boolean, Unit> function1, Continuation<? super C01011> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01011(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(true));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteCourse$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteCourse$1$2", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Function1<? super Boolean, Unit> function1, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(false));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public final void saveBatch(BatchSchedule batch, Function1<? super Boolean, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(batch, "batch");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        FirebaseFirestore firestore = getDb();
        if (firestore != null) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C04311(batch, firestore, onComplete, null), 3, null);
        } else {
            onComplete.invoke(false);
        }
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$saveBatch$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SqlConnectRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$saveBatch$1", f = "SqlConnectRepository.kt", i = {0, 1, 2, 3}, l = {398, 400, TypedValues.CycleType.TYPE_VISIBILITY, 405}, m = "invokeSuspend", n = {"data", "data", "data", "e"}, s = {"L$0", "L$0", "L$0", "L$0"})
    static final class C04311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BatchSchedule $batch;
        final /* synthetic */ FirebaseFirestore $firestore;
        final /* synthetic */ Function1<Boolean, Unit> $onComplete;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04311(BatchSchedule batchSchedule, FirebaseFirestore firebaseFirestore, Function1<? super Boolean, Unit> function1, Continuation<? super C04311> continuation) {
            super(2, continuation);
            this.$batch = batchSchedule;
            this.$firestore = firebaseFirestore;
            this.$onComplete = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04311(this.$batch, this.$firestore, this.$onComplete, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x010f, code lost:
        
            if (kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getMain(), new com.example.sushikhasacademyapp.data.SqlConnectRepository.C04311.C01021(r9.$onComplete, null), r9) == r0) goto L29;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 356
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.sushikhasacademyapp.data.SqlConnectRepository.C04311.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$saveBatch$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$saveBatch$1$1", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01021(Function1<? super Boolean, Unit> function1, Continuation<? super C01021> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01021(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(true));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$saveBatch$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$saveBatch$1$2", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Function1<? super Boolean, Unit> function1, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(false));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public final void deleteBatch(String batchId, Function1<? super Boolean, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(batchId, "batchId");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        FirebaseFirestore firestore = getDb();
        if (firestore != null) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(firestore, batchId, onComplete, null), 3, null);
        } else {
            onComplete.invoke(false);
        }
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteBatch$1, reason: invalid class name */
    /* JADX INFO: compiled from: SqlConnectRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteBatch$1", f = "SqlConnectRepository.kt", i = {2}, l = {418, 419, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE}, m = "invokeSuspend", n = {"e"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $batchId;
        final /* synthetic */ FirebaseFirestore $firestore;
        final /* synthetic */ Function1<Boolean, Unit> $onComplete;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(FirebaseFirestore firebaseFirestore, String str, Function1<? super Boolean, Unit> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$firestore = firebaseFirestore;
            this.$batchId = str;
            this.$onComplete = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$firestore, this.$batchId, this.$onComplete, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0064, code lost:
        
            if (kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getMain(), new com.example.sushikhasacademyapp.data.SqlConnectRepository.AnonymousClass1.C01001(r6.$onComplete, null), r6) == r0) goto L16;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 0
                switch(r1) {
                    case 0: goto L23;
                    case 1: goto L1f;
                    case 2: goto L1b;
                    case 3: goto L12;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L12:
                java.lang.Object r0 = r6.L$0
                java.lang.Exception r0 = (java.lang.Exception) r0
                kotlin.ResultKt.throwOnFailure(r7)
                goto La8
            L1b:
                kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L68
                goto L67
            L1f:
                kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L68
                goto L4b
            L23:
                kotlin.ResultKt.throwOnFailure(r7)
                com.google.firebase.firestore.FirebaseFirestore r1 = r6.$firestore     // Catch: java.lang.Exception -> L68
                java.lang.String r3 = "batches"
                com.google.firebase.firestore.CollectionReference r1 = r1.collection(r3)     // Catch: java.lang.Exception -> L68
                java.lang.String r3 = r6.$batchId     // Catch: java.lang.Exception -> L68
                com.google.firebase.firestore.DocumentReference r1 = r1.document(r3)     // Catch: java.lang.Exception -> L68
                com.google.android.gms.tasks.Task r1 = r1.delete()     // Catch: java.lang.Exception -> L68
                java.lang.String r3 = "delete(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)     // Catch: java.lang.Exception -> L68
                r3 = r6
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch: java.lang.Exception -> L68
                r4 = 1
                r6.label = r4     // Catch: java.lang.Exception -> L68
                java.lang.Object r1 = kotlinx.coroutines.tasks.TasksKt.await(r1, r3)     // Catch: java.lang.Exception -> L68
                if (r1 != r0) goto L4b
                goto L66
            L4b:
                kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.lang.Exception -> L68
                kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1     // Catch: java.lang.Exception -> L68
                com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteBatch$1$1 r3 = new com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteBatch$1$1     // Catch: java.lang.Exception -> L68
                kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> r4 = r6.$onComplete     // Catch: java.lang.Exception -> L68
                r3.<init>(r4, r2)     // Catch: java.lang.Exception -> L68
                kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3     // Catch: java.lang.Exception -> L68
                r4 = r6
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch: java.lang.Exception -> L68
                r5 = 2
                r6.label = r5     // Catch: java.lang.Exception -> L68
                java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r3, r4)     // Catch: java.lang.Exception -> L68
                if (r1 != r0) goto L67
            L66:
                return r0
            L67:
                goto La9
            L68:
                r1 = move-exception
                java.lang.String r3 = r1.getMessage()
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "Error deleting batch: "
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.StringBuilder r3 = r4.append(r3)
                java.lang.String r3 = r3.toString()
                java.lang.String r4 = "FirestoreRepo"
                android.util.Log.e(r4, r3)
                kotlinx.coroutines.MainCoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getMain()
                kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
                com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteBatch$1$2 r4 = new com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteBatch$1$2
                kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> r5 = r6.$onComplete
                r4.<init>(r5, r2)
                kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                r2 = r6
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
                r6.L$0 = r5
                r5 = 3
                r6.label = r5
                java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r3, r4, r2)
                if (r2 != r0) goto La7
                return r0
            La7:
                r0 = r1
            La8:
            La9:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.sushikhasacademyapp.data.SqlConnectRepository.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteBatch$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteBatch$1$1", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01001(Function1<? super Boolean, Unit> function1, Continuation<? super C01001> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01001(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(true));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteBatch$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$deleteBatch$1$2", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Function1<? super Boolean, Unit> function1, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(false));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    public final void updateContactInfo(ContactInfo contact, Function1<? super Boolean, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(contact, "contact");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        this.contactInfo.setValue(contact);
        FirebaseFirestore firestore = getDb();
        if (firestore != null) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C04331(contact, firestore, onComplete, null), 3, null);
        } else {
            onComplete.invoke(false);
        }
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$updateContactInfo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SqlConnectRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$updateContactInfo$1", f = "SqlConnectRepository.kt", i = {0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4}, l = {441, 444, 446, 448, 451}, m = "invokeSuspend", n = {"data", "data", "snapshot", "docId", "data", "snapshot", "docId", "data", "snapshot", "docId", "e"}, s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0"})
    static final class C04331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ContactInfo $contact;
        final /* synthetic */ FirebaseFirestore $firestore;
        final /* synthetic */ Function1<Boolean, Unit> $onComplete;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04331(ContactInfo contactInfo, FirebaseFirestore firebaseFirestore, Function1<? super Boolean, Unit> function1, Continuation<? super C04331> continuation) {
            super(2, continuation);
            this.$contact = contactInfo;
            this.$firestore = firebaseFirestore;
            this.$onComplete = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04331(this.$contact, this.$firestore, this.$onComplete, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04331) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:25:0x00c3 A[Catch: Exception -> 0x015f, TryCatch #0 {Exception -> 0x015f, blocks: (B:8:0x002b, B:11:0x003c, B:37:0x0130, B:14:0x004d, B:17:0x0056, B:23:0x00b0, B:25:0x00c3, B:28:0x00cb, B:33:0x0100, B:20:0x005f), top: B:49:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:26:0x00c8  */
        /* JADX WARN: Code duplicated, block: B:28:0x00cb A[Catch: Exception -> 0x015f, TryCatch #0 {Exception -> 0x015f, blocks: (B:8:0x002b, B:11:0x003c, B:37:0x0130, B:14:0x004d, B:17:0x0056, B:23:0x00b0, B:25:0x00c3, B:28:0x00cb, B:33:0x0100, B:20:0x005f), top: B:49:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:30:0x00fb  */
        /* JADX WARN: Code duplicated, block: B:31:0x00fc  */
        /* JADX WARN: Code duplicated, block: B:33:0x0100 A[Catch: Exception -> 0x015f, TryCatch #0 {Exception -> 0x015f, blocks: (B:8:0x002b, B:11:0x003c, B:37:0x0130, B:14:0x004d, B:17:0x0056, B:23:0x00b0, B:25:0x00c3, B:28:0x00cb, B:33:0x0100, B:20:0x005f), top: B:49:0x000b }] */
        /* JADX WARN: Code duplicated, block: B:36:0x012d  */
        /* JADX WARN: Code duplicated, block: B:39:0x015d A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Map data;
            Object objAwait;
            QuerySnapshot snapshot;
            DocumentSnapshot documentSnapshot;
            String docId;
            Task<DocumentReference> taskAdd;
            Map data2;
            QuerySnapshot snapshot2;
            String docId2;
            Task<Void> task;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        data = MapsKt.mapOf(TuplesKt.to("address", this.$contact.getAddress()), TuplesKt.to("email", this.$contact.getEmail()), TuplesKt.to("phone_number", this.$contact.getPhone()));
                        Task<QuerySnapshot> task2 = this.$firestore.collection("contact_info").get();
                        Intrinsics.checkNotNullExpressionValue(task2, "get(...)");
                        this.L$0 = data;
                        this.label = 1;
                        objAwait = TasksKt.await(task2, this);
                        if (objAwait == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        snapshot = (QuerySnapshot) objAwait;
                        List<DocumentSnapshot> documents = snapshot.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
                        documentSnapshot = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents);
                        if (documentSnapshot != null) {
                            docId = documentSnapshot.getId();
                        } else {
                            docId = null;
                        }
                        if (docId != null) {
                            task = this.$firestore.collection("contact_info").document(docId).set(data);
                            Intrinsics.checkNotNullExpressionValue(task, "set(...)");
                            this.L$0 = SpillingKt.nullOutSpilledVariable(data);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(docId);
                            this.label = 2;
                            if (TasksKt.await(task, this) == coroutine_suspended) {
                                data2 = data;
                                snapshot2 = snapshot;
                                docId2 = docId;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                                this.label = 4;
                                if (BuildersKt.withContext(Dispatchers.getMain(), new C01041(this.$onComplete, null), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                        } else {
                            taskAdd = this.$firestore.collection("contact_info").add(data);
                            Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                            this.L$0 = SpillingKt.nullOutSpilledVariable(data);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(docId);
                            this.label = 3;
                            if (TasksKt.await(taskAdd, this) != coroutine_suspended) {
                                data2 = data;
                                snapshot2 = snapshot;
                                docId2 = docId;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                                this.label = 4;
                                if (BuildersKt.withContext(Dispatchers.getMain(), new C01041(this.$onComplete, null), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                        }
                        return coroutine_suspended;
                    case 1:
                        data = (Map) this.L$0;
                        ResultKt.throwOnFailure($result);
                        objAwait = $result;
                        snapshot = (QuerySnapshot) objAwait;
                        List<DocumentSnapshot> documents2 = snapshot.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents2, "getDocuments(...)");
                        documentSnapshot = (DocumentSnapshot) CollectionsKt.firstOrNull((List) documents2);
                        if (documentSnapshot != null) {
                            docId = documentSnapshot.getId();
                        } else {
                            docId = null;
                        }
                        if (docId != null) {
                            task = this.$firestore.collection("contact_info").document(docId).set(data);
                            Intrinsics.checkNotNullExpressionValue(task, "set(...)");
                            this.L$0 = SpillingKt.nullOutSpilledVariable(data);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(docId);
                            this.label = 2;
                            if (TasksKt.await(task, this) == coroutine_suspended) {
                                data2 = data;
                                snapshot2 = snapshot;
                                docId2 = docId;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                                this.label = 4;
                                if (BuildersKt.withContext(Dispatchers.getMain(), new C01041(this.$onComplete, null), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                        } else {
                            taskAdd = this.$firestore.collection("contact_info").add(data);
                            Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                            this.L$0 = SpillingKt.nullOutSpilledVariable(data);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(docId);
                            this.label = 3;
                            if (TasksKt.await(taskAdd, this) != coroutine_suspended) {
                                data2 = data;
                                snapshot2 = snapshot;
                                docId2 = docId;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                                this.label = 4;
                                if (BuildersKt.withContext(Dispatchers.getMain(), new C01041(this.$onComplete, null), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                        }
                        return coroutine_suspended;
                    case 2:
                        docId2 = (String) this.L$2;
                        snapshot2 = (QuerySnapshot) this.L$1;
                        data2 = (Map) this.L$0;
                        ResultKt.throwOnFailure($result);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                        this.label = 4;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new C01041(this.$onComplete, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    case 3:
                        docId2 = (String) this.L$2;
                        snapshot2 = (QuerySnapshot) this.L$1;
                        data2 = (Map) this.L$0;
                        ResultKt.throwOnFailure($result);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(data2);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(snapshot2);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(docId2);
                        this.label = 4;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new C01041(this.$onComplete, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    case 4:
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    case 5:
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Exception e) {
                Log.e("FirestoreRepo", "Error updating contact info: " + e.getMessage());
                this.L$0 = SpillingKt.nullOutSpilledVariable(e);
                this.L$1 = null;
                this.L$2 = null;
                this.label = 5;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.$onComplete, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$updateContactInfo$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$updateContactInfo$1$1", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01041(Function1<? super Boolean, Unit> function1, Continuation<? super C01041> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01041(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(true));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.SqlConnectRepository$updateContactInfo$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SqlConnectRepository.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.SqlConnectRepository$updateContactInfo$1$2", f = "SqlConnectRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $onComplete;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Function1<? super Boolean, Unit> function1, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$onComplete = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$onComplete, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.$onComplete.invoke(Boxing.boxBoolean(false));
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }
}
