package androidx.credentials.provider;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.app.slice.Slice$Builder;
import android.app.slice.SliceItem;
import android.app.slice.SliceSpec;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.credentials.CredentialOption;
import androidx.credentials.PasswordCredential;
import androidx.credentials.R;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.time.Instant;
import java.util.Collections;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PasswordCredentialEntry.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0007\u0018\u0000 #2\u00020\u0001:\u0003!\"#BS\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011B_\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0010¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u001aR\u000e\u0010\u0014\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017¨\u0006$"}, d2 = {"Landroidx/credentials/provider/PasswordCredentialEntry;", "Landroidx/credentials/provider/CredentialEntry;", "context", "Landroid/content/Context;", HintConstants.AUTOFILL_HINT_USERNAME, "", BaseGmsClient.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "beginGetPasswordOption", "Landroidx/credentials/provider/BeginGetPasswordOption;", "displayName", "lastUsedTime", "Ljava/time/Instant;", "icon", "Landroid/graphics/drawable/Icon;", "isAutoSelectAllowed", "", "(Landroid/content/Context;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroidx/credentials/provider/BeginGetPasswordOption;Ljava/lang/CharSequence;Ljava/time/Instant;Landroid/graphics/drawable/Icon;Z)V", "typeDisplayName", "autoSelectAllowedFromOption", "isDefaultIcon", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Ljava/time/Instant;Landroid/graphics/drawable/Icon;ZLandroidx/credentials/provider/BeginGetPasswordOption;ZZ)V", "getDisplayName", "()Ljava/lang/CharSequence;", "getIcon", "()Landroid/graphics/drawable/Icon;", "()Z", "getLastUsedTime", "()Ljava/time/Instant;", "getPendingIntent", "()Landroid/app/PendingIntent;", "getTypeDisplayName", "getUsername", "Api28Impl", "Builder", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PasswordCredentialEntry extends CredentialEntry {
    private static final String AUTO_SELECT_FALSE_STRING = "false";
    private static final String AUTO_SELECT_TRUE_STRING = "true";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int REVISION_ID = 1;
    private static final String SLICE_HINT_AUTO_ALLOWED = "androidx.credentials.provider.credentialEntry.SLICE_HINT_AUTO_ALLOWED";
    private static final String SLICE_HINT_AUTO_SELECT_FROM_OPTION = "androidx.credentials.provider.credentialEntry.SLICE_HINT_AUTO_SELECT_FROM_OPTION";
    private static final String SLICE_HINT_DEFAULT_ICON_RES_ID = "androidx.credentials.provider.credentialEntry.SLICE_HINT_DEFAULT_ICON_RES_ID";
    private static final String SLICE_HINT_ICON = "androidx.credentials.provider.credentialEntry.SLICE_HINT_PROFILE_ICON";
    private static final String SLICE_HINT_LAST_USED_TIME_MILLIS = "androidx.credentials.provider.credentialEntry.SLICE_HINT_LAST_USED_TIME_MILLIS";
    private static final String SLICE_HINT_OPTION_ID = "androidx.credentials.provider.credentialEntry.SLICE_HINT_OPTION_ID";
    private static final String SLICE_HINT_PENDING_INTENT = "androidx.credentials.provider.credentialEntry.SLICE_HINT_PENDING_INTENT";
    private static final String SLICE_HINT_SUBTITLE = "androidx.credentials.provider.credentialEntry.SLICE_HINT_CREDENTIAL_TYPE_DISPLAY_NAME";
    private static final String SLICE_HINT_TITLE = "androidx.credentials.provider.credentialEntry.SLICE_HINT_USER_NAME";
    private static final String SLICE_HINT_TYPE_DISPLAY_NAME = "androidx.credentials.provider.credentialEntry.SLICE_HINT_TYPE_DISPLAY_NAME";
    private static final String TAG = "PasswordCredentialEntry";
    private final boolean autoSelectAllowedFromOption;
    private final CharSequence displayName;
    private final Icon icon;
    private final boolean isAutoSelectAllowed;
    private final boolean isDefaultIcon;
    private final Instant lastUsedTime;
    private final PendingIntent pendingIntent;
    private final CharSequence typeDisplayName;
    private final CharSequence username;

    @JvmStatic
    public static final PasswordCredentialEntry fromSlice(Slice slice) {
        return INSTANCE.fromSlice(slice);
    }

    @JvmStatic
    public static final Slice toSlice(PasswordCredentialEntry passwordCredentialEntry) {
        return INSTANCE.toSlice(passwordCredentialEntry);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PasswordCredentialEntry(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, PendingIntent pendingIntent, Instant instant, Icon icon, boolean z, BeginGetPasswordOption beginGetPasswordOption, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        boolean z4;
        z2 = (i & 256) != 0 ? false : z2;
        if ((i & 512) == 0) {
            z4 = z3;
        } else {
            z4 = false;
        }
        this(charSequence, charSequence2, charSequence3, pendingIntent, instant, icon, z, beginGetPasswordOption, z2, z4);
    }

    public final CharSequence getUsername() {
        return this.username;
    }

    public final CharSequence getDisplayName() {
        return this.displayName;
    }

    public final CharSequence getTypeDisplayName() {
        return this.typeDisplayName;
    }

    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public final Instant getLastUsedTime() {
        return this.lastUsedTime;
    }

    public final Icon getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: isAutoSelectAllowed, reason: from getter */
    public final boolean getIsAutoSelectAllowed() {
        return this.isAutoSelectAllowed;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PasswordCredentialEntry(CharSequence username, CharSequence displayName, CharSequence typeDisplayName, PendingIntent pendingIntent, Instant lastUsedTime, Icon icon, boolean isAutoSelectAllowed, BeginGetPasswordOption beginGetPasswordOption, boolean autoSelectAllowedFromOption, boolean isDefaultIcon) {
        super(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, beginGetPasswordOption);
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(typeDisplayName, "typeDisplayName");
        Intrinsics.checkNotNullParameter(pendingIntent, "pendingIntent");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(beginGetPasswordOption, "beginGetPasswordOption");
        this.username = username;
        this.displayName = displayName;
        this.typeDisplayName = typeDisplayName;
        this.pendingIntent = pendingIntent;
        this.lastUsedTime = lastUsedTime;
        this.icon = icon;
        this.isAutoSelectAllowed = isAutoSelectAllowed;
        this.autoSelectAllowedFromOption = autoSelectAllowedFromOption;
        this.isDefaultIcon = isDefaultIcon;
        if (!(this.username.length() > 0)) {
            throw new IllegalArgumentException("username must not be empty".toString());
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PasswordCredentialEntry(Context context, CharSequence charSequence, PendingIntent pendingIntent, BeginGetPasswordOption beginGetPasswordOption, CharSequence charSequence2, Instant instant, Icon icon, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        charSequence2 = (i & 16) != 0 ? null : charSequence2;
        instant = (i & 32) != 0 ? null : instant;
        if ((i & 64) != 0) {
            icon = Icon.createWithResource(context, R.drawable.ic_password);
            Intrinsics.checkNotNullExpressionValue(icon, "createWithResource(conte…, R.drawable.ic_password)");
        }
        this(context, charSequence, pendingIntent, beginGetPasswordOption, charSequence2, instant, icon, (i & 128) != 0 ? false : z);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PasswordCredentialEntry(Context context, CharSequence username, PendingIntent pendingIntent, BeginGetPasswordOption beginGetPasswordOption, CharSequence displayName, Instant lastUsedTime, Icon icon, boolean isAutoSelectAllowed) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(pendingIntent, "pendingIntent");
        Intrinsics.checkNotNullParameter(beginGetPasswordOption, "beginGetPasswordOption");
        Intrinsics.checkNotNullParameter(icon, "icon");
        String string = context.getString(R.string.android_credentials_TYPE_PASSWORD_CREDENTIAL);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …WORD_CREDENTIAL\n        )");
        this(username, displayName, string, pendingIntent, lastUsedTime, icon, isAutoSelectAllowed, beginGetPasswordOption, false, false, 768, null);
    }

    /* JADX INFO: compiled from: PasswordCredentialEntry.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Landroidx/credentials/provider/PasswordCredentialEntry$Api28Impl;", "", "()V", "fromSlice", "Landroidx/credentials/provider/PasswordCredentialEntry;", "slice", "Landroid/app/slice/Slice;", "toSlice", "entry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Api28Impl {
        public static final Api28Impl INSTANCE = new Api28Impl();

        private Api28Impl() {
        }

        @JvmStatic
        public static final Slice toSlice(PasswordCredentialEntry entry) {
            String autoSelectAllowed;
            Intrinsics.checkNotNullParameter(entry, "entry");
            String type = entry.getType();
            CharSequence title = entry.getUsername();
            CharSequence subtitle = entry.getDisplayName();
            PendingIntent pendingIntent = entry.getPendingIntent();
            CharSequence typeDisplayName = entry.getTypeDisplayName();
            Instant lastUsedTime = entry.getLastUsedTime();
            Icon icon = entry.getIcon();
            boolean isAutoSelectAllowed = entry.getIsAutoSelectAllowed();
            BeginGetCredentialOption beginGetPasswordCredentialOption = entry.getBeginGetCredentialOption();
            if (isAutoSelectAllowed) {
                autoSelectAllowed = PasswordCredentialEntry.AUTO_SELECT_TRUE_STRING;
            } else {
                autoSelectAllowed = PasswordCredentialEntry.AUTO_SELECT_FALSE_STRING;
            }
            Slice$Builder sliceBuilder = new Slice$Builder(Uri.EMPTY, new SliceSpec(type, 1)).addText(typeDisplayName, null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_TYPE_DISPLAY_NAME)).addText(title, null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_TITLE)).addText(subtitle, null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_SUBTITLE)).addText(autoSelectAllowed, null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_AUTO_ALLOWED)).addText(beginGetPasswordCredentialOption.getId(), null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_OPTION_ID)).addIcon(icon, null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_ICON));
            try {
                if (icon.getResId() == R.drawable.ic_password) {
                    sliceBuilder.addInt(1, null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_DEFAULT_ICON_RES_ID));
                }
            } catch (IllegalStateException e) {
            }
            if (CredentialOption.INSTANCE.extractAutoSelectValue$credentials_release(beginGetPasswordCredentialOption.getCandidateQueryData())) {
                sliceBuilder.addInt(1, null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_AUTO_SELECT_FROM_OPTION));
            }
            if (lastUsedTime != null) {
                sliceBuilder.addLong(lastUsedTime.toEpochMilli(), null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_LAST_USED_TIME_MILLIS));
            }
            sliceBuilder.addAction(pendingIntent, new Slice$Builder(sliceBuilder).addHints(Collections.singletonList(PasswordCredentialEntry.SLICE_HINT_PENDING_INTENT)).build(), null);
            Slice sliceBuild = sliceBuilder.build();
            Intrinsics.checkNotNullExpressionValue(sliceBuild, "sliceBuilder.build()");
            return sliceBuild;
        }

        @JvmStatic
        public static final PasswordCredentialEntry fromSlice(Slice slice) {
            Intrinsics.checkNotNullParameter(slice, "slice");
            Object beginGetPasswordOptionId = null;
            Iterable items = slice.getItems();
            Intrinsics.checkNotNullExpressionValue(items, "slice.items");
            Iterable $this$forEach$iv = items;
            CharSequence text = null;
            CharSequence text2 = null;
            CharSequence text3 = null;
            Icon icon = null;
            PendingIntent action = null;
            Instant instantOfEpochMilli = null;
            boolean autoSelectAllowed = false;
            boolean autoSelectAllowedFromOption = false;
            boolean isDefaultIcon = false;
            for (Object element$iv : $this$forEach$iv) {
                SliceItem it = (SliceItem) element$iv;
                if (it.hasHint(PasswordCredentialEntry.SLICE_HINT_TYPE_DISPLAY_NAME)) {
                    text = it.getText();
                } else if (it.hasHint(PasswordCredentialEntry.SLICE_HINT_TITLE)) {
                    text2 = it.getText();
                } else if (it.hasHint(PasswordCredentialEntry.SLICE_HINT_SUBTITLE)) {
                    text3 = it.getText();
                } else if (it.hasHint(PasswordCredentialEntry.SLICE_HINT_ICON)) {
                    icon = it.getIcon();
                } else if (it.hasHint(PasswordCredentialEntry.SLICE_HINT_PENDING_INTENT)) {
                    action = it.getAction();
                } else if (it.hasHint(PasswordCredentialEntry.SLICE_HINT_OPTION_ID)) {
                    beginGetPasswordOptionId = it.getText();
                } else if (it.hasHint(PasswordCredentialEntry.SLICE_HINT_LAST_USED_TIME_MILLIS)) {
                    instantOfEpochMilli = Instant.ofEpochMilli(it.getLong());
                } else if (it.hasHint(PasswordCredentialEntry.SLICE_HINT_AUTO_ALLOWED)) {
                    CharSequence autoSelectValue = it.getText();
                    if (Intrinsics.areEqual(autoSelectValue, PasswordCredentialEntry.AUTO_SELECT_TRUE_STRING)) {
                        autoSelectAllowed = true;
                    }
                } else if (it.hasHint(PasswordCredentialEntry.SLICE_HINT_AUTO_SELECT_FROM_OPTION)) {
                    autoSelectAllowedFromOption = true;
                } else if (it.hasHint(PasswordCredentialEntry.SLICE_HINT_DEFAULT_ICON_RES_ID)) {
                    isDefaultIcon = true;
                }
            }
            try {
                Intrinsics.checkNotNull(text2);
                Intrinsics.checkNotNull(text);
                Intrinsics.checkNotNull(action);
                Intrinsics.checkNotNull(icon);
                BeginGetPasswordOption.Companion companion = BeginGetPasswordOption.INSTANCE;
                Bundle bundle = new Bundle();
                Intrinsics.checkNotNull(beginGetPasswordOptionId);
                return new PasswordCredentialEntry(text2, text3, text, action, instantOfEpochMilli, icon, autoSelectAllowed, companion.createFrom$credentials_release(bundle, beginGetPasswordOptionId.toString()), autoSelectAllowedFromOption, isDefaultIcon);
            } catch (Exception e) {
                Log.i(PasswordCredentialEntry.TAG, "fromSlice failed with: " + e.getMessage());
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: PasswordCredentialEntry.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/credentials/provider/PasswordCredentialEntry$Companion;", "", "()V", "AUTO_SELECT_FALSE_STRING", "", "AUTO_SELECT_TRUE_STRING", "REVISION_ID", "", "SLICE_HINT_AUTO_ALLOWED", "SLICE_HINT_AUTO_SELECT_FROM_OPTION", "SLICE_HINT_DEFAULT_ICON_RES_ID", "SLICE_HINT_ICON", "SLICE_HINT_LAST_USED_TIME_MILLIS", "SLICE_HINT_OPTION_ID", "SLICE_HINT_PENDING_INTENT", "SLICE_HINT_SUBTITLE", "SLICE_HINT_TITLE", "SLICE_HINT_TYPE_DISPLAY_NAME", "TAG", "fromSlice", "Landroidx/credentials/provider/PasswordCredentialEntry;", "slice", "Landroid/app/slice/Slice;", "toSlice", "entry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Slice toSlice(PasswordCredentialEntry entry) {
            Intrinsics.checkNotNullParameter(entry, "entry");
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.toSlice(entry);
            }
            return null;
        }

        @JvmStatic
        public final PasswordCredentialEntry fromSlice(Slice slice) {
            Intrinsics.checkNotNullParameter(slice, "slice");
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.fromSlice(slice);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: PasswordCredentialEntry.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u0015\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0017\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/credentials/provider/PasswordCredentialEntry$Builder;", "", "context", "Landroid/content/Context;", HintConstants.AUTOFILL_HINT_USERNAME, "", BaseGmsClient.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "beginGetPasswordOption", "Landroidx/credentials/provider/BeginGetPasswordOption;", "(Landroid/content/Context;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroidx/credentials/provider/BeginGetPasswordOption;)V", "autoSelectAllowed", "", "displayName", "icon", "Landroid/graphics/drawable/Icon;", "lastUsedTime", "Ljava/time/Instant;", "build", "Landroidx/credentials/provider/PasswordCredentialEntry;", "setAutoSelectAllowed", "setDisplayName", "setIcon", "setLastUsedTime", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private boolean autoSelectAllowed;
        private final BeginGetPasswordOption beginGetPasswordOption;
        private final Context context;
        private CharSequence displayName;
        private Icon icon;
        private Instant lastUsedTime;
        private final PendingIntent pendingIntent;
        private final CharSequence username;

        public Builder(Context context, CharSequence username, PendingIntent pendingIntent, BeginGetPasswordOption beginGetPasswordOption) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(username, "username");
            Intrinsics.checkNotNullParameter(pendingIntent, "pendingIntent");
            Intrinsics.checkNotNullParameter(beginGetPasswordOption, "beginGetPasswordOption");
            this.context = context;
            this.username = username;
            this.pendingIntent = pendingIntent;
            this.beginGetPasswordOption = beginGetPasswordOption;
        }

        public final Builder setDisplayName(CharSequence displayName) {
            this.displayName = displayName;
            return this;
        }

        public final Builder setIcon(Icon icon) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            this.icon = icon;
            return this;
        }

        public final Builder setAutoSelectAllowed(boolean autoSelectAllowed) {
            this.autoSelectAllowed = autoSelectAllowed;
            return this;
        }

        public final Builder setLastUsedTime(Instant lastUsedTime) {
            this.lastUsedTime = lastUsedTime;
            return this;
        }

        public final PasswordCredentialEntry build() {
            if (this.icon == null) {
                int i = Build.VERSION.SDK_INT;
                this.icon = Icon.createWithResource(this.context, R.drawable.ic_password);
            }
            String typeDisplayName = this.context.getString(R.string.android_credentials_TYPE_PASSWORD_CREDENTIAL);
            Intrinsics.checkNotNullExpressionValue(typeDisplayName, "context.getString(\n     …_CREDENTIAL\n            )");
            PendingIntent pendingIntent = this.pendingIntent;
            Instant instant = this.lastUsedTime;
            Icon icon = this.icon;
            Intrinsics.checkNotNull(icon);
            return new PasswordCredentialEntry(this.username, this.displayName, typeDisplayName, pendingIntent, instant, icon, this.autoSelectAllowed, this.beginGetPasswordOption, false, false, 768, null);
        }
    }
}
