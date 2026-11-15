package com.example.sushikhasacademyapp;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.core.app.NotificationCompat;
import com.example.sushikhasacademyapp.data.ExtendedQuestion;
import com.example.sushikhasacademyapp.data.FullTestModel;
import com.example.sushikhasacademyapp.data.LocalTestManager;
import com.example.sushikhasacademyapp.data.StudentSubmission;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchTestsSection$3$1", f = "BatchDetailActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class BatchDetailActivityKt$BatchTestsSection$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $batchTitle;
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<List<StudentSubmission>> $submissionsList$delegate;
    final /* synthetic */ MutableState<List<FullTestModel>> $testsList$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BatchDetailActivityKt$BatchTestsSection$3$1(String str, Context context, MutableState<List<FullTestModel>> mutableState, MutableState<List<StudentSubmission>> mutableState2, Continuation<? super BatchDetailActivityKt$BatchTestsSection$3$1> continuation) {
        super(2, continuation);
        this.$batchTitle = str;
        this.$context = context;
        this.$testsList$delegate = mutableState;
        this.$submissionsList$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BatchDetailActivityKt$BatchTestsSection$3$1(this.$batchTitle, this.$context, this.$testsList$delegate, this.$submissionsList$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatchDetailActivityKt$BatchTestsSection$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Intrinsics.checkNotNullExpressionValue(db, "getInstance(...)");
                    final String targetKey = LocalTestManager.INSTANCE.canonicalKey(this.$batchTitle);
                    CollectionReference collectionReferenceCollection = db.collection("batch_tests");
                    final Context context = this.$context;
                    final String str = this.$batchTitle;
                    final MutableState<List<FullTestModel>> mutableState = this.$testsList$delegate;
                    collectionReferenceCollection.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchTestsSection$3$1$$ExternalSyntheticLambda0
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            BatchDetailActivityKt$BatchTestsSection$3$1.invokeSuspend$lambda$3(context, str, targetKey, mutableState, (QuerySnapshot) obj, firebaseFirestoreException);
                        }
                    });
                    CollectionReference collectionReferenceCollection2 = db.collection("test_submissions");
                    final MutableState<List<StudentSubmission>> mutableState2 = this.$submissionsList$delegate;
                    final Context context2 = this.$context;
                    Intrinsics.checkNotNull(collectionReferenceCollection2.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchTestsSection$3$1$$ExternalSyntheticLambda1
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            BatchDetailActivityKt$BatchTestsSection$3$1.invokeSuspend$lambda$7(targetKey, mutableState2, context2, (QuerySnapshot) obj, firebaseFirestoreException);
                        }
                    }));
                    break;
                } catch (Exception e) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final void invokeSuspend$lambda$3(Context $context, String $batchTitle, String $targetKey, MutableState $testsList$delegate, QuerySnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        int i;
        int i2;
        int i3;
        int iIntValue;
        if (snapshot != null) {
            Iterable documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            Iterable iterable = documents;
            int i4 = 0;
            Collection arrayList = new ArrayList();
            Iterable iterable2 = iterable;
            int i5 = 0;
            Iterable<DocumentSnapshot> iterable3 = iterable2;
            int i6 = 0;
            for (DocumentSnapshot documentSnapshot : iterable3) {
                String string = documentSnapshot.getString("batchTitle");
                if (string == null) {
                    string = "";
                }
                String string2 = StringsKt.trim((CharSequence) string).toString();
                String strCanonicalKey = LocalTestManager.INSTANCE.canonicalKey(string2);
                Iterable iterable4 = iterable;
                int i7 = i4;
                Iterable iterable5 = iterable2;
                int i8 = i5;
                Iterable iterable6 = iterable3;
                FullTestModel fullTestModel = null;
                if (StringsKt.contains$default((CharSequence) strCanonicalKey, (CharSequence) $targetKey, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) $targetKey, (CharSequence) strCanonicalKey, false, 2, (Object) null) || StringsKt.isBlank(strCanonicalKey)) {
                    Object obj = documentSnapshot.get("questions");
                    List listEmptyList = obj instanceof List ? (List) obj : null;
                    if (listEmptyList == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    List list = listEmptyList;
                    int i9 = 0;
                    Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterable iterable7 = list;
                    int i10 = 0;
                    Iterator it = iterable7.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        int i11 = i10 + 1;
                        if (i10 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        List list2 = listEmptyList;
                        Map map = (Map) next;
                        Iterable iterable8 = list;
                        Object obj2 = map.get("id");
                        int i12 = i9;
                        String str = obj2 instanceof String ? (String) obj2 : null;
                        if (str == null) {
                            i2 = i10;
                            str = "q_" + i2;
                        } else {
                            i2 = i10;
                        }
                        String str2 = str;
                        Object obj3 = map.get("text");
                        String str3 = obj3 instanceof String ? (String) obj3 : null;
                        String str4 = str3 == null ? "" : str3;
                        Object obj4 = map.get("isMcq");
                        Boolean bool = obj4 instanceof Boolean ? (Boolean) obj4 : null;
                        boolean zBooleanValue = bool != null ? bool.booleanValue() : true;
                        Object obj5 = map.get("optionA");
                        String str5 = obj5 instanceof String ? (String) obj5 : null;
                        String str6 = str5 == null ? "" : str5;
                        Object obj6 = map.get("optionB");
                        String str7 = obj6 instanceof String ? (String) obj6 : null;
                        String str8 = str7 == null ? "" : str7;
                        Object obj7 = map.get("optionC");
                        String str9 = obj7 instanceof String ? (String) obj7 : null;
                        String str10 = str9 == null ? "" : str9;
                        Object obj8 = map.get("optionD");
                        String str11 = obj8 instanceof String ? (String) obj8 : null;
                        String str12 = str11 == null ? "" : str11;
                        Object obj9 = map.get("correctAns");
                        String str13 = obj9 instanceof String ? (String) obj9 : null;
                        if (str13 == null) {
                            str13 = "Option A";
                        }
                        String str14 = str13;
                        Object obj10 = map.get("marks");
                        Iterable iterable9 = iterable7;
                        Long l = obj10 instanceof Long ? (Long) obj10 : null;
                        if (l != null) {
                            i3 = i6;
                            iIntValue = (int) l.longValue();
                        } else {
                            i3 = i6;
                            Object obj11 = map.get("marks");
                            Integer num = obj11 instanceof Integer ? (Integer) obj11 : null;
                            iIntValue = num != null ? num.intValue() : 5;
                        }
                        arrayList2.add(new ExtendedQuestion(str2, str4, zBooleanValue, str6, str8, str10, str12, str14, iIntValue, null, 0, null, 3584, null));
                        i10 = i11;
                        listEmptyList = list2;
                        list = iterable8;
                        i9 = i12;
                        iterable7 = iterable9;
                        i6 = i3;
                    }
                    i = i6;
                    List list3 = (List) arrayList2;
                    String id = documentSnapshot.getId();
                    Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                    String string3 = documentSnapshot.getString("title");
                    if (string3 == null) {
                        string3 = "Online Test";
                    }
                    String str15 = string3;
                    String str16 = string2;
                    if (StringsKt.isBlank(str16)) {
                        str16 = $batchTitle;
                    }
                    String str17 = str16;
                    Boolean bool2 = documentSnapshot.getBoolean("isOnline");
                    boolean zBooleanValue2 = bool2 != null ? bool2.booleanValue() : true;
                    Long l2 = documentSnapshot.getLong("durationMins");
                    int iLongValue = (int) (l2 != null ? l2.longValue() : 30L);
                    Long l3 = documentSnapshot.getLong("totalMaxMarks");
                    int iLongValue2 = (int) (l3 != null ? l3.longValue() : 100L);
                    String string4 = documentSnapshot.getString(NotificationCompat.CATEGORY_STATUS);
                    if (string4 == null) {
                        string4 = "Active Now";
                    }
                    String str18 = string4;
                    String string5 = documentSnapshot.getString("date");
                    String str19 = string5 == null ? "" : string5;
                    String string6 = documentSnapshot.getString("time");
                    String str20 = string6 == null ? "" : string6;
                    String string7 = documentSnapshot.getString("location");
                    String str21 = string7 == null ? "" : string7;
                    String string8 = documentSnapshot.getString("syllabus");
                    String str22 = string8 == null ? "" : string8;
                    String string9 = documentSnapshot.getString("instructions");
                    fullTestModel = new FullTestModel(id, str15, str17, zBooleanValue2, iLongValue, iLongValue2, str18, list3, str19, str20, str21, str22, string9 == null ? "" : string9, 0L, 8192, null);
                } else {
                    i = i6;
                }
                if (fullTestModel != null) {
                    arrayList.add(fullTestModel);
                }
                iterable = iterable4;
                i4 = i7;
                iterable2 = iterable5;
                i5 = i8;
                iterable3 = iterable6;
                i6 = i;
            }
            List remoteTests = (List) arrayList;
            $testsList$delegate.setValue(remoteTests);
            LocalTestManager.INSTANCE.saveTestsLocally($context, $batchTitle, remoteTests);
        }
    }

    static final void invokeSuspend$lambda$7(String $targetKey, MutableState $submissionsList$delegate, Context $context, QuerySnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        Iterable iterable;
        Iterable iterable2;
        int iIntValue;
        if (snapshot != null) {
            Iterable documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            Iterable iterable3 = documents;
            int i = 0;
            Collection arrayList = new ArrayList();
            Iterable iterable4 = iterable3;
            int i2 = 0;
            Iterable<DocumentSnapshot> iterable5 = iterable4;
            for (DocumentSnapshot documentSnapshot : iterable5) {
                String string = documentSnapshot.getString("batchTitle");
                if (string == null) {
                    string = "";
                }
                String string2 = StringsKt.trim((CharSequence) string).toString();
                String strCanonicalKey = LocalTestManager.INSTANCE.canonicalKey(string2);
                Iterable iterable6 = iterable3;
                int i3 = i;
                Iterable iterable7 = iterable4;
                int i4 = i2;
                StudentSubmission studentSubmission = null;
                if (StringsKt.contains$default((CharSequence) strCanonicalKey, (CharSequence) $targetKey, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) $targetKey, (CharSequence) strCanonicalKey, false, 2, (Object) null) || StringsKt.isBlank(strCanonicalKey)) {
                    Object obj = documentSnapshot.get("answersMap");
                    Map mapEmptyMap = obj instanceof Map ? (Map) obj : null;
                    if (mapEmptyMap == null) {
                        mapEmptyMap = MapsKt.emptyMap();
                    }
                    Map map = mapEmptyMap;
                    Object obj2 = documentSnapshot.get("scoresMap");
                    Map mapEmptyMap2 = obj2 instanceof Map ? (Map) obj2 : null;
                    if (mapEmptyMap2 == null) {
                        mapEmptyMap2 = MapsKt.emptyMap();
                    }
                    Map map2 = mapEmptyMap2;
                    Map linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map2.size()));
                    Map map3 = map2;
                    for (Object obj3 : map3.entrySet()) {
                        Map map4 = mapEmptyMap2;
                        Object key = ((Map.Entry) obj3).getKey();
                        Map map5 = map2;
                        Object value = ((Map.Entry) obj3).getValue();
                        Map map6 = map3;
                        Long l = value instanceof Long ? (Long) value : null;
                        if (l != null) {
                            iterable2 = iterable5;
                            iIntValue = (int) l.longValue();
                        } else {
                            iterable2 = iterable5;
                            Integer num = value instanceof Integer ? (Integer) value : null;
                            iIntValue = num != null ? num.intValue() : 0;
                        }
                        linkedHashMap.put(key, Integer.valueOf(iIntValue));
                        map3 = map6;
                        map2 = map5;
                        mapEmptyMap2 = map4;
                        iterable5 = iterable2;
                    }
                    iterable = iterable5;
                    Object obj4 = documentSnapshot.get("remarksMap");
                    Map mapEmptyMap3 = obj4 instanceof Map ? (Map) obj4 : null;
                    if (mapEmptyMap3 == null) {
                        mapEmptyMap3 = MapsKt.emptyMap();
                    }
                    Map map7 = mapEmptyMap3;
                    String id = documentSnapshot.getId();
                    Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                    String string3 = documentSnapshot.getString("testId");
                    String str = string3 == null ? "" : string3;
                    String string4 = documentSnapshot.getString("testTitle");
                    if (string4 == null) {
                        string4 = "Test";
                    }
                    String str2 = string4;
                    String string5 = documentSnapshot.getString("studentUid");
                    String str3 = string5 == null ? "" : string5;
                    String string6 = documentSnapshot.getString("studentName");
                    if (string6 == null) {
                        string6 = "Student";
                    }
                    String str4 = string6;
                    String string7 = documentSnapshot.getString("studentEmail");
                    String str5 = string7 == null ? "" : string7;
                    Long l2 = documentSnapshot.getLong("autoMcqScore");
                    int iLongValue = (int) (l2 != null ? l2.longValue() : 0L);
                    Long l3 = documentSnapshot.getLong("totalScore");
                    int iLongValue2 = (int) (l3 != null ? l3.longValue() : 0L);
                    Long l4 = documentSnapshot.getLong("maxMarks");
                    int iLongValue3 = (int) (l4 != null ? l4.longValue() : 0L);
                    Double d = documentSnapshot.getDouble("percentage");
                    double dDoubleValue = d != null ? d.doubleValue() : 0.0d;
                    String string8 = documentSnapshot.getString(NotificationCompat.CATEGORY_STATUS);
                    if (string8 == null) {
                        string8 = "Pending Evaluation";
                    }
                    String str6 = string8;
                    Boolean bool = documentSnapshot.getBoolean("isInterrupted");
                    boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
                    Boolean bool2 = documentSnapshot.getBoolean("canRetake");
                    boolean zBooleanValue2 = bool2 != null ? bool2.booleanValue() : false;
                    Long l5 = documentSnapshot.getLong("submittedAt");
                    long jLongValue = l5 != null ? l5.longValue() : System.currentTimeMillis();
                    Long l6 = documentSnapshot.getLong("evaluatedAt");
                    studentSubmission = new StudentSubmission(id, str, str2, string2, str3, str4, str5, map, linkedHashMap, map7, iLongValue, iLongValue2, iLongValue3, dDoubleValue, str6, zBooleanValue, zBooleanValue2, jLongValue, l6 != null ? l6.longValue() : 0L);
                } else {
                    iterable = iterable5;
                }
                if (studentSubmission != null) {
                    arrayList.add(studentSubmission);
                }
                iterable3 = iterable6;
                i = i3;
                iterable4 = iterable7;
                i2 = i4;
                iterable5 = iterable;
            }
            List remoteSubs = (List) arrayList;
            $submissionsList$delegate.setValue(remoteSubs);
            Iterator it = remoteSubs.iterator();
            while (it.hasNext()) {
                LocalTestManager.INSTANCE.saveSubmissionLocally($context, (StudentSubmission) it.next());
            }
        }
    }
}
