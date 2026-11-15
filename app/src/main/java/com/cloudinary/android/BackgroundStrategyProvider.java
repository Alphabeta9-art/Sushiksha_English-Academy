package com.cloudinary.android;

/* JADX INFO: loaded from: classes11.dex */
class BackgroundStrategyProvider {
    BackgroundStrategyProvider() {
    }

    static BackgroundRequestStrategy provideStrategy() {
        return new AndroidJobStrategy();
    }
}
