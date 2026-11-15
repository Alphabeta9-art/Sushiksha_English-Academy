package androidx.credentials.provider.utils;

import android.app.slice.Slice;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.service.credentials.Action;
import android.service.credentials.BeginGetCredentialOption;
import android.service.credentials.BeginGetCredentialResponse;
import androidx.credentials.provider.AuthenticationAction;
import androidx.credentials.provider.BeginGetCredentialRequest;
import androidx.credentials.provider.CallingAppInfo;
import androidx.credentials.provider.CredentialEntry;
import androidx.credentials.provider.RemoteEntry;
import java.util.ArrayList;
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

/* JADX INFO: compiled from: BeginGetCredentialUtil.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/provider/utils/BeginGetCredentialUtil;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BeginGetCredentialUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: BeginGetCredentialUtil.kt */
    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0015\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\u0010J\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bJ\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u001e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0017H\u0002J\u001e\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0017H\u0002J\u001a\u0010 \u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\"H\u0003¨\u0006#"}, d2 = {"Landroidx/credentials/provider/utils/BeginGetCredentialUtil$Companion;", "", "()V", "convertToFrameworkRequest", "Landroid/service/credentials/BeginGetCredentialRequest;", "request", "Landroidx/credentials/provider/BeginGetCredentialRequest;", "convertToFrameworkResponse", "Landroid/service/credentials/BeginGetCredentialResponse;", "response", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "convertToJetpackBeginOption", "Landroid/service/credentials/BeginGetCredentialOption;", "option", "Landroidx/credentials/provider/BeginGetCredentialOption;", "convertToJetpackRequest", "convertToJetpackRequest$credentials_release", "convertToJetpackResponse", "populateActionEntries", "", "builder", "Landroid/service/credentials/BeginGetCredentialResponse$Builder;", "actionEntries", "", "Landroidx/credentials/provider/Action;", "populateAuthenticationEntries", "frameworkBuilder", "authenticationActions", "Landroidx/credentials/provider/AuthenticationAction;", "populateCredentialEntries", "credentialEntries", "Landroidx/credentials/provider/CredentialEntry;", "populateRemoteEntry", "remoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BeginGetCredentialRequest convertToJetpackRequest$credentials_release(android.service.credentials.BeginGetCredentialRequest request) {
            CallingAppInfo callingAppInfo;
            Intrinsics.checkNotNullParameter(request, "request");
            List beginGetCredentialOptions = new ArrayList();
            Iterable beginGetCredentialOptions2 = request.getBeginGetCredentialOptions();
            Intrinsics.checkNotNullExpressionValue(beginGetCredentialOptions2, "request.beginGetCredentialOptions");
            Iterable $this$forEach$iv = beginGetCredentialOptions2;
            for (Object element$iv : $this$forEach$iv) {
                BeginGetCredentialOption it = (BeginGetCredentialOption) element$iv;
                androidx.credentials.provider.BeginGetCredentialOption.Companion companion = androidx.credentials.provider.BeginGetCredentialOption.INSTANCE;
                String id = it.getId();
                Intrinsics.checkNotNullExpressionValue(id, "it.id");
                String type = it.getType();
                Intrinsics.checkNotNullExpressionValue(type, "it.type");
                Bundle candidateQueryData = it.getCandidateQueryData();
                Intrinsics.checkNotNullExpressionValue(candidateQueryData, "it.candidateQueryData");
                beginGetCredentialOptions.add(companion.createFrom$credentials_release(id, type, candidateQueryData));
            }
            android.service.credentials.CallingAppInfo it2 = request.getCallingAppInfo();
            if (it2 != null) {
                String packageName = it2.getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "it.packageName");
                SigningInfo signingInfo = it2.getSigningInfo();
                Intrinsics.checkNotNullExpressionValue(signingInfo, "it.signingInfo");
                callingAppInfo = new CallingAppInfo(packageName, signingInfo, it2.getOrigin());
            } else {
                callingAppInfo = null;
            }
            return new BeginGetCredentialRequest(beginGetCredentialOptions, callingAppInfo);
        }

        public final BeginGetCredentialResponse convertToFrameworkResponse(androidx.credentials.provider.BeginGetCredentialResponse response) {
            Intrinsics.checkNotNullParameter(response, "response");
            BeginGetCredentialResponse.Builder frameworkBuilder = new BeginGetCredentialResponse.Builder();
            populateCredentialEntries(frameworkBuilder, response.getCredentialEntries());
            populateActionEntries(frameworkBuilder, response.getActions());
            populateAuthenticationEntries(frameworkBuilder, response.getAuthenticationActions());
            populateRemoteEntry(frameworkBuilder, response.getRemoteEntry());
            BeginGetCredentialResponse beginGetCredentialResponseBuild = frameworkBuilder.build();
            Intrinsics.checkNotNullExpressionValue(beginGetCredentialResponseBuild, "frameworkBuilder.build()");
            return beginGetCredentialResponseBuild;
        }

        private final void populateRemoteEntry(BeginGetCredentialResponse.Builder frameworkBuilder, RemoteEntry remoteEntry) {
            if (remoteEntry == null) {
                return;
            }
            frameworkBuilder.setRemoteCredentialEntry(new android.service.credentials.RemoteEntry(RemoteEntry.INSTANCE.toSlice(remoteEntry)));
        }

        private final void populateAuthenticationEntries(BeginGetCredentialResponse.Builder frameworkBuilder, List<AuthenticationAction> authenticationActions) {
            List<AuthenticationAction> $this$forEach$iv = authenticationActions;
            for (Object element$iv : $this$forEach$iv) {
                AuthenticationAction it = (AuthenticationAction) element$iv;
                frameworkBuilder.addAuthenticationAction(new Action(AuthenticationAction.INSTANCE.toSlice(it)));
            }
        }

        private final void populateActionEntries(BeginGetCredentialResponse.Builder builder, List<androidx.credentials.provider.Action> actionEntries) {
            List<androidx.credentials.provider.Action> $this$forEach$iv = actionEntries;
            for (Object element$iv : $this$forEach$iv) {
                androidx.credentials.provider.Action it = (androidx.credentials.provider.Action) element$iv;
                builder.addAction(new Action(androidx.credentials.provider.Action.INSTANCE.toSlice(it)));
            }
        }

        private final void populateCredentialEntries(BeginGetCredentialResponse.Builder builder, List<? extends CredentialEntry> credentialEntries) {
            List<? extends CredentialEntry> $this$forEach$iv = credentialEntries;
            for (Object element$iv : $this$forEach$iv) {
                CredentialEntry it = (CredentialEntry) element$iv;
                Slice entrySlice = CredentialEntry.INSTANCE.toSlice$credentials_release(it);
                if (entrySlice != null) {
                    builder.addCredentialEntry(new android.service.credentials.CredentialEntry(new BeginGetCredentialOption(it.getBeginGetCredentialOption().getId(), it.getType(), Bundle.EMPTY), entrySlice));
                }
            }
        }

        public final android.service.credentials.BeginGetCredentialRequest convertToFrameworkRequest(BeginGetCredentialRequest request) {
            Intrinsics.checkNotNullParameter(request, "request");
            android.service.credentials.BeginGetCredentialRequest.Builder builder = new android.service.credentials.BeginGetCredentialRequest.Builder();
            if (request.getCallingAppInfo() != null) {
                builder.setCallingAppInfo(new android.service.credentials.CallingAppInfo(request.getCallingAppInfo().getPackageName(), request.getCallingAppInfo().getSigningInfo(), request.getCallingAppInfo().getOrigin()));
            }
            Stream<androidx.credentials.provider.BeginGetCredentialOption> stream = request.getBeginGetCredentialOptions().stream();
            final BeginGetCredentialUtil$Companion$convertToFrameworkRequest$1 beginGetCredentialUtil$Companion$convertToFrameworkRequest$1 = new Function1<androidx.credentials.provider.BeginGetCredentialOption, BeginGetCredentialOption>() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$convertToFrameworkRequest$1
                @Override // kotlin.jvm.functions.Function1
                public final BeginGetCredentialOption invoke(androidx.credentials.provider.BeginGetCredentialOption option) {
                    BeginGetCredentialUtil.Companion companion = BeginGetCredentialUtil.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(option, "option");
                    return companion.convertToJetpackBeginOption(option);
                }
            };
            android.service.credentials.BeginGetCredentialRequest beginGetCredentialRequestBuild = builder.setBeginGetCredentialOptions((List) stream.map(new Function() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda9
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BeginGetCredentialUtil.Companion.convertToFrameworkRequest$lambda$5(beginGetCredentialUtil$Companion$convertToFrameworkRequest$1, obj);
                }
            }).collect(Collectors.toList())).build();
            Intrinsics.checkNotNullExpressionValue(beginGetCredentialRequestBuild, "builder.setBeginGetCrede…\n                .build()");
            return beginGetCredentialRequestBuild;
        }

        static final BeginGetCredentialOption convertToFrameworkRequest$lambda$5(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return (BeginGetCredentialOption) tmp0.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final BeginGetCredentialOption convertToJetpackBeginOption(androidx.credentials.provider.BeginGetCredentialOption option) {
            return new BeginGetCredentialOption(option.getId(), option.getType(), option.getCandidateQueryData());
        }

        public final androidx.credentials.provider.BeginGetCredentialResponse convertToJetpackResponse(BeginGetCredentialResponse response) {
            RemoteEntry remoteEntryFromSlice;
            Intrinsics.checkNotNullParameter(response, "response");
            Stream<android.service.credentials.CredentialEntry> stream = response.getCredentialEntries().stream();
            final BeginGetCredentialUtil$Companion$convertToJetpackResponse$1 beginGetCredentialUtil$Companion$convertToJetpackResponse$1 = new Function1<android.service.credentials.CredentialEntry, CredentialEntry>() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$convertToJetpackResponse$1
                @Override // kotlin.jvm.functions.Function1
                public final CredentialEntry invoke(android.service.credentials.CredentialEntry entry) {
                    CredentialEntry.Companion companion = CredentialEntry.INSTANCE;
                    Slice slice = entry.getSlice();
                    Intrinsics.checkNotNullExpressionValue(slice, "entry.slice");
                    return companion.createFrom$credentials_release(slice);
                }
            };
            Stream<R> map = stream.map(new Function() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$6(beginGetCredentialUtil$Companion$convertToJetpackResponse$1, obj);
                }
            });
            final BeginGetCredentialUtil$Companion$convertToJetpackResponse$2 beginGetCredentialUtil$Companion$convertToJetpackResponse$2 = new Function1<CredentialEntry, Boolean>() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$convertToJetpackResponse$2
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(CredentialEntry entry) {
                    return Boolean.valueOf(entry != null);
                }
            };
            Stream streamFilter = map.filter(new Predicate() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$7(beginGetCredentialUtil$Companion$convertToJetpackResponse$2, obj);
                }
            });
            final BeginGetCredentialUtil$Companion$convertToJetpackResponse$3 beginGetCredentialUtil$Companion$convertToJetpackResponse$3 = new Function1<CredentialEntry, CredentialEntry>() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$convertToJetpackResponse$3
                @Override // kotlin.jvm.functions.Function1
                public final CredentialEntry invoke(CredentialEntry entry) {
                    Intrinsics.checkNotNull(entry);
                    return entry;
                }
            };
            Object objCollect = streamFilter.map(new Function() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$8(beginGetCredentialUtil$Companion$convertToJetpackResponse$3, obj);
                }
            }).collect(Collectors.toList());
            Intrinsics.checkNotNullExpressionValue(objCollect, "response.credentialEntri…lect(Collectors.toList())");
            List list = (List) objCollect;
            Stream<Action> stream2 = response.getActions().stream();
            final BeginGetCredentialUtil$Companion$convertToJetpackResponse$4 beginGetCredentialUtil$Companion$convertToJetpackResponse$4 = new Function1<Action, androidx.credentials.provider.Action>() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$convertToJetpackResponse$4
                @Override // kotlin.jvm.functions.Function1
                public final androidx.credentials.provider.Action invoke(Action entry) {
                    androidx.credentials.provider.Action.Companion companion = androidx.credentials.provider.Action.INSTANCE;
                    Slice slice = entry.getSlice();
                    Intrinsics.checkNotNullExpressionValue(slice, "entry.slice");
                    return companion.fromSlice(slice);
                }
            };
            Stream<R> map2 = stream2.map(new Function() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$9(beginGetCredentialUtil$Companion$convertToJetpackResponse$4, obj);
                }
            });
            final BeginGetCredentialUtil$Companion$convertToJetpackResponse$5 beginGetCredentialUtil$Companion$convertToJetpackResponse$5 = new Function1<androidx.credentials.provider.Action, Boolean>() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$convertToJetpackResponse$5
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(androidx.credentials.provider.Action entry) {
                    return Boolean.valueOf(entry != null);
                }
            };
            Stream streamFilter2 = map2.filter(new Predicate() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$10(beginGetCredentialUtil$Companion$convertToJetpackResponse$5, obj);
                }
            });
            final BeginGetCredentialUtil$Companion$convertToJetpackResponse$6 beginGetCredentialUtil$Companion$convertToJetpackResponse$6 = new Function1<androidx.credentials.provider.Action, androidx.credentials.provider.Action>() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$convertToJetpackResponse$6
                @Override // kotlin.jvm.functions.Function1
                public final androidx.credentials.provider.Action invoke(androidx.credentials.provider.Action entry) {
                    Intrinsics.checkNotNull(entry);
                    return entry;
                }
            };
            Object objCollect2 = streamFilter2.map(new Function() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$11(beginGetCredentialUtil$Companion$convertToJetpackResponse$6, obj);
                }
            }).collect(Collectors.toList());
            Intrinsics.checkNotNullExpressionValue(objCollect2, "response.actions.stream(…lect(Collectors.toList())");
            List list2 = (List) objCollect2;
            Stream<Action> stream3 = response.getAuthenticationActions().stream();
            final BeginGetCredentialUtil$Companion$convertToJetpackResponse$7 beginGetCredentialUtil$Companion$convertToJetpackResponse$7 = new Function1<Action, AuthenticationAction>() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$convertToJetpackResponse$7
                @Override // kotlin.jvm.functions.Function1
                public final AuthenticationAction invoke(Action entry) {
                    AuthenticationAction.Companion companion = AuthenticationAction.INSTANCE;
                    Slice slice = entry.getSlice();
                    Intrinsics.checkNotNullExpressionValue(slice, "entry.slice");
                    return companion.fromSlice(slice);
                }
            };
            Stream<R> map3 = stream3.map(new Function() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$12(beginGetCredentialUtil$Companion$convertToJetpackResponse$7, obj);
                }
            });
            final BeginGetCredentialUtil$Companion$convertToJetpackResponse$8 beginGetCredentialUtil$Companion$convertToJetpackResponse$8 = new Function1<AuthenticationAction, Boolean>() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$convertToJetpackResponse$8
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(AuthenticationAction entry) {
                    return Boolean.valueOf(entry != null);
                }
            };
            Stream streamFilter3 = map3.filter(new Predicate() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda7
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$13(beginGetCredentialUtil$Companion$convertToJetpackResponse$8, obj);
                }
            });
            final BeginGetCredentialUtil$Companion$convertToJetpackResponse$9 beginGetCredentialUtil$Companion$convertToJetpackResponse$9 = new Function1<AuthenticationAction, AuthenticationAction>() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$convertToJetpackResponse$9
                @Override // kotlin.jvm.functions.Function1
                public final AuthenticationAction invoke(AuthenticationAction entry) {
                    Intrinsics.checkNotNull(entry);
                    return entry;
                }
            };
            Object objCollect3 = streamFilter3.map(new Function() { // from class: androidx.credentials.provider.utils.BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$14(beginGetCredentialUtil$Companion$convertToJetpackResponse$9, obj);
                }
            }).collect(Collectors.toList());
            Intrinsics.checkNotNullExpressionValue(objCollect3, "response.authenticationA…lect(Collectors.toList())");
            List list3 = (List) objCollect3;
            android.service.credentials.RemoteEntry it = response.getRemoteCredentialEntry();
            if (it != null) {
                RemoteEntry.Companion companion = RemoteEntry.INSTANCE;
                Slice slice = it.getSlice();
                Intrinsics.checkNotNullExpressionValue(slice, "it.slice");
                remoteEntryFromSlice = companion.fromSlice(slice);
            } else {
                remoteEntryFromSlice = null;
            }
            return new androidx.credentials.provider.BeginGetCredentialResponse(list, list2, list3, remoteEntryFromSlice);
        }

        static final CredentialEntry convertToJetpackResponse$lambda$6(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return (CredentialEntry) tmp0.invoke(p0);
        }

        static final boolean convertToJetpackResponse$lambda$7(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return ((Boolean) tmp0.invoke(p0)).booleanValue();
        }

        static final CredentialEntry convertToJetpackResponse$lambda$8(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return (CredentialEntry) tmp0.invoke(p0);
        }

        static final androidx.credentials.provider.Action convertToJetpackResponse$lambda$9(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return (androidx.credentials.provider.Action) tmp0.invoke(p0);
        }

        static final boolean convertToJetpackResponse$lambda$10(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return ((Boolean) tmp0.invoke(p0)).booleanValue();
        }

        static final androidx.credentials.provider.Action convertToJetpackResponse$lambda$11(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return (androidx.credentials.provider.Action) tmp0.invoke(p0);
        }

        static final AuthenticationAction convertToJetpackResponse$lambda$12(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return (AuthenticationAction) tmp0.invoke(p0);
        }

        static final boolean convertToJetpackResponse$lambda$13(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return ((Boolean) tmp0.invoke(p0)).booleanValue();
        }

        static final AuthenticationAction convertToJetpackResponse$lambda$14(Function1 tmp0, Object p0) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return (AuthenticationAction) tmp0.invoke(p0);
        }
    }
}
