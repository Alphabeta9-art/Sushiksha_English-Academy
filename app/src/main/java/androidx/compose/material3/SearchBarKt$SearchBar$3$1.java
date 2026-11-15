package androidx.compose.material3;

import androidx.activity.BackEventCompat;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SearchBar.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.SearchBarKt$SearchBar$3$1", f = "SearchBar.kt", i = {}, l = {559}, m = "invokeSuspend", n = {}, s = {})
final class SearchBarKt$SearchBar$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $animationProgress;
    final /* synthetic */ MutableState<BackEventCompat> $currentBackEvent;
    final /* synthetic */ boolean $expanded;
    final /* synthetic */ MutableFloatState $finalBackProgress;
    final /* synthetic */ MutableState<BackEventCompat> $firstBackEvent;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SearchBarKt$SearchBar$3$1(Animatable<Float, AnimationVector1D> animatable, boolean z, MutableFloatState mutableFloatState, MutableState<BackEventCompat> mutableState, MutableState<BackEventCompat> mutableState2, Continuation<? super SearchBarKt$SearchBar$3$1> continuation) {
        super(2, continuation);
        this.$animationProgress = animatable;
        this.$expanded = z;
        this.$finalBackProgress = mutableFloatState;
        this.$firstBackEvent = mutableState;
        this.$currentBackEvent = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SearchBarKt$SearchBar$3$1(this.$animationProgress, this.$expanded, this.$finalBackProgress, this.$firstBackEvent, this.$currentBackEvent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SearchBarKt$SearchBar$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0096  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        FiniteAnimationSpec animationSpec;
        Object $result2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                boolean animationInProgress = this.$animationProgress.getValue().floatValue() > 0.0f && this.$animationProgress.getValue().floatValue() < 1.0f;
                if (animationInProgress) {
                    animationSpec = SearchBarKt.AnimationPredictiveBackExitFloatSpec;
                } else {
                    boolean animationInProgress2 = this.$expanded;
                    animationSpec = animationInProgress2 ? SearchBarKt.AnimationEnterFloatSpec : SearchBarKt.AnimationExitFloatSpec;
                }
                float targetValue = this.$expanded ? 1.0f : 0.0f;
                if (!(this.$animationProgress.getValue().floatValue() == targetValue)) {
                    Animatable<Float, AnimationVector1D> animatable = this.$animationProgress;
                    SearchBarKt$SearchBar$3$1 searchBarKt$SearchBar$3$1 = this;
                    this.label = 1;
                    if (animatable.animateTo(Boxing.boxFloat(targetValue), (4 & 2) != 0 ? animatable.defaultSpringSpec : animationSpec, (4 & 4) != 0 ? animatable.getVelocity() : null, (4 & 8) != 0 ? null : null, searchBarKt$SearchBar$3$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                }
                if (!this.$expanded) {
                    this.$finalBackProgress.setFloatValue(Float.NaN);
                    this.$firstBackEvent.setValue(null);
                    this.$currentBackEvent.setValue(null);
                }
                return Unit.INSTANCE;
            case 1:
                $result2 = $result;
                ResultKt.throwOnFailure($result2);
                if (!this.$expanded) {
                    this.$finalBackProgress.setFloatValue(Float.NaN);
                    this.$firstBackEvent.setValue(null);
                    this.$currentBackEvent.setValue(null);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
