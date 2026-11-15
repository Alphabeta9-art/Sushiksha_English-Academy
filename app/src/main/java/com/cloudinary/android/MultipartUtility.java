package com.cloudinary.android;

import android.os.Build;
import com.cloudinary.Cloudinary;
import com.facebook.common.util.UriUtil;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class MultipartUtility {
    private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    private static final String LINE_FEED = "\r\n";
    public static final String USER_AGENT = "CloudinaryAndroid/3.0.2 (Android " + Build.VERSION.RELEASE + "; " + Build.MODEL + " Build/" + Build.DISPLAY + ") CloudinaryJava/" + Cloudinary.VERSION;
    private final String boundary;
    private String charset;
    private HttpURLConnection httpConn;
    private final MultipartCallback multipartCallback;
    private OutputStream outputStream;
    private PrintWriter writer;

    interface MultipartCallback {
        void totalBytesLoaded(long j);
    }

    public MultipartUtility(String requestURL, String charset, String boundary, Map<String, String> headers) throws IOException {
        this(requestURL, charset, boundary, headers, null);
    }

    public MultipartUtility(String requestURL, String charset, String boundary, Map<String, String> headers, MultipartCallback multipartCallback) throws IOException {
        this(requestURL, charset, boundary, headers, multipartCallback, 0, 0);
    }

    public MultipartUtility(String requestURL, String charset, String boundary, Map<String, String> headers, MultipartCallback multipartCallback, int connectTimeout, int readTimeout) throws IOException {
        this.charset = charset;
        this.boundary = boundary;
        this.multipartCallback = multipartCallback;
        URL url = new URL(requestURL);
        this.httpConn = (HttpURLConnection) url.openConnection();
        if (connectTimeout > 0) {
            this.httpConn.setConnectTimeout(connectTimeout);
        }
        if (readTimeout > 0) {
            this.httpConn.setReadTimeout(readTimeout);
        }
        this.httpConn.setDoOutput(true);
        this.httpConn.setChunkedStreamingMode(0);
        this.httpConn.setDoInput(true);
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                this.httpConn.setRequestProperty(header.getKey(), header.getValue());
            }
        }
        this.httpConn.setRequestProperty(HttpHeaders.CONTENT_TYPE, "multipart/form-data; boundary=" + boundary);
        this.httpConn.setRequestProperty(HttpHeaders.USER_AGENT, USER_AGENT);
        this.outputStream = this.httpConn.getOutputStream();
        this.writer = new PrintWriter((Writer) new OutputStreamWriter(this.outputStream, charset), true);
    }

    public MultipartUtility(String requestURL, String charset, String boundary) throws IOException {
        this(requestURL, charset, boundary, null, null);
    }

    public void addFormField(String name, String value) {
        this.writer.append((CharSequence) ("--" + this.boundary)).append((CharSequence) LINE_FEED);
        this.writer.append((CharSequence) ("Content-Disposition: form-data; name=\"" + name + "\"")).append((CharSequence) LINE_FEED);
        this.writer.append((CharSequence) ("Content-Type: text/plain; charset=" + this.charset)).append((CharSequence) LINE_FEED);
        this.writer.append((CharSequence) LINE_FEED);
        this.writer.append((CharSequence) value).append((CharSequence) LINE_FEED);
        this.writer.flush();
    }

    public void addFilePart(String fieldName, File uploadFile, String fileName) throws IOException {
        if (fileName == null) {
            fileName = uploadFile.getName();
        }
        FileInputStream inputStream = new FileInputStream(uploadFile);
        addFilePart(fieldName, inputStream, fileName);
    }

    public void addFilePart(String fieldName, File uploadFile) throws IOException {
        addFilePart(fieldName, uploadFile, UriUtil.LOCAL_FILE_SCHEME);
    }

    public void addFilePart(String fieldName, InputStream inputStream, String fileName) throws IOException {
        if (fileName == null) {
            fileName = UriUtil.LOCAL_FILE_SCHEME;
        }
        this.writer.append((CharSequence) ("--" + this.boundary)).append((CharSequence) LINE_FEED);
        this.writer.append((CharSequence) ("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"")).append((CharSequence) LINE_FEED);
        this.writer.append((CharSequence) "Content-Type: ").append((CharSequence) APPLICATION_OCTET_STREAM).append((CharSequence) LINE_FEED);
        this.writer.append((CharSequence) "Content-Transfer-Encoding: binary").append((CharSequence) LINE_FEED);
        this.writer.append((CharSequence) LINE_FEED);
        this.writer.flush();
        byte[] buffer = new byte[4096];
        long totalRead = 0;
        while (true) {
            int bytesRead = inputStream.read(buffer);
            if (bytesRead != -1) {
                this.outputStream.write(buffer, 0, bytesRead);
                long j = ((long) bytesRead) + totalRead;
                totalRead = j;
                notifyCallback(j);
            } else {
                this.outputStream.flush();
                inputStream.close();
                this.writer.append((CharSequence) LINE_FEED);
                this.writer.flush();
                return;
            }
        }
    }

    private void notifyCallback(long bytes) {
        if (this.multipartCallback != null) {
            this.multipartCallback.totalBytesLoaded(bytes);
        }
    }

    public void addFilePart(String fieldName, InputStream inputStream) throws IOException {
        addFilePart(fieldName, inputStream, UriUtil.LOCAL_FILE_SCHEME);
    }

    public HttpURLConnection execute() throws IOException {
        this.writer.append((CharSequence) ("--" + this.boundary + "--")).append((CharSequence) LINE_FEED);
        this.writer.close();
        return this.httpConn;
    }

    public void close() {
        if (this.writer != null) {
            this.writer.close();
        }
    }
}
