package androidx.credentials.provider.utils;

import android.app.slice.Slice;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.service.credentials.BeginCreateCredentialResponse;
import androidx.credentials.PasswordCredential;
import androidx.credentials.PublicKeyCredential;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.provider.BeginCreateCredentialRequest;
import androidx.credentials.provider.BeginCreateCustomCredentialRequest;
import androidx.credentials.provider.BeginCreatePasswordCredentialRequest;
import androidx.credentials.provider.BeginCreatePublicKeyCredentialRequest;
import androidx.credentials.provider.CallingAppInfo;
import androidx.credentials.provider.CreateEntry;
import androidx.credentials.provider.RemoteEntry;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BeginCreateCredentialUtil.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/provider/utils/BeginCreateCredentialUtil;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BeginCreateCredentialUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: BeginCreateCredentialUtil.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\bJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002J\u001a\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0003¨\u0006\u0019"}, d2 = {"Landroidx/credentials/provider/utils/BeginCreateCredentialUtil$Companion;", "", "()V", "convertToFrameworkRequest", "Landroid/service/credentials/BeginCreateCredentialRequest;", "request", "Landroidx/credentials/provider/BeginCreateCredentialRequest;", "convertToFrameworkResponse", "Landroid/service/credentials/BeginCreateCredentialResponse;", "response", "Landroidx/credentials/provider/BeginCreateCredentialResponse;", "convertToJetpackRequest", "convertToJetpackRequest$credentials_release", "convertToJetpackResponse", "frameworkResponse", "populateCreateEntries", "", "frameworkBuilder", "Landroid/service/credentials/BeginCreateCredentialResponse$Builder;", "createEntries", "", "Landroidx/credentials/provider/CreateEntry;", "populateRemoteEntry", "remoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @JvmStatic
        public final BeginCreateCredentialRequest convertToJetpackRequest$credentials_release(android.service.credentials.BeginCreateCredentialRequest request) {
            CallingAppInfo callingAppInfo;
            CallingAppInfo callingAppInfo2;
            CallingAppInfo callingAppInfo3;
            Intrinsics.checkNotNullParameter(request, "request");
            CallingAppInfo callingAppInfo4 = null;
            try {
                String type = request.getType();
                switch (type.hashCode()) {
                    case -543568185:
                        if (type.equals(PasswordCredential.TYPE_PASSWORD_CREDENTIAL)) {
                            BeginCreatePasswordCredentialRequest.Companion companion = BeginCreatePasswordCredentialRequest.INSTANCE;
                            Bundle data = request.getData();
                            Intrinsics.checkNotNullExpressionValue(data, "request.data");
                            android.service.credentials.CallingAppInfo it = request.getCallingAppInfo();
                            if (it == null) {
                                callingAppInfo = null;
                            } else {
                                String packageName = it.getPackageName();
                                Intrinsics.checkNotNullExpressionValue(packageName, "it.packageName");
                                SigningInfo signingInfo = it.getSigningInfo();
                                Intrinsics.checkNotNullExpressionValue(signingInfo, "it.signingInfo");
                                callingAppInfo = new CallingAppInfo(packageName, signingInfo, it.getOrigin());
                            }
                            return companion.createFrom$credentials_release(data, callingAppInfo);
                        }
                        break;
                    case -95037569:
                        if (type.equals(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL)) {
                            BeginCreatePublicKeyCredentialRequest.Companion companion2 = BeginCreatePublicKeyCredentialRequest.INSTANCE;
                            Bundle data2 = request.getData();
                            Intrinsics.checkNotNullExpressionValue(data2, "request.data");
                            android.service.credentials.CallingAppInfo it2 = request.getCallingAppInfo();
                            if (it2 == null) {
                                callingAppInfo2 = null;
                            } else {
                                String packageName2 = it2.getPackageName();
                                Intrinsics.checkNotNullExpressionValue(packageName2, "it.packageName");
                                SigningInfo signingInfo2 = it2.getSigningInfo();
                                Intrinsics.checkNotNullExpressionValue(signingInfo2, "it.signingInfo");
                                callingAppInfo2 = new CallingAppInfo(packageName2, signingInfo2, it2.getOrigin());
                            }
                            return companion2.createFrom$credentials_release(data2, callingAppInfo2);
                        }
                        break;
                }
                String type2 = request.getType();
                Intrinsics.checkNotNullExpressionValue(type2, "request.type");
                Bundle data3 = request.getData();
                Intrinsics.checkNotNullExpressionValue(data3, "request.data");
                android.service.credentials.CallingAppInfo it3 = request.getCallingAppInfo();
                if (it3 == null) {
                    callingAppInfo3 = null;
                } else {
                    String packageName3 = it3.getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName3, "it.packageName");
                    SigningInfo signingInfo3 = it3.getSigningInfo();
                    Intrinsics.checkNotNullExpressionValue(signingInfo3, "it.signingInfo");
                    callingAppInfo3 = new CallingAppInfo(packageName3, signingInfo3, it3.getOrigin());
                }
                return new BeginCreateCustomCredentialRequest(type2, data3, callingAppInfo3);
            } catch (FrameworkClassParsingException e) {
                String type3 = request.getType();
                Intrinsics.checkNotNullExpressionValue(type3, "request.type");
                Bundle data4 = request.getData();
                Intrinsics.checkNotNullExpressionValue(data4, "request.data");
                android.service.credentials.CallingAppInfo it4 = request.getCallingAppInfo();
                if (it4 != null) {
                    String packageName4 = it4.getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName4, "it.packageName");
                    SigningInfo signingInfo4 = it4.getSigningInfo();
                    Intrinsics.checkNotNullExpressionValue(signingInfo4, "it.signingInfo");
                    callingAppInfo4 = new CallingAppInfo(packageName4, signingInfo4, it4.getOrigin());
                }
                return new BeginCreateCustomCredentialRequest(type3, data4, callingAppInfo4);
            }
        }

        public final BeginCreateCredentialResponse convertToFrameworkResponse(androidx.credentials.provider.BeginCreateCredentialResponse response) {
            Intrinsics.checkNotNullParameter(response, "response");
            BeginCreateCredentialResponse.Builder frameworkBuilder = new BeginCreateCredentialResponse.Builder();
            populateCreateEntries(frameworkBuilder, response.getCreateEntries());
            populateRemoteEntry(frameworkBuilder, response.getRemoteEntry());
            BeginCreateCredentialResponse beginCreateCredentialResponseBuild = frameworkBuilder.build();
            Intrinsics.checkNotNullExpressionValue(beginCreateCredentialResponseBuild, "frameworkBuilder.build()");
            return beginCreateCredentialResponseBuild;
        }

        private final void populateRemoteEntry(BeginCreateCredentialResponse.Builder frameworkBuilder, RemoteEntry remoteEntry) {
            if (remoteEntry == null) {
                return;
            }
            frameworkBuilder.setRemoteCreateEntry(new android.service.credentials.RemoteEntry(RemoteEntry.INSTANCE.toSlice(remoteEntry)));
        }

        private final void populateCreateEntries(BeginCreateCredentialResponse.Builder frameworkBuilder, List<CreateEntry> createEntries) {
            List<CreateEntry> $this$forEach$iv = createEntries;
            for (Object element$iv : $this$forEach$iv) {
                CreateEntry it = (CreateEntry) element$iv;
                Slice entrySlice = CreateEntry.INSTANCE.toSlice(it);
                if (entrySlice != null) {
                    frameworkBuilder.addCreateEntry(new android.service.credentials.CreateEntry(entrySlice));
                }
            }
        }

        public final android.service.credentials.BeginCreateCredentialRequest convertToFrameworkRequest(BeginCreateCredentialRequest request) {
            Intrinsics.checkNotNullParameter(request, "request");
            android.service.credentials.CallingAppInfo callingAppInfo = null;
            if (request.getCallingAppInfo() != null) {
                callingAppInfo = new android.service.credentials.CallingAppInfo(request.getCallingAppInfo().getPackageName(), request.getCallingAppInfo().getSigningInfo(), request.getCallingAppInfo().getOrigin());
            }
            return new android.service.credentials.BeginCreateCredentialRequest(request.getType(), request.getCandidateQueryData(), callingAppInfo);
        }

        public final androidx.credentials.provider.BeginCreateCredentialResponse convertToJetpackResponse(BeginCreateCredentialResponse frameworkResponse) {
            RemoteEntry remoteEntryFromSlice;
            Intrinsics.checkNotNullParameter(frameworkResponse, "frameworkResponse");
            Stream<android.service.credentials.CreateEntry> stream = frameworkResponse.getCreateEntries().stream();
            final BeginCreateCredentialUtil$Companion$convertToJetpackResponse$1 beginCreateCredentialUtil$Companion$convertToJetpackResponse$1 = new Function1<android.service.credentials.CreateEntry, CreateEntry>() { // from class: androidx.credentials.provider.utils.BeginCreateCredentialUtil$Companion$convertToJetpackResponse$1
                @Override // kotlin.jvm.functions.Function1
                public final CreateEntry invoke(android.service.credentials.CreateEntry entry) {
                    CreateEntry.Companion companion = CreateEntry.INSTANCE;
                    Slice slice = entry.getSlice();
                    Intrinsics.checkNotNullExpressionValue(slice, "entry.slice");
                    return companion.fromSlice(slice);
                }
            };
            Stream<R> map = stream.map(new Function() { // from class: androidx.credentials.provider.utils.BeginCreateCredentialUtil$Companion$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BeginCreateCredentialUtil.Companion.convertToJetpackResponse$lambda$5(beginCreateCredentialUtil$Companion$convertToJetpackResponse$1, obj);
                }
            });
            final BeginCreateCredentialUtil$Companion$convertToJetpackResponse$2 beginCreateCredentialUtil$Companion$convertToJetpackResponse$2 = new Function1<CreateEntry, Boolean>() { // from class: androidx.credentials.provider.utils.BeginCreateCredentialUtil$Companion$convertToJetpackResponse$2
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(CreateEntry entry) {
                    return Boolean.valueOf(entry != null);
                }
            };
            Stream streamFilter = map.filter(new Predicate() { // from class: androidx.credentials.provider.utils.BeginCreateCredentialUtil$Companion$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return BeginCreateCredentialUtil.Companion.convertToJetpackResponse$lambda$6(beginCreateCredentialUtil$Companion$convertToJetpackResponse$2, obj);
                }
            });
            final BeginCreateCredentialUtil$Companion$convertToJetpackResponse$3 beginCreateCredentialUtil$Companion$convertToJetpackResponse$3 = new Function1<CreateEntry, CreateEntry>() { // from class: androidx.credentials.provider.utils.BeginCreateCredentialUtil$Companion$convertToJetpackResponse$3
                @Override // kotlin.jvm.functions.Function1
                public final CreateEntry invoke(CreateEntry entry) {
                    Intrinsics.checkNotNull(entry);
                    return entry;
                }
            };
            Object objCollect = streamFilter.map(new Function() { // from class: androidx.credentials.provider.utils.BeginCreateCredentialUtil$Companion$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BeginCreateCredentialUtil.Companion.convertToJetpackResponse$lambda$7(beginCreateCredentialUtil$Companion$convertToJetpackResponse$3, obj);
                }
            }).collect(Collectors.toList());
            Intrinsics.checkNotNullExpressionValue(objCollect, "frameworkResponse.create…lect(Collectors.toList())");
            List list = (List) objCollect;
            android.service.credentials.RemoteEntry it = frameworkResponse.getRemoteCreateEntry();
            if (it != null) {
                RemoteEntry.Companion companion = RemoteEntry.INSTANCE;
                Slice slice = it.getSlice();
                Intrinsics.checkNotNullExpressionValue(slice, "it.slice");
                remoteEntryFromSlice = companion.fromSlice(slice);
            } else {
                remoteEntryFromSlice = null;
            }
            return new androidx.credentials.provider.BeginCreateCredentialResponse(list, remoteEntryFromSlice);
        }

        static final CreateEntry convertToJetpackResponse$lambda$5(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return (CreateEntry) tmp0.invoke(p0);
        }

        static final boolean convertToJetpackResponse$lambda$6(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return ((Boolean) tmp0.invoke(p0)).booleanValue();
        }

        static final CreateEntry convertToJetpackResponse$lambda$7(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return (CreateEntry) tmp0.invoke(p0);
        }
    }
}
