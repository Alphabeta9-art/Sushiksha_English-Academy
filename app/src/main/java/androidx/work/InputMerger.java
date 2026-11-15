package androidx.work;

import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public abstract class InputMerger {
    private static final String TAG = Logger.tagWithPrefix("InputMerger");

    public abstract Data merge(List<Data> inputs);

    public static InputMerger fromClassName(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return (InputMerger) clazz.newInstance();
        } catch (Exception e) {
            Logger.get().error(TAG, "Trouble instantiating + " + className, e);
            return null;
        }
    }
}
