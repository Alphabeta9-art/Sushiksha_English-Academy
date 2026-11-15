package com.example.sushikhasacademyapp.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.sushikhasacademyapp.BatchDetailActivity;
import com.example.sushikhasacademyapp.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BatchNotificationHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ2\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/example/sushikhasacademyapp/utils/BatchNotificationHelper;", "", "<init>", "()V", "CHANNEL_ID", "", "CHANNEL_NAME", "CHANNEL_DESC", "createNotificationChannel", "", "context", "Landroid/content/Context;", "showBatchAnnouncementNotification", "batchTitle", "noticeTitle", "noticeMessage", "isAdmin", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BatchNotificationHelper {
    public static final int $stable = 0;
    private static final String CHANNEL_DESC = "Notifications for new notices in your enrolled batches";
    private static final String CHANNEL_ID = "batch_announcements_channel";
    private static final String CHANNEL_NAME = "Batch Announcements & Notices";
    public static final BatchNotificationHelper INSTANCE = new BatchNotificationHelper();

    private BatchNotificationHelper() {
    }

    public final void createNotificationChannel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, 4);
            channel.setDescription(CHANNEL_DESC);
            channel.enableVibration(true);
            Object systemService = context.getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            NotificationManager notificationManager = (NotificationManager) systemService;
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static /* synthetic */ void showBatchAnnouncementNotification$default(BatchNotificationHelper batchNotificationHelper, Context context, String str, String str2, String str3, boolean z, int i, Object obj) {
        boolean z2;
        if ((i & 16) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        batchNotificationHelper.showBatchAnnouncementNotification(context, str, str2, str3, z2);
    }

    public final void showBatchAnnouncementNotification(Context context, String batchTitle, String noticeTitle, String noticeMessage, boolean isAdmin) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        Intrinsics.checkNotNullParameter(noticeTitle, "noticeTitle");
        Intrinsics.checkNotNullParameter(noticeMessage, "noticeMessage");
        try {
            createNotificationChannel(context);
            if (Build.VERSION.SDK_INT >= 33 && ActivityCompat.checkSelfPermission(context, "android.permission.POST_NOTIFICATIONS") != 0) {
                return;
            }
            Intent intent = new Intent(context, (Class<?>) BatchDetailActivity.class);
            intent.putExtra("BATCH_TITLE", batchTitle);
            intent.putExtra("IS_ADMIN", isAdmin);
            intent.setFlags(335544320);
            int notificationId = (int) (System.currentTimeMillis() % 10000);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, notificationId, intent, 201326592);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.mipmap.c0fc0e51858631fdfa94da6791808372).setContentTitle("📢 Notice: " + noticeTitle).setContentText("[" + batchTitle + "] " + noticeMessage).setStyle(new NotificationCompat.BigTextStyle().bigText("[" + batchTitle + "]\n" + noticeMessage)).setPriority(1).setAutoCancel(true).setContentIntent(pendingIntent);
            Intrinsics.checkNotNullExpressionValue(builder, "setContentIntent(...)");
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            Intrinsics.checkNotNullExpressionValue(notificationManager, "from(...)");
            notificationManager.notify(notificationId, builder.build());
        } catch (Exception e) {
        }
    }
}
