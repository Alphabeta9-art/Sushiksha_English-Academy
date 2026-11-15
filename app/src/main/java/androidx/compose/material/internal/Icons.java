package androidx.compose.material.internal;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Icons.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/compose/material/internal/Icons;", "", "<init>", "()V", "Filled", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Icons {
    public static final int $stable = 0;
    public static final Icons INSTANCE = new Icons();

    /* JADX INFO: compiled from: Icons.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/compose/material/internal/Icons$Filled;", "", "<init>", "()V", "ArrowDropDown", "Landroidx/compose/ui/graphics/vector/ImageVector;", "getArrowDropDown$material", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "_arrowDropDown", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Filled {
        private static ImageVector _arrowDropDown;
        public static final Filled INSTANCE = new Filled();
        public static final int $stable = 8;

        private Filled() {
        }

        public final ImageVector getArrowDropDown$material() {
            if (_arrowDropDown != null) {
                ImageVector imageVector = _arrowDropDown;
                Intrinsics.checkNotNull(imageVector);
                return imageVector;
            }
            float $this$dp$iv$iv = Dp.m7905constructorimpl(24.0f);
            float $this$dp$iv$iv2 = Dp.m7905constructorimpl(24.0f);
            ImageVector.Builder $this$_get_ArrowDropDown__u24lambda_u240 = new ImageVector.Builder("Filled.ArrowDropDown", $this$dp$iv$iv, $this$dp$iv$iv2, 24.0f, 24.0f, 0L, 0, false, 224, null);
            int defaultFillType = VectorKt.getDefaultFillType();
            Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m5123getBlack0d7_KjU(), null);
            int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m5471getButtKaPHkGw();
            int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m5481getBevelLxFBmk8();
            PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
            $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(7.0f, 10.0f);
            $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(5.0f, 5.0f);
            $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(5.0f, -5.0f);
            $this$PathData_u24lambda_u240$iv$iv$iv.close();
            _arrowDropDown = ImageVector.Builder.m5828addPathoIyEayM$default($this$_get_ArrowDropDown__u24lambda_u240, $this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), defaultFillType, "", fill$iv$iv, 1.0f, null, 1.0f, 1.0f, strokeLineCap$iv$iv, strokeLineJoin$iv$iv, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
            ImageVector imageVector2 = _arrowDropDown;
            Intrinsics.checkNotNull(imageVector2);
            return imageVector2;
        }
    }

    private Icons() {
    }
}
