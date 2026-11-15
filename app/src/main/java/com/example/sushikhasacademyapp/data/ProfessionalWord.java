package com.example.sushikhasacademyapp.data;

import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DictionaryData.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/example/sushikhasacademyapp/data/ProfessionalWord;", "", "id", "", "word", "", "pronunciation", "meaning", "example", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getWord", "()Ljava/lang/String;", "getPronunciation", "getMeaning", "getExample", "component1", "component2", "component3", "component4", "component5", "copy", MetadataValidation.EQUALS, "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ProfessionalWord {
    public static final int $stable = 0;
    private final String example;
    private final int id;
    private final String meaning;
    private final String pronunciation;
    private final String word;

    public static /* synthetic */ ProfessionalWord copy$default(ProfessionalWord professionalWord, int i, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = professionalWord.id;
        }
        if ((i2 & 2) != 0) {
            str = professionalWord.word;
        }
        if ((i2 & 4) != 0) {
            str2 = professionalWord.pronunciation;
        }
        if ((i2 & 8) != 0) {
            str3 = professionalWord.meaning;
        }
        if ((i2 & 16) != 0) {
            str4 = professionalWord.example;
        }
        String str5 = str4;
        String str6 = str2;
        return professionalWord.copy(i, str, str6, str3, str5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getWord() {
        return this.word;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPronunciation() {
        return this.pronunciation;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getMeaning() {
        return this.meaning;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getExample() {
        return this.example;
    }

    public final ProfessionalWord copy(int id, String word, String pronunciation, String meaning, String example) {
        Intrinsics.checkNotNullParameter(word, "word");
        Intrinsics.checkNotNullParameter(pronunciation, "pronunciation");
        Intrinsics.checkNotNullParameter(meaning, "meaning");
        Intrinsics.checkNotNullParameter(example, "example");
        return new ProfessionalWord(id, word, pronunciation, meaning, example);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProfessionalWord)) {
            return false;
        }
        ProfessionalWord professionalWord = (ProfessionalWord) other;
        return this.id == professionalWord.id && Intrinsics.areEqual(this.word, professionalWord.word) && Intrinsics.areEqual(this.pronunciation, professionalWord.pronunciation) && Intrinsics.areEqual(this.meaning, professionalWord.meaning) && Intrinsics.areEqual(this.example, professionalWord.example);
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.id) * 31) + this.word.hashCode()) * 31) + this.pronunciation.hashCode()) * 31) + this.meaning.hashCode()) * 31) + this.example.hashCode();
    }

    public String toString() {
        return "ProfessionalWord(id=" + this.id + ", word=" + this.word + ", pronunciation=" + this.pronunciation + ", meaning=" + this.meaning + ", example=" + this.example + ")";
    }

    public ProfessionalWord(int id, String word, String pronunciation, String meaning, String example) {
        Intrinsics.checkNotNullParameter(word, "word");
        Intrinsics.checkNotNullParameter(pronunciation, "pronunciation");
        Intrinsics.checkNotNullParameter(meaning, "meaning");
        Intrinsics.checkNotNullParameter(example, "example");
        this.id = id;
        this.word = word;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
        this.example = example;
    }

    public final int getId() {
        return this.id;
    }

    public final String getWord() {
        return this.word;
    }

    public final String getPronunciation() {
        return this.pronunciation;
    }

    public final String getMeaning() {
        return this.meaning;
    }

    public final String getExample() {
        return this.example;
    }
}
