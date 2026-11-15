package androidx.compose.material.pullrefresh;

import androidx.compose.animation.core.EasingKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: PullRefreshIndicatorTransform.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"pullRefreshIndicatorTransform", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/material/pullrefresh/PullRefreshState;", "scale", "", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PullRefreshIndicatorTransformKt {
    public static /* synthetic */ Modifier pullRefreshIndicatorTransform$default(Modifier modifier, PullRefreshState pullRefreshState, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return pullRefreshIndicatorTransform(modifier, pullRefreshState, z);
    }

    public static final Modifier pullRefreshIndicatorTransform(Modifier $this$pullRefreshIndicatorTransform, final PullRefreshState state, final boolean scale) {
        return GraphicsLayerModifierKt.graphicsLayer(DrawModifierKt.drawWithContent($this$pullRefreshIndicatorTransform, new Function1() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorTransformKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PullRefreshIndicatorTransformKt.pullRefreshIndicatorTransform$lambda$0((ContentDrawScope) obj);
            }
        }), new Function1() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorTransformKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PullRefreshIndicatorTransformKt.pullRefreshIndicatorTransform$lambda$1(state, scale, (GraphicsLayerScope) obj);
            }
        });
    }

    static final Unit pullRefreshIndicatorTransform$lambda$0(ContentDrawScope $this$drawWithContent) {
        ContentDrawScope $this$clipRect_u2drOu3jXo_u24default$iv = $this$drawWithContent;
        int clipOp$iv = ClipOp.INSTANCE.m5086getIntersectrtfAjoo();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$clipRect_u2drOu3jXo_u24default$iv.getDrawContext();
        long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo5592getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
        try {
            DrawTransform $this$clipRect_rOu3jXo_u24lambda_u244$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
            $this$clipRect_rOu3jXo_u24lambda_u244$iv.mo5595clipRectN_I0leg(-3.4028235E38f, 0.0f, Float.MAX_VALUE, Float.MAX_VALUE, clipOp$iv);
            $this$drawWithContent.drawContent();
            return Unit.INSTANCE;
        } finally {
            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u246$iv$iv.mo5593setSizeuvyYCjk(previousSize$iv$iv);
        }
    }

    static final Unit pullRefreshIndicatorTransform$lambda$1(PullRefreshState $state, boolean $scale, GraphicsLayerScope $this$graphicsLayer) {
        float position$material = $state.getPosition$material();
        long arg0$iv = $this$graphicsLayer.getSize();
        int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
        $this$graphicsLayer.setTranslationY(position$material - Float.intBitsToFloat(bits$iv$iv$iv));
        if ($scale && !$state.getRefreshing$material()) {
            float $this$fastCoerceIn$iv = EasingKt.getLinearOutSlowInEasing().transform($state.getPosition$material() / $state.getThreshold$material());
            float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
            if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
                $this$fastCoerceAtLeast$iv$iv = 0.0f;
            }
            if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
                $this$fastCoerceAtLeast$iv$iv = 1.0f;
            }
            $this$graphicsLayer.setScaleX($this$fastCoerceAtLeast$iv$iv);
            $this$graphicsLayer.setScaleY($this$fastCoerceAtLeast$iv$iv);
        }
        return Unit.INSTANCE;
    }
}
