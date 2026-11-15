package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Tab.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class TabKt$TabBaselineLayout$2$1 implements MeasurePolicy {
    final /* synthetic */ Function2<Composer, Integer, Unit> $icon;
    final /* synthetic */ Function2<Composer, Integer, Unit> $text;

    /* JADX WARN: Multi-variable type inference failed */
    TabKt$TabBaselineLayout$2$1(Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3) {
        this.$text = function2;
        this.$icon = function3;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo42measure3p2s80s(final MeasureScope $this$Layout, List<? extends Measurable> list, long j) {
        Placeable placeableMo6541measureBRTryo0;
        final Placeable iconPlaceable;
        Function2<Composer, Integer, Unit> function2 = this.$text;
        if (function2 != null) {
            int index$iv$iv = 0;
            int size = list.size();
            while (true) {
                if (index$iv$iv >= size) {
                    ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                    throw new KotlinNothingValueException();
                }
                Object item$iv$iv = list.get(index$iv$iv);
                Measurable it = (Measurable) item$iv$iv;
                Function2<Composer, Integer, Unit> function3 = function2;
                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "text")) {
                    placeableMo6541measureBRTryo0 = ((Measurable) item$iv$iv).mo6541measureBRTryo0(Constraints.m7847copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(j) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(j) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(j) : 0));
                    break;
                }
                index$iv$iv++;
                function2 = function3;
            }
        } else {
            placeableMo6541measureBRTryo0 = null;
        }
        final Placeable textPlaceable = placeableMo6541measureBRTryo0;
        Function2<Composer, Integer, Unit> function4 = this.$icon;
        if (function4 != null) {
            int index$iv$iv2 = 0;
            int size2 = list.size();
            while (true) {
                if (index$iv$iv2 >= size2) {
                    ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                    throw new KotlinNothingValueException();
                }
                Object item$iv$iv2 = list.get(index$iv$iv2);
                Measurable it2 = (Measurable) item$iv$iv2;
                Function2<Composer, Integer, Unit> function5 = function4;
                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "icon")) {
                    iconPlaceable = ((Measurable) item$iv$iv2).mo6541measureBRTryo0(j);
                    break;
                }
                index$iv$iv2++;
                function4 = function5;
            }
        } else {
            iconPlaceable = null;
        }
        final int tabWidth = Math.max(textPlaceable != null ? textPlaceable.getWidth() : 0, iconPlaceable != null ? iconPlaceable.getWidth() : 0);
        final int tabHeight = $this$Layout.mo400roundToPx0680j_4((textPlaceable == null || iconPlaceable == null) ? TabKt.SmallTabHeight : TabKt.LargeTabHeight);
        final Integer firstBaseline = textPlaceable != null ? Integer.valueOf(textPlaceable.get(AlignmentLineKt.getFirstBaseline())) : null;
        final Integer lastBaseline = textPlaceable != null ? Integer.valueOf(textPlaceable.get(AlignmentLineKt.getLastBaseline())) : null;
        return MeasureScope.layout$default($this$Layout, tabWidth, tabHeight, null, new Function1() { // from class: androidx.compose.material.TabKt$TabBaselineLayout$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TabKt$TabBaselineLayout$2$1.measure_3p2s80s$lambda$2(textPlaceable, iconPlaceable, $this$Layout, tabWidth, tabHeight, firstBaseline, lastBaseline, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$2(Placeable $textPlaceable, Placeable $iconPlaceable, MeasureScope $this_Layout, int $tabWidth, int $tabHeight, Integer $firstBaseline, Integer $lastBaseline, Placeable.PlacementScope $this$layout) {
        if ($textPlaceable != null && $iconPlaceable != null) {
            Intrinsics.checkNotNull($firstBaseline);
            int iIntValue = $firstBaseline.intValue();
            Intrinsics.checkNotNull($lastBaseline);
            TabKt.placeTextAndIcon($this$layout, $this_Layout, $textPlaceable, $iconPlaceable, $tabWidth, $tabHeight, iIntValue, $lastBaseline.intValue());
        } else if ($textPlaceable != null) {
            TabKt.placeTextOrIcon($this$layout, $textPlaceable, $tabHeight);
        } else if ($iconPlaceable != null) {
            TabKt.placeTextOrIcon($this$layout, $iconPlaceable, $tabHeight);
        }
        return Unit.INSTANCE;
    }
}
