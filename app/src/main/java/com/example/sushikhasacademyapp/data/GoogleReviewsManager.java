package com.example.sushikhasacademyapp.data;

import android.util.Log;
import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: GoogleReviewsManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0010\u001a\u00020\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0086@¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/example/sushikhasacademyapp/data/GoogleReviewsManager;", "", "<init>", "()V", "ACADEMY_SEARCH_QUERY", "", "defaultTopGoogleReview", "Lcom/example/sushikhasacademyapp/data/Testimonial;", "getDefaultTopGoogleReview", "()Lcom/example/sushikhasacademyapp/data/Testimonial;", "_topGoogleReview", "Lkotlinx/coroutines/flow/MutableStateFlow;", "topGoogleReview", "Lkotlinx/coroutines/flow/StateFlow;", "getTopGoogleReview", "()Lkotlinx/coroutines/flow/StateFlow;", "fetchHighestRatedReview", "apiKey", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GoogleReviewsManager {
    public static final int $stable;
    private static final String ACADEMY_SEARCH_QUERY = "Sushiksha English Academy, Konnagar, West Bengal";
    private static final MutableStateFlow<Testimonial> _topGoogleReview;
    private static final StateFlow<Testimonial> topGoogleReview;
    public static final GoogleReviewsManager INSTANCE = new GoogleReviewsManager();
    private static final Testimonial defaultTopGoogleReview = new Testimonial("Subhajit Roy", "Board & Spoken English (Konnagar)", "Sushiksha English Academy in Konnagar is outstanding. Suman Sir's individual guidance and innovative methods make English grammar and communication effortless. Best coaching in Hooghly!", 5, "Google Review", true, "Konnagar, Hooghly, West Bengal");

    private GoogleReviewsManager() {
    }

    static {
        GoogleReviewsManager googleReviewsManager = INSTANCE;
        _topGoogleReview = StateFlowKt.MutableStateFlow(defaultTopGoogleReview);
        topGoogleReview = FlowKt.asStateFlow(_topGoogleReview);
        $stable = 8;
    }

    public final Testimonial getDefaultTopGoogleReview() {
        return defaultTopGoogleReview;
    }

    public final StateFlow<Testimonial> getTopGoogleReview() {
        return topGoogleReview;
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.GoogleReviewsManager$fetchHighestRatedReview$2, reason: invalid class name */
    /* JADX INFO: compiled from: GoogleReviewsManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/sushikhasacademyapp/data/Testimonial;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.GoogleReviewsManager$fetchHighestRatedReview$2", f = "GoogleReviewsManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Testimonial>, Object> {
        final /* synthetic */ String $apiKey;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$apiKey = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$apiKey, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Testimonial> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    try {
                        String str = this.$apiKey;
                        if (!(str == null || StringsKt.isBlank(str))) {
                            if (!StringsKt.startsWith$default(this.$apiKey, "AIzaSyDummy", false, 2, (Object) null)) {
                                String encodedQuery = URLEncoder.encode(GoogleReviewsManager.ACADEMY_SEARCH_QUERY, Key.STRING_CHARSET_NAME);
                                String searchUrl = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + encodedQuery + "&key=" + this.$apiKey;
                                URLConnection uRLConnectionOpenConnection = new URL(searchUrl).openConnection();
                                Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                                HttpURLConnection connection = (HttpURLConnection) uRLConnectionOpenConnection;
                                connection.setConnectTimeout(5000);
                                connection.setReadTimeout(5000);
                                if (connection.getResponseCode() == 200) {
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                    String body = TextStreamsKt.readText(reader);
                                    reader.close();
                                    JSONObject json = new JSONObject(body);
                                    JSONArray results = json.optJSONArray("results");
                                    if (results != null && results.length() > 0) {
                                        String placeId = results.getJSONObject(0).optString("place_id");
                                        Intrinsics.checkNotNull(placeId);
                                        if (!StringsKt.isBlank(placeId)) {
                                            String detailsUrl = "https://maps.googleapis.com/maps/api/place/details/json?place_id=" + placeId + "&fields=name,rating,reviews&key=" + this.$apiKey;
                                            URLConnection uRLConnectionOpenConnection2 = new URL(detailsUrl).openConnection();
                                            Intrinsics.checkNotNull(uRLConnectionOpenConnection2, "null cannot be cast to non-null type java.net.HttpURLConnection");
                                            HttpURLConnection detailsConn = (HttpURLConnection) uRLConnectionOpenConnection2;
                                            if (detailsConn.getResponseCode() == 200) {
                                                BufferedReader dReader = new BufferedReader(new InputStreamReader(detailsConn.getInputStream()));
                                                String dBody = TextStreamsKt.readText(dReader);
                                                dReader.close();
                                                JSONObject dJson = new JSONObject(dBody).optJSONObject("result");
                                                JSONArray reviewsArr = dJson != null ? dJson.optJSONArray("reviews") : null;
                                                if (reviewsArr != null && reviewsArr.length() > 0) {
                                                    Testimonial highestReview = null;
                                                    int length = reviewsArr.length();
                                                    int i = 0;
                                                    while (i < length) {
                                                        JSONObject rObj = reviewsArr.getJSONObject(i);
                                                        int i2 = length;
                                                        int i3 = i;
                                                        String encodedQuery2 = encodedQuery;
                                                        int rating = rObj.optInt("rating", 5);
                                                        String placeId2 = placeId;
                                                        String authorName = rObj.optString("author_name", "Google Reviewer");
                                                        String text = rObj.optString("text", "");
                                                        Intrinsics.checkNotNull(text);
                                                        if (!StringsKt.isBlank(text) && (highestReview == null || rating >= highestReview.getRating())) {
                                                            Intrinsics.checkNotNull(authorName);
                                                            highestReview = new Testimonial(authorName, "Google Verified Learner", text, rating, "Google Review", true, "Konnagar, West Bengal");
                                                        }
                                                        i = i3 + 1;
                                                        length = i2;
                                                        placeId = placeId2;
                                                        encodedQuery = encodedQuery2;
                                                    }
                                                    if (highestReview != null) {
                                                        GoogleReviewsManager._topGoogleReview.setValue(highestReview);
                                                        return highestReview;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        Log.e("GoogleReviewsManager", "Failed to query Google Reviews: " + e.getMessage());
                    }
                    GoogleReviewsManager._topGoogleReview.setValue(GoogleReviewsManager.INSTANCE.getDefaultTopGoogleReview());
                    return GoogleReviewsManager.INSTANCE.getDefaultTopGoogleReview();
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static /* synthetic */ Object fetchHighestRatedReview$default(GoogleReviewsManager googleReviewsManager, String str, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return googleReviewsManager.fetchHighestRatedReview(str, continuation);
    }

    public final Object fetchHighestRatedReview(String apiKey, Continuation<? super Testimonial> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(apiKey, null), continuation);
    }
}
