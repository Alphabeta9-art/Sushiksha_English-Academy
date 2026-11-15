package com.facebook.drawee.view;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchyInflater;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class GenericDraweeView extends DraweeView<GenericDraweeHierarchy> {

    @Nullable
    private static InflateHierarchyListener sInflateHierarchyListener;

    public interface InflateHierarchyListener {
        void onInflateHierarchy(GenericDraweeView genericDraweeView, Context context, @Nullable AttributeSet attributeSet);
    }

    public static void setInflateHierarchyListener(@Nullable InflateHierarchyListener inflateHierarchyListener) {
        sInflateHierarchyListener = inflateHierarchyListener;
    }

    public GenericDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context);
        setHierarchy(hierarchy);
    }

    public GenericDraweeView(Context context) throws Throwable {
        super(context);
        inflateHierarchy(context, null);
    }

    public GenericDraweeView(Context context, @Nullable AttributeSet attrs) throws Throwable {
        super(context, attrs);
        inflateHierarchy(context, attrs);
    }

    public GenericDraweeView(Context context, @Nullable AttributeSet attrs, int defStyle) throws Throwable {
        super(context, attrs, defStyle);
        inflateHierarchy(context, attrs);
    }

    public GenericDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) throws Throwable {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateHierarchy(context, attrs);
    }

    protected void inflateHierarchy(Context context, @Nullable AttributeSet attrs) throws Throwable {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("GenericDraweeView#inflateHierarchy");
        }
        GenericDraweeHierarchyBuilder builder = GenericDraweeHierarchyInflater.inflateBuilder(context, attrs);
        setAspectRatio(builder.getDesiredAspectRatio());
        setHierarchy(builder.build());
        if (sInflateHierarchyListener != null) {
            sInflateHierarchyListener.onInflateHierarchy(this, context, attrs);
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }
}
