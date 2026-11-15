package com.facebook.imagepipeline.core;

import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.SimpleDataSource;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.ForwardingRequestListener2;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.InternalRequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImagePipeline.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 n2\u00020\u0001:\u0001nB±\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00110\r\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001e¢\u0006\u0002\u0010\u001fJ\u0006\u00104\u001a\u000205J\u0006\u00106\u001a\u000205J\u0006\u00107\u001a\u000205J\u000e\u00108\u001a\u0002052\u0006\u00109\u001a\u00020:J\u0010\u0010;\u001a\u0002052\b\u00109\u001a\u0004\u0018\u00010:J\u0010\u0010;\u001a\u0002052\b\u0010<\u001a\u0004\u0018\u00010=J\u000e\u0010>\u001a\u0002052\u0006\u00109\u001a\u00020:J&\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J.\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010.\u001a\u00020\u0006J.\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010C\u001a\u00020DJV\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010C\u001a\u00020D2\b\u0010.\u001a\u0004\u0018\u00010\u00062\b\u0010E\u001a\u0004\u0018\u00010F2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020F\u0012\u0002\b\u0003\u0018\u00010HJJ\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010D2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010E\u001a\u0004\u0018\u00010FJ$\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110A0@2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J.\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110A0@2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u0006J$\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J\u0006\u0010K\u001a\u00020FJ\u001c\u0010L\u001a\u0004\u0018\u00010\u000e2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J\u0018\u0010M\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010A2\b\u0010N\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010O\u001a\u00020\u00062\b\u0010P\u001a\u0004\u0018\u00010\u0006J4\u0010Q\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@0\n2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010R\u001a\u0004\u0018\u00010DJ>\u0010Q\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@0\n2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010R\u001a\u0004\u0018\u00010D2\b\u0010.\u001a\u0004\u0018\u00010\u0006JH\u0010Q\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@0\n2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010R\u001a\u0004\u0018\u00010D2\b\u0010.\u001a\u0004\u0018\u00010\u00062\b\u0010E\u001a\u0004\u0018\u00010FJ*\u0010S\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110A0@0\n2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J\u001a\u0010T\u001a\u00020\u00062\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010.\u001a\u0004\u0018\u00010\u0006J\u0010\u0010U\u001a\u00020\u000b2\b\u0010N\u001a\u0004\u0018\u00010\u000eJ\u0006\u0010V\u001a\u000205J\u0010\u0010W\u001a\u00020\u000b2\b\u00109\u001a\u0004\u0018\u00010:J\u0010\u0010W\u001a\u00020\u000b2\b\u0010<\u001a\u0004\u0018\u00010=J\u0016\u0010X\u001a\b\u0012\u0004\u0012\u00020\u000b0@2\b\u00109\u001a\u0004\u0018\u00010:J\u0016\u0010X\u001a\b\u0012\u0004\u0012\u00020\u000b0@2\b\u0010<\u001a\u0004\u0018\u00010=J\u0010\u0010Y\u001a\u00020\u000b2\b\u00109\u001a\u0004\u0018\u00010:J\u001a\u0010Y\u001a\u00020\u000b2\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010Z\u001a\u0004\u0018\u00010[J\u000e\u0010Y\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020=J\u0010\u0010\\\u001a\u00020\u000b2\b\u00109\u001a\u0004\u0018\u00010:J\u0010\u0010\\\u001a\u00020\u000b2\b\u0010<\u001a\u0004\u0018\u00010=J\u0006\u0010]\u001a\u000205J\u0016\u0010^\u001a\b\u0012\u0004\u0012\u00020\u000e0_2\u0006\u00109\u001a\u00020:H\u0002J$\u0010`\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u0001H\u0007J.\u0010`\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u0006H\u0007J\"\u0010b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J*\u0010b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010c\u001a\u00020dJ6\u0010b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010c\u001a\u00020d2\b\u0010.\u001a\u0004\u0018\u00010\u0006H\u0007J,\u0010b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u0006J:\u0010e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010c\u001a\u00020d2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u0006H\u0007J,\u0010e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u0006J\u0006\u0010f\u001a\u000205J\\\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0@\"\u0004\b\u0000\u0010h2\u0012\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0j2\u0006\u0010<\u001a\u00020=2\u0006\u0010C\u001a\u00020D2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u00062\b\u0010E\u001a\u0004\u0018\u00010FH\u0002Jp\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0@\"\u0004\b\u0000\u0010h2\u0012\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0j2\u0006\u0010<\u001a\u00020=2\u0006\u0010C\u001a\u00020D2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u00062\b\u0010E\u001a\u0004\u0018\u00010F2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020F\u0012\u0002\b\u0003\u0018\u00010HH\u0002Jf\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0@\"\u0004\b\u0000\u0010h2\u0012\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0j2\u0006\u0010<\u001a\u00020=2\u0006\u0010C\u001a\u00020D2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u00062\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020F\u0012\u0002\b\u0003\u0018\u00010HH\u0002J@\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0@\"\u0004\b\u0000\u0010h2\u0014\u0010i\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002Hh\u0018\u00010A0j2\u0006\u0010k\u001a\u00020l2\b\u0010.\u001a\u0004\u0018\u00010\u0006JL\u0010m\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\u000e\u0010i\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0j2\u0006\u0010<\u001a\u00020=2\u0006\u0010C\u001a\u00020D2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010c\u001a\u00020d2\b\u0010.\u001a\u0004\u0018\u00010\u0006H\u0002R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00110\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u00100\u001a\u0002018F¢\u0006\u0006\u001a\u0004\b2\u00103¨\u0006o"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipeline;", "", "producerSequenceFactory", "Lcom/facebook/imagepipeline/core/ProducerSequenceFactory;", "requestListeners", "", "Lcom/facebook/imagepipeline/listener/RequestListener;", "requestListener2s", "Lcom/facebook/imagepipeline/listener/RequestListener2;", "isPrefetchEnabledSupplier", "Lcom/facebook/common/internal/Supplier;", "", "bitmapMemoryCache", "Lcom/facebook/imagepipeline/cache/MemoryCache;", "Lcom/facebook/cache/common/CacheKey;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "encodedMemoryCache", "Lcom/facebook/common/memory/PooledByteBuffer;", "mainBufferedDiskCache", "Lcom/facebook/imagepipeline/cache/BufferedDiskCache;", "smallImageBufferedDiskCache", "cacheKeyFactory", "Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "threadHandoffProducerQueue", "Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;", "suppressBitmapPrefetchingSupplier", "lazyDataSource", "callerContextVerifier", "Lcom/facebook/callercontext/CallerContextVerifier;", "config", "Lcom/facebook/imagepipeline/core/ImagePipelineConfigInterface;", "(Lcom/facebook/imagepipeline/core/ProducerSequenceFactory;Ljava/util/Set;Ljava/util/Set;Lcom/facebook/common/internal/Supplier;Lcom/facebook/imagepipeline/cache/MemoryCache;Lcom/facebook/imagepipeline/cache/MemoryCache;Lcom/facebook/imagepipeline/cache/BufferedDiskCache;Lcom/facebook/imagepipeline/cache/BufferedDiskCache;Lcom/facebook/imagepipeline/cache/CacheKeyFactory;Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;Lcom/facebook/common/internal/Supplier;Lcom/facebook/common/internal/Supplier;Lcom/facebook/callercontext/CallerContextVerifier;Lcom/facebook/imagepipeline/core/ImagePipelineConfigInterface;)V", "getBitmapMemoryCache", "()Lcom/facebook/imagepipeline/cache/MemoryCache;", "getCacheKeyFactory", "()Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "getConfig", "()Lcom/facebook/imagepipeline/core/ImagePipelineConfigInterface;", "idCounter", "Ljava/util/concurrent/atomic/AtomicLong;", "isLazyDataSource", "()Lcom/facebook/common/internal/Supplier;", "isPaused", "()Z", "getProducerSequenceFactory", "()Lcom/facebook/imagepipeline/core/ProducerSequenceFactory;", "requestListener", "requestListener2", "usedDiskCacheSize", "", "getUsedDiskCacheSize", "()J", "clearCaches", "", "clearDiskCaches", "clearMemoryCaches", "evictFromCache", "uri", "Landroid/net/Uri;", "evictFromDiskCache", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "evictFromMemoryCache", "fetchDecodedImage", "Lcom/facebook/datasource/DataSource;", "Lcom/facebook/common/references/CloseableReference;", "callerContext", "lowestPermittedRequestLevelOnSubmit", "Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;", "uiComponentId", "", "extras", "", "fetchEncodedImage", "fetchImageFromBitmapCache", "generateUniqueFutureId", "getCacheKey", "getCachedImage", "cacheKey", "getCombinedRequestListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "getDataSourceSupplier", "requestLevel", "getEncodedImageDataSourceSupplier", "getRequestListenerForRequest", "hasCachedImage", "init", "isInBitmapMemoryCache", "isInDiskCache", "isInDiskCacheSync", "cacheChoice", "Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;", "isInEncodedMemoryCache", "pause", "predicateForUri", "Lcom/facebook/common/internal/Predicate;", "prefetchToBitmapCache", "Ljava/lang/Void;", "prefetchToDiskCache", "priority", "Lcom/facebook/imagepipeline/common/Priority;", "prefetchToEncodedCache", "resume", "submitFetchRequest", ExifInterface.GPS_DIRECTION_TRUE, "producerSequence", "Lcom/facebook/imagepipeline/producers/Producer;", "settableProducerContext", "Lcom/facebook/imagepipeline/producers/SettableProducerContext;", "submitPrefetchRequest", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ImagePipeline {
    private final MemoryCache<CacheKey, CloseableImage> bitmapMemoryCache;
    private final CacheKeyFactory cacheKeyFactory;
    private final CallerContextVerifier callerContextVerifier;
    private final ImagePipelineConfigInterface config;
    private final MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache;
    private final AtomicLong idCounter;
    private final Supplier<Boolean> isLazyDataSource;
    private final Supplier<Boolean> isPrefetchEnabledSupplier;
    private final BufferedDiskCache mainBufferedDiskCache;
    private final ProducerSequenceFactory producerSequenceFactory;
    private final RequestListener requestListener;
    private final RequestListener2 requestListener2;
    private final BufferedDiskCache smallImageBufferedDiskCache;
    private final Supplier<Boolean> suppressBitmapPrefetchingSupplier;
    private final ThreadHandoffProducerQueue threadHandoffProducerQueue;
    private static final CancellationException PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    private static final CancellationException NULL_IMAGEREQUEST_EXCEPTION = new CancellationException("ImageRequest is null");

    /* JADX INFO: compiled from: ImagePipeline.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ImageRequest.CacheChoice.values().length];
            try {
                iArr[ImageRequest.CacheChoice.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ImageRequest.CacheChoice.SMALL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, Object obj) {
        return prefetchToEncodedCache$default(this, imageRequest, obj, null, null, 12, null);
    }

    public final DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, Object obj, Priority priority) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        return prefetchToEncodedCache$default(this, imageRequest, obj, priority, null, 8, null);
    }

    public ImagePipeline(ProducerSequenceFactory producerSequenceFactory, Set<? extends RequestListener> requestListeners, Set<? extends RequestListener2> requestListener2s, Supplier<Boolean> isPrefetchEnabledSupplier, MemoryCache<CacheKey, CloseableImage> bitmapMemoryCache, MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache, BufferedDiskCache mainBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, CacheKeyFactory cacheKeyFactory, ThreadHandoffProducerQueue threadHandoffProducerQueue, Supplier<Boolean> suppressBitmapPrefetchingSupplier, Supplier<Boolean> lazyDataSource, CallerContextVerifier callerContextVerifier, ImagePipelineConfigInterface config) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "producerSequenceFactory");
        Intrinsics.checkNotNullParameter(requestListeners, "requestListeners");
        Intrinsics.checkNotNullParameter(requestListener2s, "requestListener2s");
        Intrinsics.checkNotNullParameter(isPrefetchEnabledSupplier, "isPrefetchEnabledSupplier");
        Intrinsics.checkNotNullParameter(bitmapMemoryCache, "bitmapMemoryCache");
        Intrinsics.checkNotNullParameter(encodedMemoryCache, "encodedMemoryCache");
        Intrinsics.checkNotNullParameter(mainBufferedDiskCache, "mainBufferedDiskCache");
        Intrinsics.checkNotNullParameter(smallImageBufferedDiskCache, "smallImageBufferedDiskCache");
        Intrinsics.checkNotNullParameter(cacheKeyFactory, "cacheKeyFactory");
        Intrinsics.checkNotNullParameter(threadHandoffProducerQueue, "threadHandoffProducerQueue");
        Intrinsics.checkNotNullParameter(suppressBitmapPrefetchingSupplier, "suppressBitmapPrefetchingSupplier");
        Intrinsics.checkNotNullParameter(lazyDataSource, "lazyDataSource");
        Intrinsics.checkNotNullParameter(config, "config");
        this.producerSequenceFactory = producerSequenceFactory;
        this.isPrefetchEnabledSupplier = isPrefetchEnabledSupplier;
        this.requestListener = new ForwardingRequestListener((Set<RequestListener>) requestListeners);
        this.requestListener2 = new ForwardingRequestListener2(requestListener2s);
        this.idCounter = new AtomicLong();
        this.bitmapMemoryCache = bitmapMemoryCache;
        this.encodedMemoryCache = encodedMemoryCache;
        this.mainBufferedDiskCache = mainBufferedDiskCache;
        this.smallImageBufferedDiskCache = smallImageBufferedDiskCache;
        this.cacheKeyFactory = cacheKeyFactory;
        this.threadHandoffProducerQueue = threadHandoffProducerQueue;
        this.suppressBitmapPrefetchingSupplier = suppressBitmapPrefetchingSupplier;
        this.isLazyDataSource = lazyDataSource;
        this.callerContextVerifier = callerContextVerifier;
        this.config = config;
    }

    public final ProducerSequenceFactory getProducerSequenceFactory() {
        return this.producerSequenceFactory;
    }

    public final MemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        return this.bitmapMemoryCache;
    }

    public final CacheKeyFactory getCacheKeyFactory() {
        return this.cacheKeyFactory;
    }

    public final Supplier<Boolean> isLazyDataSource() {
        return this.isLazyDataSource;
    }

    public final ImagePipelineConfigInterface getConfig() {
        return this.config;
    }

    public final String generateUniqueFutureId() {
        return String.valueOf(this.idCounter.getAndIncrement());
    }

    public final Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, final Object callerContext, final ImageRequest.RequestLevel requestLevel) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.getDataSourceSupplier.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.fetchDecodedImage$default(ImagePipeline.this, imageRequest, callerContext, requestLevel, null, null, 24, null);
            }

            public String toString() {
                String string = Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
                Intrinsics.checkNotNullExpressionValue(string, "toStringHelper(this).add…est.sourceUri).toString()");
                return string;
            }
        };
    }

    public final Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, final Object callerContext, final ImageRequest.RequestLevel requestLevel, final RequestListener requestListener) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.getDataSourceSupplier.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.fetchDecodedImage$default(ImagePipeline.this, imageRequest, callerContext, requestLevel, requestListener, null, 16, null);
            }

            public String toString() {
                String string = Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
                Intrinsics.checkNotNullExpressionValue(string, "toStringHelper(this).add…est.sourceUri).toString()");
                return string;
            }
        };
    }

    public final Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, final Object callerContext, final ImageRequest.RequestLevel requestLevel, final RequestListener requestListener, final String uiComponentId) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.getDataSourceSupplier.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, callerContext, requestLevel, requestListener, uiComponentId);
            }

            public String toString() {
                String string = Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
                Intrinsics.checkNotNullExpressionValue(string, "toStringHelper(this).add…est.sourceUri).toString()");
                return string;
            }
        };
    }

    public final Supplier<DataSource<CloseableReference<PooledByteBuffer>>> getEncodedImageDataSourceSupplier(final ImageRequest imageRequest, final Object callerContext) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return new Supplier<DataSource<CloseableReference<PooledByteBuffer>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.getEncodedImageDataSourceSupplier.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<PooledByteBuffer>> get() {
                return ImagePipeline.this.fetchEncodedImage(imageRequest, callerContext);
            }

            public String toString() {
                String string = Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
                Intrinsics.checkNotNullExpressionValue(string, "toStringHelper(this).add…est.sourceUri).toString()");
                return string;
            }
        };
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache(ImageRequest imageRequest, Object callerContext) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return fetchDecodedImage(imageRequest, callerContext, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE);
    }

    public static /* synthetic */ DataSource fetchDecodedImage$default(ImagePipeline imagePipeline, ImageRequest imageRequest, Object obj, ImageRequest.RequestLevel requestLevel, RequestListener requestListener, String str, int i, Object obj2) {
        if ((i & 4) != 0) {
            requestLevel = null;
        }
        if ((i & 8) != 0) {
            requestListener = null;
        }
        if ((i & 16) != 0) {
            str = null;
        }
        return imagePipeline.fetchDecodedImage(imageRequest, obj, requestLevel, requestListener, str);
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, RequestListener requestListener, String uiComponentId) {
        ImageRequest.RequestLevel requestLevel;
        if (imageRequest == null) {
            DataSource<CloseableReference<CloseableImage>> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(new NullPointerException());
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "immediateFailedDataSource(NullPointerException())");
            return dataSourceImmediateFailedDataSource;
        }
        try {
            Producer<CloseableReference<CloseableImage>> decodedImageProducerSequence = this.producerSequenceFactory.getDecodedImageProducerSequence(imageRequest);
            if (lowestPermittedRequestLevelOnSubmit != null) {
                requestLevel = lowestPermittedRequestLevelOnSubmit;
            } else {
                try {
                    requestLevel = ImageRequest.RequestLevel.FULL_FETCH;
                } catch (Exception e) {
                    exception = e;
                    DataSource<CloseableReference<CloseableImage>> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(exception);
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n      DataSources.imme…taSource(exception)\n    }");
                    return dataSourceImmediateFailedDataSource2;
                }
            }
            try {
                return submitFetchRequest(decodedImageProducerSequence, imageRequest, requestLevel, callerContext, requestListener, uiComponentId);
            } catch (Exception e2) {
                exception = e2;
                DataSource<CloseableReference<CloseableImage>> dataSourceImmediateFailedDataSource3 = DataSources.immediateFailedDataSource(exception);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource3, "{\n      DataSources.imme…taSource(exception)\n    }");
                return dataSourceImmediateFailedDataSource3;
            }
        } catch (Exception e3) {
            exception = e3;
        }
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext) {
        return fetchDecodedImage$default(this, imageRequest, callerContext, null, null, null, 24, null);
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext, RequestListener requestListener) {
        Intrinsics.checkNotNullParameter(requestListener, "requestListener");
        return fetchDecodedImage$default(this, imageRequest, callerContext, null, requestListener, null, 16, null);
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit) {
        Intrinsics.checkNotNullParameter(lowestPermittedRequestLevelOnSubmit, "lowestPermittedRequestLevelOnSubmit");
        return fetchDecodedImage$default(this, imageRequest, callerContext, lowestPermittedRequestLevelOnSubmit, null, null, 16, null);
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, RequestListener requestListener, String uiComponentId, Map<String, ?> extras) {
        Object exception;
        Intrinsics.checkNotNullParameter(lowestPermittedRequestLevelOnSubmit, "lowestPermittedRequestLevelOnSubmit");
        if (imageRequest == null) {
            DataSource<CloseableReference<CloseableImage>> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(new NullPointerException());
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "immediateFailedDataSource(NullPointerException())");
            return dataSourceImmediateFailedDataSource;
        }
        try {
            try {
                return submitFetchRequest(this.producerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, lowestPermittedRequestLevelOnSubmit, callerContext, requestListener, uiComponentId, extras);
            } catch (Exception e) {
                exception = e;
                Object callerContext2 = exception;
                DataSource<CloseableReference<CloseableImage>> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource((Throwable) callerContext2);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n      DataSources.imme…taSource(exception)\n    }");
                return dataSourceImmediateFailedDataSource2;
            }
        } catch (Exception e2) {
            exception = e2;
        }
    }

    public final DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, Object callerContext) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return fetchEncodedImage(imageRequest, callerContext, null);
    }

    public final DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, Object callerContext, RequestListener requestListener) {
        ImageRequest imageRequest2;
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        ImageRequest imageRequest3 = imageRequest;
        if (imageRequest3.getSourceUri() == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        try {
            Producer<CloseableReference<PooledByteBuffer>> encodedImageProducerSequence = this.producerSequenceFactory.getEncodedImageProducerSequence(imageRequest3);
            if (imageRequest3.getResizeOptions() == null) {
                imageRequest2 = imageRequest3;
            } else {
                try {
                    ImageRequest imageRequest4 = ImageRequestBuilder.fromRequest(imageRequest3).setResizeOptions(null).build();
                    Intrinsics.checkNotNullExpressionValue(imageRequest4, "fromRequest(imageRequest…sizeOptions(null).build()");
                    imageRequest2 = imageRequest4;
                } catch (Exception e) {
                    exception = e;
                    Exception callerContext2 = exception;
                    DataSource<CloseableReference<PooledByteBuffer>> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(callerContext2);
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n      DataSources.imme…taSource(exception)\n    }");
                    return dataSourceImmediateFailedDataSource;
                }
            }
            try {
                try {
                    return submitFetchRequest(encodedImageProducerSequence, imageRequest2, ImageRequest.RequestLevel.FULL_FETCH, callerContext, requestListener, null, null);
                } catch (Exception e2) {
                    exception = e2;
                    imageRequest3 = imageRequest2;
                    Exception callerContext3 = exception;
                    DataSource<CloseableReference<PooledByteBuffer>> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(callerContext3);
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n      DataSources.imme…taSource(exception)\n    }");
                    return dataSourceImmediateFailedDataSource2;
                }
            } catch (Exception e3) {
                exception = e3;
                imageRequest3 = imageRequest2;
            }
        } catch (Exception e4) {
            exception = e4;
        }
    }

    public final DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, Object callerContext) {
        return prefetchToBitmapCache(imageRequest, callerContext, null);
    }

    public final DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, Object callerContext, RequestListener requestListener) {
        DataSource<Void> dataSourceSubmitPrefetchRequest;
        DataSource<Void> dataSourceImmediateSuccessfulDataSource;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        boolean zBooleanValue = true;
        if (!FrescoSystrace.isTracing()) {
            if (!this.isPrefetchEnabledSupplier.get().booleanValue()) {
                DataSource<Void> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "immediateFailedDataSource(PREFETCH_EXCEPTION)");
                return dataSourceImmediateFailedDataSource;
            }
            try {
                if (this.config.getExperiments().getPrefetchShortcutEnabled() && isInBitmapMemoryCache(imageRequest)) {
                    DataSource<Void> dataSourceImmediateSuccessfulDataSource2 = DataSources.immediateSuccessfulDataSource();
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateSuccessfulDataSource2, "immediateSuccessfulDataSource()");
                    return dataSourceImmediateSuccessfulDataSource2;
                }
                if (imageRequest == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                Boolean shouldDecodePrefetches = imageRequest.shouldDecodePrefetches();
                if (shouldDecodePrefetches == null) {
                    Boolean bool = this.suppressBitmapPrefetchingSupplier.get();
                    Intrinsics.checkNotNullExpressionValue(bool, "suppressBitmapPrefetchin…                   .get()");
                    zBooleanValue = bool.booleanValue();
                } else if (shouldDecodePrefetches.booleanValue()) {
                    zBooleanValue = false;
                }
                boolean skipBitmapCache = zBooleanValue;
                return submitPrefetchRequest(skipBitmapCache ? this.producerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest) : this.producerSequenceFactory.getDecodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, Priority.MEDIUM, requestListener);
            } catch (Exception exception) {
                DataSource<Void> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(exception);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n          DataSources.…urce(exception)\n        }");
                return dataSourceImmediateFailedDataSource2;
            }
        }
        FrescoSystrace.beginSection("ImagePipeline#prefetchToBitmapCache");
        try {
            if (this.isPrefetchEnabledSupplier.get().booleanValue()) {
                try {
                    if (!this.config.getExperiments().getPrefetchShortcutEnabled() || !isInBitmapMemoryCache(imageRequest)) {
                        if (imageRequest == null) {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        Boolean shouldDecodePrefetches2 = imageRequest.shouldDecodePrefetches();
                        if (shouldDecodePrefetches2 == null) {
                            Boolean bool2 = this.suppressBitmapPrefetchingSupplier.get();
                            Intrinsics.checkNotNullExpressionValue(bool2, "suppressBitmapPrefetchin…                   .get()");
                            zBooleanValue = bool2.booleanValue();
                        } else if (shouldDecodePrefetches2.booleanValue()) {
                            zBooleanValue = false;
                        }
                        boolean skipBitmapCache2 = zBooleanValue;
                        dataSourceSubmitPrefetchRequest = submitPrefetchRequest(skipBitmapCache2 ? this.producerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest) : this.producerSequenceFactory.getDecodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, Priority.MEDIUM, requestListener);
                        FrescoSystrace.endSection();
                        return dataSourceSubmitPrefetchRequest;
                    }
                    dataSourceImmediateSuccessfulDataSource = DataSources.immediateSuccessfulDataSource();
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateSuccessfulDataSource, "immediateSuccessfulDataSource()");
                } catch (Exception exception2) {
                    DataSource<Void> dataSourceImmediateFailedDataSource3 = DataSources.immediateFailedDataSource(exception2);
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource3, "{\n          DataSources.…urce(exception)\n        }");
                    dataSourceSubmitPrefetchRequest = dataSourceImmediateFailedDataSource3;
                }
            } else {
                dataSourceImmediateSuccessfulDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateSuccessfulDataSource, "immediateFailedDataSource(PREFETCH_EXCEPTION)");
            }
            FrescoSystrace.endSection();
            return dataSourceImmediateSuccessfulDataSource;
        } catch (Throwable th) {
            FrescoSystrace.endSection();
            throw th;
        }
    }

    public final DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext, RequestListener requestListener) {
        return prefetchToDiskCache(imageRequest, callerContext, Priority.MEDIUM, requestListener);
    }

    public final DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext) {
        return prefetchToDiskCache(imageRequest, callerContext, Priority.MEDIUM, null);
    }

    public final DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext, Priority priority) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        return prefetchToDiskCache(imageRequest, callerContext, priority, null);
    }

    public final DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext, Priority priority, RequestListener requestListener) {
        Object exception;
        Intrinsics.checkNotNullParameter(priority, "priority");
        if (!this.isPrefetchEnabledSupplier.get().booleanValue()) {
            DataSource<Void> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "immediateFailedDataSource(PREFETCH_EXCEPTION)");
            return dataSourceImmediateFailedDataSource;
        }
        if (imageRequest == null) {
            DataSource<Void> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(new NullPointerException("imageRequest is null"));
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n      DataSources.imme…eRequest is null\"))\n    }");
            return dataSourceImmediateFailedDataSource2;
        }
        try {
            try {
                return submitPrefetchRequest(this.producerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, priority, requestListener);
            } catch (Exception e) {
                exception = e;
                Object callerContext2 = exception;
                DataSource<Void> dataSourceImmediateFailedDataSource3 = DataSources.immediateFailedDataSource((Throwable) callerContext2);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource3, "{\n        DataSources.im…Source(exception)\n      }");
                return dataSourceImmediateFailedDataSource3;
            }
        } catch (Exception e2) {
            exception = e2;
        }
    }

    public final DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, Object callerContext, RequestListener requestListener) {
        return prefetchToEncodedCache(imageRequest, callerContext, Priority.MEDIUM, requestListener);
    }

    public static /* synthetic */ DataSource prefetchToEncodedCache$default(ImagePipeline imagePipeline, ImageRequest imageRequest, Object obj, Priority priority, RequestListener requestListener, int i, Object obj2) {
        if ((i & 4) != 0) {
            priority = Priority.MEDIUM;
        }
        if ((i & 8) != 0) {
            requestListener = null;
        }
        return imagePipeline.prefetchToEncodedCache(imageRequest, obj, priority, requestListener);
    }

    public final DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, Object callerContext, Priority priority, RequestListener requestListener) {
        DataSource<Void> dataSourceSubmitPrefetchRequest;
        DataSource<Void> dataSourceImmediateSuccessfulDataSource;
        Intrinsics.checkNotNullParameter(priority, "priority");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            if (!this.isPrefetchEnabledSupplier.get().booleanValue()) {
                DataSource<Void> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "immediateFailedDataSource(PREFETCH_EXCEPTION)");
                return dataSourceImmediateFailedDataSource;
            }
            if (imageRequest == null) {
                DataSource<Void> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(NULL_IMAGEREQUEST_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "immediateFailedDataSourc…L_IMAGEREQUEST_EXCEPTION)");
                return dataSourceImmediateFailedDataSource2;
            }
            try {
                if (!this.config.getExperiments().getPrefetchShortcutEnabled() || !isInEncodedMemoryCache(imageRequest)) {
                    return submitPrefetchRequest(this.producerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, priority, requestListener);
                }
                DataSource<Void> dataSourceImmediateSuccessfulDataSource2 = DataSources.immediateSuccessfulDataSource();
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateSuccessfulDataSource2, "immediateSuccessfulDataSource()");
                return dataSourceImmediateSuccessfulDataSource2;
            } catch (Exception exception) {
                DataSource<Void> dataSourceImmediateFailedDataSource3 = DataSources.immediateFailedDataSource(exception);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource3, "{\n          DataSources.…urce(exception)\n        }");
                return dataSourceImmediateFailedDataSource3;
            }
        }
        FrescoSystrace.beginSection("ImagePipeline#prefetchToEncodedCache");
        try {
            if (!this.isPrefetchEnabledSupplier.get().booleanValue()) {
                dataSourceImmediateSuccessfulDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateSuccessfulDataSource, "immediateFailedDataSource(PREFETCH_EXCEPTION)");
            } else if (imageRequest == null) {
                dataSourceImmediateSuccessfulDataSource = DataSources.immediateFailedDataSource(NULL_IMAGEREQUEST_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateSuccessfulDataSource, "immediateFailedDataSourc…L_IMAGEREQUEST_EXCEPTION)");
            } else {
                try {
                    if (!this.config.getExperiments().getPrefetchShortcutEnabled() || !isInEncodedMemoryCache(imageRequest)) {
                        dataSourceSubmitPrefetchRequest = submitPrefetchRequest(this.producerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, priority, requestListener);
                        return dataSourceSubmitPrefetchRequest;
                    }
                    dataSourceImmediateSuccessfulDataSource = DataSources.immediateSuccessfulDataSource();
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateSuccessfulDataSource, "immediateSuccessfulDataSource()");
                } catch (Exception exception2) {
                    DataSource<Void> dataSourceImmediateFailedDataSource4 = DataSources.immediateFailedDataSource(exception2);
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource4, "{\n          DataSources.…urce(exception)\n        }");
                    dataSourceSubmitPrefetchRequest = dataSourceImmediateFailedDataSource4;
                }
            }
            return dataSourceImmediateSuccessfulDataSource;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final void evictFromMemoryCache(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Predicate<CacheKey> predicatePredicateForUri = predicateForUri(uri);
        this.bitmapMemoryCache.removeAll(predicatePredicateForUri);
        this.encodedMemoryCache.removeAll(predicatePredicateForUri);
    }

    public final void evictFromDiskCache(Uri uri) {
        ImageRequest imageRequestFromUri = ImageRequest.fromUri(uri);
        if (imageRequestFromUri == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        evictFromDiskCache(imageRequestFromUri);
    }

    public final void evictFromDiskCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return;
        }
        CacheKey cacheKey = this.cacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        BufferedDiskCache bufferedDiskCache = this.mainBufferedDiskCache;
        Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
        bufferedDiskCache.remove(cacheKey);
        this.smallImageBufferedDiskCache.remove(cacheKey);
    }

    public final void evictFromCache(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        evictFromMemoryCache(uri);
        evictFromDiskCache(uri);
    }

    static final boolean clearMemoryCaches$lambda$2(CacheKey it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return true;
    }

    public final void clearMemoryCaches() {
        Predicate<CacheKey> predicate = new Predicate() { // from class: com.facebook.imagepipeline.core.ImagePipeline$$ExternalSyntheticLambda3
            @Override // com.facebook.common.internal.Predicate
            public final boolean apply(Object obj) {
                return ImagePipeline.clearMemoryCaches$lambda$2((CacheKey) obj);
            }
        };
        this.bitmapMemoryCache.removeAll(predicate);
        this.encodedMemoryCache.removeAll(predicate);
    }

    public final void clearDiskCaches() {
        this.mainBufferedDiskCache.clearAll();
        this.smallImageBufferedDiskCache.clearAll();
    }

    public final long getUsedDiskCacheSize() {
        return this.mainBufferedDiskCache.getSize() + this.smallImageBufferedDiskCache.getSize();
    }

    public final void clearCaches() {
        clearMemoryCaches();
        clearDiskCaches();
    }

    public final boolean isInBitmapMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.bitmapMemoryCache.contains(predicateForUri(uri));
    }

    public final boolean isInBitmapMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CacheKey cacheKey = this.cacheKeyFactory.getBitmapCacheKey(imageRequest, null);
        MemoryCache<CacheKey, CloseableImage> memoryCache = this.bitmapMemoryCache;
        Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
        CloseableReference<CloseableImage> closeableReference = memoryCache.get(cacheKey);
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public final boolean isInEncodedMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.encodedMemoryCache.contains(predicateForUri(uri));
    }

    public final boolean isInEncodedMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CacheKey cacheKey = this.cacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        MemoryCache<CacheKey, PooledByteBuffer> memoryCache = this.encodedMemoryCache;
        Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
        CloseableReference<PooledByteBuffer> closeableReference = memoryCache.get(cacheKey);
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public final boolean isInDiskCacheSync(Uri uri) {
        return isInDiskCacheSync(uri, ImageRequest.CacheChoice.SMALL) || isInDiskCacheSync(uri, ImageRequest.CacheChoice.DEFAULT);
    }

    public final boolean isInDiskCacheSync(Uri uri, ImageRequest.CacheChoice cacheChoice) {
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri).setCacheChoice(cacheChoice).build();
        Intrinsics.checkNotNullExpressionValue(imageRequest, "imageRequest");
        return isInDiskCacheSync(imageRequest);
    }

    public final boolean isInDiskCacheSync(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        CacheKey cacheKey = this.cacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        ImageRequest.CacheChoice cacheChoice = imageRequest.getCacheChoice();
        Intrinsics.checkNotNullExpressionValue(cacheChoice, "imageRequest.cacheChoice");
        switch (WhenMappings.$EnumSwitchMapping$0[cacheChoice.ordinal()]) {
            case 1:
                BufferedDiskCache bufferedDiskCache = this.mainBufferedDiskCache;
                Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
                return bufferedDiskCache.diskCheckSync(cacheKey);
            case 2:
                BufferedDiskCache bufferedDiskCache2 = this.smallImageBufferedDiskCache;
                Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
                return bufferedDiskCache2.diskCheckSync(cacheKey);
            default:
                return false;
        }
    }

    public final DataSource<Boolean> isInDiskCache(Uri uri) {
        ImageRequest imageRequestFromUri = ImageRequest.fromUri(uri);
        if (imageRequestFromUri != null) {
            return isInDiskCache(imageRequestFromUri);
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public final DataSource<Boolean> isInDiskCache(ImageRequest imageRequest) {
        final CacheKey cacheKey = this.cacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        final SimpleDataSource dataSource = SimpleDataSource.create();
        BufferedDiskCache bufferedDiskCache = this.mainBufferedDiskCache;
        Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
        bufferedDiskCache.contains(cacheKey).continueWithTask(new Continuation() { // from class: com.facebook.imagepipeline.core.ImagePipeline$$ExternalSyntheticLambda0
            @Override // bolts.Continuation
            public final Object then(Task task) {
                return ImagePipeline.isInDiskCache$lambda$3(this.f$0, cacheKey, task);
            }
        }).continueWith(new Continuation() { // from class: com.facebook.imagepipeline.core.ImagePipeline$$ExternalSyntheticLambda1
            @Override // bolts.Continuation
            public final Object then(Task task) {
                return ImagePipeline.isInDiskCache$lambda$4(dataSource, task);
            }
        });
        Intrinsics.checkNotNullExpressionValue(dataSource, "dataSource");
        return dataSource;
    }

    static final Task isInDiskCache$lambda$3(ImagePipeline this$0, CacheKey cacheKey, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!task.isCancelled() && !task.isFaulted()) {
            Object result = task.getResult();
            Intrinsics.checkNotNullExpressionValue(result, "task.result");
            if (((Boolean) result).booleanValue()) {
                return Task.forResult(true);
            }
        }
        BufferedDiskCache bufferedDiskCache = this$0.smallImageBufferedDiskCache;
        Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
        return bufferedDiskCache.contains(cacheKey);
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0020  */
    static final Void isInDiskCache$lambda$4(SimpleDataSource $dataSource, Task task) {
        boolean z;
        if (task.isCancelled() || task.isFaulted()) {
            z = false;
        } else {
            Object result = task.getResult();
            Intrinsics.checkNotNullExpressionValue(result, "task.result");
            if (((Boolean) result).booleanValue()) {
                z = true;
            } else {
                z = false;
            }
        }
        $dataSource.setResult(Boolean.valueOf(z));
        return null;
    }

    public final CacheKey getCacheKey(ImageRequest imageRequest, Object callerContext) {
        CacheKey bitmapCacheKey;
        CacheKey bitmapCacheKey2;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            if (imageRequest == null) {
                return null;
            }
            if (imageRequest.getPostprocessor() != null) {
                bitmapCacheKey2 = this.cacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, callerContext);
            } else {
                bitmapCacheKey2 = this.cacheKeyFactory.getBitmapCacheKey(imageRequest, callerContext);
            }
            CacheKey cacheKey = bitmapCacheKey2;
            return cacheKey;
        }
        FrescoSystrace.beginSection("ImagePipeline#getCacheKey");
        CacheKey cacheKey2 = null;
        if (imageRequest != null) {
            try {
                if (imageRequest.getPostprocessor() != null) {
                    bitmapCacheKey = this.cacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, callerContext);
                } else {
                    bitmapCacheKey = this.cacheKeyFactory.getBitmapCacheKey(imageRequest, callerContext);
                }
                cacheKey2 = bitmapCacheKey;
            } finally {
                FrescoSystrace.endSection();
            }
        }
        return cacheKey2;
    }

    public final CloseableReference<CloseableImage> getCachedImage(CacheKey cacheKey) {
        if (cacheKey == null) {
            return null;
        }
        CloseableReference<CloseableImage> closeableReference = this.bitmapMemoryCache.get(cacheKey);
        if (closeableReference != null && !closeableReference.get().getQualityInfo().isOfFullQuality()) {
            closeableReference.close();
            return null;
        }
        return closeableReference;
    }

    public final boolean hasCachedImage(CacheKey cacheKey) {
        if (cacheKey == null) {
            return false;
        }
        return this.bitmapMemoryCache.contains(cacheKey);
    }

    private final <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producerSequence, ImageRequest imageRequest, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext, RequestListener requestListener, String uiComponentId) {
        return submitFetchRequest(producerSequence, imageRequest, lowestPermittedRequestLevelOnSubmit, callerContext, requestListener, uiComponentId, null);
    }

    private final <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producerSequence, ImageRequest imageRequest, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext, RequestListener requestListener, String uiComponentId, Map<String, ?> extras) {
        DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource;
        String str;
        String str2;
        boolean z;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (FrescoSystrace.isTracing()) {
            boolean z2 = true;
            FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
            try {
                InternalRequestListener requestListener2 = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.requestListener2);
                CallerContextVerifier callerContextVerifier = this.callerContextVerifier;
                if (callerContextVerifier != null) {
                    callerContextVerifier.verifyCallerContext(callerContext, false);
                }
                try {
                    ImageRequest.RequestLevel lowestPermittedRequestLevel = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit);
                    Intrinsics.checkNotNullExpressionValue(lowestPermittedRequestLevel, "getMax(\n                …ttedRequestLevelOnSubmit)");
                    String strGenerateUniqueFutureId = generateUniqueFutureId();
                    InternalRequestListener internalRequestListener = requestListener2;
                    if (!imageRequest.getProgressiveRenderingEnabled() && UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                        z2 = false;
                    }
                    SettableProducerContext settableProducerContext = new SettableProducerContext(imageRequest, strGenerateUniqueFutureId, uiComponentId, internalRequestListener, callerContext, lowestPermittedRequestLevel, false, z2, imageRequest.getPriority(), this.config);
                    settableProducerContext.putExtras(extras);
                    dataSourceImmediateFailedDataSource = CloseableProducerToDataSourceAdapter.create(producerSequence, settableProducerContext, requestListener2);
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          val lowestPe…questListener2)\n        }");
                } catch (Exception exception) {
                    dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(exception);
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          DataSources.…urce(exception)\n        }");
                }
                FrescoSystrace.endSection();
                return dataSourceImmediateFailedDataSource;
            } catch (Throwable th) {
                FrescoSystrace.endSection();
                throw th;
            }
        }
        InternalRequestListener requestListener3 = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.requestListener2);
        CallerContextVerifier callerContextVerifier2 = this.callerContextVerifier;
        if (callerContextVerifier2 != null) {
            callerContextVerifier2.verifyCallerContext(callerContext, false);
        }
        try {
            ImageRequest.RequestLevel lowestPermittedRequestLevel2 = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit);
            Intrinsics.checkNotNullExpressionValue(lowestPermittedRequestLevel2, "getMax(\n                …ttedRequestLevelOnSubmit)");
            String strGenerateUniqueFutureId2 = generateUniqueFutureId();
            try {
                InternalRequestListener internalRequestListener2 = requestListener3;
                if (imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                    str2 = "{\n          DataSources.…urce(exception)\n        }";
                    z = true;
                } else {
                    z = false;
                    str2 = "{\n          DataSources.…urce(exception)\n        }";
                }
                try {
                    str = str2;
                    try {
                        SettableProducerContext settableProducerContext2 = new SettableProducerContext(imageRequest, strGenerateUniqueFutureId2, uiComponentId, internalRequestListener2, callerContext, lowestPermittedRequestLevel2, false, z, imageRequest.getPriority(), this.config);
                        settableProducerContext2.putExtras(extras);
                        DataSource<CloseableReference<T>> dataSourceCreate = CloseableProducerToDataSourceAdapter.create(producerSequence, settableProducerContext2, requestListener3);
                        Intrinsics.checkNotNullExpressionValue(dataSourceCreate, "{\n          val lowestPe…questListener2)\n        }");
                        return dataSourceCreate;
                    } catch (Exception e) {
                        exception = e;
                        DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(exception);
                        Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, str);
                        return dataSourceImmediateFailedDataSource2;
                    }
                } catch (Exception e2) {
                    exception = e2;
                    str = str2;
                }
            } catch (Exception e3) {
                exception = e3;
                str = "{\n          DataSources.…urce(exception)\n        }";
            }
        } catch (Exception e4) {
            exception = e4;
            str = "{\n          DataSources.…urce(exception)\n        }";
        }
    }

    private final <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producerSequence, ImageRequest imageRequest, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext, RequestListener requestListener, Map<String, ?> extras) {
        DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource;
        String str;
        String str2;
        boolean z;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
            try {
                InternalRequestListener requestListener2 = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.requestListener2);
                CallerContextVerifier callerContextVerifier = this.callerContextVerifier;
                if (callerContextVerifier != null) {
                    callerContextVerifier.verifyCallerContext(callerContext, false);
                }
                try {
                    ImageRequest.RequestLevel lowestPermittedRequestLevel = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit);
                    Intrinsics.checkNotNullExpressionValue(lowestPermittedRequestLevel, "getMax(\n                …ttedRequestLevelOnSubmit)");
                    SettableProducerContext settableProducerContext = new SettableProducerContext(imageRequest, generateUniqueFutureId(), null, requestListener2, callerContext, lowestPermittedRequestLevel, false, imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()), imageRequest.getPriority(), this.config);
                    dataSourceImmediateFailedDataSource = CloseableProducerToDataSourceAdapter.create(producerSequence, settableProducerContext, requestListener2);
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          val lowestPe…questListener2)\n        }");
                } catch (Exception exception) {
                    dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(exception);
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          DataSources.…urce(exception)\n        }");
                }
                FrescoSystrace.endSection();
                return dataSourceImmediateFailedDataSource;
            } catch (Throwable th) {
                FrescoSystrace.endSection();
                throw th;
            }
        }
        InternalRequestListener requestListener3 = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.requestListener2);
        CallerContextVerifier callerContextVerifier2 = this.callerContextVerifier;
        if (callerContextVerifier2 != null) {
            callerContextVerifier2.verifyCallerContext(callerContext, false);
        }
        try {
            ImageRequest.RequestLevel lowestPermittedRequestLevel2 = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit);
            Intrinsics.checkNotNullExpressionValue(lowestPermittedRequestLevel2, "getMax(\n                …ttedRequestLevelOnSubmit)");
            String strGenerateUniqueFutureId = generateUniqueFutureId();
            try {
                InternalRequestListener internalRequestListener = requestListener3;
                if (imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                    str2 = "{\n          DataSources.…urce(exception)\n        }";
                    z = true;
                } else {
                    z = false;
                    str2 = "{\n          DataSources.…urce(exception)\n        }";
                }
                try {
                    str = str2;
                    try {
                        SettableProducerContext settableProducerContext2 = new SettableProducerContext(imageRequest, strGenerateUniqueFutureId, null, internalRequestListener, callerContext, lowestPermittedRequestLevel2, false, z, imageRequest.getPriority(), this.config);
                        DataSource<CloseableReference<T>> dataSourceCreate = CloseableProducerToDataSourceAdapter.create(producerSequence, settableProducerContext2, requestListener3);
                        Intrinsics.checkNotNullExpressionValue(dataSourceCreate, "{\n          val lowestPe…questListener2)\n        }");
                        return dataSourceCreate;
                    } catch (Exception e) {
                        exception = e;
                        DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(exception);
                        Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, str);
                        return dataSourceImmediateFailedDataSource2;
                    }
                } catch (Exception e2) {
                    exception = e2;
                    str = str2;
                }
            } catch (Exception e3) {
                exception = e3;
                str = "{\n          DataSources.…urce(exception)\n        }";
            }
        } catch (Exception e4) {
            exception = e4;
            str = "{\n          DataSources.…urce(exception)\n        }";
        }
    }

    public final <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producerSequence, SettableProducerContext settableProducerContext, RequestListener requestListener) {
        DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource;
        Intrinsics.checkNotNullParameter(producerSequence, "producerSequence");
        Intrinsics.checkNotNullParameter(settableProducerContext, "settableProducerContext");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            try {
                InternalRequestListener requestListener2 = new InternalRequestListener(requestListener, this.requestListener2);
                DataSource<CloseableReference<T>> dataSourceCreate = CloseableProducerToDataSourceAdapter.create(producerSequence, settableProducerContext, requestListener2);
                Intrinsics.checkNotNullExpressionValue(dataSourceCreate, "{\n          val requestL…questListener2)\n        }");
                return dataSourceCreate;
            } catch (Exception exception) {
                DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(exception);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n          DataSources.…urce(exception)\n        }");
                return dataSourceImmediateFailedDataSource2;
            }
        }
        FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        try {
            try {
                InternalRequestListener requestListener3 = new InternalRequestListener(requestListener, this.requestListener2);
                dataSourceImmediateFailedDataSource = CloseableProducerToDataSourceAdapter.create(producerSequence, settableProducerContext, requestListener3);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          val requestL…questListener2)\n        }");
            } catch (Exception exception2) {
                dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(exception2);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          DataSources.…urce(exception)\n        }");
            }
            return dataSourceImmediateFailedDataSource;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    private final DataSource<Void> submitPrefetchRequest(Producer<Void> producerSequence, ImageRequest imageRequest, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext, Priority priority, RequestListener requestListener) {
        Object obj;
        InternalRequestListener requestListener2 = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.requestListener2);
        CallerContextVerifier callerContextVerifier = this.callerContextVerifier;
        if (callerContextVerifier != null) {
            obj = callerContext;
            callerContextVerifier.verifyCallerContext(obj, true);
        } else {
            obj = callerContext;
        }
        try {
            try {
                ImageRequest.RequestLevel lowestPermittedRequestLevel = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit);
                Intrinsics.checkNotNullExpressionValue(lowestPermittedRequestLevel, "getMax(\n              im…ttedRequestLevelOnSubmit)");
                String strGenerateUniqueFutureId = generateUniqueFutureId();
                InternalRequestListener internalRequestListener = requestListener2;
                ImagePipelineExperiments experiments = this.config.getExperiments();
                boolean z = false;
                if ((experiments != null && experiments.getAllowProgressiveOnPrefetch()) && imageRequest.getProgressiveRenderingEnabled()) {
                    z = true;
                }
                SettableProducerContext settableProducerContext = new SettableProducerContext(imageRequest, strGenerateUniqueFutureId, internalRequestListener, obj, lowestPermittedRequestLevel, true, z, priority, this.config);
                return ProducerToDataSourceAdapter.INSTANCE.create(producerSequence, settableProducerContext, requestListener2);
            } catch (Exception e) {
                exception = e;
                DataSource<Void> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(exception);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n      DataSources.imme…taSource(exception)\n    }");
                return dataSourceImmediateFailedDataSource;
            }
        } catch (Exception e2) {
            exception = e2;
        }
    }

    public final RequestListener getRequestListenerForRequest(ImageRequest imageRequest, RequestListener requestListener) {
        ForwardingRequestListener forwardingRequestListener;
        if (imageRequest == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        if (requestListener == null) {
            if (imageRequest.getRequestListener() == null) {
                return this.requestListener;
            }
            return new ForwardingRequestListener(this.requestListener, imageRequest.getRequestListener());
        }
        if (imageRequest.getRequestListener() == null) {
            forwardingRequestListener = new ForwardingRequestListener(this.requestListener, requestListener);
        } else {
            forwardingRequestListener = new ForwardingRequestListener(this.requestListener, requestListener, imageRequest.getRequestListener());
        }
        return forwardingRequestListener;
    }

    public final RequestListener getCombinedRequestListener(RequestListener listener) {
        return listener != null ? new ForwardingRequestListener(this.requestListener, listener) : this.requestListener;
    }

    private final Predicate<CacheKey> predicateForUri(final Uri uri) {
        return new Predicate() { // from class: com.facebook.imagepipeline.core.ImagePipeline$$ExternalSyntheticLambda2
            @Override // com.facebook.common.internal.Predicate
            public final boolean apply(Object obj) {
                return ImagePipeline.predicateForUri$lambda$10(uri, (CacheKey) obj);
            }
        };
    }

    static final boolean predicateForUri$lambda$10(Uri uri, CacheKey key) {
        Intrinsics.checkNotNullParameter(uri, "$uri");
        Intrinsics.checkNotNullParameter(key, "key");
        return key.containsUri(uri);
    }

    public final void pause() {
        this.threadHandoffProducerQueue.startQueueing();
    }

    public final void resume() {
        this.threadHandoffProducerQueue.stopQueuing();
    }

    public final boolean isPaused() {
        return this.threadHandoffProducerQueue.isQueueing();
    }

    public final void init() {
    }
}
