package com.android.tools.r8.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes9.dex */
@SynthesizedClassV2(apiLevel = 24, kind = 5, versionHash = "f3a348d7d571cfc3f89005b557595f12bc85b7451dde524d9d228b1d59adcb6b")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public /* synthetic */ @interface LambdaMethod {
    String holder();

    String method();

    String proto();
}
