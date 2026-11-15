package com.example.sushikhasacademyapp;

import androidx.compose.runtime.MutableState;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchDetailsSection$2$1", f = "BatchDetailActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class BatchDetailActivityKt$BatchDetailsSection$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $batchTitle;
    final /* synthetic */ MutableState<List<SyllabusTopic>> $syllabusTopics$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BatchDetailActivityKt$BatchDetailsSection$2$1(String str, MutableState<List<SyllabusTopic>> mutableState, Continuation<? super BatchDetailActivityKt$BatchDetailsSection$2$1> continuation) {
        super(2, continuation);
        this.$batchTitle = str;
        this.$syllabusTopics$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BatchDetailActivityKt$BatchDetailsSection$2$1(this.$batchTitle, this.$syllabusTopics$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatchDetailActivityKt$BatchDetailsSection$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    final FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Intrinsics.checkNotNullExpressionValue(db, "getInstance(...)");
                    Query queryWhereEqualTo = db.collection("batch_syllabus").whereEqualTo("batchTitle", this.$batchTitle);
                    final MutableState<List<SyllabusTopic>> mutableState = this.$syllabusTopics$delegate;
                    final String str = this.$batchTitle;
                    Intrinsics.checkNotNull(queryWhereEqualTo.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchDetailsSection$2$1$$ExternalSyntheticLambda0
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            BatchDetailActivityKt$BatchDetailsSection$2$1.invokeSuspend$lambda$2(mutableState, db, str, (QuerySnapshot) obj, firebaseFirestoreException);
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

    static final void invokeSuspend$lambda$2(MutableState $syllabusTopics$delegate, FirebaseFirestore $db, String $batchTitle, QuerySnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        String str = "isCompleted";
        char c = 0;
        if (snapshot != null && !snapshot.isEmpty()) {
            Iterable documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            Iterable<DocumentSnapshot> iterable = documents;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (DocumentSnapshot documentSnapshot : iterable) {
                String id = documentSnapshot.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                String string = documentSnapshot.getString("title");
                if (string == null) {
                    string = "Topic";
                }
                Boolean bool = documentSnapshot.getBoolean("isCompleted");
                arrayList.add(new SyllabusTopic(id, string, bool != null ? bool.booleanValue() : false));
            }
            List remoteTopics = (List) arrayList;
            if (remoteTopics.isEmpty()) {
                return;
            }
            $syllabusTopics$delegate.setValue(remoteTopics);
            return;
        }
        if (snapshot == null || !snapshot.isEmpty()) {
            return;
        }
        char c2 = 1;
        char c3 = 2;
        char c4 = 3;
        int i = 4;
        Iterable<Pair> initialDefaults = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("Parts of Speech & Sentence Construction", true), TuplesKt.to("Subject-Verb Agreement & Concord Rules", true), TuplesKt.to("Active & Passive Voice Transformations", true), TuplesKt.to("Direct & Indirect Reported Speech", true), TuplesKt.to("Tenses & Aspect Mastery (Past, Present, Future)", true), TuplesKt.to("Phonetics, Pronunciation & Accent Training", true), TuplesKt.to("Vocabulary & Synonyms Builder (1000 Words)", false), TuplesKt.to("Public Speaking, Debates & Essay Writing", false)});
        for (Pair pair : initialDefaults) {
            String str2 = (String) pair.component1();
            boolean zBooleanValue = ((Boolean) pair.component2()).booleanValue();
            char c5 = c;
            char c6 = c2;
            CollectionReference collectionReferenceCollection = $db.collection("batch_syllabus");
            char c7 = c3;
            Pair[] pairArr = new Pair[i];
            pairArr[c5] = TuplesKt.to("title", str2);
            char c8 = c4;
            pairArr[c6] = TuplesKt.to(str, Boolean.valueOf(zBooleanValue));
            pairArr[c7] = TuplesKt.to("batchTitle", $batchTitle);
            pairArr[c8] = TuplesKt.to("createdAt", Timestamp.INSTANCE.now());
            collectionReferenceCollection.add(MapsKt.hashMapOf(pairArr));
            c = c5;
            c2 = c6;
            c3 = c7;
            c4 = c8;
            str = str;
            i = 4;
        }
    }
}
