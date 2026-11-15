package com.example.sushikhasacademyapp;

import androidx.compose.runtime.MutableState;
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
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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
@DebugMetadata(c = "com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchAnnouncementsSection$1$1", f = "BatchDetailActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class BatchDetailActivityKt$BatchAnnouncementsSection$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<List<AnnouncementItem>> $announcementsList$delegate;
    final /* synthetic */ String $batchTitle;
    final /* synthetic */ MutableState<List<AnnouncementReply>> $repliesList$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BatchDetailActivityKt$BatchAnnouncementsSection$1$1(String str, MutableState<List<AnnouncementItem>> mutableState, MutableState<List<AnnouncementReply>> mutableState2, Continuation<? super BatchDetailActivityKt$BatchAnnouncementsSection$1$1> continuation) {
        super(2, continuation);
        this.$batchTitle = str;
        this.$announcementsList$delegate = mutableState;
        this.$repliesList$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BatchDetailActivityKt$BatchAnnouncementsSection$1$1(this.$batchTitle, this.$announcementsList$delegate, this.$repliesList$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatchDetailActivityKt$BatchAnnouncementsSection$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                    Query queryWhereEqualTo = db.collection("batch_announcements").whereEqualTo("batchTitle", this.$batchTitle);
                    final MutableState<List<AnnouncementItem>> mutableState = this.$announcementsList$delegate;
                    queryWhereEqualTo.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchAnnouncementsSection$1$1$$ExternalSyntheticLambda0
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            BatchDetailActivityKt$BatchAnnouncementsSection$1$1.invokeSuspend$lambda$1(mutableState, (QuerySnapshot) obj, firebaseFirestoreException);
                        }
                    });
                    Query queryWhereEqualTo2 = db.collection("announcement_replies").whereEqualTo("batchTitle", this.$batchTitle);
                    final MutableState<List<AnnouncementReply>> mutableState2 = this.$repliesList$delegate;
                    Intrinsics.checkNotNull(queryWhereEqualTo2.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchAnnouncementsSection$1$1$$ExternalSyntheticLambda1
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            BatchDetailActivityKt$BatchAnnouncementsSection$1$1.invokeSuspend$lambda$3(mutableState2, (QuerySnapshot) obj, firebaseFirestoreException);
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

    static final void invokeSuspend$lambda$1(MutableState $announcementsList$delegate, QuerySnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (snapshot != null) {
            Iterable documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            Iterable<DocumentSnapshot> iterable = documents;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (DocumentSnapshot documentSnapshot : iterable) {
                String id = documentSnapshot.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                String string = documentSnapshot.getString("title");
                if (string == null) {
                    string = "Announcement";
                }
                String string2 = documentSnapshot.getString("message");
                if (string2 == null) {
                    string2 = "";
                }
                String string3 = documentSnapshot.getString("timestamp");
                if (string3 == null) {
                    string3 = "Recently";
                }
                arrayList.add(new AnnouncementItem(id, string, string2, string3));
            }
            List remote = (List) arrayList;
            $announcementsList$delegate.setValue(remote);
        }
    }

    static final void invokeSuspend$lambda$3(MutableState $repliesList$delegate, QuerySnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (snapshot != null) {
            Iterable documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            Iterable<DocumentSnapshot> iterable = documents;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (DocumentSnapshot documentSnapshot : iterable) {
                String id = documentSnapshot.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                String string = documentSnapshot.getString("announcementId");
                if (string == null) {
                    string = "";
                }
                String string2 = documentSnapshot.getString("studentUid");
                if (string2 == null) {
                    string2 = "";
                }
                String string3 = documentSnapshot.getString("studentName");
                if (string3 == null) {
                    string3 = "Student";
                }
                String string4 = documentSnapshot.getString("studentEmail");
                if (string4 == null) {
                    string4 = "";
                }
                Iterable iterable2 = iterable;
                String string5 = documentSnapshot.getString("replyText");
                String str = string5 != null ? string5 : "";
                String string6 = documentSnapshot.getString("timestamp");
                if (string6 == null) {
                    string6 = "Recently";
                }
                arrayList.add(new AnnouncementReply(id, string, string2, string3, string4, str, string6));
                iterable = iterable2;
            }
            List replies = (List) arrayList;
            $repliesList$delegate.setValue(replies);
        }
    }
}
