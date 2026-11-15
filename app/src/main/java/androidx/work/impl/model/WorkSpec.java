package androidx.work.impl.model;

import androidx.arch.core.util.Function;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes11.dex */
public final class WorkSpec {
    public static final long SCHEDULE_NOT_REQUESTED_YET = -1;
    private static final String TAG = Logger.tagWithPrefix("WorkSpec");
    public static final Function<List<WorkInfoPojo>, List<WorkInfo>> WORK_INFO_MAPPER = new Function<List<WorkInfoPojo>, List<WorkInfo>>() { // from class: androidx.work.impl.model.WorkSpec.1
        @Override // androidx.arch.core.util.Function
        public List<WorkInfo> apply(List<WorkInfoPojo> input) {
            if (input == null) {
                return null;
            }
            List<WorkInfo> output = new ArrayList<>(input.size());
            for (WorkInfoPojo in : input) {
                output.add(in.toWorkInfo());
            }
            return output;
        }
    };
    public long backoffDelayDuration;
    public BackoffPolicy backoffPolicy;
    public Constraints constraints;
    public boolean expedited;
    public long flexDuration;
    public String id;
    public long initialDelay;
    public Data input;
    public String inputMergerClassName;
    public long intervalDuration;
    public long minimumRetentionDuration;
    public OutOfQuotaPolicy outOfQuotaPolicy;
    public Data output;
    public long periodStartTime;
    public int runAttemptCount;
    public long scheduleRequestedAt;
    public WorkInfo.State state;
    public String workerClassName;

    public WorkSpec(String id, String workerClassName) {
        this.state = WorkInfo.State.ENQUEUED;
        this.input = Data.EMPTY;
        this.output = Data.EMPTY;
        this.constraints = Constraints.NONE;
        this.backoffPolicy = BackoffPolicy.EXPONENTIAL;
        this.backoffDelayDuration = 30000L;
        this.scheduleRequestedAt = -1L;
        this.outOfQuotaPolicy = OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST;
        this.id = id;
        this.workerClassName = workerClassName;
    }

    public WorkSpec(WorkSpec other) {
        this.state = WorkInfo.State.ENQUEUED;
        this.input = Data.EMPTY;
        this.output = Data.EMPTY;
        this.constraints = Constraints.NONE;
        this.backoffPolicy = BackoffPolicy.EXPONENTIAL;
        this.backoffDelayDuration = 30000L;
        this.scheduleRequestedAt = -1L;
        this.outOfQuotaPolicy = OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST;
        this.id = other.id;
        this.workerClassName = other.workerClassName;
        this.state = other.state;
        this.inputMergerClassName = other.inputMergerClassName;
        this.input = new Data(other.input);
        this.output = new Data(other.output);
        this.initialDelay = other.initialDelay;
        this.intervalDuration = other.intervalDuration;
        this.flexDuration = other.flexDuration;
        this.constraints = new Constraints(other.constraints);
        this.runAttemptCount = other.runAttemptCount;
        this.backoffPolicy = other.backoffPolicy;
        this.backoffDelayDuration = other.backoffDelayDuration;
        this.periodStartTime = other.periodStartTime;
        this.minimumRetentionDuration = other.minimumRetentionDuration;
        this.scheduleRequestedAt = other.scheduleRequestedAt;
        this.expedited = other.expedited;
        this.outOfQuotaPolicy = other.outOfQuotaPolicy;
    }

    public void setBackoffDelayDuration(long backoffDelayDuration) {
        if (backoffDelayDuration > WorkRequest.MAX_BACKOFF_MILLIS) {
            Logger.get().warning(TAG, "Backoff delay duration exceeds maximum value", new Throwable[0]);
            backoffDelayDuration = WorkRequest.MAX_BACKOFF_MILLIS;
        }
        if (backoffDelayDuration < 10000) {
            Logger.get().warning(TAG, "Backoff delay duration less than minimum value", new Throwable[0]);
            backoffDelayDuration = 10000;
        }
        this.backoffDelayDuration = backoffDelayDuration;
    }

    public boolean isPeriodic() {
        return this.intervalDuration != 0;
    }

    public boolean isBackedOff() {
        return this.state == WorkInfo.State.ENQUEUED && this.runAttemptCount > 0;
    }

    public void setPeriodic(long intervalDuration) {
        if (intervalDuration < PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) {
            Logger.get().warning(TAG, String.format("Interval duration lesser than minimum allowed value; Changed to %s", Long.valueOf(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS)), new Throwable[0]);
            intervalDuration = PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;
        }
        setPeriodic(intervalDuration, intervalDuration);
    }

    public void setPeriodic(long intervalDuration, long flexDuration) {
        if (intervalDuration < PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) {
            Logger.get().warning(TAG, String.format("Interval duration lesser than minimum allowed value; Changed to %s", Long.valueOf(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS)), new Throwable[0]);
            intervalDuration = PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;
        }
        if (flexDuration < 300000) {
            Logger.get().warning(TAG, String.format("Flex duration lesser than minimum allowed value; Changed to %s", 300000L), new Throwable[0]);
            flexDuration = 300000;
        }
        if (flexDuration > intervalDuration) {
            Logger.get().warning(TAG, String.format("Flex duration greater than interval duration; Changed to %s", Long.valueOf(intervalDuration)), new Throwable[0]);
            flexDuration = intervalDuration;
        }
        this.intervalDuration = intervalDuration;
        this.flexDuration = flexDuration;
    }

    public long calculateNextRunTime() {
        boolean isLinearBackoff;
        long offset;
        if (isBackedOff()) {
            isLinearBackoff = this.backoffPolicy == BackoffPolicy.LINEAR;
            long delay = isLinearBackoff ? this.backoffDelayDuration * ((long) this.runAttemptCount) : (long) Math.scalb(this.backoffDelayDuration, this.runAttemptCount - 1);
            return this.periodStartTime + Math.min(WorkRequest.MAX_BACKOFF_MILLIS, delay);
        }
        if (isPeriodic()) {
            long now = System.currentTimeMillis();
            long start = this.periodStartTime == 0 ? this.initialDelay + now : this.periodStartTime;
            isLinearBackoff = this.flexDuration != this.intervalDuration;
            if (isLinearBackoff) {
                offset = this.periodStartTime == 0 ? this.flexDuration * (-1) : 0L;
                return this.intervalDuration + start + offset;
            }
            offset = this.periodStartTime != 0 ? this.intervalDuration : 0L;
            return start + offset;
        }
        return this.initialDelay + (this.periodStartTime == 0 ? System.currentTimeMillis() : this.periodStartTime);
    }

    public boolean hasConstraints() {
        return !Constraints.NONE.equals(this.constraints);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkSpec workSpec = (WorkSpec) o;
        if (this.initialDelay != workSpec.initialDelay || this.intervalDuration != workSpec.intervalDuration || this.flexDuration != workSpec.flexDuration || this.runAttemptCount != workSpec.runAttemptCount || this.backoffDelayDuration != workSpec.backoffDelayDuration || this.periodStartTime != workSpec.periodStartTime || this.minimumRetentionDuration != workSpec.minimumRetentionDuration || this.scheduleRequestedAt != workSpec.scheduleRequestedAt || this.expedited != workSpec.expedited || !this.id.equals(workSpec.id) || this.state != workSpec.state || !this.workerClassName.equals(workSpec.workerClassName)) {
            return false;
        }
        if (this.inputMergerClassName == null ? workSpec.inputMergerClassName != null : !this.inputMergerClassName.equals(workSpec.inputMergerClassName)) {
            return false;
        }
        if (this.input.equals(workSpec.input) && this.output.equals(workSpec.output) && this.constraints.equals(workSpec.constraints) && this.backoffPolicy == workSpec.backoffPolicy && this.outOfQuotaPolicy == workSpec.outOfQuotaPolicy) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.state.hashCode()) * 31) + this.workerClassName.hashCode()) * 31) + (this.inputMergerClassName != null ? this.inputMergerClassName.hashCode() : 0)) * 31) + this.input.hashCode()) * 31) + this.output.hashCode()) * 31) + ((int) (this.initialDelay ^ (this.initialDelay >>> 32)))) * 31) + ((int) (this.intervalDuration ^ (this.intervalDuration >>> 32)))) * 31) + ((int) (this.flexDuration ^ (this.flexDuration >>> 32)))) * 31) + this.constraints.hashCode()) * 31) + this.runAttemptCount) * 31) + this.backoffPolicy.hashCode()) * 31) + ((int) (this.backoffDelayDuration ^ (this.backoffDelayDuration >>> 32)))) * 31) + ((int) (this.periodStartTime ^ (this.periodStartTime >>> 32)))) * 31) + ((int) (this.minimumRetentionDuration ^ (this.minimumRetentionDuration >>> 32)))) * 31) + ((int) (this.scheduleRequestedAt ^ (this.scheduleRequestedAt >>> 32)))) * 31) + (this.expedited ? 1 : 0)) * 31) + this.outOfQuotaPolicy.hashCode();
    }

    public String toString() {
        return "{WorkSpec: " + this.id + "}";
    }

    public static class IdAndState {
        public String id;
        public WorkInfo.State state;

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof IdAndState)) {
                return false;
            }
            IdAndState that = (IdAndState) o;
            if (this.state != that.state) {
                return false;
            }
            return this.id.equals(that.id);
        }

        public int hashCode() {
            int result = this.id.hashCode();
            return (result * 31) + this.state.hashCode();
        }
    }

    public static class WorkInfoPojo {
        public String id;
        public Data output;
        public List<Data> progress;
        public int runAttemptCount;
        public WorkInfo.State state;
        public List<String> tags;

        public WorkInfo toWorkInfo() {
            Data data;
            if (this.progress != null && !this.progress.isEmpty()) {
                data = this.progress.get(0);
            } else {
                data = Data.EMPTY;
            }
            Data progress = data;
            return new WorkInfo(UUID.fromString(this.id), this.state, this.output, this.tags, progress, this.runAttemptCount);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof WorkInfoPojo)) {
                return false;
            }
            WorkInfoPojo that = (WorkInfoPojo) o;
            if (this.runAttemptCount != that.runAttemptCount) {
                return false;
            }
            if (this.id == null ? that.id != null : !this.id.equals(that.id)) {
                return false;
            }
            if (this.state != that.state) {
                return false;
            }
            if (this.output == null ? that.output != null : !this.output.equals(that.output)) {
                return false;
            }
            if (this.tags == null ? that.tags != null : !this.tags.equals(that.tags)) {
                return false;
            }
            if (this.progress != null) {
                return this.progress.equals(that.progress);
            }
            return that.progress == null;
        }

        public int hashCode() {
            int result = this.id != null ? this.id.hashCode() : 0;
            return (((((((((result * 31) + (this.state != null ? this.state.hashCode() : 0)) * 31) + (this.output != null ? this.output.hashCode() : 0)) * 31) + this.runAttemptCount) * 31) + (this.tags != null ? this.tags.hashCode() : 0)) * 31) + (this.progress != null ? this.progress.hashCode() : 0);
        }
    }
}
