package com.facebook.datasource;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes11.dex */
public abstract class BaseDataSubscriber<T> implements DataSubscriber<T> {
    protected abstract void onFailureImpl(@Nonnull DataSource<T> dataSource);

    protected abstract void onNewResultImpl(@Nonnull DataSource<T> dataSource);

    @Override // com.facebook.datasource.DataSubscriber
    public void onNewResult(@Nonnull DataSource<T> dataSource) {
        boolean shouldClose = dataSource.isFinished();
        try {
            onNewResultImpl(dataSource);
        } finally {
            if (shouldClose) {
                dataSource.close();
            }
        }
    }

    @Override // com.facebook.datasource.DataSubscriber
    public void onFailure(@Nonnull DataSource<T> dataSource) {
        try {
            onFailureImpl(dataSource);
        } finally {
            dataSource.close();
        }
    }

    @Override // com.facebook.datasource.DataSubscriber
    public void onCancellation(@Nonnull DataSource<T> dataSource) {
    }

    @Override // com.facebook.datasource.DataSubscriber
    public void onProgressUpdate(@Nonnull DataSource<T> dataSource) {
    }
}
