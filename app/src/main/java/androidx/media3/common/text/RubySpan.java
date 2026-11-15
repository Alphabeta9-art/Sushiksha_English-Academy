package androidx.media3.common.text;

/* JADX INFO: loaded from: classes11.dex */
public final class RubySpan implements LanguageFeatureSpan {
    public final int position;
    public final String rubyText;

    public RubySpan(String rubyText, int position) {
        this.rubyText = rubyText;
        this.position = position;
    }
}
