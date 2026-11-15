package com.example.sushikhasacademyapp.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: TextToSpeechHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/example/sushikhasacademyapp/utils/TextToSpeechHelper;", "", "speak", "", "word", "", "speakAndToast", "pronunciation", "shutdown", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface TextToSpeechHelper {
    void shutdown();

    void speak(String word);

    void speakAndToast(String word, String pronunciation);
}
