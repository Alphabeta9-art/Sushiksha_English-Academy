package com.facebook.imagepipeline.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import com.facebook.common.internal.Preconditions;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RenderScriptBlurFilter.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/imagepipeline/filter/RenderScriptBlurFilter;", "", "()V", "BLUR_MAX_RADIUS", "", "blurBitmap", "", "dest", "Landroid/graphics/Bitmap;", "src", "context", "Landroid/content/Context;", "radius", "canUseRenderScript", "", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RenderScriptBlurFilter {
    public static final int BLUR_MAX_RADIUS = 25;
    public static final RenderScriptBlurFilter INSTANCE = new RenderScriptBlurFilter();

    private RenderScriptBlurFilter() {
    }

    @JvmStatic
    public static final void blurBitmap(Bitmap dest, Bitmap src, Context context, int radius) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.checkNotNullParameter(context, "context");
        Preconditions.checkArgument(Boolean.valueOf(radius > 0 && radius <= 25));
        RenderScript rs = null;
        try {
            RenderScript rs2 = RenderScript.create(context);
            if (rs2 == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs2, Element.U8_4(rs2));
            Allocation allIn = Allocation.createFromBitmap(rs2, src);
            if (allIn == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            Allocation allOut = Allocation.createFromBitmap(rs2, dest);
            if (allOut == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            blurScript.setRadius(radius);
            blurScript.setInput(allIn);
            blurScript.forEach(allOut);
            allOut.copyTo(dest);
            blurScript.destroy();
            allIn.destroy();
            allOut.destroy();
            if (rs2 != null) {
                rs2.destroy();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                rs.destroy();
            }
            throw th;
        }
    }

    @JvmStatic
    public static final boolean canUseRenderScript() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }
}
