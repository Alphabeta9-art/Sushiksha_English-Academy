package androidx.work.impl.model;

import android.net.Uri;
import android.os.Build;
import androidx.work.BackoffPolicy;
import androidx.work.ContentUriTriggers;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: classes11.dex */
public class WorkTypeConverters {

    public interface BackoffPolicyIds {
        public static final int EXPONENTIAL = 0;
        public static final int LINEAR = 1;
    }

    public interface NetworkTypeIds {
        public static final int CONNECTED = 1;
        public static final int METERED = 4;
        public static final int NOT_REQUIRED = 0;
        public static final int NOT_ROAMING = 3;
        public static final int TEMPORARILY_UNMETERED = 5;
        public static final int UNMETERED = 2;
    }

    public interface OutOfPolicyIds {
        public static final int DROP_WORK_REQUEST = 1;
        public static final int RUN_AS_NON_EXPEDITED_WORK_REQUEST = 0;
    }

    public interface StateIds {
        public static final int BLOCKED = 4;
        public static final int CANCELLED = 5;
        public static final String COMPLETED_STATES = "(2, 3, 5)";
        public static final int ENQUEUED = 0;
        public static final int FAILED = 3;
        public static final int RUNNING = 1;
        public static final int SUCCEEDED = 2;
    }

    public static int stateToInt(WorkInfo.State state) {
        switch (state) {
            case ENQUEUED:
                return 0;
            case RUNNING:
                return 1;
            case SUCCEEDED:
                return 2;
            case FAILED:
                return 3;
            case BLOCKED:
                return 4;
            case CANCELLED:
                return 5;
            default:
                throw new IllegalArgumentException("Could not convert " + state + " to int");
        }
    }

    public static WorkInfo.State intToState(int value) {
        switch (value) {
            case 0:
                return WorkInfo.State.ENQUEUED;
            case 1:
                return WorkInfo.State.RUNNING;
            case 2:
                return WorkInfo.State.SUCCEEDED;
            case 3:
                return WorkInfo.State.FAILED;
            case 4:
                return WorkInfo.State.BLOCKED;
            case 5:
                return WorkInfo.State.CANCELLED;
            default:
                throw new IllegalArgumentException("Could not convert " + value + " to State");
        }
    }

    public static int backoffPolicyToInt(BackoffPolicy backoffPolicy) {
        switch (backoffPolicy) {
            case EXPONENTIAL:
                return 0;
            case LINEAR:
                return 1;
            default:
                throw new IllegalArgumentException("Could not convert " + backoffPolicy + " to int");
        }
    }

    public static BackoffPolicy intToBackoffPolicy(int value) {
        switch (value) {
            case 0:
                return BackoffPolicy.EXPONENTIAL;
            case 1:
                return BackoffPolicy.LINEAR;
            default:
                throw new IllegalArgumentException("Could not convert " + value + " to BackoffPolicy");
        }
    }

    public static int networkTypeToInt(NetworkType networkType) {
        switch (networkType) {
            case NOT_REQUIRED:
                return 0;
            case CONNECTED:
                return 1;
            case UNMETERED:
                return 2;
            case NOT_ROAMING:
                return 3;
            case METERED:
                return 4;
            default:
                if (Build.VERSION.SDK_INT >= 30 && networkType == NetworkType.TEMPORARILY_UNMETERED) {
                    return 5;
                }
                throw new IllegalArgumentException("Could not convert " + networkType + " to int");
        }
    }

    public static NetworkType intToNetworkType(int value) {
        switch (value) {
            case 0:
                return NetworkType.NOT_REQUIRED;
            case 1:
                return NetworkType.CONNECTED;
            case 2:
                return NetworkType.UNMETERED;
            case 3:
                return NetworkType.NOT_ROAMING;
            case 4:
                return NetworkType.METERED;
            default:
                if (Build.VERSION.SDK_INT >= 30 && value == 5) {
                    return NetworkType.TEMPORARILY_UNMETERED;
                }
                throw new IllegalArgumentException("Could not convert " + value + " to NetworkType");
        }
    }

    public static int outOfQuotaPolicyToInt(OutOfQuotaPolicy policy) {
        switch (policy) {
            case RUN_AS_NON_EXPEDITED_WORK_REQUEST:
                return 0;
            case DROP_WORK_REQUEST:
                return 1;
            default:
                throw new IllegalArgumentException("Could not convert " + policy + " to int");
        }
    }

    public static OutOfQuotaPolicy intToOutOfQuotaPolicy(int value) {
        switch (value) {
            case 0:
                return OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST;
            case 1:
                return OutOfQuotaPolicy.DROP_WORK_REQUEST;
            default:
                throw new IllegalArgumentException("Could not convert " + value + " to OutOfQuotaPolicy");
        }
    }

    public static byte[] contentUriTriggersToByteArray(ContentUriTriggers triggers) {
        if (triggers.size() == 0) {
            return null;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            try {
                try {
                    objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeInt(triggers.size());
                    for (ContentUriTriggers.Trigger trigger : triggers.getTriggers()) {
                        objectOutputStream.writeUTF(trigger.getUri().toString());
                        objectOutputStream.writeBoolean(trigger.shouldTriggerForDescendants());
                    }
                } finally {
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        outputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e3.printStackTrace();
                if (objectOutputStream != null) {
                }
                return outputStream.toByteArray();
            }
            objectOutputStream.close();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    public static ContentUriTriggers byteArrayToContentUriTriggers(byte[] bytes) {
        ContentUriTriggers triggers = new ContentUriTriggers();
        if (bytes == null) {
            return triggers;
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(inputStream);
                for (int i = objectInputStream.readInt(); i > 0; i--) {
                    Uri uri = Uri.parse(objectInputStream.readUTF());
                    boolean triggersForDescendants = objectInputStream.readBoolean();
                    triggers.add(uri, triggersForDescendants);
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (objectInputStream != null) {
                }
                return triggers;
            }
            try {
                objectInputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return triggers;
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    private WorkTypeConverters() {
    }
}
