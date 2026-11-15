package androidx.compose.material;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.material.ripple.RippleAlpha;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Ripple.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B'\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/material/DelegatingThemeAwareRippleNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "bounded", "", "radius", "Landroidx/compose/ui/unit/Dp;", "color", "Landroidx/compose/ui/graphics/ColorProducer;", "<init>", "(Landroidx/compose/foundation/interaction/InteractionSource;ZFLandroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "rippleNode", "Landroidx/compose/ui/node/DelegatableNode;", "onAttach", "", "onObservedReadsChanged", "updateConfiguration", "attachNewRipple", "removeRipple", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class DelegatingThemeAwareRippleNode extends DelegatingNode implements CompositionLocalConsumerModifierNode, ObserverModifierNode {
    private final boolean bounded;
    private final ColorProducer color;
    private final InteractionSource interactionSource;
    private final float radius;
    private DelegatableNode rippleNode;

    public /* synthetic */ DelegatingThemeAwareRippleNode(InteractionSource interactionSource, boolean z, float f, ColorProducer colorProducer, DefaultConstructorMarker defaultConstructorMarker) {
        this(interactionSource, z, f, colorProducer);
    }

    private DelegatingThemeAwareRippleNode(InteractionSource interactionSource, boolean bounded, float radius, ColorProducer color) {
        this.interactionSource = interactionSource;
        this.bounded = bounded;
        this.radius = radius;
        this.color = color;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        updateConfiguration();
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        updateConfiguration();
    }

    private final void updateConfiguration() {
        ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.material.DelegatingThemeAwareRippleNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DelegatingThemeAwareRippleNode.updateConfiguration$lambda$0(this.f$0);
            }
        });
    }

    static final Unit updateConfiguration$lambda$0(DelegatingThemeAwareRippleNode this$0) {
        RippleConfiguration configuration = (RippleConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this$0, RippleKt.getLocalRippleConfiguration());
        if (configuration == null) {
            this$0.removeRipple();
        } else if (this$0.rippleNode == null) {
            this$0.attachNewRipple();
        }
        return Unit.INSTANCE;
    }

    private final void attachNewRipple() {
        ColorProducer calculateColor = new ColorProducer() { // from class: androidx.compose.material.DelegatingThemeAwareRippleNode$attachNewRipple$calculateColor$1
            /* JADX WARN: Code duplicated, block: B:16:0x003c  */
            @Override // androidx.compose.ui.graphics.ColorProducer
            /* JADX INFO: renamed from: invoke-0d7_KjU, reason: not valid java name */
            public final long mo1797invoke0d7_KjU() {
                boolean z;
                long userDefinedColor = this.this$0.color.mo1797invoke0d7_KjU();
                if (userDefinedColor != 16) {
                    return userDefinedColor;
                }
                RippleConfiguration rippleConfiguration = (RippleConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.this$0, RippleKt.getLocalRippleConfiguration());
                if (rippleConfiguration != null) {
                    long $this$isSpecified$iv = rippleConfiguration.getColor();
                    z = ($this$isSpecified$iv > 16L ? 1 : ($this$isSpecified$iv == 16L ? 0 : -1)) != 0;
                }
                if (z) {
                    return rippleConfiguration.getColor();
                }
                return RippleDefaults.INSTANCE.m1905rippleColor5vOe2sY(((Color) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.this$0, ContentColorKt.getLocalContentColor())).m5107unboximpl(), ((Colors) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.this$0, ColorsKt.getLocalColors())).isLight());
            }
        };
        Function0 calculateRippleAlpha = new Function0() { // from class: androidx.compose.material.DelegatingThemeAwareRippleNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DelegatingThemeAwareRippleNode.attachNewRipple$lambda$0(this.f$0);
            }
        };
        this.rippleNode = delegate(androidx.compose.material.ripple.RippleKt.m2048createRippleModifierNodeTDGSqEk(this.interactionSource, this.bounded, this.radius, calculateColor, calculateRippleAlpha));
    }

    static final RippleAlpha attachNewRipple$lambda$0(DelegatingThemeAwareRippleNode this$0) {
        RippleAlpha rippleAlpha;
        RippleConfiguration rippleConfiguration = (RippleConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this$0, RippleKt.getLocalRippleConfiguration());
        if (rippleConfiguration == null || (rippleAlpha = rippleConfiguration.getRippleAlpha()) == null) {
            return RippleDefaults.INSTANCE.m1904rippleAlphaDxMtmZc(((Color) CompositionLocalConsumerModifierNodeKt.currentValueOf(this$0, ContentColorKt.getLocalContentColor())).m5107unboximpl(), ((Colors) CompositionLocalConsumerModifierNodeKt.currentValueOf(this$0, ColorsKt.getLocalColors())).isLight());
        }
        return rippleAlpha;
    }

    private final void removeRipple() {
        DelegatableNode it = this.rippleNode;
        if (it != null) {
            undelegate(it);
        }
        this.rippleNode = null;
    }
}
