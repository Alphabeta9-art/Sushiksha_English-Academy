package androidx.compose.material.ripple;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: RippleHostView.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\tJ\u001f\u0010\u0018\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0012\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/material/ripple/UnprojectedRipple;", "Landroid/graphics/drawable/RippleDrawable;", "bounded", "", "<init>", "(Z)V", "rippleColor", "Landroidx/compose/ui/graphics/Color;", "rippleRadius", "", "Ljava/lang/Integer;", "setColor", "", "color", "alpha", "", "setColor-DxMtmZc", "(JF)V", "projected", "isProjected", "getDirtyBounds", "Landroid/graphics/Rect;", "trySetRadius", "radius", "calculateRippleColor", "calculateRippleColor-5vOe2sY", "(JF)J", "MRadiusHelper", "Companion", "material-ripple"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class UnprojectedRipple extends RippleDrawable {
    private static boolean setMaxRadiusFetched;
    private static Method setMaxRadiusMethod;
    private final boolean bounded;
    private boolean projected;
    private Color rippleColor;
    private Integer rippleRadius;

    public UnprojectedRipple(boolean bounded) {
        super(ColorStateList.valueOf(-16777216), null, bounded ? new ColorDrawable(-1) : null);
        this.bounded = bounded;
    }

    /* JADX INFO: renamed from: setColor-DxMtmZc, reason: not valid java name */
    public final void m2057setColorDxMtmZc(long color, float alpha) {
        long newColor = m2056calculateRippleColor5vOe2sY(color, alpha);
        Color color2 = this.rippleColor;
        if (!(color2 == null ? false : Color.m5098equalsimpl0(color2.m5107unboximpl(), newColor))) {
            this.rippleColor = Color.m5087boximpl(newColor);
            setColor(ColorStateList.valueOf(ColorKt.m5151toArgb8_81llA(newColor)));
        }
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public boolean isProjected() {
        return this.projected;
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.Drawable
    public Rect getDirtyBounds() {
        if (!this.bounded) {
            this.projected = true;
        }
        Rect bounds = super.getDirtyBounds();
        this.projected = false;
        return bounds;
    }

    public final void trySetRadius(int radius) {
        Integer num = this.rippleRadius;
        if (num == null || num.intValue() != radius) {
            this.rippleRadius = Integer.valueOf(radius);
            int i = Build.VERSION.SDK_INT;
            MRadiusHelper.INSTANCE.setRadius(this, radius);
        }
    }

    /* JADX INFO: renamed from: calculateRippleColor-5vOe2sY, reason: not valid java name */
    private final long m2056calculateRippleColor5vOe2sY(long color, float alpha) {
        float f;
        if (Build.VERSION.SDK_INT < 28) {
            f = 2.0f * alpha;
        } else {
            f = alpha;
        }
        float transformedAlpha = RangesKt.coerceAtMost(f, 1.0f);
        return Color.m5095copywmQWz5c(color, (14 & 1) != 0 ? Color.m5099getAlphaimpl(color) : transformedAlpha, (14 & 2) != 0 ? Color.m5103getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(color) : 0.0f);
    }

    /* JADX INFO: compiled from: RippleHostView.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Landroidx/compose/material/ripple/UnprojectedRipple$MRadiusHelper;", "", "<init>", "()V", "setRadius", "", "ripple", "Landroid/graphics/drawable/RippleDrawable;", "radius", "", "material-ripple"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class MRadiusHelper {
        public static final MRadiusHelper INSTANCE = new MRadiusHelper();

        private MRadiusHelper() {
        }

        public final void setRadius(RippleDrawable ripple, int radius) {
            ripple.setRadius(radius);
        }
    }
}
