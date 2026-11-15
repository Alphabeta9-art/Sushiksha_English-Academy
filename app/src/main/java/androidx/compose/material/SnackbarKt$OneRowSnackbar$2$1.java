package androidx.compose.material;

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
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Snackbar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class SnackbarKt$OneRowSnackbar$2$1 implements MeasurePolicy {
    final /* synthetic */ String $actionTag;
    final /* synthetic */ String $textTag;

    SnackbarKt$OneRowSnackbar$2$1(String str, String str2) {
        this.$actionTag = str;
        this.$textTag = str2;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo42measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long j) {
        int baselineOffset;
        final int buttonBaseline;
        final int containerHeight;
        SnackbarKt$OneRowSnackbar$2$1 snackbarKt$OneRowSnackbar$2$1 = this;
        String str = snackbarKt$OneRowSnackbar$2$1.$actionTag;
        int index$iv$iv = 0;
        int size = list.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), str)) {
                Measurable measurable = (Measurable) item$iv$iv;
                long j2 = j;
                final Placeable buttonPlaceable = measurable.mo6541measureBRTryo0(j2);
                int textMaxWidth = RangesKt.coerceAtLeast((Constraints.m7858getMaxWidthimpl(j2) - buttonPlaceable.getWidth()) - $this$Layout.mo400roundToPx0680j_4(SnackbarKt.TextEndExtraSpacing), Constraints.m7860getMinWidthimpl(j2));
                String str2 = snackbarKt$OneRowSnackbar$2$1.$textTag;
                int index$iv$iv2 = 0;
                int size2 = list.size();
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = list.get(index$iv$iv2);
                    Measurable it2 = (Measurable) item$iv$iv2;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), str2)) {
                        final Placeable textPlaceable = ((Measurable) item$iv$iv2).mo6541measureBRTryo0(Constraints.m7847copyZbe2FdA(j2, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(j2) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(j2) : textMaxWidth, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(j2) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(j2) : 0));
                        int firstTextBaseline = textPlaceable.get(AlignmentLineKt.getFirstBaseline());
                        int lastTextBaseline = textPlaceable.get(AlignmentLineKt.getLastBaseline());
                        boolean z = true;
                        boolean hasText = (firstTextBaseline == Integer.MIN_VALUE || lastTextBaseline == Integer.MIN_VALUE) ? false : true;
                        if (firstTextBaseline != lastTextBaseline && hasText) {
                            z = false;
                        }
                        boolean isOneLine = z;
                        final int buttonPlaceX = Constraints.m7858getMaxWidthimpl(j) - buttonPlaceable.getWidth();
                        if (isOneLine) {
                            int minContainerHeight = $this$Layout.mo400roundToPx0680j_4(SnackbarKt.SnackbarMinHeightOneLine);
                            int contentHeight = buttonPlaceable.getHeight();
                            int containerHeight2 = Math.max(minContainerHeight, contentHeight);
                            int textPlaceY = (containerHeight2 - textPlaceable.getHeight()) / 2;
                            int buttonBaseline2 = buttonPlaceable.get(AlignmentLineKt.getFirstBaseline());
                            int i = buttonBaseline2 != Integer.MIN_VALUE ? (textPlaceY + firstTextBaseline) - buttonBaseline2 : 0;
                            baselineOffset = containerHeight2;
                            buttonBaseline = textPlaceY;
                            containerHeight = i;
                        } else {
                            int baselineOffset2 = $this$Layout.mo400roundToPx0680j_4(SnackbarKt.HeightToFirstLine);
                            int textPlaceY2 = baselineOffset2 - firstTextBaseline;
                            int minContainerHeight2 = $this$Layout.mo400roundToPx0680j_4(SnackbarKt.SnackbarMinHeightTwoLines);
                            int contentHeight2 = textPlaceY2 + textPlaceable.getHeight();
                            int containerHeight3 = Math.max(minContainerHeight2, contentHeight2);
                            int height = (containerHeight3 - buttonPlaceable.getHeight()) / 2;
                            baselineOffset = containerHeight3;
                            buttonBaseline = textPlaceY2;
                            containerHeight = height;
                        }
                        int buttonPlaceY = baselineOffset;
                        return MeasureScope.layout$default($this$Layout, Constraints.m7858getMaxWidthimpl(j), buttonPlaceY, null, new Function1() { // from class: androidx.compose.material.SnackbarKt$OneRowSnackbar$2$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return SnackbarKt$OneRowSnackbar$2$1.measure_3p2s80s$lambda$3(textPlaceable, buttonBaseline, buttonPlaceable, buttonPlaceX, containerHeight, (Placeable.PlacementScope) obj);
                            }
                        }, 4, null);
                    }
                    index$iv$iv2++;
                    $this$Layout = $this$Layout;
                    j2 = j;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                throw new KotlinNothingValueException();
            }
            index$iv$iv++;
            snackbarKt$OneRowSnackbar$2$1 = this;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    static final Unit measure_3p2s80s$lambda$3(Placeable $textPlaceable, int $textPlaceY, Placeable $buttonPlaceable, int $buttonPlaceX, int $buttonPlaceY, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $textPlaceable, 0, $textPlaceY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $buttonPlaceable, $buttonPlaceX, $buttonPlaceY, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
