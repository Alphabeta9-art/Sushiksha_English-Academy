package com.example.sushikhasacademyapp.data;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* JADX INFO: compiled from: DictionaryData.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u000b\u001a\u00020\u0006J\u0006\u0010\f\u001a\u00020\u0006R!\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/example/sushikhasacademyapp/data/DictionaryData;", "", "<init>", "()V", "words", "", "Lcom/example/sushikhasacademyapp/data/ProfessionalWord;", "getWords", "()Ljava/util/List;", "words$delegate", "Lkotlin/Lazy;", "getRandomWord", "getWordForToday", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DictionaryData {
    public static final DictionaryData INSTANCE = new DictionaryData();

    /* JADX INFO: renamed from: words$delegate, reason: from kotlin metadata */
    private static final Lazy words = LazyKt.lazy(new Function0() { // from class: com.example.sushikhasacademyapp.data.DictionaryData$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return DictionaryData.words_delegate$lambda$0();
        }
    });
    public static final int $stable = 8;

    private DictionaryData() {
    }

    public final List<ProfessionalWord> getWords() {
        return (List) words.getValue();
    }

    static final List words_delegate$lambda$0() {
        String wordName;
        List baseWords = CollectionsKt.listOf((Object[]) new ProfessionalWord[]{new ProfessionalWord(1, "Articulate", "/ɑːrˈtɪk.jə.lət/", "Able to express thoughts and ideas clearly and effectively.", "Suman Sir helped us articulate our opinions confidently in meetings."), new ProfessionalWord(2, "Eloquence", "/ˈel.ə.kwəns/", "Fluent or persuasive speaking or writing.", "Her eloquence during the presentation impressed the board members."), new ProfessionalWord(3, "Meticulous", "/məˈtɪk.jə.ləs/", "Showing great attention to detail; careful and precise.", "He is meticulous in reviewing reports before submitting them."), new ProfessionalWord(4, "Pragmatic", "/præɡˈmæt.ɪk/", "Dealing with things sensibly and realistically based on practical considerations.", "We need a pragmatic approach to resolve this communication issue."), new ProfessionalWord(5, "Resilient", "/rɪˈzɪl.jənt/", "Able to withstand or recover quickly from difficult conditions.", "Resilient professionals adapt swiftly to workplace changes."), new ProfessionalWord(6, "Cognizant", "/ˈkɒɡ.nɪ.zənt/", "Having knowledge or being aware of something.", "Be cognizant of cultural differences in international business dealings."), new ProfessionalWord(7, "Perspicacious", "/ˌpɜː.spɪˈkeɪ.ʃəs/", "Having a ready insight into and understanding of things; shrewd.", "His perspicacious analysis identified the core problem immediately."), new ProfessionalWord(8, "Equanimity", "/ˌek.wəˈnɪm.ə.ti/", "Mental calmness, composure, and evenness of temper, especially in difficult situations.", "She handled the crisis with remarkable equanimity."), new ProfessionalWord(9, "Ubiquitous", "/juːˈbɪk.wɪ.təs/", "Present, appearing, or found everywhere.", "Digital communication tools have become ubiquitous in corporate life."), new ProfessionalWord(10, "Fastidious", "/fæsˈtɪd.i.əs/", "Very attentive to and concerned about accuracy and detail.", "Suman Sir is fastidious about correct pronunciation and grammar."), new ProfessionalWord(11, "Tenacious", "/təˈneɪ.ʃəs/", "Tending to keep a firm hold of something; persistent.", "A tenacious attitude is essential for mastering advanced English."), new ProfessionalWord(12, "Veracity", "/vəˈræs.ə.ti/", "Conformity to facts; accuracy and truthfulness.", "The audit verified the veracity of the financial statement."), new ProfessionalWord(13, "Scrupulous", "/ˈskruː.pjə.ləs/", "Diligent, thorough, and extremely attentive to details.", "Scrupulous proofreading prevents embarrassing grammatical errors."), new ProfessionalWord(14, "Erudite", "/ˈer.ʊ.daɪt/", "Having or showing great knowledge or learning.", "The professor delivered an erudite lecture on English literature."), new ProfessionalWord(15, "Alacrity", "/əˈlæk.rə.ti/", "Brisk and cheerful readiness to act.", "She accepted the leadership challenge with great alacrity."), new ProfessionalWord(16, "Quintessential", "/ˌkwɪn.təˈsen.ʃəl/", "Representing the most perfect or typical example of a quality or class.", "Suman Sir is the quintessential English mentor."), new ProfessionalWord(17, "Sagacious", "/səˈɡeɪ.ʃəs/", "Having or showing keen mental discernment and good judgment.", "A sagacious leader foresees potential challenges early."), new ProfessionalWord(18, "Magnanimous", "/mæɡˈnæn.ɪ.məs/", "Generous or forgiving, especially toward a rival or less powerful person.", "He was magnanimous in accepting constructive feedback."), new ProfessionalWord(19, "Ineffable", "/ɪnˈef.ə.bəl/", "Too great or extreme to be expressed or described in words.", "The feeling of speaking fluent English for the first time was ineffable."), new ProfessionalWord(20, "Proactive", "/prəʊˈæk.tɪv/", "Creating or controlling a situation by causing something to happen rather than responding to it.", "Taking a proactive stance in learning vocabulary speeds up fluency."), new ProfessionalWord(21, "Juxtaposition", "/ˌdʒʌk.stə.pəˈzɪʃ.ən/", "The fact of two things being seen or placed close together with contrasting effect.", "The juxtaposition of formal and informal register in business writing."), new ProfessionalWord(22, "Synergy", "/ˈsɪn.ə.dʒi/", "The interaction or cooperation of two or more organizations or substances to produce a combined effect.", "Team synergy leads to extraordinary productivity."), new ProfessionalWord(23, "Catalyst", "/ˈkæt.əl.ɪst/", "A person or thing that precipitates an event or change.", "Effective feedback acts as a catalyst for personal growth."), new ProfessionalWord(24, "Efficacy", "/ˈef.ɪ.kə.si/", "The ability to produce a desired or intended result.", "The efficacy of Suman Ghosh's interactive modules is proven."), new ProfessionalWord(25, "Paradigm", "/ˈpær.ə.daɪm/", "A typical example or pattern of something; a model.", "This course sets a new paradigm in communicative language teaching."), new ProfessionalWord(26, "Diligent", "/ˈdɪl.ɪ.dʒənt/", "Having or showing care and conscientiousness in one's work.", "Diligent practice ensures flawless English fluency."), new ProfessionalWord(27, "Ambiguity", "/ˌæm.bɪˈɡjuː.ə.ti/", "The quality of being open to more than one interpretation.", "Clear communication eliminates ambiguity in corporate emails."), new ProfessionalWord(28, "Comprehensive", "/ˌkɒm.prɪˈhen.sɪv/", "Complete; including all or nearly all elements or aspects.", "Sushiksha Academy provides a comprehensive English curriculum."), new ProfessionalWord(29, "Precedent", "/ˈpres.ɪ.dənt/", "An earlier event or action that is regarded as an example or guide.", "This successful project sets a valuable precedent for the team."), new ProfessionalWord(30, "Sanction", "/ˈsæŋk.ʃən/", "A threatened penalty for disobeying a law or rule, or official permission.", "The board gave its sanction to expand the English academy.")});
        List prefixList = CollectionsKt.listOf((Object[]) new String[]{"Advocate", "Acumen", "Aesthetic", "Ameliorate", "Benchmark", "Brevity", "Candor", "Capacious", "Cognition", "Coherent", "Collator", "Commendable", "Compelling", "Conscientious", "Consensus", "Contextual", "Criterion", "Decisive", "Deference", "Delineate", "Diplomatic", "Discernment", "Discreet", "Disparate", "Empathy", "Empirical", "Endeavor", "Enigmatic", "Exemplary", "Exigent", "Expedite", "Facet", "Fidelity", "Fortitude", "Galvanize", "Harmonious", "Hierarchy", "Holistic", "Imperative", "Incisive", "Indefatigable", "Innovative", "Insightful", "Integrity", "Intuitive", "Lucid", "Malleable", "Methodical", "Nurture", "Objective", "Optimum", "Paramount", "Perceptive", "Plausible", "Polished", "Prerequisite", "Proficient", "Prudent", "Rationale", "Reciprocity", "Refined", "Resolute", "Salient", "Scrutiny", "Stature", "Strategic", "Streamline", "Substantive", "Succinct", "Tactful", "Tolerant", "Transparency", "Unanimous", "Unprecedented", "Validation", "Venerable", "Versatile", "Viable", "Vigilant", "Vindicated", "Wisdom", "Zealous"});
        List adjectives = CollectionsKt.listOf((Object[]) new String[]{"Advanced", "Expert", "Master", "Professional", "Elite", "Prime", "Global", "Supreme", "Premier", "Dynamic", "Fluent", "Classic", "Exquisite", "Exemplary", "Profound"});
        List fullList = new ArrayList();
        fullList.addAll(baseWords);
        int count = baseWords.size() + 1;
        int loopIndex = 0;
        int count2 = count;
        while (count2 <= 1000) {
            String root = (String) prefixList.get(loopIndex % prefixList.size());
            int variantNum = (count2 / prefixList.size()) + 1;
            if (variantNum == 1) {
                wordName = root;
            } else {
                String adj = (String) adjectives.get((variantNum - 2) % adjectives.size());
                wordName = adj + " " + root;
            }
            String lowerCase = root.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String pron = DomExceptionUtils.SEPARATOR + lowerCase + DomExceptionUtils.SEPARATOR;
            String mean = "Professional attribute or action denoting " + root + " in advanced English communication.";
            String ex = "Mastering the term '" + wordName + "' enhances professional vocabulary in formal conversations.";
            fullList.add(new ProfessionalWord(count2, wordName, pron, mean, ex));
            count2++;
            loopIndex++;
        }
        return fullList;
    }

    public final ProfessionalWord getRandomWord() {
        return (ProfessionalWord) CollectionsKt.random(getWords(), Random.INSTANCE);
    }

    public final ProfessionalWord getWordForToday() {
        int dayOfYear = Calendar.getInstance().get(6);
        return getWords().get(dayOfYear % getWords().size());
    }
}
