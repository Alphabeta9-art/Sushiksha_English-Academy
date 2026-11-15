package com.example.sushikhasacademyapp.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextToSpeechHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/example/sushikhasacademyapp/utils/AndroidTextToSpeechHelper;", "Lcom/example/sushikhasacademyapp/utils/TextToSpeechHelper;", "Landroid/speech/tts/TextToSpeech$OnInitListener;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "tts", "Landroid/speech/tts/TextToSpeech;", "isReady", "", "onInit", "", NotificationCompat.CATEGORY_STATUS, "", "speak", "word", "", "speakAndToast", "pronunciation", "shutdown", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AndroidTextToSpeechHelper implements TextToSpeechHelper, TextToSpeech.OnInitListener {
    public static final int $stable = 8;
    private final Context context;
    private boolean isReady;
    private TextToSpeech tts;

    public AndroidTextToSpeechHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.tts = new TextToSpeech(this.context, this);
    }

    @Override // android.speech.tts.TextToSpeech.OnInitListener
    public void onInit(int status) {
        if (status == 0) {
            TextToSpeech textToSpeech = this.tts;
            Integer result = textToSpeech != null ? Integer.valueOf(textToSpeech.setLanguage(Locale.US)) : null;
            if ((result != null && result.intValue() == -1) || (result != null && result.intValue() == -2)) {
                Log.e("TTS", "Language not supported or missing data");
                this.isReady = false;
                return;
            } else {
                this.isReady = true;
                Log.d("TTS", "TextToSpeech initialized successfully");
                return;
            }
        }
        Log.e("TTS", "Initialization failed with status: " + status);
        this.isReady = false;
    }

    @Override // com.example.sushikhasacademyapp.utils.TextToSpeechHelper
    public void speak(final String word) {
        Intrinsics.checkNotNullParameter(word, "word");
        if (this.isReady && this.tts != null) {
            Log.d("TTS", "Speaking: " + word);
            TextToSpeech textToSpeech = this.tts;
            if (textToSpeech != null) {
                textToSpeech.speak(word, 0, null, "TTS_ID_" + word);
                return;
            }
            return;
        }
        Log.w("TTS", "TTS not ready yet, re-initializing...");
        this.tts = new TextToSpeech(this.context, new TextToSpeech.OnInitListener() { // from class: com.example.sushikhasacademyapp.utils.AndroidTextToSpeechHelper$$ExternalSyntheticLambda0
            @Override // android.speech.tts.TextToSpeech.OnInitListener
            public final void onInit(int i) {
                AndroidTextToSpeechHelper.speak$lambda$0(this.f$0, word, i);
            }
        });
    }

    static final void speak$lambda$0(AndroidTextToSpeechHelper this$0, String $word, int status) {
        if (status == 0) {
            TextToSpeech textToSpeech = this$0.tts;
            if (textToSpeech != null) {
                textToSpeech.setLanguage(Locale.US);
            }
            this$0.isReady = true;
            TextToSpeech textToSpeech2 = this$0.tts;
            if (textToSpeech2 != null) {
                textToSpeech2.speak($word, 0, null, "TTS_ID_" + $word);
            }
        }
    }

    @Override // com.example.sushikhasacademyapp.utils.TextToSpeechHelper
    public void speakAndToast(String word, String pronunciation) {
        Intrinsics.checkNotNullParameter(word, "word");
        Intrinsics.checkNotNullParameter(pronunciation, "pronunciation");
        speak(word);
    }

    @Override // com.example.sushikhasacademyapp.utils.TextToSpeechHelper
    public void shutdown() {
        try {
            TextToSpeech textToSpeech = this.tts;
            if (textToSpeech != null) {
                textToSpeech.stop();
            }
            TextToSpeech textToSpeech2 = this.tts;
            if (textToSpeech2 != null) {
                textToSpeech2.shutdown();
            }
        } catch (Exception e) {
        }
        this.tts = null;
        this.isReady = false;
    }
}
