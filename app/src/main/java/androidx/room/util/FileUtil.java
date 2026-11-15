package androidx.room.util;

import android.os.Build;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/* JADX INFO: loaded from: classes11.dex */
public class FileUtil {
    public static void copy(ReadableByteChannel input, FileChannel output) throws Throwable {
        ReadableByteChannel input2;
        FileChannel output2;
        Throwable th;
        try {
            int i = Build.VERSION.SDK_INT;
            input2 = input;
            output2 = output;
            try {
                output2.transferFrom(input2, 0L, Long.MAX_VALUE);
                output2.force(false);
                input2.close();
                output2.close();
            } catch (Throwable th2) {
                th = th2;
                input2.close();
                output2.close();
                throw th;
            }
        } catch (Throwable th3) {
            input2 = input;
            output2 = output;
            th = th3;
        }
    }

    private FileUtil() {
    }
}
