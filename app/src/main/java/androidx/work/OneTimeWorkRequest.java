package androidx.work;

import android.os.Build;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public final class OneTimeWorkRequest extends WorkRequest {
    public static OneTimeWorkRequest from(Class<? extends ListenableWorker> workerClass) {
        return new Builder(workerClass).build();
    }

    public static List<OneTimeWorkRequest> from(List<Class<? extends ListenableWorker>> workerClasses) {
        List<OneTimeWorkRequest> workList = new ArrayList<>(workerClasses.size());
        for (Class<? extends ListenableWorker> workerClass : workerClasses) {
            workList.add(new Builder(workerClass).build());
        }
        return workList;
    }

    OneTimeWorkRequest(Builder builder) {
        super(builder.mId, builder.mWorkSpec, builder.mTags);
    }

    public static final class Builder extends WorkRequest.Builder<Builder, OneTimeWorkRequest> {
        public Builder(Class<? extends ListenableWorker> workerClass) {
            super(workerClass);
            this.mWorkSpec.inputMergerClassName = OverwritingInputMerger.class.getName();
        }

        public Builder setInputMerger(Class<? extends InputMerger> inputMerger) {
            this.mWorkSpec.inputMergerClassName = inputMerger.getName();
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.work.WorkRequest.Builder
        public OneTimeWorkRequest buildInternal() {
            if (this.mBackoffCriteriaSet) {
                int i = Build.VERSION.SDK_INT;
                if (this.mWorkSpec.constraints.requiresDeviceIdle()) {
                    throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
                }
            }
            return new OneTimeWorkRequest(this);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.work.WorkRequest.Builder
        public Builder getThis() {
            return this;
        }
    }
}
