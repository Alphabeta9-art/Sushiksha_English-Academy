package androidx.work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public final class OverwritingInputMerger extends InputMerger {
    @Override // androidx.work.InputMerger
    public Data merge(List<Data> inputs) {
        Data.Builder output = new Data.Builder();
        Map<String, Object> mergedValues = new HashMap<>();
        for (Data input : inputs) {
            mergedValues.putAll(input.getKeyValueMap());
        }
        output.putAll(mergedValues);
        return output.build();
    }
}
