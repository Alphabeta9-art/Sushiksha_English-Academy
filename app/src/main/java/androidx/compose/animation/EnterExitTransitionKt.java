package androidx.compose.animation;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.AnimationVector4D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImplKt;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.exifinterface.media.ExifInterface;
import com.cloudinary.metadata.MetadataField;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: EnterExitTransition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¬\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\u0004\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\u0004\u001a\"\u0010\u0005\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\bH\u0007\u001a\"\u0010\n\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0007\u001a;\u0010\f\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\r0\u000fH\u0007\u001a;\u0010\u0014\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072!\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\r0\u000fH\u0007\u001a3\u0010\u0016\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u0017\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a3\u0010\u001c\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u001d\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a3\u0010 \u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020!0\u00072\b\b\u0002\u0010\"\u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020$H\u0007¢\u0006\u0004\b%\u0010&\u001a3\u0010'\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020!0\u00072\b\b\u0002\u0010(\u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020$H\u0007¢\u0006\u0004\b)\u0010*\u001aQ\u0010+\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u0010,\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u0010/\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00100\u000fH\u0007\u001aQ\u00100\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u00101\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u00102\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00100\u000fH\u0007\u001aQ\u00103\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u0010,\u001a\u0002042\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u00105\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002060\u000fH\u0007\u001aQ\u00108\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u0010,\u001a\u0002092\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u0010:\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002060\u000fH\u0007\u001aQ\u0010<\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u00101\u001a\u0002042\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u0010=\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002060\u000fH\u0007\u001aQ\u0010>\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\b\b\u0002\u00101\u001a\u0002092\b\b\u0002\u0010.\u001a\u00020$2#\b\u0002\u0010?\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002060\u000fH\u0007\u001a=\u0010@\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072#\b\u0002\u0010A\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002060\u000fH\u0007\u001a=\u0010B\u001a\u00020\u00012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072#\b\u0002\u0010C\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002060\u000fH\u0007\u001a=\u0010D\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072#\b\u0002\u0010E\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002060\u000fH\u0007\u001a=\u0010F\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072#\b\u0002\u0010G\u001a\u001d\u0012\u0013\u0012\u001106¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002060\u000fH\u0007\u001a\f\u0010H\u001a\u00020-*\u000204H\u0002\u001a\f\u0010H\u001a\u00020-*\u000209H\u0002\u001a,\u0010I\u001a\u0004\u0018\u0001HJ\"\b\b\u0000\u0010J*\u00020\u0003*\u00020\u00012\f\u0010K\u001a\b\u0012\u0004\u0012\u0002HJ0LH\u0080\u0002¢\u0006\u0002\u0010M\u001a,\u0010I\u001a\u0004\u0018\u0001HJ\"\b\b\u0000\u0010J*\u00020\u0003*\u00020\u00042\f\u0010K\u001a\b\u0012\u0004\u0012\u0002HJ0LH\u0080\u0002¢\u0006\u0002\u0010N\u001a?\u0010O\u001a\u00020P*\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010S\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\u00042\u000e\b\u0002\u0010U\u001a\b\u0012\u0004\u0012\u00020$0V2\u0006\u0010W\u001a\u00020XH\u0001¢\u0006\u0002\u0010Y\u001a\u001f\u0010Z\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010S\u001a\u00020\u0001H\u0001¢\u0006\u0002\u0010[\u001a\u001f\u0010\\\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010T\u001a\u00020\u0004H\u0001¢\u0006\u0002\u0010]\u001a/\u0010^\u001a\u00020_*\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010S\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\u00042\u0006\u0010W\u001a\u00020XH\u0003¢\u0006\u0002\u0010`\"\u001a\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020c0bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010d\u001a\b\u0012\u0004\u0012\u00020\b0eX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010f\u001a\b\u0012\u0004\u0012\u00020!0eX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010g\u001a\b\u0012\u0004\u0012\u00020\r0eX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00100eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006i²\u0006\n\u0010j\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\n\u0010k\u001a\u00020\u0004X\u008a\u008e\u0002"}, d2 = {"withEffect", "Landroidx/compose/animation/EnterTransition;", "effect", "Landroidx/compose/animation/TransitionEffect;", "Landroidx/compose/animation/ExitTransition;", "fadeIn", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "initialAlpha", "fadeOut", "targetAlpha", "slideIn", "Landroidx/compose/ui/unit/IntOffset;", "initialOffset", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/IntSize;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "fullSize", "slideOut", "targetOffset", "scaleIn", "initialScale", "transformOrigin", "Landroidx/compose/ui/graphics/TransformOrigin;", "scaleIn-L8ZKh-E", "(Landroidx/compose/animation/core/FiniteAnimationSpec;FJ)Landroidx/compose/animation/EnterTransition;", "scaleOut", "targetScale", "scaleOut-L8ZKh-E", "(Landroidx/compose/animation/core/FiniteAnimationSpec;FJ)Landroidx/compose/animation/ExitTransition;", "unveilIn", "Landroidx/compose/ui/graphics/Color;", "initialColor", "matchParentSize", "", "unveilIn-bw27NRU", "(Landroidx/compose/animation/core/FiniteAnimationSpec;JZ)Landroidx/compose/animation/EnterTransition;", "veilOut", "targetColor", "veilOut-bw27NRU", "(Landroidx/compose/animation/core/FiniteAnimationSpec;JZ)Landroidx/compose/animation/ExitTransition;", "expandIn", "expandFrom", "Landroidx/compose/ui/Alignment;", "clip", "initialSize", "shrinkOut", "shrinkTowards", "targetSize", "expandHorizontally", "Landroidx/compose/ui/Alignment$Horizontal;", "initialWidth", "", "fullWidth", "expandVertically", "Landroidx/compose/ui/Alignment$Vertical;", "initialHeight", "fullHeight", "shrinkHorizontally", "targetWidth", "shrinkVertically", "targetHeight", "slideInHorizontally", "initialOffsetX", "slideInVertically", "initialOffsetY", "slideOutHorizontally", "targetOffsetX", "slideOutVertically", "targetOffsetY", "toAlignment", "get", ExifInterface.GPS_DIRECTION_TRUE, "key", "Landroidx/compose/animation/TransitionEffectKey;", "(Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/TransitionEffectKey;)Landroidx/compose/animation/TransitionEffect;", "(Landroidx/compose/animation/ExitTransition;Landroidx/compose/animation/TransitionEffectKey;)Landroidx/compose/animation/TransitionEffect;", "createModifier", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "enter", "exit", "isEnabled", "Lkotlin/Function0;", MetadataField.LABEL, "", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/Modifier;", "trackActiveEnter", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/EnterTransition;", "trackActiveExit", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/ExitTransition;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/ExitTransition;", "createGraphicsLayerBlock", "Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;", "TransformOriginVectorConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/animation/core/AnimationVector2D;", "DefaultAlphaAndScaleSpring", "Landroidx/compose/animation/core/SpringSpec;", "DefaultColorAnimationSpec", "DefaultOffsetAnimationSpec", "DefaultSizeAnimationSpec", "animation", "activeEnter", "activeExit"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class EnterExitTransitionKt {
    private static final TwoWayConverter<TransformOrigin, AnimationVector2D> TransformOriginVectorConverter = VectorConvertersKt.TwoWayConverter(new Function1<TransformOrigin, AnimationVector2D>() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ AnimationVector2D invoke(TransformOrigin transformOrigin) {
            return m94invoke__ExYCQ(transformOrigin.getPackedValue());
        }

        /* JADX INFO: renamed from: invoke-__ExYCQ, reason: not valid java name */
        public final AnimationVector2D m94invoke__ExYCQ(long j) {
            return new AnimationVector2D(TransformOrigin.m5505getPivotFractionXimpl(j), TransformOrigin.m5506getPivotFractionYimpl(j));
        }
    }, new Function1<AnimationVector2D, TransformOrigin>() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$2
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ TransformOrigin invoke(AnimationVector2D animationVector2D) {
            return TransformOrigin.m5497boximpl(m95invokeLIALnN8(animationVector2D));
        }

        /* JADX INFO: renamed from: invoke-LIALnN8, reason: not valid java name */
        public final long m95invokeLIALnN8(AnimationVector2D it) {
            return TransformOriginKt.TransformOrigin(it.getV1(), it.getV2());
        }
    });
    private static final SpringSpec<Float> DefaultAlphaAndScaleSpring = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
    private static final SpringSpec<Color> DefaultColorAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
    private static final SpringSpec<IntOffset> DefaultOffsetAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m8024boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
    private static final SpringSpec<IntSize> DefaultSizeAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m8068boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);

    public static final EnterTransition withEffect(EnterTransition $this$withEffect, TransitionEffect effect) {
        return new EnterTransitionImpl(new TransitionData(null, null, null, null, null, false, MapsKt.mapOf(TuplesKt.to(effect.getKey$animation(), effect)), 63, null));
    }

    public static final ExitTransition withEffect(ExitTransition $this$withEffect, TransitionEffect effect) {
        return new ExitTransitionImpl(new TransitionData(null, null, null, null, null, false, MapsKt.mapOf(TuplesKt.to(effect.getKey$animation(), effect)), 63, null));
    }

    public static /* synthetic */ EnterTransition fadeIn$default(FiniteAnimationSpec finiteAnimationSpec, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return fadeIn(finiteAnimationSpec, f);
    }

    public static final EnterTransition fadeIn(FiniteAnimationSpec<Float> finiteAnimationSpec, float initialAlpha) {
        return new EnterTransitionImpl(new TransitionData(new Fade(initialAlpha, finiteAnimationSpec), null, null, null, null, false, null, WebSocketProtocol.PAYLOAD_SHORT, null));
    }

    public static /* synthetic */ ExitTransition fadeOut$default(FiniteAnimationSpec finiteAnimationSpec, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return fadeOut(finiteAnimationSpec, f);
    }

    public static final ExitTransition fadeOut(FiniteAnimationSpec<Float> finiteAnimationSpec, float targetAlpha) {
        return new ExitTransitionImpl(new TransitionData(new Fade(targetAlpha, finiteAnimationSpec), null, null, null, null, false, null, WebSocketProtocol.PAYLOAD_SHORT, null));
    }

    public static /* synthetic */ EnterTransition slideIn$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m8024boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        return slideIn(finiteAnimationSpec, function1);
    }

    public static final EnterTransition slideIn(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, Function1<? super IntSize, IntOffset> function1) {
        return new EnterTransitionImpl(new TransitionData(null, new Slide(function1, finiteAnimationSpec), null, null, null, false, null, ComposerImplKt.nodeKey, null));
    }

    public static /* synthetic */ ExitTransition slideOut$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m8024boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        return slideOut(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOut(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, Function1<? super IntSize, IntOffset> function1) {
        return new ExitTransitionImpl(new TransitionData(null, new Slide(function1, finiteAnimationSpec), null, null, null, false, null, ComposerImplKt.nodeKey, null));
    }

    /* JADX INFO: renamed from: scaleIn-L8ZKh-E$default, reason: not valid java name */
    public static /* synthetic */ EnterTransition m87scaleInL8ZKhE$default(FiniteAnimationSpec finiteAnimationSpec, float f, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        if ((i & 4) != 0) {
            j = TransformOrigin.INSTANCE.m5510getCenterSzJe1aQ();
        }
        return m86scaleInL8ZKhE(finiteAnimationSpec, f, j);
    }

    /* JADX INFO: renamed from: scaleIn-L8ZKh-E, reason: not valid java name */
    public static final EnterTransition m86scaleInL8ZKhE(FiniteAnimationSpec<Float> finiteAnimationSpec, float initialScale, long j) {
        return new EnterTransitionImpl(new TransitionData(null, null, null, new Scale(initialScale, j, finiteAnimationSpec, null), null, false, null, 119, null));
    }

    /* JADX INFO: renamed from: scaleOut-L8ZKh-E$default, reason: not valid java name */
    public static /* synthetic */ ExitTransition m89scaleOutL8ZKhE$default(FiniteAnimationSpec finiteAnimationSpec, float f, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        if ((i & 4) != 0) {
            j = TransformOrigin.INSTANCE.m5510getCenterSzJe1aQ();
        }
        return m88scaleOutL8ZKhE(finiteAnimationSpec, f, j);
    }

    /* JADX INFO: renamed from: scaleOut-L8ZKh-E, reason: not valid java name */
    public static final ExitTransition m88scaleOutL8ZKhE(FiniteAnimationSpec<Float> finiteAnimationSpec, float targetScale, long j) {
        return new ExitTransitionImpl(new TransitionData(null, null, null, new Scale(targetScale, j, finiteAnimationSpec, null), null, false, null, 119, null));
    }

    /* JADX INFO: renamed from: unveilIn-bw27NRU$default, reason: not valid java name */
    public static /* synthetic */ EnterTransition m91unveilInbw27NRU$default(FiniteAnimationSpec finiteAnimationSpec, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            long jM5123getBlack0d7_KjU = Color.INSTANCE.m5123getBlack0d7_KjU();
            j = Color.m5095copywmQWz5c(jM5123getBlack0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5123getBlack0d7_KjU) : 0.5f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5123getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5123getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5123getBlack0d7_KjU) : 0.0f);
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return m90unveilInbw27NRU(finiteAnimationSpec, j, z);
    }

    /* JADX INFO: renamed from: unveilIn-bw27NRU, reason: not valid java name */
    public static final EnterTransition m90unveilInbw27NRU(FiniteAnimationSpec<Color> finiteAnimationSpec, long j, boolean matchParentSize) {
        return new EnterTransitionImpl(new TransitionData(null, null, null, null, new Veil(j, Color.m5095copywmQWz5c(j, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j) : 0.0f, (14 & 2) != 0 ? Color.m5103getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j) : 0.0f), finiteAnimationSpec, matchParentSize, null), false, null, 111, null));
    }

    /* JADX INFO: renamed from: veilOut-bw27NRU$default, reason: not valid java name */
    public static /* synthetic */ ExitTransition m93veilOutbw27NRU$default(FiniteAnimationSpec finiteAnimationSpec, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            long jM5123getBlack0d7_KjU = Color.INSTANCE.m5123getBlack0d7_KjU();
            j = Color.m5095copywmQWz5c(jM5123getBlack0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5123getBlack0d7_KjU) : 0.5f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5123getBlack0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5123getBlack0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5123getBlack0d7_KjU) : 0.0f);
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return m92veilOutbw27NRU(finiteAnimationSpec, j, z);
    }

    /* JADX INFO: renamed from: veilOut-bw27NRU, reason: not valid java name */
    public static final ExitTransition m92veilOutbw27NRU(FiniteAnimationSpec<Color> finiteAnimationSpec, long j, boolean matchParentSize) {
        return new ExitTransitionImpl(new TransitionData(null, null, null, null, new Veil(Color.m5095copywmQWz5c(j, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j) : 0.0f, (14 & 2) != 0 ? Color.m5103getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j) : 0.0f), j, finiteAnimationSpec, matchParentSize, null), false, null, 111, null));
    }

    public static /* synthetic */ EnterTransition expandIn$default(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m8068boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            alignment = Alignment.INSTANCE.getBottomEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandIn.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                    return IntSize.m8068boximpl(m98invokemzRDjE0(intSize.m8080unboximpl()));
                }

                /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
                public final long m98invokemzRDjE0(long j) {
                    return IntSize.m8071constructorimpl((((long) 0) << 32) | (((long) 0) & 4294967295L));
                }
            };
        }
        return expandIn(finiteAnimationSpec, alignment, z, function1);
    }

    public static final EnterTransition expandIn(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment expandFrom, boolean clip, Function1<? super IntSize, IntSize> function1) {
        return new EnterTransitionImpl(new TransitionData(null, null, new ChangeSize(expandFrom, function1, finiteAnimationSpec, clip), null, null, false, null, 123, null));
    }

    public static /* synthetic */ ExitTransition shrinkOut$default(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m8068boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            alignment = Alignment.INSTANCE.getBottomEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkOut.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                    return IntSize.m8068boximpl(m101invokemzRDjE0(intSize.m8080unboximpl()));
                }

                /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
                public final long m101invokemzRDjE0(long j) {
                    return IntSize.m8071constructorimpl((((long) 0) << 32) | (((long) 0) & 4294967295L));
                }
            };
        }
        return shrinkOut(finiteAnimationSpec, alignment, z, function1);
    }

    public static final ExitTransition shrinkOut(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment shrinkTowards, boolean clip, Function1<? super IntSize, IntSize> function1) {
        return new ExitTransitionImpl(new TransitionData(null, null, new ChangeSize(shrinkTowards, function1, finiteAnimationSpec, clip), null, null, false, null, 123, null));
    }

    public static /* synthetic */ EnterTransition expandHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m8068boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            horizontal = Alignment.INSTANCE.getEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandHorizontally.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return expandHorizontally(finiteAnimationSpec, horizontal, z, function1);
    }

    public static final EnterTransition expandHorizontally(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Horizontal expandFrom, boolean clip, final Function1<? super Integer, Integer> function1) {
        return expandIn(finiteAnimationSpec, toAlignment(expandFrom), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m8068boximpl(m97invokemzRDjE0(intSize.m8080unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m97invokemzRDjE0(long j) {
                int width$iv = function1.invoke(Integer.valueOf((int) (j >> 32))).intValue();
                int height$iv = (int) (j & 4294967295L);
                return IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
            }
        });
    }

    public static /* synthetic */ EnterTransition expandVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m8068boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            vertical = Alignment.INSTANCE.getBottom();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandVertically.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return expandVertically(finiteAnimationSpec, vertical, z, function1);
    }

    public static final EnterTransition expandVertically(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Vertical expandFrom, boolean clip, final Function1<? super Integer, Integer> function1) {
        return expandIn(finiteAnimationSpec, toAlignment(expandFrom), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m8068boximpl(m99invokemzRDjE0(intSize.m8080unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m99invokemzRDjE0(long j) {
                int width$iv = (int) (j >> 32);
                int $i$f$unpackInt2 = (int) (j & 4294967295L);
                int height$iv = function1.invoke(Integer.valueOf($i$f$unpackInt2)).intValue();
                return IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
            }
        });
    }

    public static /* synthetic */ ExitTransition shrinkHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m8068boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            horizontal = Alignment.INSTANCE.getEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkHorizontally.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return shrinkHorizontally(finiteAnimationSpec, horizontal, z, function1);
    }

    public static final ExitTransition shrinkHorizontally(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Horizontal shrinkTowards, boolean clip, final Function1<? super Integer, Integer> function1) {
        return shrinkOut(finiteAnimationSpec, toAlignment(shrinkTowards), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m8068boximpl(m100invokemzRDjE0(intSize.m8080unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m100invokemzRDjE0(long j) {
                int width$iv = function1.invoke(Integer.valueOf((int) (j >> 32))).intValue();
                int height$iv = (int) (j & 4294967295L);
                return IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
            }
        });
    }

    public static /* synthetic */ ExitTransition shrinkVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m8068boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            vertical = Alignment.INSTANCE.getBottom();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkVertically.1
                public final Integer invoke(int it) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return shrinkVertically(finiteAnimationSpec, vertical, z, function1);
    }

    public static final ExitTransition shrinkVertically(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Vertical shrinkTowards, boolean clip, final Function1<? super Integer, Integer> function1) {
        return shrinkOut(finiteAnimationSpec, toAlignment(shrinkTowards), clip, new Function1<IntSize, IntSize>() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntSize invoke(IntSize intSize) {
                return IntSize.m8068boximpl(m102invokemzRDjE0(intSize.m8080unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m102invokemzRDjE0(long j) {
                int width$iv = (int) (j >> 32);
                int $i$f$unpackInt2 = (int) (j & 4294967295L);
                int height$iv = function1.invoke(Integer.valueOf($i$f$unpackInt2)).intValue();
                return IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
            }
        });
    }

    public static /* synthetic */ EnterTransition slideInHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m8024boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInHorizontally.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideInHorizontally(finiteAnimationSpec, function1);
    }

    public static final EnterTransition slideInHorizontally(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideIn(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m8024boximpl(m103invokemHKZG7I(intSize.m8080unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m103invokemHKZG7I(long j) {
                int x$iv = function1.invoke(Integer.valueOf((int) (j >> 32))).intValue();
                return IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) 0) & 4294967295L));
            }
        });
    }

    public static /* synthetic */ EnterTransition slideInVertically$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m8024boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInVertically.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideInVertically(finiteAnimationSpec, function1);
    }

    public static final EnterTransition slideInVertically(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideIn(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideInVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m8024boximpl(m104invokemHKZG7I(intSize.m8080unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m104invokemHKZG7I(long j) {
                int y$iv = function1.invoke(Integer.valueOf((int) (j & 4294967295L))).intValue();
                return IntOffset.m8027constructorimpl((((long) 0) << 32) | (((long) y$iv) & 4294967295L));
            }
        });
    }

    public static /* synthetic */ ExitTransition slideOutHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m8024boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutHorizontally.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideOutHorizontally(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOutHorizontally(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideOut(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutHorizontally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m8024boximpl(m105invokemHKZG7I(intSize.m8080unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m105invokemHKZG7I(long j) {
                int x$iv = function1.invoke(Integer.valueOf((int) (j >> 32))).intValue();
                return IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) 0) & 4294967295L));
            }
        });
    }

    public static /* synthetic */ ExitTransition slideOutVertically$default(FiniteAnimationSpec finiteAnimationSpec, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m8024boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i & 2) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutVertically.1
                public final Integer invoke(int it) {
                    return Integer.valueOf((-it) / 2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return slideOutVertically(finiteAnimationSpec, function1);
    }

    public static final ExitTransition slideOutVertically(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, final Function1<? super Integer, Integer> function1) {
        return slideOut(finiteAnimationSpec, new Function1<IntSize, IntOffset>() { // from class: androidx.compose.animation.EnterExitTransitionKt.slideOutVertically.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ IntOffset invoke(IntSize intSize) {
                return IntOffset.m8024boximpl(m106invokemHKZG7I(intSize.m8080unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
            public final long m106invokemHKZG7I(long j) {
                int y$iv = function1.invoke(Integer.valueOf((int) (j & 4294967295L))).intValue();
                return IntOffset.m8027constructorimpl((((long) 0) << 32) | (((long) y$iv) & 4294967295L));
            }
        });
    }

    private static final Alignment toAlignment(Alignment.Horizontal $this$toAlignment) {
        if (Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getStart())) {
            return Alignment.INSTANCE.getCenterStart();
        }
        return Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getEnd()) ? Alignment.INSTANCE.getCenterEnd() : Alignment.INSTANCE.getCenter();
    }

    private static final Alignment toAlignment(Alignment.Vertical $this$toAlignment) {
        if (Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getTop())) {
            return Alignment.INSTANCE.getTopCenter();
        }
        return Intrinsics.areEqual($this$toAlignment, Alignment.INSTANCE.getBottom()) ? Alignment.INSTANCE.getBottomCenter() : Alignment.INSTANCE.getCenter();
    }

    public static final <T extends TransitionEffect> T get(EnterTransition $this$get, TransitionEffectKey<T> transitionEffectKey) {
        TransitionEffect transitionEffect = $this$get.getData().getEffectsMap().get(transitionEffectKey);
        if (transitionEffect instanceof TransitionEffect) {
            return (T) transitionEffect;
        }
        return null;
    }

    public static final <T extends TransitionEffect> T get(ExitTransition $this$get, TransitionEffectKey<T> transitionEffectKey) {
        TransitionEffect transitionEffect = $this$get.getData().getEffectsMap().get(transitionEffectKey);
        if (transitionEffect instanceof TransitionEffect) {
            return (T) transitionEffect;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:125:0x03dc  */
    /* JADX WARN: Code duplicated, block: B:126:0x03df  */
    /* JADX WARN: Code duplicated, block: B:129:0x03ed  */
    /* JADX WARN: Code duplicated, block: B:71:0x0239  */
    public static final Modifier createModifier(Transition<EnterExitState> transition, EnterTransition enter, ExitTransition exit, Function0<Boolean> function0, String label, Composer $composer, int $changed, int i) {
        final Function0<Boolean> function1;
        Transition.DeferredAnimation deferredAnimation;
        Transition.DeferredAnimation deferredAnimation2;
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation;
        boolean disableClip;
        Rgb colorSpace;
        final boolean disableClip2;
        Transition<EnterExitState> transition2;
        Modifier.Companion companion;
        EnterTransition activeEnter;
        Modifier.Companion companion2;
        ComposerKt.sourceInformationMarkerStart($composer, 28261782, "C(createModifier)931@41585L8,934@41650L31,935@41703L28,985@43657L56,987@43829L38:EnterExitTransition.kt#xbi5r1");
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, 1389772958, "CC(remember):EnterExitTransition.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function0) new Function0<Boolean>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$1$1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return true;
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            function1 = (Function0) it$iv;
        } else {
            function1 = function0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(28261782, $changed, -1, "androidx.compose.animation.createModifier (EnterExitTransition.kt:933)");
        }
        EnterTransition activeEnter2 = trackActiveEnter(transition, enter, $composer, ($changed & 14) | ($changed & 112));
        ExitTransition activeExit = trackActiveExit(transition, exit, $composer, ($changed & 14) | (($changed >> 3) & 112));
        boolean shouldAnimateVeil = (activeEnter2.getData().getVeil() == null && activeExit.getData().getVeil() == null) ? false : true;
        boolean shouldAnimateSlide = (activeEnter2.getData().getSlide() == null && activeExit.getData().getSlide() == null) ? false : true;
        boolean shouldAnimateSizeChange = (activeEnter2.getData().getChangeSize() == null && activeExit.getData().getChangeSize() == null) ? false : true;
        if (shouldAnimateSlide) {
            $composer.startReplaceGroup(133792645);
            ComposerKt.sourceInformation($composer, "944@42155L27,944@42104L79");
            TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
            ComposerKt.sourceInformationMarkerStart($composer, 1389791217, "CC(remember):EnterExitTransition.kt#9igjgp");
            Object it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = label + " slide";
                $composer.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation2 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter, (String) it$iv2, $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
            deferredAnimation = deferredAnimationCreateDeferredAnimation2;
        } else {
            $composer.startReplaceGroup(133898448);
            $composer.endReplaceGroup();
            deferredAnimation = null;
        }
        Transition.DeferredAnimation slideAnimation = deferredAnimation;
        if (shouldAnimateSizeChange) {
            $composer.startReplaceGroup(133990239);
            ComposerKt.sourceInformation($composer, "950@42352L35,950@42303L85");
            TwoWayConverter<IntSize, AnimationVector2D> vectorConverter2 = VectorConvertersKt.getVectorConverter(IntSize.INSTANCE);
            ComposerKt.sourceInformationMarkerStart($composer, 1389797529, "CC(remember):EnterExitTransition.kt#9igjgp");
            Object it$iv3 = $composer.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = label + " shrink/expand";
                $composer.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation3 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter2, (String) it$iv3, $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
            deferredAnimation2 = deferredAnimationCreateDeferredAnimation3;
        } else {
            $composer.startReplaceGroup(134101063);
            $composer.endReplaceGroup();
            deferredAnimation2 = null;
        }
        Transition.DeferredAnimation sizeAnimation = deferredAnimation2;
        if (shouldAnimateSizeChange) {
            $composer.startReplaceGroup(134174689);
            ComposerKt.sourceInformation($composer, "957@42571L48,955@42487L147");
            TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter3 = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
            ComposerKt.sourceInformationMarkerStart($composer, 1389804550, "CC(remember):EnterExitTransition.kt#9igjgp");
            Object it$iv4 = $composer.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = label + " InterruptionHandlingOffset";
                $composer.updateRememberedValue(value$iv4);
                it$iv4 = value$iv4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            deferredAnimationCreateDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter3, (String) it$iv4, $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(134345095);
            $composer.endReplaceGroup();
            deferredAnimationCreateDeferredAnimation = null;
        }
        Transition.DeferredAnimation offsetAnimation = deferredAnimationCreateDeferredAnimation;
        ChangeSize changeSize = activeEnter2.getData().getChangeSize();
        if ((changeSize == null || changeSize.getClip()) ? false : true) {
            disableClip = true;
        } else {
            ChangeSize changeSize2 = activeExit.getData().getChangeSize();
            if (((changeSize2 == null || changeSize2.getClip()) ? false : true) || !shouldAnimateSizeChange) {
                disableClip = true;
            } else {
                disableClip = false;
            }
        }
        Veil veil = activeEnter2.getData().getVeil();
        if (veil == null || (colorSpace = Color.m5101getColorSpaceimpl(veil.m159getInitialColor0d7_KjU())) == null) {
            Veil veil2 = activeEnter2.getData().getVeil();
            if (veil2 != null) {
                colorSpace = Color.m5101getColorSpaceimpl(veil2.m160getTargetColor0d7_KjU());
            } else {
                Veil veil3 = activeExit.getData().getVeil();
                colorSpace = veil3 != null ? Color.m5101getColorSpaceimpl(veil3.m159getInitialColor0d7_KjU()) : null;
                if (colorSpace == null) {
                    Veil veil4 = activeExit.getData().getVeil();
                    ColorSpace colorSpaceM5101getColorSpaceimpl = veil4 != null ? Color.m5101getColorSpaceimpl(veil4.m160getTargetColor0d7_KjU()) : null;
                    colorSpace = colorSpaceM5101getColorSpaceimpl == null ? ColorSpaces.INSTANCE.getSrgb() : colorSpaceM5101getColorSpaceimpl;
                }
            }
        }
        if (shouldAnimateVeil) {
            $composer.startReplaceGroup(134871569);
            ComposerKt.sourceInformation($composer, "976@43322L26,974@43222L145");
            TwoWayConverter<Color, AnimationVector4D> twoWayConverterInvoke = ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(colorSpace);
            ComposerKt.sourceInformationMarkerStart($composer, 1389828560, "CC(remember):EnterExitTransition.kt#9igjgp");
            boolean disableClip3 = disableClip;
            Object value$iv5 = $composer.rememberedValue();
            if (value$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv5 = label + " veil";
                $composer.updateRememberedValue(value$iv5);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            disableClip2 = disableClip3;
            transition2 = transition;
            Transition.DeferredAnimation veilAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition2, twoWayConverterInvoke, (String) value$iv5, $composer, ($changed & 14) | 384, 0);
            VeilModifierElement veilModifierElement = new VeilModifierElement(transition2, veilAnimation, activeEnter2, activeExit);
            $composer.endReplaceGroup();
            companion = veilModifierElement;
        } else {
            disableClip2 = disableClip;
            transition2 = transition;
            $composer.startReplaceGroup(135150476);
            $composer.endReplaceGroup();
            companion = Modifier.INSTANCE;
        }
        Modifier veilModifierElement2 = companion;
        Veil veil5 = activeEnter2.getData().getVeil();
        boolean shouldVeilMatchParentSize = (veil5 == null && (veil5 = activeExit.getData().getVeil()) == null) ? false : veil5.getMatchParentSize();
        GraphicsLayerBlockForEnterExit graphicsLayerBlock = createGraphicsLayerBlock(transition2, activeEnter2, activeExit, label, $composer, ($changed & 14) | (($changed >> 3) & 7168));
        Modifier.Companion companion3 = shouldVeilMatchParentSize ? veilModifierElement2 : Modifier.INSTANCE;
        Modifier.Companion companion4 = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, 1389844796, "CC(remember):EnterExitTransition.kt#9igjgp");
        boolean invalid$iv = $composer.changed(disableClip2) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(function1)) || ($changed & 3072) == 2048);
        Object it$iv5 = $composer.rememberedValue();
        if (!invalid$iv) {
            activeEnter = activeEnter2;
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierThen = companion3.then(GraphicsLayerModifierKt.graphicsLayer(companion4, (Function1) it$iv5)).then(new EnterExitTransitionElement(transition, sizeAnimation, offsetAnimation, slideAnimation, activeEnter, activeExit, function1, graphicsLayerBlock));
            if (shouldVeilMatchParentSize) {
                companion2 = Modifier.INSTANCE;
            } else {
                companion2 = veilModifierElement2;
            }
            Modifier modifierThen2 = modifierThen.then(companion2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return modifierThen2;
        }
        activeEnter = activeEnter2;
        Object value$iv6 = (Function1) new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                invoke2(graphicsLayerScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                $this$graphicsLayer.setClip(!disableClip2 && function1.invoke().booleanValue());
            }
        };
        $composer.updateRememberedValue(value$iv6);
        it$iv5 = value$iv6;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierThen3 = companion3.then(GraphicsLayerModifierKt.graphicsLayer(companion4, (Function1) it$iv5)).then(new EnterExitTransitionElement(transition, sizeAnimation, offsetAnimation, slideAnimation, activeEnter, activeExit, function1, graphicsLayerBlock));
        if (shouldVeilMatchParentSize) {
            companion2 = veilModifierElement2;
        } else {
            companion2 = Modifier.INSTANCE;
        }
        Modifier modifierThen4 = modifierThen3.then(companion2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return modifierThen4;
    }

    public static final EnterTransition trackActiveEnter(Transition<EnterExitState> transition, EnterTransition enter, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 21614502, "C(trackActiveEnter)1009@44788L40:EnterExitTransition.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(21614502, $changed, -1, "androidx.compose.animation.trackActiveEnter (EnterExitTransition.kt:1004)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -703284114, "CC(remember):EnterExitTransition.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(transition)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(enter, null, 2, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        MutableState activeEnter$delegate = (MutableState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (transition.getCurrentState() == transition.getTargetState() && transition.getCurrentState() == EnterExitState.Visible) {
            if (transition.isSeeking()) {
                activeEnter$delegate.setValue(enter);
            } else {
                activeEnter$delegate.setValue(EnterTransition.INSTANCE.getNone());
            }
        } else if (transition.getTargetState() == EnterExitState.Visible) {
            activeEnter$delegate.setValue(trackActiveEnter$lambda$1(activeEnter$delegate).plus(enter));
        }
        EnterTransition enterTransitionTrackActiveEnter$lambda$1 = trackActiveEnter$lambda$1(activeEnter$delegate);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return enterTransitionTrackActiveEnter$lambda$1;
    }

    private static final EnterTransition trackActiveEnter$lambda$1(MutableState<EnterTransition> mutableState) {
        MutableState<EnterTransition> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    public static final ExitTransition trackActiveExit(Transition<EnterExitState> transition, ExitTransition exit, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1363864804, "C(trackActiveExit)1029@45769L39:EnterExitTransition.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1363864804, $changed, -1, "androidx.compose.animation.trackActiveExit (EnterExitTransition.kt:1024)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -709035581, "CC(remember):EnterExitTransition.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(transition)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(exit, null, 2, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        MutableState activeExit$delegate = (MutableState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (transition.getCurrentState() == transition.getTargetState() && transition.getCurrentState() == EnterExitState.Visible) {
            if (transition.isSeeking()) {
                activeExit$delegate.setValue(exit);
            } else {
                activeExit$delegate.setValue(ExitTransition.INSTANCE.getNone());
            }
        } else if (transition.getTargetState() != EnterExitState.Visible) {
            activeExit$delegate.setValue(trackActiveExit$lambda$1(activeExit$delegate).plus(exit));
        }
        ExitTransition exitTransitionTrackActiveExit$lambda$1 = trackActiveExit$lambda$1(activeExit$delegate);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return exitTransitionTrackActiveExit$lambda$1;
    }

    private static final ExitTransition trackActiveExit$lambda$1(MutableState<ExitTransition> mutableState) {
        MutableState<ExitTransition> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0178 A[PHI: r3
  0x0178: PHI (r3v5 androidx.compose.animation.EnterTransition) = (r3v3 androidx.compose.animation.EnterTransition), (r3v6 androidx.compose.animation.EnterTransition) binds: [B:44:0x0176, B:40:0x016f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:49:0x0184  */
    /* JADX WARN: Code duplicated, block: B:52:0x018d  */
    /* JADX WARN: Code duplicated, block: B:55:0x0193 A[PHI: r5
  0x0193: PHI (r5v8 androidx.compose.animation.ExitTransition) = (r5v6 androidx.compose.animation.ExitTransition), (r5v9 androidx.compose.animation.ExitTransition) binds: [B:54:0x0191, B:50:0x018a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:56:0x0195  */
    /* JADX WARN: Code duplicated, block: B:59:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:62:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:65:0x01b2 A[PHI: r6
  0x01b2: PHI (r6v10 androidx.compose.animation.core.Transition<androidx.compose.animation.EnterExitState>) = 
  (r6v8 androidx.compose.animation.core.Transition<androidx.compose.animation.EnterExitState>)
  (r6v11 androidx.compose.animation.core.Transition<androidx.compose.animation.EnterExitState>)
 binds: [B:64:0x01b0, B:60:0x01a9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:66:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:69:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:72:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:75:0x01fb  */
    private static final GraphicsLayerBlockForEnterExit createGraphicsLayerBlock(Transition<EnterExitState> transition, EnterTransition enter, ExitTransition exit, String label, Composer $composer, int $changed) {
        Transition.DeferredAnimation deferredAnimation;
        Transition.DeferredAnimation deferredAnimation2;
        Transition.DeferredAnimation deferredAnimation3;
        EnterTransition enterTransition;
        boolean z;
        ExitTransition exitTransition;
        boolean z2;
        Transition<EnterExitState> transition2;
        boolean z3;
        boolean invalid$iv;
        Object it$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 642253525, "C(createGraphicsLayerBlock)1084@47699L3043:EnterExitTransition.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(642253525, $changed, -1, "androidx.compose.animation.createGraphicsLayerBlock (EnterExitTransition.kt:1052)");
        }
        boolean shouldAnimateAlpha = (enter.getData().getFade() == null && exit.getData().getFade() == null) ? false : true;
        boolean shouldAnimateScale = (enter.getData().getScale() == null && exit.getData().getScale() == null) ? false : true;
        if (shouldAnimateAlpha) {
            $composer.startReplaceGroup(-703879421);
            ComposerKt.sourceInformation($composer, "1064@47133L27,1062@47029L146");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            ComposerKt.sourceInformationMarkerStart($composer, -1269628144, "CC(remember):EnterExitTransition.kt#9igjgp");
            Object it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv = label + " alpha";
                $composer.updateRememberedValue(value$iv);
                it$iv2 = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter, (String) it$iv2, $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
            deferredAnimation = deferredAnimationCreateDeferredAnimation;
        } else {
            $composer.startReplaceGroup(-703709976);
            $composer.endReplaceGroup();
            deferredAnimation = null;
        }
        final Transition.DeferredAnimation alphaAnimation = deferredAnimation;
        if (shouldAnimateScale) {
            $composer.startReplaceGroup(-703642333);
            ComposerKt.sourceInformation($composer, "1072@47372L27,1070@47268L146");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            ComposerKt.sourceInformationMarkerStart($composer, -1269620496, "CC(remember):EnterExitTransition.kt#9igjgp");
            Object it$iv3 = $composer.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = label + " scale";
                $composer.updateRememberedValue(value$iv2);
                it$iv3 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation2 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter2, (String) it$iv3, $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
            deferredAnimation2 = deferredAnimationCreateDeferredAnimation2;
        } else {
            $composer.startReplaceGroup(-703472888);
            $composer.endReplaceGroup();
            deferredAnimation2 = null;
        }
        final Transition.DeferredAnimation scaleAnimation = deferredAnimation2;
        if (shouldAnimateScale) {
            $composer.startReplaceGroup(-703395232);
            ComposerKt.sourceInformation($composer, "1078@47517L149");
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation3 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, TransformOriginVectorConverter, "TransformOriginInterruptionHandling", $composer, ($changed & 14) | 384, 0);
            $composer.endReplaceGroup();
            deferredAnimation3 = deferredAnimationCreateDeferredAnimation3;
        } else {
            $composer.startReplaceGroup(-703222904);
            $composer.endReplaceGroup();
            deferredAnimation3 = null;
        }
        final Transition.DeferredAnimation transformOriginAnimation = deferredAnimation3;
        ComposerKt.sourceInformationMarkerStart($composer, -1269607016, "CC(remember):EnterExitTransition.kt#9igjgp");
        boolean zChangedInstance = $composer.changedInstance(alphaAnimation);
        if ((($changed & 112) ^ 48) > 32) {
            enterTransition = enter;
            if ($composer.changed(enterTransition)) {
                z = true;
            }
            boolean z4 = zChangedInstance | z;
            if ((($changed & 896) ^ 384) > 256) {
                exitTransition = exit;
                if (!$composer.changed(exitTransition)) {
                    z2 = true;
                }
                boolean zChangedInstance2 = z4 | z2 | $composer.changedInstance(scaleAnimation);
                if ((($changed & 14) ^ 6) > 4) {
                    transition2 = transition;
                    if (!$composer.changed(transition2)) {
                        z3 = true;
                    }
                    invalid$iv = zChangedInstance2 | z3 | $composer.changedInstance(transformOriginAnimation);
                    it$iv = $composer.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        final EnterTransition enterTransition2 = enterTransition;
                        final ExitTransition exitTransition2 = exitTransition;
                        final Transition<EnterExitState> transition3 = transition2;
                        Object value$iv3 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                            @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                            public final Function1 init() {
                                return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition3, enterTransition2, exitTransition2, transformOriginAnimation);
                            }
                        };
                        $composer.updateRememberedValue(value$iv3);
                        it$iv = value$iv3;
                    }
                    GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit = (GraphicsLayerBlockForEnterExit) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return graphicsLayerBlockForEnterExit;
                }
                transition2 = transition;
                if (($changed & 6) == 4) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                invalid$iv = zChangedInstance2 | z3 | $composer.changedInstance(transformOriginAnimation);
                it$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    final EnterTransition enterTransition3 = enterTransition;
                    final ExitTransition exitTransition3 = exitTransition;
                    final Transition transition4 = transition2;
                    Object value$iv4 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                        @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                        public final Function1 init() {
                            return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition4, enterTransition3, exitTransition3, transformOriginAnimation);
                        }
                    };
                    $composer.updateRememberedValue(value$iv4);
                    it$iv = value$iv4;
                } else {
                    final EnterTransition enterTransition4 = enterTransition;
                    final ExitTransition exitTransition4 = exitTransition;
                    final Transition transition5 = transition2;
                    Object value$iv5 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                        @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                        public final Function1 init() {
                            return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition5, enterTransition4, exitTransition4, transformOriginAnimation);
                        }
                    };
                    $composer.updateRememberedValue(value$iv5);
                    it$iv = value$iv5;
                }
                GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit2 = (GraphicsLayerBlockForEnterExit) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return graphicsLayerBlockForEnterExit2;
            }
            exitTransition = exit;
            if (($changed & 384) == 256) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean zChangedInstance3 = z4 | z2 | $composer.changedInstance(scaleAnimation);
            if ((($changed & 14) ^ 6) > 4) {
                transition2 = transition;
                if (!$composer.changed(transition2)) {
                    z3 = true;
                }
                invalid$iv = zChangedInstance3 | z3 | $composer.changedInstance(transformOriginAnimation);
                it$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    final EnterTransition enterTransition5 = enterTransition;
                    final ExitTransition exitTransition5 = exitTransition;
                    final Transition transition6 = transition2;
                    Object value$iv6 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                        @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                        public final Function1 init() {
                            return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition6, enterTransition5, exitTransition5, transformOriginAnimation);
                        }
                    };
                    $composer.updateRememberedValue(value$iv6);
                    it$iv = value$iv6;
                } else {
                    final EnterTransition enterTransition6 = enterTransition;
                    final ExitTransition exitTransition6 = exitTransition;
                    final Transition transition7 = transition2;
                    Object value$iv7 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                        @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                        public final Function1 init() {
                            return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition7, enterTransition6, exitTransition6, transformOriginAnimation);
                        }
                    };
                    $composer.updateRememberedValue(value$iv7);
                    it$iv = value$iv7;
                }
                GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit3 = (GraphicsLayerBlockForEnterExit) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return graphicsLayerBlockForEnterExit3;
            }
            transition2 = transition;
            if (($changed & 6) == 4) {
                z3 = true;
            } else {
                z3 = false;
            }
            invalid$iv = zChangedInstance3 | z3 | $composer.changedInstance(transformOriginAnimation);
            it$iv = $composer.rememberedValue();
            if (invalid$iv) {
                final EnterTransition enterTransition7 = enterTransition;
                final ExitTransition exitTransition7 = exitTransition;
                final Transition transition8 = transition2;
                Object value$iv8 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                    @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                    public final Function1 init() {
                        return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition8, enterTransition7, exitTransition7, transformOriginAnimation);
                    }
                };
                $composer.updateRememberedValue(value$iv8);
                it$iv = value$iv8;
            } else {
                final EnterTransition enterTransition8 = enterTransition;
                final ExitTransition exitTransition8 = exitTransition;
                final Transition transition9 = transition2;
                Object value$iv9 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                    @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                    public final Function1 init() {
                        return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition9, enterTransition8, exitTransition8, transformOriginAnimation);
                    }
                };
                $composer.updateRememberedValue(value$iv9);
                it$iv = value$iv9;
            }
            GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit4 = (GraphicsLayerBlockForEnterExit) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return graphicsLayerBlockForEnterExit4;
        }
        enterTransition = enter;
        if (($changed & 48) == 32) {
            z = true;
        } else {
            z = false;
        }
        boolean z5 = zChangedInstance | z;
        if ((($changed & 896) ^ 384) > 256) {
            exitTransition = exit;
            if (!$composer.changed(exitTransition)) {
                z2 = true;
            }
            boolean zChangedInstance4 = z5 | z2 | $composer.changedInstance(scaleAnimation);
            if ((($changed & 14) ^ 6) > 4) {
                transition2 = transition;
                if (!$composer.changed(transition2)) {
                    z3 = true;
                }
                invalid$iv = zChangedInstance4 | z3 | $composer.changedInstance(transformOriginAnimation);
                it$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    final EnterTransition enterTransition9 = enterTransition;
                    final ExitTransition exitTransition9 = exitTransition;
                    final Transition transition10 = transition2;
                    Object value$iv10 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                        @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                        public final Function1 init() {
                            return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition10, enterTransition9, exitTransition9, transformOriginAnimation);
                        }
                    };
                    $composer.updateRememberedValue(value$iv10);
                    it$iv = value$iv10;
                } else {
                    final EnterTransition enterTransition10 = enterTransition;
                    final ExitTransition exitTransition10 = exitTransition;
                    final Transition transition11 = transition2;
                    Object value$iv11 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                        @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                        public final Function1 init() {
                            return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition11, enterTransition10, exitTransition10, transformOriginAnimation);
                        }
                    };
                    $composer.updateRememberedValue(value$iv11);
                    it$iv = value$iv11;
                }
                GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit5 = (GraphicsLayerBlockForEnterExit) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return graphicsLayerBlockForEnterExit5;
            }
            transition2 = transition;
            if (($changed & 6) == 4) {
                z3 = true;
            } else {
                z3 = false;
            }
            invalid$iv = zChangedInstance4 | z3 | $composer.changedInstance(transformOriginAnimation);
            it$iv = $composer.rememberedValue();
            if (invalid$iv) {
                final EnterTransition enterTransition11 = enterTransition;
                final ExitTransition exitTransition11 = exitTransition;
                final Transition transition12 = transition2;
                Object value$iv12 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                    @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                    public final Function1 init() {
                        return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition12, enterTransition11, exitTransition11, transformOriginAnimation);
                    }
                };
                $composer.updateRememberedValue(value$iv12);
                it$iv = value$iv12;
            } else {
                final EnterTransition enterTransition12 = enterTransition;
                final ExitTransition exitTransition12 = exitTransition;
                final Transition transition13 = transition2;
                Object value$iv13 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                    @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                    public final Function1 init() {
                        return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition13, enterTransition12, exitTransition12, transformOriginAnimation);
                    }
                };
                $composer.updateRememberedValue(value$iv13);
                it$iv = value$iv13;
            }
            GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit6 = (GraphicsLayerBlockForEnterExit) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return graphicsLayerBlockForEnterExit6;
        }
        exitTransition = exit;
        if (($changed & 384) == 256) {
            z2 = true;
        } else {
            z2 = false;
        }
        boolean zChangedInstance5 = z5 | z2 | $composer.changedInstance(scaleAnimation);
        if ((($changed & 14) ^ 6) > 4) {
            transition2 = transition;
            if (!$composer.changed(transition2)) {
                z3 = true;
            }
            invalid$iv = zChangedInstance5 | z3 | $composer.changedInstance(transformOriginAnimation);
            it$iv = $composer.rememberedValue();
            if (invalid$iv) {
                final EnterTransition enterTransition13 = enterTransition;
                final ExitTransition exitTransition13 = exitTransition;
                final Transition transition14 = transition2;
                Object value$iv14 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                    @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                    public final Function1 init() {
                        return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition14, enterTransition13, exitTransition13, transformOriginAnimation);
                    }
                };
                $composer.updateRememberedValue(value$iv14);
                it$iv = value$iv14;
            } else {
                final EnterTransition enterTransition14 = enterTransition;
                final ExitTransition exitTransition14 = exitTransition;
                final Transition transition15 = transition2;
                Object value$iv15 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                    @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                    public final Function1 init() {
                        return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition15, enterTransition14, exitTransition14, transformOriginAnimation);
                    }
                };
                $composer.updateRememberedValue(value$iv15);
                it$iv = value$iv15;
            }
            GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit7 = (GraphicsLayerBlockForEnterExit) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return graphicsLayerBlockForEnterExit7;
        }
        transition2 = transition;
        if (($changed & 6) == 4) {
            z3 = true;
        } else {
            z3 = false;
        }
        invalid$iv = zChangedInstance5 | z3 | $composer.changedInstance(transformOriginAnimation);
        it$iv = $composer.rememberedValue();
        if (invalid$iv) {
            final EnterTransition enterTransition15 = enterTransition;
            final ExitTransition exitTransition15 = exitTransition;
            final Transition transition16 = transition2;
            Object value$iv16 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                public final Function1 init() {
                    return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition16, enterTransition15, exitTransition15, transformOriginAnimation);
                }
            };
            $composer.updateRememberedValue(value$iv16);
            it$iv = value$iv16;
        } else {
            final EnterTransition enterTransition16 = enterTransition;
            final ExitTransition exitTransition16 = exitTransition;
            final Transition transition17 = transition2;
            Object value$iv17 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                public final Function1 init() {
                    return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$2$0(alphaAnimation, scaleAnimation, transition17, enterTransition16, exitTransition16, transformOriginAnimation);
                }
            };
            $composer.updateRememberedValue(value$iv17);
            it$iv = value$iv17;
        }
        GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit8 = (GraphicsLayerBlockForEnterExit) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return graphicsLayerBlockForEnterExit8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1 createGraphicsLayerBlock$lambda$2$0(Transition.DeferredAnimation $alphaAnimation, Transition.DeferredAnimation $scaleAnimation, Transition $this_createGraphicsLayerBlock, final EnterTransition $enter, final ExitTransition $exit, Transition.DeferredAnimation $transformOriginAnimation) {
        final TransformOrigin transformOriginWhenVisible;
        final State alpha = $alphaAnimation != null ? $alphaAnimation.animate(new Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$alpha$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<EnterExitState> segment) {
                FiniteAnimationSpec<Float> animationSpec;
                FiniteAnimationSpec<Float> animationSpec2;
                if (segment.isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible)) {
                    Fade fade = $enter.getData().getFade();
                    if (fade == null || (animationSpec2 = fade.getAnimationSpec()) == null) {
                        return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                    }
                    return animationSpec2;
                }
                if (!segment.isTransitioningTo(EnterExitState.Visible, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                Fade fade2 = $exit.getData().getFade();
                if (fade2 == null || (animationSpec = fade2.getAnimationSpec()) == null) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                return animationSpec;
            }
        }, new Function1<EnterExitState, Float>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$alpha$2

            /* JADX INFO: compiled from: EnterExitTransition.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Float invoke(EnterExitState it) {
                float alpha2 = 1.0f;
                switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                    case 1:
                        break;
                    case 2:
                        Fade fade = $enter.getData().getFade();
                        if (fade != null) {
                            alpha2 = fade.getAlpha();
                        }
                        break;
                    case 3:
                        Fade fade2 = $exit.getData().getFade();
                        if (fade2 != null) {
                            alpha2 = fade2.getAlpha();
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                return Float.valueOf(alpha2);
            }
        }) : null;
        final State scale = $scaleAnimation != null ? $scaleAnimation.animate(new Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$scale$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<EnterExitState> segment) {
                FiniteAnimationSpec<Float> animationSpec;
                FiniteAnimationSpec<Float> animationSpec2;
                if (segment.isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible)) {
                    Scale scale2 = $enter.getData().getScale();
                    if (scale2 == null || (animationSpec2 = scale2.getAnimationSpec()) == null) {
                        return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                    }
                    return animationSpec2;
                }
                if (!segment.isTransitioningTo(EnterExitState.Visible, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                Scale scale3 = $exit.getData().getScale();
                if (scale3 == null || (animationSpec = scale3.getAnimationSpec()) == null) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                return animationSpec;
            }
        }, new Function1<EnterExitState, Float>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$scale$2

            /* JADX INFO: compiled from: EnterExitTransition.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Float invoke(EnterExitState it) {
                float scale2 = 1.0f;
                switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                    case 1:
                        break;
                    case 2:
                        Scale scale3 = $enter.getData().getScale();
                        if (scale3 != null) {
                            scale2 = scale3.getScale();
                        }
                        break;
                    case 3:
                        Scale scale4 = $exit.getData().getScale();
                        if (scale4 != null) {
                            scale2 = scale4.getScale();
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                return Float.valueOf(scale2);
            }
        }) : null;
        if ($this_createGraphicsLayerBlock.getCurrentState() == EnterExitState.PreEnter) {
            Scale scale2 = $enter.getData().getScale();
            transformOriginWhenVisible = (scale2 == null && (scale2 = $exit.getData().getScale()) == null) ? null : TransformOrigin.m5497boximpl(scale2.m120getTransformOriginSzJe1aQ());
        } else {
            Scale scale3 = $exit.getData().getScale();
            transformOriginWhenVisible = (scale3 == null && (scale3 = $enter.getData().getScale()) == null) ? null : TransformOrigin.m5497boximpl(scale3.m120getTransformOriginSzJe1aQ());
        }
        final State transformOrigin = $transformOriginAnimation != null ? $transformOriginAnimation.animate(new Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<TransformOrigin>>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$1
            @Override // kotlin.jvm.functions.Function1
            public final FiniteAnimationSpec<TransformOrigin> invoke(Transition.Segment<EnterExitState> segment) {
                return AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
            }
        }, new Function1<EnterExitState, TransformOrigin>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$2

            /* JADX INFO: compiled from: EnterExitTransition.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ TransformOrigin invoke(EnterExitState enterExitState) {
                return TransformOrigin.m5497boximpl(m96invokeLIALnN8(enterExitState));
            }

            /* JADX INFO: renamed from: invoke-LIALnN8, reason: not valid java name */
            public final long m96invokeLIALnN8(EnterExitState it) {
                TransformOrigin transformOriginM5497boximpl = null;
                switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                    case 1:
                        transformOriginM5497boximpl = transformOriginWhenVisible;
                        break;
                    case 2:
                        Scale scale4 = $enter.getData().getScale();
                        if (scale4 != null || (scale4 = $exit.getData().getScale()) != null) {
                            transformOriginM5497boximpl = TransformOrigin.m5497boximpl(scale4.m120getTransformOriginSzJe1aQ());
                        }
                        break;
                    case 3:
                        Scale scale5 = $exit.getData().getScale();
                        if (scale5 != null || (scale5 = $enter.getData().getScale()) != null) {
                            transformOriginM5497boximpl = TransformOrigin.m5497boximpl(scale5.m120getTransformOriginSzJe1aQ());
                        }
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                return transformOriginM5497boximpl != null ? transformOriginM5497boximpl.getPackedValue() : TransformOrigin.INSTANCE.m5510getCenterSzJe1aQ();
            }
        }) : null;
        return new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$block$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                invoke2(graphicsLayerScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GraphicsLayerScope graphicsLayerScope) {
                State<Float> state = alpha;
                graphicsLayerScope.setAlpha(state != null ? state.getValue().floatValue() : 1.0f);
                State<Float> state2 = scale;
                graphicsLayerScope.setScaleX(state2 != null ? state2.getValue().floatValue() : 1.0f);
                State<Float> state3 = scale;
                graphicsLayerScope.setScaleY(state3 != null ? state3.getValue().floatValue() : 1.0f);
                State<TransformOrigin> state4 = transformOrigin;
                graphicsLayerScope.mo5298setTransformOrigin__ExYCQ(state4 != null ? state4.getValue().getPackedValue() : TransformOrigin.INSTANCE.m5510getCenterSzJe1aQ());
            }
        };
    }
}
