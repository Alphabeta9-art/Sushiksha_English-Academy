package androidx.compose.material;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material.internal.LayoutUtilKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ)\u0010\n\u001a\u00020\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\"\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00170\u000e2\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\"\u0010\u0019\u001a\u00020\u0015*\u00020\u00162\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00170\u000e2\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J\"\u0010\u001a\u001a\u00020\u0015*\u00020\u00162\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00170\u000e2\u0006\u0010\u001b\u001a\u00020\u0015H\u0016J\"\u0010\u001c\u001a\u00020\u0015*\u00020\u00162\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00170\u000e2\u0006\u0010\u001b\u001a\u00020\u0015H\u0016J8\u0010\u001d\u001a\u00020\u00152\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00170\u000e2\u0006\u0010\u001b\u001a\u00020\u00152\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u001fH\u0002J<\u0010 \u001a\u00020\u0015*\u00020\u00162\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00170\u000e2\u0006\u0010\u0018\u001a\u00020\u00152\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Landroidx/compose/material/TextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "singleLine", "", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "<init>", "(ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "minIntrinsicHeight", "maxIntrinsicWidth", "height", "minIntrinsicWidth", "intrinsicWidth", "intrinsicMeasurer", "Lkotlin/Function2;", "intrinsicHeight", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class TextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    public TextFieldMeasurePolicy(boolean singleLine, float animationProgress, PaddingValues paddingValues) {
        this.singleLine = singleLine;
        this.animationProgress = animationProgress;
        this.paddingValues = paddingValues;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo42measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long j) {
        int occupiedSpaceHorizontally;
        Object it$iv;
        Placeable leadingPlaceable;
        long looseConstraints;
        Object it$iv2;
        long looseConstraints2;
        final Placeable leadingPlaceable2;
        Object it$iv3;
        int it;
        Object it$iv4;
        final TextFieldMeasurePolicy textFieldMeasurePolicy = this;
        final MeasureScope measureScope = $this$measure_u2d3p2s80s;
        final int topPaddingValue = measureScope.mo400roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getTop());
        int bottomPaddingValue = measureScope.mo400roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getBottom());
        final int topPadding = measureScope.mo400roundToPx0680j_4(TextFieldKt.getTextFieldTopPadding());
        int occupiedSpaceHorizontally2 = 0;
        long looseConstraints3 = Constraints.m7847copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(j) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(j) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(j) : 0);
        int index$iv$iv = 0;
        int size = list.size();
        while (true) {
            if (index$iv$iv >= size) {
                occupiedSpaceHorizontally = occupiedSpaceHorizontally2;
                it$iv = null;
                break;
            }
            it$iv = list.get(index$iv$iv);
            Measurable it2 = (Measurable) it$iv;
            occupiedSpaceHorizontally = occupiedSpaceHorizontally2;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Leading")) {
                break;
            }
            index$iv$iv++;
            occupiedSpaceHorizontally2 = occupiedSpaceHorizontally;
        }
        Measurable measurable = (Measurable) it$iv;
        Placeable leadingPlaceable3 = measurable != null ? measurable.mo6541measureBRTryo0(looseConstraints3) : null;
        int occupiedSpaceHorizontally3 = occupiedSpaceHorizontally + TextFieldImplKt.widthOrZero(leadingPlaceable3);
        int index$iv$iv2 = 0;
        int size2 = list.size();
        while (true) {
            if (index$iv$iv2 >= size2) {
                leadingPlaceable = leadingPlaceable3;
                looseConstraints = looseConstraints3;
                it$iv2 = null;
                break;
            }
            it$iv2 = list.get(index$iv$iv2);
            Measurable it3 = (Measurable) it$iv2;
            leadingPlaceable = leadingPlaceable3;
            looseConstraints = looseConstraints3;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it3), "Trailing")) {
                break;
            }
            index$iv$iv2++;
            looseConstraints3 = looseConstraints;
            leadingPlaceable3 = leadingPlaceable;
        }
        Measurable measurable2 = (Measurable) it$iv2;
        if (measurable2 != null) {
            looseConstraints2 = looseConstraints;
            leadingPlaceable2 = measurable2.mo6541measureBRTryo0(ConstraintsKt.m7878offsetNN6EwU$default(looseConstraints2, -occupiedSpaceHorizontally3, 0, 2, null));
        } else {
            looseConstraints2 = looseConstraints;
            leadingPlaceable2 = null;
        }
        int occupiedSpaceHorizontally4 = TextFieldImplKt.widthOrZero(leadingPlaceable2) + occupiedSpaceHorizontally3;
        int occupiedSpaceHorizontally5 = -bottomPaddingValue;
        long labelConstraints = ConstraintsKt.m7877offsetNN6EwU(looseConstraints2, -occupiedSpaceHorizontally4, occupiedSpaceHorizontally5);
        int index$iv$iv3 = 0;
        int size3 = list.size();
        while (true) {
            if (index$iv$iv3 >= size3) {
                it$iv3 = null;
                break;
            }
            it$iv3 = list.get(index$iv$iv3);
            Measurable it4 = (Measurable) it$iv3;
            int i = size3;
            long looseConstraints4 = looseConstraints2;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it4), "Label")) {
                break;
            }
            index$iv$iv3++;
            size3 = i;
            looseConstraints2 = looseConstraints4;
        }
        Measurable measurable3 = (Measurable) it$iv3;
        Placeable labelPlaceable = measurable3 != null ? measurable3.mo6541measureBRTryo0(labelConstraints) : null;
        if (labelPlaceable != null) {
            it = labelPlaceable.get(AlignmentLineKt.getLastBaseline());
            if (it == Integer.MIN_VALUE) {
                it = labelPlaceable.getHeight();
            }
        } else {
            it = 0;
        }
        final int effectiveLabelBaseline = Math.max(it, topPaddingValue);
        int topPaddingValue2 = labelPlaceable != null ? ((-bottomPaddingValue) - topPadding) - effectiveLabelBaseline : (-topPaddingValue) - bottomPaddingValue;
        final int lastBaseline = it;
        long textFieldConstraints = ConstraintsKt.m7877offsetNN6EwU(Constraints.m7847copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(j) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(j) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(j) : 0), -occupiedSpaceHorizontally4, topPaddingValue2);
        List<? extends Measurable> list2 = list;
        int size4 = list2.size();
        int occupiedSpaceHorizontally6 = 0;
        while (occupiedSpaceHorizontally6 < size4) {
            final Placeable labelPlaceable2 = labelPlaceable;
            List<? extends Measurable> list3 = list2;
            Object item$iv$iv = list3.get(occupiedSpaceHorizontally6);
            Measurable it5 = (Measurable) item$iv$iv;
            int index$iv$iv4 = occupiedSpaceHorizontally6;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it5), "TextField")) {
                final Placeable textFieldPlaceable = ((Measurable) item$iv$iv).mo6541measureBRTryo0(textFieldConstraints);
                long placeholderConstraints = Constraints.m7847copyZbe2FdA(textFieldConstraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(textFieldConstraints) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(textFieldConstraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(textFieldConstraints) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(textFieldConstraints) : 0);
                int size5 = list.size();
                int index$iv$iv5 = 0;
                while (true) {
                    if (index$iv$iv5 >= size5) {
                        it$iv4 = null;
                        break;
                    }
                    it$iv4 = list.get(index$iv$iv5);
                    Measurable it6 = (Measurable) it$iv4;
                    int i2 = size5;
                    int index$iv$iv6 = index$iv$iv5;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it6), "Hint")) {
                        break;
                    }
                    index$iv$iv5 = index$iv$iv6 + 1;
                    size5 = i2;
                }
                Measurable measurable4 = (Measurable) it$iv4;
                Placeable placeholderPlaceable = measurable4 != null ? measurable4.mo6541measureBRTryo0(placeholderConstraints) : null;
                final int width = TextFieldKt.m2009calculateWidthVsPV1Ek(TextFieldImplKt.widthOrZero(leadingPlaceable), TextFieldImplKt.widthOrZero(leadingPlaceable2), textFieldPlaceable.getWidth(), TextFieldImplKt.widthOrZero(labelPlaceable2), TextFieldImplKt.widthOrZero(placeholderPlaceable), j);
                final int height = TextFieldKt.m2008calculateHeightO3s9Psw(textFieldPlaceable.getHeight(), labelPlaceable2 != null, effectiveLabelBaseline, TextFieldImplKt.heightOrZero(leadingPlaceable), TextFieldImplKt.heightOrZero(leadingPlaceable2), TextFieldImplKt.heightOrZero(placeholderPlaceable), j, measureScope.getDensity(), textFieldMeasurePolicy.paddingValues);
                final Placeable placeholderPlaceable2 = placeholderPlaceable;
                final Placeable leadingPlaceable4 = leadingPlaceable;
                return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, height, null, new Function1() { // from class: androidx.compose.material.TextFieldMeasurePolicy$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TextFieldMeasurePolicy.measure_3p2s80s$lambda$6(labelPlaceable2, topPaddingValue, lastBaseline, width, height, textFieldPlaceable, placeholderPlaceable2, leadingPlaceable4, leadingPlaceable2, textFieldMeasurePolicy, effectiveLabelBaseline, topPadding, measureScope, (Placeable.PlacementScope) obj);
                    }
                }, 4, null);
            }
            Placeable trailingPlaceable = leadingPlaceable2;
            Placeable trailingPlaceable2 = leadingPlaceable;
            int verticalConstraintOffset = topPaddingValue2;
            occupiedSpaceHorizontally6 = index$iv$iv4 + 1;
            measureScope = $this$measure_u2d3p2s80s;
            topPaddingValue2 = verticalConstraintOffset;
            labelPlaceable = labelPlaceable2;
            list2 = list3;
            leadingPlaceable = trailingPlaceable2;
            leadingPlaceable2 = trailingPlaceable;
            textFieldMeasurePolicy = this;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    static final Unit measure_3p2s80s$lambda$6(Placeable $labelPlaceable, int $topPaddingValue, int $lastBaseline, int $width, int $height, Placeable $textFieldPlaceable, Placeable $placeholderPlaceable, Placeable $leadingPlaceable, Placeable $trailingPlaceable, TextFieldMeasurePolicy this$0, int $effectiveLabelBaseline, int $topPadding, MeasureScope $this_measure, Placeable.PlacementScope $this$layout) {
        if ($labelPlaceable == null) {
            TextFieldKt.placeWithoutLabel($this$layout, $width, $height, $textFieldPlaceable, $placeholderPlaceable, $leadingPlaceable, $trailingPlaceable, this$0.singleLine, $this_measure.getDensity(), this$0.paddingValues);
        } else {
            int labelEndPosition = RangesKt.coerceAtLeast($topPaddingValue - $lastBaseline, 0);
            TextFieldKt.placeWithLabel($this$layout, $width, $height, $textFieldPlaceable, $labelPlaceable, $placeholderPlaceable, $leadingPlaceable, $trailingPlaceable, this$0.singleLine, labelEndPosition, $effectiveLabelBaseline + $topPadding, this$0.animationProgress, $this_measure.getDensity());
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        return intrinsicHeight($this$maxIntrinsicHeight, list, width, new Function2() { // from class: androidx.compose.material.TextFieldMeasurePolicy$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).maxIntrinsicHeight(((Integer) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
        return intrinsicHeight($this$minIntrinsicHeight, list, width, new Function2() { // from class: androidx.compose.material.TextFieldMeasurePolicy$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).minIntrinsicHeight(((Integer) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        return intrinsicWidth(list, height, new Function2() { // from class: androidx.compose.material.TextFieldMeasurePolicy$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).maxIntrinsicWidth(((Integer) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
        return intrinsicWidth(list, height, new Function2() { // from class: androidx.compose.material.TextFieldMeasurePolicy$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).minIntrinsicWidth(((Integer) obj2).intValue()));
            }
        });
    }

    private final int intrinsicWidth(List<? extends IntrinsicMeasurable> measurables, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> intrinsicMeasurer) {
        Object it$iv;
        Object it$iv2;
        Object it$iv3;
        int leadingWidth;
        Object obj;
        int size = measurables.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = measurables.get(index$iv$iv);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv), "TextField")) {
                int textFieldWidth = intrinsicMeasurer.invoke(item$iv$iv, Integer.valueOf(height)).intValue();
                int index$iv$iv2 = 0;
                int size2 = measurables.size();
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        it$iv = null;
                        break;
                    }
                    it$iv = measurables.get(index$iv$iv2);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv), "Label")) {
                        break;
                    }
                    index$iv$iv2++;
                }
                IntrinsicMeasurable it = (IntrinsicMeasurable) it$iv;
                int labelWidth = it != null ? intrinsicMeasurer.invoke(it, Integer.valueOf(height)).intValue() : 0;
                int index$iv$iv3 = 0;
                int size3 = measurables.size();
                while (true) {
                    if (index$iv$iv3 >= size3) {
                        it$iv2 = null;
                        break;
                    }
                    it$iv2 = measurables.get(index$iv$iv3);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv2), "Trailing")) {
                        break;
                    }
                    index$iv$iv3++;
                }
                IntrinsicMeasurable it2 = (IntrinsicMeasurable) it$iv2;
                int trailingWidth = it2 != null ? intrinsicMeasurer.invoke(it2, Integer.valueOf(height)).intValue() : 0;
                List<? extends IntrinsicMeasurable> list = measurables;
                int index$iv$iv4 = 0;
                int size4 = list.size();
                while (true) {
                    if (index$iv$iv4 >= size4) {
                        it$iv3 = null;
                        break;
                    }
                    it$iv3 = list.get(index$iv$iv4);
                    List<? extends IntrinsicMeasurable> list2 = list;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv3), "Leading")) {
                        break;
                    }
                    index$iv$iv4++;
                    list = list2;
                }
                IntrinsicMeasurable it3 = (IntrinsicMeasurable) it$iv3;
                int leadingWidth2 = it3 != null ? intrinsicMeasurer.invoke(it3, Integer.valueOf(height)).intValue() : 0;
                List<? extends IntrinsicMeasurable> list3 = measurables;
                int index$iv$iv5 = 0;
                int size5 = list3.size();
                while (true) {
                    if (index$iv$iv5 >= size5) {
                        leadingWidth = leadingWidth2;
                        obj = null;
                        break;
                    }
                    Object item$iv$iv2 = list3.get(index$iv$iv5);
                    List<? extends IntrinsicMeasurable> list4 = list3;
                    leadingWidth = leadingWidth2;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv2), "Hint")) {
                        obj = item$iv$iv2;
                        break;
                    }
                    index$iv$iv5++;
                    list3 = list4;
                    leadingWidth2 = leadingWidth;
                }
                IntrinsicMeasurable it4 = (IntrinsicMeasurable) obj;
                int placeholderWidth = it4 != null ? intrinsicMeasurer.invoke(it4, Integer.valueOf(height)).intValue() : 0;
                return TextFieldKt.m2009calculateWidthVsPV1Ek(leadingWidth, trailingWidth, textFieldWidth, labelWidth, placeholderWidth, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }

    private final int intrinsicHeight(IntrinsicMeasureScope $this$intrinsicHeight, List<? extends IntrinsicMeasurable> list, int width, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
        Object it$iv;
        int iIntValue;
        Object it$iv2;
        int trailingHeight;
        int remainingWidth;
        Object it$iv3;
        Object it$iv4;
        int remainingWidth2 = width;
        int index$iv$iv = 0;
        int size = list.size();
        while (true) {
            if (index$iv$iv >= size) {
                it$iv = null;
                break;
            }
            it$iv = list.get(index$iv$iv);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv), "Leading")) {
                break;
            }
            index$iv$iv++;
        }
        IntrinsicMeasurable it = (IntrinsicMeasurable) it$iv;
        if (it != null) {
            remainingWidth2 = LayoutUtilKt.subtractConstraintSafely(remainingWidth2, it.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue = function2.invoke(it, Integer.valueOf(width)).intValue();
        } else {
            iIntValue = 0;
        }
        int leadingHeight = iIntValue;
        int index$iv$iv2 = 0;
        int size2 = list.size();
        while (true) {
            if (index$iv$iv2 >= size2) {
                it$iv2 = null;
                break;
            }
            it$iv2 = list.get(index$iv$iv2);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv2), "Trailing")) {
                break;
            }
            index$iv$iv2++;
        }
        IntrinsicMeasurable it2 = (IntrinsicMeasurable) it$iv2;
        if (it2 != null) {
            remainingWidth2 = LayoutUtilKt.subtractConstraintSafely(remainingWidth2, it2.maxIntrinsicWidth(Integer.MAX_VALUE));
            trailingHeight = function2.invoke(it2, Integer.valueOf(width)).intValue();
        } else {
            trailingHeight = 0;
        }
        int index$iv$iv3 = 0;
        int size3 = list.size();
        while (true) {
            if (index$iv$iv3 >= size3) {
                remainingWidth = remainingWidth2;
                it$iv3 = null;
                break;
            }
            it$iv3 = list.get(index$iv$iv3);
            remainingWidth = remainingWidth2;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv3), "Label")) {
                break;
            }
            index$iv$iv3++;
            remainingWidth2 = remainingWidth;
        }
        IntrinsicMeasurable it3 = (IntrinsicMeasurable) it$iv3;
        int labelHeight = it3 != null ? function2.invoke(it3, Integer.valueOf(remainingWidth)).intValue() : 0;
        List<? extends IntrinsicMeasurable> list2 = list;
        int index$iv$iv4 = 0;
        int size4 = list2.size();
        while (index$iv$iv4 < size4) {
            Object item$iv$iv = list2.get(index$iv$iv4);
            List<? extends IntrinsicMeasurable> list3 = list2;
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) item$iv$iv), "TextField")) {
                int textFieldHeight = function2.invoke(item$iv$iv, Integer.valueOf(remainingWidth)).intValue();
                List<? extends IntrinsicMeasurable> list4 = list;
                int $i$f$fastFirstOrNull = 0;
                int index$iv$iv5 = 0;
                int size5 = list4.size();
                while (true) {
                    if (index$iv$iv5 >= size5) {
                        it$iv4 = null;
                        break;
                    }
                    it$iv4 = list4.get(index$iv$iv5);
                    List<? extends IntrinsicMeasurable> list5 = list4;
                    int $i$f$fastFirstOrNull2 = $i$f$fastFirstOrNull;
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) it$iv4), "Hint")) {
                        break;
                    }
                    index$iv$iv5++;
                    list4 = list5;
                    $i$f$fastFirstOrNull = $i$f$fastFirstOrNull2;
                }
                IntrinsicMeasurable it4 = (IntrinsicMeasurable) it$iv4;
                int placeholderHeight = it4 != null ? function2.invoke(it4, Integer.valueOf(remainingWidth)).intValue() : 0;
                return TextFieldKt.m2008calculateHeightO3s9Psw(textFieldHeight, labelHeight > 0, labelHeight, leadingHeight, trailingHeight, placeholderHeight, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null), $this$intrinsicHeight.getDensity(), this.paddingValues);
            }
            index$iv$iv4++;
            list2 = list3;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }
}
