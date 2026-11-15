package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.common.internal.Objects;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class HttpUrlConnectionNetworkFetcher extends BaseNetworkFetcher<HttpUrlConnectionNetworkFetchState> {
    private static final String FETCH_TIME = "fetch_time";
    public static final int HTTP_DEFAULT_TIMEOUT = 30000;
    public static final int HTTP_PERMANENT_REDIRECT = 308;
    public static final int HTTP_TEMPORARY_REDIRECT = 307;
    private static final String IMAGE_SIZE = "image_size";
    private static final int MAX_REDIRECTS = 5;
    private static final int NUM_NETWORK_THREADS = 3;
    private static final String QUEUE_TIME = "queue_time";
    private static final String TOTAL_TIME = "total_time";
    private final ExecutorService mExecutorService;
    private int mHttpConnectionTimeout;
    private final MonotonicClock mMonotonicClock;

    @Nullable
    private final Map<String, String> mRequestHeaders;

    @Nullable
    private String mUserAgent;

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public /* bridge */ /* synthetic */ FetchState createFetchState(Consumer consumer, ProducerContext producerContext) {
        return createFetchState((Consumer<EncodedImage>) consumer, producerContext);
    }

    public static class HttpUrlConnectionNetworkFetchState extends FetchState {
        private long fetchCompleteTime;
        private long responseTime;
        private long submitTime;

        public HttpUrlConnectionNetworkFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
        }
    }

    public HttpUrlConnectionNetworkFetcher() {
        this((String) null, (Map<String, String>) null, RealtimeSinceBootClock.get());
    }

    public HttpUrlConnectionNetworkFetcher(int httpConnectionTimeout) {
        this((String) null, (Map<String, String>) null, RealtimeSinceBootClock.get());
        this.mHttpConnectionTimeout = httpConnectionTimeout;
    }

    public HttpUrlConnectionNetworkFetcher(String userAgent, int httpConnectionTimeout) {
        this(userAgent, (Map<String, String>) null, RealtimeSinceBootClock.get());
        this.mHttpConnectionTimeout = httpConnectionTimeout;
    }

    public HttpUrlConnectionNetworkFetcher(String userAgent, @Nullable Map<String, String> requestHeaders, int httpConnectionTimeout) {
        this(userAgent, requestHeaders, RealtimeSinceBootClock.get());
        this.mHttpConnectionTimeout = httpConnectionTimeout;
    }

    HttpUrlConnectionNetworkFetcher(@Nullable String userAgent, @Nullable Map<String, String> requestHeaders, MonotonicClock monotonicClock) {
        this.mExecutorService = Executors.newFixedThreadPool(3);
        this.mMonotonicClock = monotonicClock;
        this.mRequestHeaders = requestHeaders;
        this.mUserAgent = userAgent;
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public HttpUrlConnectionNetworkFetchState createFetchState(Consumer<EncodedImage> consumer, ProducerContext context) {
        return new HttpUrlConnectionNetworkFetchState(consumer, context);
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public void fetch(final HttpUrlConnectionNetworkFetchState fetchState, final NetworkFetcher.Callback callback) {
        fetchState.submitTime = this.mMonotonicClock.now();
        final Future<?> future = this.mExecutorService.submit(new Runnable() { // from class: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.1
            @Override // java.lang.Runnable
            public void run() {
                HttpUrlConnectionNetworkFetcher.this.fetchSync(fetchState, callback);
            }
        });
        fetchState.getContext().addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.2
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                if (future.cancel(false)) {
                    callback.onCancellation();
                }
            }
        });
    }

    void fetchSync(HttpUrlConnectionNetworkFetchState fetchState, NetworkFetcher.Callback callback) {
        HttpURLConnection connection = null;
        InputStream is = null;
        try {
            connection = downloadFrom(fetchState.getUri(), 5);
            fetchState.responseTime = this.mMonotonicClock.now();
            if (connection != null) {
                is = connection.getInputStream();
                callback.onResponse(is, -1);
            }
        } catch (IOException e) {
            callback.onFailure(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e2) {
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private HttpURLConnection downloadFrom(Uri uri, int maxRedirects) throws IOException {
        String message;
        HttpURLConnection connection = openConnectionTo(uri);
        if (this.mUserAgent != null) {
            connection.setRequestProperty(HttpHeaders.USER_AGENT, this.mUserAgent);
        }
        if (this.mRequestHeaders != null) {
            for (Map.Entry<String, String> entry : this.mRequestHeaders.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        connection.setConnectTimeout(this.mHttpConnectionTimeout);
        int responseCode = connection.getResponseCode();
        if (isHttpSuccess(responseCode)) {
            return connection;
        }
        if (isHttpRedirect(responseCode)) {
            String nextUriString = connection.getHeaderField(HttpHeaders.LOCATION);
            connection.disconnect();
            Uri nextUri = nextUriString == null ? null : Uri.parse(nextUriString);
            String originalScheme = uri.getScheme();
            if (maxRedirects > 0 && nextUri != null && !Objects.equal(nextUri.getScheme(), originalScheme)) {
                return downloadFrom(nextUri, maxRedirects - 1);
            }
            if (maxRedirects == 0) {
                message = error("URL %s follows too many redirects", uri.toString());
            } else {
                message = error("URL %s returned %d without a valid redirect", uri.toString(), Integer.valueOf(responseCode));
            }
            throw new IOException(message);
        }
        connection.disconnect();
        throw new IOException(String.format("Image URL %s returned HTTP code %d", uri.toString(), Integer.valueOf(responseCode)));
    }

    static HttpURLConnection openConnectionTo(Uri uri) throws IOException {
        URL url = UriUtil.uriToUrl(uri);
        return (HttpURLConnection) url.openConnection();
    }

    @Override // com.facebook.imagepipeline.producers.BaseNetworkFetcher, com.facebook.imagepipeline.producers.NetworkFetcher
    public void onFetchCompletion(HttpUrlConnectionNetworkFetchState fetchState, int byteSize) {
        fetchState.fetchCompleteTime = this.mMonotonicClock.now();
    }

    private static boolean isHttpSuccess(int responseCode) {
        return responseCode >= 200 && responseCode < 300;
    }

    private static boolean isHttpRedirect(int responseCode) {
        switch (responseCode) {
            case 300:
            case 301:
            case 302:
            case 303:
            case 307:
            case 308:
                return true;
            case 304:
            case 305:
            case 306:
            default:
                return false;
        }
    }

    private static String error(String format, Object... args) {
        return String.format(Locale.getDefault(), format, args);
    }

    @Override // com.facebook.imagepipeline.producers.BaseNetworkFetcher, com.facebook.imagepipeline.producers.NetworkFetcher
    public Map<String, String> getExtraMap(HttpUrlConnectionNetworkFetchState fetchState, int byteSize) {
        Map<String, String> extraMap = new HashMap<>(4);
        extraMap.put(QUEUE_TIME, Long.toString(fetchState.responseTime - fetchState.submitTime));
        extraMap.put(FETCH_TIME, Long.toString(fetchState.fetchCompleteTime - fetchState.responseTime));
        extraMap.put(TOTAL_TIME, Long.toString(fetchState.fetchCompleteTime - fetchState.submitTime));
        extraMap.put(IMAGE_SIZE, Integer.toString(byteSize));
        return extraMap;
    }
}
