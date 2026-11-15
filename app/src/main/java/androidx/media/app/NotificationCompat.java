package androidx.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.RemoteViews;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.media.R;

/* JADX INFO: loaded from: classes11.dex */
public class NotificationCompat {
    private NotificationCompat() {
    }

    public static class MediaStyle extends androidx.core.app.NotificationCompat.Style {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int[] mActionsToShowInCompact = null;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        MediaSessionCompat.Token mToken;

        public static MediaSessionCompat.Token getMediaSession(Notification notification) {
            Bundle extras = androidx.core.app.NotificationCompat.getExtras(notification);
            if (extras != null) {
                int i = Build.VERSION.SDK_INT;
                Object tokenInner = extras.getParcelable(androidx.core.app.NotificationCompat.EXTRA_MEDIA_SESSION);
                if (tokenInner != null) {
                    return MediaSessionCompat.Token.fromToken(tokenInner);
                }
                return null;
            }
            return null;
        }

        public MediaStyle() {
        }

        public MediaStyle(androidx.core.app.NotificationCompat.Builder builder) {
            setBuilder(builder);
        }

        public MediaStyle setShowActionsInCompactView(int... actions) {
            this.mActionsToShowInCompact = actions;
            return this;
        }

        public MediaStyle setMediaSession(MediaSessionCompat.Token token) {
            this.mToken = token;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean show) {
            int i = Build.VERSION.SDK_INT;
            return this;
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            this.mCancelButtonIntent = pendingIntent;
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            Api21Impl.setMediaStyle(builder.getBuilder(), Api21Impl.fillInMediaStyle(Api21Impl.createMediaStyle(), this.mActionsToShowInCompact, this.mToken));
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            return null;
        }

        RemoteViews generateContentView() {
            int numActionsInCompact;
            RemoteViews view = applyStandardTemplate(false, getContentViewLayoutResource(), true);
            int numActions = this.mBuilder.mActions.size();
            if (this.mActionsToShowInCompact == null) {
                numActionsInCompact = 0;
            } else {
                numActionsInCompact = Math.min(this.mActionsToShowInCompact.length, 3);
            }
            view.removeAllViews(R.id.media_actions);
            if (numActionsInCompact > 0) {
                for (int i = 0; i < numActionsInCompact; i++) {
                    if (i >= numActions) {
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", Integer.valueOf(i), Integer.valueOf(numActions - 1)));
                    }
                    androidx.core.app.NotificationCompat.Action action = this.mBuilder.mActions.get(this.mActionsToShowInCompact[i]);
                    RemoteViews button = generateMediaActionButton(action);
                    view.addView(R.id.media_actions, button);
                }
            }
            if (!this.mShowCancelButton) {
                view.setViewVisibility(R.id.end_padder, 0);
                view.setViewVisibility(R.id.cancel_action, 8);
            } else {
                view.setViewVisibility(R.id.end_padder, 8);
                view.setViewVisibility(R.id.cancel_action, 0);
                view.setOnClickPendingIntent(R.id.cancel_action, this.mCancelButtonIntent);
                view.setInt(R.id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
            }
            return view;
        }

        private RemoteViews generateMediaActionButton(androidx.core.app.NotificationCompat.Action action) {
            boolean tombstone = action.getActionIntent() == null;
            RemoteViews button = new RemoteViews(this.mBuilder.mContext.getPackageName(), R.layout.notification_media_action);
            button.setImageViewResource(R.id.action0, action.getIcon());
            if (!tombstone) {
                button.setOnClickPendingIntent(R.id.action0, action.getActionIntent());
            }
            int i = Build.VERSION.SDK_INT;
            Api15Impl.setContentDescription(button, R.id.action0, action.getTitle());
            return button;
        }

        int getContentViewLayoutResource() {
            return R.layout.notification_template_media;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            return null;
        }

        RemoteViews generateBigContentView() {
            int actionCount = Math.min(this.mBuilder.mActions.size(), 5);
            RemoteViews big = applyStandardTemplate(false, getBigContentViewLayoutResource(actionCount), false);
            big.removeAllViews(R.id.media_actions);
            if (actionCount > 0) {
                for (int i = 0; i < actionCount; i++) {
                    RemoteViews button = generateMediaActionButton(this.mBuilder.mActions.get(i));
                    big.addView(R.id.media_actions, button);
                }
            }
            if (this.mShowCancelButton) {
                big.setViewVisibility(R.id.cancel_action, 0);
                big.setInt(R.id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
                big.setOnClickPendingIntent(R.id.cancel_action, this.mCancelButtonIntent);
            } else {
                big.setViewVisibility(R.id.cancel_action, 8);
            }
            return big;
        }

        int getBigContentViewLayoutResource(int actionCount) {
            if (actionCount <= 3) {
                return R.layout.notification_template_big_media_narrow;
            }
            return R.layout.notification_template_big_media;
        }
    }

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        @Override // androidx.media.app.NotificationCompat.MediaStyle, androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            Api21Impl.setMediaStyle(builder.getBuilder(), Api21Impl.fillInMediaStyle(Api24Impl.createDecoratedMediaCustomViewStyle(), this.mActionsToShowInCompact, this.mToken));
        }

        @Override // androidx.media.app.NotificationCompat.MediaStyle, androidx.core.app.NotificationCompat.Style
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            return null;
        }

        @Override // androidx.media.app.NotificationCompat.MediaStyle
        int getContentViewLayoutResource() {
            if (this.mBuilder.getContentView() != null) {
                return R.layout.notification_template_media_custom;
            }
            return super.getContentViewLayoutResource();
        }

        @Override // androidx.media.app.NotificationCompat.MediaStyle, androidx.core.app.NotificationCompat.Style
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            return null;
        }

        @Override // androidx.media.app.NotificationCompat.MediaStyle
        int getBigContentViewLayoutResource(int actionCount) {
            if (actionCount <= 3) {
                return R.layout.notification_template_big_media_narrow_custom;
            }
            return R.layout.notification_template_big_media_custom;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            return null;
        }

        private void setBackgroundColor(RemoteViews views) {
            int color;
            if (this.mBuilder.getColor() != 0) {
                color = this.mBuilder.getColor();
            } else {
                color = this.mBuilder.mContext.getResources().getColor(R.color.notification_material_background_media_default_color);
            }
            views.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", color);
        }
    }

    private static class Api15Impl {
        private Api15Impl() {
        }

        static void setContentDescription(RemoteViews remoteViews, int viewId, CharSequence contentDescription) {
            remoteViews.setContentDescription(viewId, contentDescription);
        }
    }

    private static class Api21Impl {
        private Api21Impl() {
        }

        static void setMediaStyle(Notification.Builder builder, Notification.MediaStyle style) {
            builder.setStyle(style);
        }

        static Notification.MediaStyle createMediaStyle() {
            return new Notification.MediaStyle();
        }

        static Notification.MediaStyle fillInMediaStyle(Notification.MediaStyle style, int[] actionsToShowInCompact, MediaSessionCompat.Token token) {
            if (actionsToShowInCompact != null) {
                setShowActionsInCompactView(style, actionsToShowInCompact);
            }
            if (token != null) {
                setMediaSession(style, (MediaSession.Token) token.getToken());
            }
            return style;
        }

        static void setShowActionsInCompactView(Notification.MediaStyle style, int... actions) {
            style.setShowActionsInCompactView(actions);
        }

        static void setMediaSession(Notification.MediaStyle style, MediaSession.Token token) {
            style.setMediaSession(token);
        }
    }

    private static class Api24Impl {
        private Api24Impl() {
        }

        static Notification.DecoratedMediaCustomViewStyle createDecoratedMediaCustomViewStyle() {
            return new Notification.DecoratedMediaCustomViewStyle();
        }
    }
}
