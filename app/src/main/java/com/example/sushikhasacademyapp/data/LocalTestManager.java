package com.example.sushikhasacademyapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: LocalTestManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0005J\u0016\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016J\u001e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005J\u001e\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0005J\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00112\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00160\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/example/sushikhasacademyapp/data/LocalTestManager;", "", "<init>", "()V", "PREF_NAME", "", "KEY_TESTS", "KEY_SUBMISSIONS", "getPrefs", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "canonicalKey", "batchTitle", "saveTestsLocally", "", "tests", "", "Lcom/example/sushikhasacademyapp/data/FullTestModel;", "getLocalTests", "saveSubmissionLocally", "submission", "Lcom/example/sushikhasacademyapp/data/StudentSubmission;", "deleteSubmissionsForTestLocally", "testId", "deleteSingleSubmissionLocally", "submissionId", "getLocalSubmissions", "computeLeaderboard", "Lcom/example/sushikhasacademyapp/data/BatchLeaderboardEntry;", "submissions", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LocalTestManager {
    public static final int $stable = 0;
    public static final LocalTestManager INSTANCE = new LocalTestManager();
    private static final String KEY_SUBMISSIONS = "cached_submissions_";
    private static final String KEY_TESTS = "cached_tests_";
    private static final String PREF_NAME = "sushiksha_local_tests_v2";

    private LocalTestManager() {
    }

    private final SharedPreferences getPrefs(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        return sharedPreferences;
    }

    public final String canonicalKey(String batchTitle) {
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        String lowerCase = StringsKt.trim((CharSequence) batchTitle).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return new Regex("[^a-zA-Z0-9_]").replace(StringsKt.trim((CharSequence) StringsKt.substringBefore$default(lowerCase, "(", (String) null, 2, (Object) null)).toString(), "_");
    }

    public final void saveTestsLocally(Context context, String batchTitle, List<FullTestModel> tests) {
        String str = "id";
        Intrinsics.checkNotNullParameter(context, "context");
        String str2 = "batchTitle";
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        Intrinsics.checkNotNullParameter(tests, "tests");
        try {
            String key = KEY_TESTS + canonicalKey(batchTitle);
            JSONArray jSONArray = new JSONArray();
            for (FullTestModel fullTestModel : tests) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(str, fullTestModel.getId());
                jSONObject.put("title", fullTestModel.getTitle());
                jSONObject.put(str2, fullTestModel.getBatchTitle());
                jSONObject.put("isOnline", fullTestModel.isOnline());
                jSONObject.put("durationMins", fullTestModel.getDurationMins());
                jSONObject.put("totalMaxMarks", fullTestModel.getTotalMaxMarks());
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, fullTestModel.getStatus());
                jSONObject.put("date", fullTestModel.getDate());
                jSONObject.put("time", fullTestModel.getTime());
                jSONObject.put("location", fullTestModel.getLocation());
                jSONObject.put("syllabus", fullTestModel.getSyllabus());
                jSONObject.put("instructions", fullTestModel.getInstructions());
                String str3 = str2;
                jSONObject.put("createdAt", fullTestModel.getCreatedAt());
                JSONArray jSONArray2 = new JSONArray();
                Iterable<ExtendedQuestion> questions = fullTestModel.getQuestions();
                int i = 0;
                for (ExtendedQuestion extendedQuestion : questions) {
                    Iterable iterable = questions;
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(str, extendedQuestion.getId());
                    jSONObject2.put("text", extendedQuestion.getText());
                    jSONObject2.put("isMcq", extendedQuestion.isMcq());
                    jSONObject2.put("optionA", extendedQuestion.getOptionA());
                    jSONObject2.put("optionB", extendedQuestion.getOptionB());
                    jSONObject2.put("optionC", extendedQuestion.getOptionC());
                    jSONObject2.put("optionD", extendedQuestion.getOptionD());
                    jSONObject2.put("correctAns", extendedQuestion.getCorrectAns());
                    jSONObject2.put("marks", extendedQuestion.getMarks());
                    jSONArray2.put(jSONObject2);
                    questions = iterable;
                    str = str;
                    i = i;
                }
                jSONObject.put("questions", jSONArray2);
                jSONArray.put(jSONObject);
                str2 = str3;
                str = str;
            }
            getPrefs(context).edit().putString(key, jSONArray.toString()).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final List<FullTestModel> getLocalTests(Context context, String batchTitle) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = "batchTitle";
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        List list = new ArrayList();
        try {
            String key = KEY_TESTS + canonicalKey(batchTitle);
            String jsonStr = getPrefs(context).getString(key, null);
            if (jsonStr == null) {
                return CollectionsKt.emptyList();
            }
            JSONArray jsonArray = new JSONArray(jsonStr);
            int i = 0;
            int length = jsonArray.length();
            while (i < length) {
                JSONObject obj = jsonArray.getJSONObject(i);
                JSONArray qArr = obj.optJSONArray("questions");
                List qList = new ArrayList();
                if (qArr != null) {
                    int length2 = qArr.length();
                    for (int j = 0; j < length2; j++) {
                        JSONObject qObj = qArr.getJSONObject(j);
                        String strOptString = qObj.optString("id");
                        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                        String strOptString2 = qObj.optString("text");
                        Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                        boolean zOptBoolean = qObj.optBoolean("isMcq", true);
                        String strOptString3 = qObj.optString("optionA");
                        Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                        String strOptString4 = qObj.optString("optionB");
                        Intrinsics.checkNotNullExpressionValue(strOptString4, "optString(...)");
                        String strOptString5 = qObj.optString("optionC");
                        Intrinsics.checkNotNullExpressionValue(strOptString5, "optString(...)");
                        String strOptString6 = qObj.optString("optionD");
                        Intrinsics.checkNotNullExpressionValue(strOptString6, "optString(...)");
                        String strOptString7 = qObj.optString("correctAns", "Option A");
                        Intrinsics.checkNotNullExpressionValue(strOptString7, "optString(...)");
                        qList.add(new ExtendedQuestion(strOptString, strOptString2, zOptBoolean, strOptString3, strOptString4, strOptString5, strOptString6, strOptString7, qObj.optInt("marks", 5), null, 0, null, 3584, null));
                    }
                }
                String strOptString8 = obj.optString("id");
                Intrinsics.checkNotNullExpressionValue(strOptString8, "optString(...)");
                String strOptString9 = obj.optString("title");
                Intrinsics.checkNotNullExpressionValue(strOptString9, "optString(...)");
                String strOptString10 = obj.optString(str);
                Intrinsics.checkNotNullExpressionValue(strOptString10, "optString(...)");
                String str2 = str;
                boolean zOptBoolean2 = obj.optBoolean("isOnline", true);
                int iOptInt = obj.optInt("durationMins", 30);
                int iOptInt2 = obj.optInt("totalMaxMarks", 100);
                String strOptString11 = obj.optString(NotificationCompat.CATEGORY_STATUS, "Active Now");
                Intrinsics.checkNotNullExpressionValue(strOptString11, "optString(...)");
                String strOptString12 = obj.optString("date");
                Intrinsics.checkNotNullExpressionValue(strOptString12, "optString(...)");
                String strOptString13 = obj.optString("time");
                Intrinsics.checkNotNullExpressionValue(strOptString13, "optString(...)");
                String strOptString14 = obj.optString("location");
                Intrinsics.checkNotNullExpressionValue(strOptString14, "optString(...)");
                String strOptString15 = obj.optString("syllabus");
                Intrinsics.checkNotNullExpressionValue(strOptString15, "optString(...)");
                String strOptString16 = obj.optString("instructions");
                Intrinsics.checkNotNullExpressionValue(strOptString16, "optString(...)");
                list.add(new FullTestModel(strOptString8, strOptString9, strOptString10, zOptBoolean2, iOptInt, iOptInt2, strOptString11, qList, strOptString12, strOptString13, strOptString14, strOptString15, strOptString16, obj.optLong("createdAt", System.currentTimeMillis())));
                i++;
                str = str2;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void saveSubmissionLocally(Context context, StudentSubmission submission) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(submission, "submission");
        try {
            String key = KEY_SUBMISSIONS + canonicalKey(submission.getBatchTitle());
            List<StudentSubmission> existing = CollectionsKt.toMutableList((Collection) getLocalSubmissions(context, submission.getBatchTitle()));
            int index = 0;
            Iterator it = existing.iterator();
            while (true) {
                if (it.hasNext()) {
                    StudentSubmission studentSubmission = (StudentSubmission) it.next();
                    if (Intrinsics.areEqual(studentSubmission.getId(), submission.getId()) || (Intrinsics.areEqual(studentSubmission.getTestId(), submission.getTestId()) && Intrinsics.areEqual(studentSubmission.getStudentUid(), submission.getStudentUid()))) {
                        break;
                    } else {
                        index++;
                    }
                } else {
                    index = -1;
                    break;
                }
            }
            if (index != -1) {
                existing.set(index, submission);
            } else {
                existing.add(submission);
            }
            JSONArray jsonArray = new JSONArray();
            for (StudentSubmission studentSubmission2 : existing) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", studentSubmission2.getId());
                jSONObject.put("testId", studentSubmission2.getTestId());
                jSONObject.put("testTitle", studentSubmission2.getTestTitle());
                jSONObject.put("batchTitle", studentSubmission2.getBatchTitle());
                jSONObject.put("studentUid", studentSubmission2.getStudentUid());
                jSONObject.put("studentName", studentSubmission2.getStudentName());
                jSONObject.put("studentEmail", studentSubmission2.getStudentEmail());
                jSONObject.put("autoMcqScore", studentSubmission2.getAutoMcqScore());
                jSONObject.put("totalScore", studentSubmission2.getTotalScore());
                jSONObject.put("maxMarks", studentSubmission2.getMaxMarks());
                jSONObject.put("percentage", studentSubmission2.getPercentage());
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, studentSubmission2.getStatus());
                jSONObject.put("isInterrupted", studentSubmission2.isInterrupted());
                jSONObject.put("canRetake", studentSubmission2.getCanRetake());
                jSONObject.put("submittedAt", studentSubmission2.getSubmittedAt());
                jSONObject.put("evaluatedAt", studentSubmission2.getEvaluatedAt());
                Map<String, String> answersMap = studentSubmission2.getAnswersMap();
                Intrinsics.checkNotNull(answersMap, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                jSONObject.put("answersMap", new JSONObject(answersMap));
                Map<String, Integer> scoresMap = studentSubmission2.getScoresMap();
                Intrinsics.checkNotNull(scoresMap, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                jSONObject.put("scoresMap", new JSONObject(scoresMap));
                Map<String, String> remarksMap = studentSubmission2.getRemarksMap();
                Intrinsics.checkNotNull(remarksMap, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                jSONObject.put("remarksMap", new JSONObject(remarksMap));
                jsonArray.put(jSONObject);
            }
            getPrefs(context).edit().putString(key, jsonArray.toString()).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void deleteSubmissionsForTestLocally(Context context, String batchTitle, String testId) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = "batchTitle";
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        Intrinsics.checkNotNullParameter(testId, "testId");
        try {
            Iterable localSubmissions = getLocalSubmissions(context, batchTitle);
            Collection arrayList = new ArrayList();
            for (Object obj : localSubmissions) {
                if (!Intrinsics.areEqual(((StudentSubmission) obj).getTestId(), testId)) {
                    arrayList.add(obj);
                }
            }
            Iterable<StudentSubmission> existing = (List) arrayList;
            try {
                String key = KEY_SUBMISSIONS + canonicalKey(batchTitle);
                JSONArray jsonArray = new JSONArray();
                for (StudentSubmission studentSubmission : existing) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", studentSubmission.getId());
                    jSONObject.put("testId", studentSubmission.getTestId());
                    jSONObject.put("testTitle", studentSubmission.getTestTitle());
                    jSONObject.put(str, studentSubmission.getBatchTitle());
                    jSONObject.put("studentUid", studentSubmission.getStudentUid());
                    jSONObject.put("studentName", studentSubmission.getStudentName());
                    jSONObject.put("studentEmail", studentSubmission.getStudentEmail());
                    jSONObject.put("autoMcqScore", studentSubmission.getAutoMcqScore());
                    jSONObject.put("totalScore", studentSubmission.getTotalScore());
                    jSONObject.put("maxMarks", studentSubmission.getMaxMarks());
                    String str2 = str;
                    jSONObject.put("percentage", studentSubmission.getPercentage());
                    jSONObject.put(NotificationCompat.CATEGORY_STATUS, studentSubmission.getStatus());
                    jSONObject.put("isInterrupted", studentSubmission.isInterrupted());
                    jSONObject.put("canRetake", studentSubmission.getCanRetake());
                    jSONObject.put("submittedAt", studentSubmission.getSubmittedAt());
                    jSONObject.put("evaluatedAt", studentSubmission.getEvaluatedAt());
                    Map<String, String> answersMap = studentSubmission.getAnswersMap();
                    Intrinsics.checkNotNull(answersMap, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                    jSONObject.put("answersMap", new JSONObject(answersMap));
                    Map<String, Integer> scoresMap = studentSubmission.getScoresMap();
                    Intrinsics.checkNotNull(scoresMap, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                    jSONObject.put("scoresMap", new JSONObject(scoresMap));
                    Map<String, String> remarksMap = studentSubmission.getRemarksMap();
                    Intrinsics.checkNotNull(remarksMap, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                    jSONObject.put("remarksMap", new JSONObject(remarksMap));
                    jsonArray.put(jSONObject);
                    str = str2;
                }
                getPrefs(context).edit().putString(key, jsonArray.toString()).apply();
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public final void deleteSingleSubmissionLocally(Context context, String batchTitle, String submissionId) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = "batchTitle";
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        Intrinsics.checkNotNullParameter(submissionId, "submissionId");
        try {
            Iterable localSubmissions = getLocalSubmissions(context, batchTitle);
            Collection arrayList = new ArrayList();
            for (Object obj : localSubmissions) {
                if (!Intrinsics.areEqual(((StudentSubmission) obj).getId(), submissionId)) {
                    arrayList.add(obj);
                }
            }
            Iterable<StudentSubmission> existing = (List) arrayList;
            try {
                String key = KEY_SUBMISSIONS + canonicalKey(batchTitle);
                JSONArray jsonArray = new JSONArray();
                for (StudentSubmission studentSubmission : existing) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", studentSubmission.getId());
                    jSONObject.put("testId", studentSubmission.getTestId());
                    jSONObject.put("testTitle", studentSubmission.getTestTitle());
                    jSONObject.put(str, studentSubmission.getBatchTitle());
                    jSONObject.put("studentUid", studentSubmission.getStudentUid());
                    jSONObject.put("studentName", studentSubmission.getStudentName());
                    jSONObject.put("studentEmail", studentSubmission.getStudentEmail());
                    jSONObject.put("autoMcqScore", studentSubmission.getAutoMcqScore());
                    jSONObject.put("totalScore", studentSubmission.getTotalScore());
                    jSONObject.put("maxMarks", studentSubmission.getMaxMarks());
                    String str2 = str;
                    jSONObject.put("percentage", studentSubmission.getPercentage());
                    jSONObject.put(NotificationCompat.CATEGORY_STATUS, studentSubmission.getStatus());
                    jSONObject.put("isInterrupted", studentSubmission.isInterrupted());
                    jSONObject.put("canRetake", studentSubmission.getCanRetake());
                    jSONObject.put("submittedAt", studentSubmission.getSubmittedAt());
                    jSONObject.put("evaluatedAt", studentSubmission.getEvaluatedAt());
                    Map<String, String> answersMap = studentSubmission.getAnswersMap();
                    Intrinsics.checkNotNull(answersMap, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                    jSONObject.put("answersMap", new JSONObject(answersMap));
                    Map<String, Integer> scoresMap = studentSubmission.getScoresMap();
                    Intrinsics.checkNotNull(scoresMap, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                    jSONObject.put("scoresMap", new JSONObject(scoresMap));
                    Map<String, String> remarksMap = studentSubmission.getRemarksMap();
                    Intrinsics.checkNotNull(remarksMap, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
                    jSONObject.put("remarksMap", new JSONObject(remarksMap));
                    jsonArray.put(jSONObject);
                    str = str2;
                }
                getPrefs(context).edit().putString(key, jsonArray.toString()).apply();
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public final List<StudentSubmission> getLocalSubmissions(Context context, String batchTitle) {
        Map scMap;
        String key;
        Iterator<String> itKeys;
        Iterator<String> itKeys2;
        Iterator<String> itKeys3;
        Intrinsics.checkNotNullParameter(context, "context");
        String str = "batchTitle";
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        List list = new ArrayList();
        try {
            String key2 = KEY_SUBMISSIONS + canonicalKey(batchTitle);
            String jsonStr = getPrefs(context).getString(key2, null);
            if (jsonStr == null) {
                return CollectionsKt.emptyList();
            }
            JSONArray jsonArray = new JSONArray(jsonStr);
            int i = 0;
            int length = jsonArray.length();
            while (i < length) {
                JSONObject obj = jsonArray.getJSONObject(i);
                JSONObject ansObj = obj.optJSONObject("answersMap");
                Map ansMap = new LinkedHashMap();
                if (ansObj != null && (itKeys3 = ansObj.keys()) != null) {
                    while (itKeys3.hasNext()) {
                        String str2 = (String) itKeys3.next();
                        ansMap.put(str2, ansObj.optString(str2));
                    }
                }
                JSONObject scObj = obj.optJSONObject("scoresMap");
                Map scMap2 = new LinkedHashMap();
                if (scObj != null && (itKeys2 = scObj.keys()) != null) {
                    while (itKeys2.hasNext()) {
                        String str3 = (String) itKeys2.next();
                        scMap2.put(str3, Integer.valueOf(scObj.optInt(str3)));
                        scObj = scObj;
                    }
                }
                JSONObject remObj = obj.optJSONObject("remarksMap");
                Map remMap = new LinkedHashMap();
                if (remObj == null || (itKeys = remObj.keys()) == null) {
                    scMap = scMap2;
                    key = key2;
                } else {
                    while (itKeys.hasNext()) {
                        String str4 = (String) itKeys.next();
                        remMap.put(str4, remObj.optString(str4));
                        scMap2 = scMap2;
                        key2 = key2;
                    }
                    scMap = scMap2;
                    key = key2;
                }
                String strOptString = obj.optString("id");
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                String strOptString2 = obj.optString("testId");
                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                String strOptString3 = obj.optString("testTitle");
                Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
                String strOptString4 = obj.optString(str);
                Intrinsics.checkNotNullExpressionValue(strOptString4, "optString(...)");
                String strOptString5 = obj.optString("studentUid");
                Intrinsics.checkNotNullExpressionValue(strOptString5, "optString(...)");
                String strOptString6 = obj.optString("studentName");
                Intrinsics.checkNotNullExpressionValue(strOptString6, "optString(...)");
                String strOptString7 = obj.optString("studentEmail");
                Intrinsics.checkNotNullExpressionValue(strOptString7, "optString(...)");
                int iOptInt = obj.optInt("autoMcqScore");
                int iOptInt2 = obj.optInt("totalScore");
                int iOptInt3 = obj.optInt("maxMarks");
                String str5 = str;
                double dOptDouble = obj.optDouble("percentage", 0.0d);
                String strOptString8 = obj.optString(NotificationCompat.CATEGORY_STATUS, "Pending Evaluation");
                Intrinsics.checkNotNullExpressionValue(strOptString8, "optString(...)");
                list.add(new StudentSubmission(strOptString, strOptString2, strOptString3, strOptString4, strOptString5, strOptString6, strOptString7, ansMap, scMap, remMap, iOptInt, iOptInt2, iOptInt3, dOptDouble, strOptString8, obj.optBoolean("isInterrupted", false), obj.optBoolean("canRetake", false), obj.optLong("submittedAt"), obj.optLong("evaluatedAt")));
                i++;
                key2 = key;
                str = str5;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final List<BatchLeaderboardEntry> computeLeaderboard(List<StudentSubmission> submissions) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(submissions, "submissions");
        Map studentMap = new LinkedHashMap();
        for (StudentSubmission studentSubmission : submissions) {
            if (!StringsKt.isBlank(studentSubmission.getStudentUid())) {
                String studentUid = studentSubmission.getStudentUid();
                Object obj = studentMap.get(studentUid);
                if (obj == null) {
                    arrayList = new ArrayList();
                    studentMap.put(studentUid, arrayList);
                } else {
                    arrayList = obj;
                }
                ((List) arrayList).add(studentSubmission);
            }
        }
        Collection arrayList2 = new ArrayList(studentMap.size());
        for (Map.Entry entry : studentMap.entrySet()) {
            String str = (String) entry.getKey();
            List<StudentSubmission> list = (List) entry.getValue();
            StudentSubmission studentSubmission2 = (StudentSubmission) CollectionsKt.first(list);
            int size = list.size();
            Iterator it = list.iterator();
            int totalScore = 0;
            while (it.hasNext()) {
                totalScore += ((StudentSubmission) it.next()).getTotalScore();
            }
            int i = totalScore;
            int maxMarks = 0;
            for (StudentSubmission studentSubmission3 : list) {
                maxMarks += studentSubmission3.getMaxMarks() > 0 ? studentSubmission3.getMaxMarks() : 100;
            }
            int i2 = maxMarks;
            double d = i2 > 0 ? (((double) i) / ((double) i2)) * 100.0d : 0.0d;
            String studentName = studentSubmission2.getStudentName();
            if (StringsKt.isBlank(studentName)) {
                studentName = "Student";
            }
            arrayList2.add(new BatchLeaderboardEntry(str, studentName, studentSubmission2.getStudentEmail(), studentSubmission2.getBatchTitle(), size, i, i2, d, 0, 256, null));
            studentMap = studentMap;
        }
        List<BatchLeaderboardEntry> mutableList = CollectionsKt.toMutableList((Collection) CollectionsKt.sortedWith((List) arrayList2, new Comparator() { // from class: com.example.sushikhasacademyapp.data.LocalTestManager$computeLeaderboard$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((BatchLeaderboardEntry) t2).getTotalScore()), Integer.valueOf(((BatchLeaderboardEntry) t).getTotalScore()));
            }
        }));
        int i3 = 0;
        for (Object obj2 : mutableList) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((BatchLeaderboardEntry) obj2).setRank(i3 + 1);
            i3 = i4;
        }
        return mutableList;
    }
}
