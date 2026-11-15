package androidx.core.os;

import android.os.Build;
import android.os.Message;

/* JADX INFO: loaded from: classes10.dex */
public final class MessageCompat {
    private static boolean sTrySetAsynchronous = true;
    private static boolean sTryIsAsynchronous = true;

    public static void setAsynchronous(Message message, boolean async) {
        int i = Build.VERSION.SDK_INT;
        Api22Impl.setAsynchronous(message, async);
    }

    public static boolean isAsynchronous(Message message) {
        int i = Build.VERSION.SDK_INT;
        return Api22Impl.isAsynchronous(message);
    }

    private MessageCompat() {
    }

    static class Api22Impl {
        private Api22Impl() {
        }

        static boolean isAsynchronous(Message message) {
            return message.isAsynchronous();
        }

        static void setAsynchronous(Message message, boolean async) {
            message.setAsynchronous(async);
        }
    }
}
