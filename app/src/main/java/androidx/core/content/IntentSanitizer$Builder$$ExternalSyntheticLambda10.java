package androidx.core.content;

import androidx.core.util.Predicate;
import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import com.cloudinary.metadata.MetadataValidation;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes10.dex */
@LambdaMethod(holder = "Ljava/lang/String;", method = MetadataValidation.EQUALS, proto = "(Ljava/lang/Object;)Z")
@SynthesizedClassV2(apiLevel = -2, kind = 28, versionHash = "f3a348d7d571cfc3f89005b557595f12bc85b7451dde524d9d228b1d59adcb6b")
public final /* synthetic */ class IntentSanitizer$Builder$$ExternalSyntheticLambda10 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ IntentSanitizer$Builder$$ExternalSyntheticLambda10(String str) {
        this.f$0 = str;
    }

    @Override // androidx.core.util.Predicate
    public final boolean test(Object obj) {
        return this.f$0.equals((String) obj);
    }
}
