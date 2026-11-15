package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.ContactsContract;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LocalContentUriFetchProducer.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/imagepipeline/producers/LocalContentUriFetchProducer;", "Lcom/facebook/imagepipeline/producers/LocalFetchProducer;", "executor", "Ljava/util/concurrent/Executor;", "pooledByteBufferFactory", "Lcom/facebook/common/memory/PooledByteBufferFactory;", "contentResolver", "Landroid/content/ContentResolver;", "(Ljava/util/concurrent/Executor;Lcom/facebook/common/memory/PooledByteBufferFactory;Landroid/content/ContentResolver;)V", "getCameraImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "uri", "Landroid/net/Uri;", "getEncodedImage", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "getProducerName", "", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LocalContentUriFetchProducer extends LocalFetchProducer {
    public static final String PRODUCER_NAME = "LocalContentUriFetchProducer";
    private final ContentResolver contentResolver;
    private static final String[] PROJECTION = {"_id", "_data"};

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocalContentUriFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        super(executor, pooledByteBufferFactory);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(pooledByteBufferFactory, "pooledByteBufferFactory");
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        this.contentResolver = contentResolver;
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    protected EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        EncodedImage cameraImage;
        InputStream inputStream;
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        Uri uri = imageRequest.getSourceUri();
        Intrinsics.checkNotNullExpressionValue(uri, "imageRequest.sourceUri");
        if (UriUtil.isLocalContactUri(uri)) {
            String string = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string, "uri.toString()");
            if (StringsKt.endsWith$default(string, "/photo", false, 2, (Object) null)) {
                inputStream = this.contentResolver.openInputStream(uri);
            } else {
                String string2 = uri.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "uri.toString()");
                if (StringsKt.endsWith$default(string2, "/display_photo", false, 2, (Object) null)) {
                    try {
                        AssetFileDescriptor fd = this.contentResolver.openAssetFileDescriptor(uri, "r");
                        if (fd == null) {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        inputStream = fd.createInputStream();
                    } catch (IOException e) {
                        throw new IOException("Contact photo does not exist: " + uri);
                    }
                } else {
                    inputStream = ContactsContract.Contacts.openContactPhotoInputStream(this.contentResolver, uri);
                    if (inputStream == null) {
                        throw new IOException("Contact photo does not exist: " + uri);
                    }
                }
            }
            if (inputStream == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            return getEncodedImage(inputStream, -1);
        }
        if (UriUtil.isLocalCameraUri(uri) && (cameraImage = getCameraImage(uri)) != null) {
            return cameraImage;
        }
        InputStream inputStreamOpenInputStream = this.contentResolver.openInputStream(uri);
        if (inputStreamOpenInputStream != null) {
            return getEncodedImage(inputStreamOpenInputStream, -1);
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    private final EncodedImage getCameraImage(Uri uri) throws IOException {
        try {
            ParcelFileDescriptor parcelFileDescriptor = this.contentResolver.openFileDescriptor(uri, "r");
            if (parcelFileDescriptor == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            FileDescriptor fd = parcelFileDescriptor.getFileDescriptor();
            EncodedImage encodedImage = getEncodedImage(new FileInputStream(fd), (int) parcelFileDescriptor.getStatSize());
            Intrinsics.checkNotNullExpressionValue(encodedImage, "this.getEncodedImage(Fil…criptor.statSize.toInt())");
            parcelFileDescriptor.close();
            return encodedImage;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    protected String getProducerName() {
        return PRODUCER_NAME;
    }
}
