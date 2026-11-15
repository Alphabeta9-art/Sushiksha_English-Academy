package com.facebook.drawee.backends.pipeline.info;

import androidx.core.os.EnvironmentCompat;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheGetProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.DataFetchProducer;
import com.facebook.imagepipeline.producers.DiskCacheReadProducer;
import com.facebook.imagepipeline.producers.EncodedMemoryCacheProducer;
import com.facebook.imagepipeline.producers.LocalAssetFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriThumbnailFetchProducer;
import com.facebook.imagepipeline.producers.LocalFileFetchProducer;
import com.facebook.imagepipeline.producers.LocalResourceFetchProducer;
import com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer;
import com.facebook.imagepipeline.producers.NetworkFetchProducer;
import com.facebook.imagepipeline.producers.PartialDiskCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessedBitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.QualifiedResourceFetchProducer;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Ascii;

/* JADX INFO: loaded from: classes11.dex */
public class ImageOriginUtils {
    public static String toString(int imageOrigin) {
        switch (imageOrigin) {
            case 2:
                return "network";
            case 3:
                return "disk";
            case 4:
                return "memory_encoded";
            case 5:
                return "memory_bitmap";
            case 6:
                return "memory_bitmap_shortcut";
            case 7:
                return ImagesContract.LOCAL;
            default:
                return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:50:0x00b2  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int mapProducerNameToImageOrigin(String producerName) {
        byte b;
        switch (producerName.hashCode()) {
            case -1917159454:
                if (!producerName.equals(QualifiedResourceFetchProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = Ascii.SO;
                }
                break;
            case -1914072202:
                if (!producerName.equals(BitmapMemoryCacheGetProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = 0;
                }
                break;
            case -1683996557:
                if (!producerName.equals(LocalResourceFetchProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = Ascii.FF;
                }
                break;
            case -1579985851:
                if (!producerName.equals(LocalFileFetchProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = Ascii.VT;
                }
                break;
            case -1307634203:
                if (!producerName.equals(EncodedMemoryCacheProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = 3;
                }
                break;
            case -1224383234:
                if (!producerName.equals(NetworkFetchProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = 6;
                }
                break;
            case 473552259:
                if (!producerName.equals(LocalVideoThumbnailProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = Ascii.CR;
                }
                break;
            case 656304759:
                if (!producerName.equals(DiskCacheReadProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = 4;
                }
                break;
            case 957714404:
                if (!producerName.equals(BitmapMemoryCacheProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = 1;
                }
                break;
            case 1019542023:
                if (!producerName.equals(LocalAssetFetchProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = 8;
                }
                break;
            case 1023071510:
                if (!producerName.equals(PostprocessedBitmapMemoryCacheProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = 2;
                }
                break;
            case 1721672898:
                if (!producerName.equals(DataFetchProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = 7;
                }
                break;
            case 1793127518:
                if (!producerName.equals(LocalContentUriThumbnailFetchProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = 10;
                }
                break;
            case 2109593398:
                if (!producerName.equals(PartialDiskCacheProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = 5;
                }
                break;
            case 2113652014:
                if (!producerName.equals(LocalContentUriFetchProducer.PRODUCER_NAME)) {
                    b = -1;
                } else {
                    b = 9;
                }
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
            case 1:
            case 2:
                return 5;
            case 3:
                return 4;
            case 4:
            case 5:
                return 3;
            case 6:
                return 2;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return 7;
            default:
                return 1;
        }
    }

    private ImageOriginUtils() {
    }
}
