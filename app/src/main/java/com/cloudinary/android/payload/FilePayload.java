package com.cloudinary.android.payload;

import android.content.Context;
import com.cloudinary.utils.Base64Coder;
import java.io.File;

/* JADX INFO: loaded from: classes11.dex */
public class FilePayload extends Payload<String> {
    static final String URI_KEY = "file";

    public FilePayload(String filePath) {
        super(filePath);
    }

    public FilePayload() {
    }

    @Override // com.cloudinary.android.payload.Payload
    public long getLength(Context context) throws PayloadNotFoundException {
        return getFile(context).length();
    }

    @Override // com.cloudinary.android.payload.Payload
    public Object prepare(Context context) throws PayloadNotFoundException {
        return getFile(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private File getFile(Context context) throws FileNotFoundException {
        File file = ((String) this.data).contains(File.separator) ? new File((String) this.data) : context.getFileStreamPath((String) this.data);
        if (!file.exists()) {
            throw new FileNotFoundException(String.format("File '%s' does not exist", this.data));
        }
        return file;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cloudinary.android.payload.Payload
    public String toUri() {
        return "file://" + Base64Coder.encodeString((String) this.data);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.lang.String] */
    @Override // com.cloudinary.android.payload.Payload
    void loadData(String encodedData) {
        this.data = Base64Coder.decodeString(encodedData);
    }
}
