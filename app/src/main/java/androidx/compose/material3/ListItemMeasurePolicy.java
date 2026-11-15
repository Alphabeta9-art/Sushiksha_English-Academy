package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: ListItem.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ(\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J(\u0010\u0013\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0014\u001a\u00020\u000fH\u0016J(\u0010\u0015\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J(\u0010\u0016\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0014\u001a\u00020\u000fH\u0016JV\u0010\u0017\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0014\u001a\u00020\u000f2,\u0010\u0018\u001a(\u0012\u0004\u0012\u00020\u0011\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\b\u001cH\u0002JV\u0010\u001d\u001a\u00020\u000f*\u00020\u00102\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\b0\b2\u0006\u0010\u0012\u001a\u00020\u000f2,\u0010\u0018\u001a(\u0012\u0004\u0012\u00020\u0011\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\b\u001cH\u0002¨\u0006\u001e"}, d2 = {"Landroidx/compose/material3/ListItemMeasurePolicy;", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "<init>", "()V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "minIntrinsicHeight", "minIntrinsicWidth", "calculateIntrinsicWidth", "intrinsicMeasure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "Lkotlin/ExtensionFunctionType;", "calculateIntrinsicHeight", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ListItemMeasurePolicy implements MultiContentMeasurePolicy {
    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo763measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends List<? extends Measurable>> list, long constraints) {
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        List<? extends Measurable> list4 = list.get(2);
        List<? extends Measurable> list5 = list.get(3);
        List<? extends Measurable> list6 = list.get(4);
        long looseConstraints = Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0);
        float startPadding = ListItemKt.getListItemStartPadding();
        float endPadding = ListItemKt.getListItemEndPadding();
        int horizontalPadding = $this$measure_u2d3p2s80s.mo400roundToPx0680j_4(Dp.m7905constructorimpl(startPadding + endPadding));
        Measurable measurable = (Measurable) CollectionsKt.firstOrNull((List) list5);
        int intrinsicLeadingWidth = measurable != null ? measurable.minIntrinsicWidth(Constraints.m7857getMaxHeightimpl(constraints)) : 0;
        Measurable measurable2 = (Measurable) CollectionsKt.firstOrNull((List) list6);
        int intrinsicTrailingWidth = measurable2 != null ? measurable2.minIntrinsicWidth(Constraints.m7857getMaxHeightimpl(constraints)) : 0;
        int intrinsicSupportingWidthConstraint = LayoutUtilKt.subtractConstraintSafely(Constraints.m7858getMaxWidthimpl(looseConstraints), intrinsicLeadingWidth + intrinsicTrailingWidth + horizontalPadding);
        Measurable measurable3 = (Measurable) CollectionsKt.firstOrNull((List) list4);
        int intrinsicSupportingHeight = measurable3 != null ? measurable3.minIntrinsicHeight(intrinsicSupportingWidthConstraint) : 0;
        boolean intrinsicIsSupportingMultiline = ListItemKt.isSupportingMultilineHeuristic($this$measure_u2d3p2s80s, intrinsicSupportingHeight);
        int intrinsicListItemType = ListItemType.INSTANCE.m2593invokeZLSjz4$material3(CollectionsKt.firstOrNull((List) list3) != null, CollectionsKt.firstOrNull((List) list4) != null, intrinsicIsSupportingMultiline);
        float arg0$iv = ListItemKt.m2580verticalPaddingyh95HIg(intrinsicListItemType);
        float arg0$iv2 = 2;
        int intrinsicVerticalPadding = $this$measure_u2d3p2s80s.mo400roundToPx0680j_4(Dp.m7905constructorimpl(arg0$iv2 * arg0$iv));
        long paddedLooseConstraints = ConstraintsKt.m7877offsetNN6EwU(looseConstraints, -horizontalPadding, -intrinsicVerticalPadding);
        Measurable measurable4 = (Measurable) CollectionsKt.firstOrNull((List) list5);
        Placeable placeableMo6541measureBRTryo0 = null;
        Placeable leadingPlaceable = measurable4 != null ? measurable4.mo6541measureBRTryo0(paddedLooseConstraints) : null;
        Placeable leadingPlaceable2 = leadingPlaceable;
        int currentTotalWidth = 0 + LayoutUtilKt.getWidthOrZero(leadingPlaceable);
        Measurable measurable5 = (Measurable) CollectionsKt.firstOrNull((List) list6);
        Placeable trailingPlaceable = measurable5 != null ? measurable5.mo6541measureBRTryo0(ConstraintsKt.m7878offsetNN6EwU$default(paddedLooseConstraints, -currentTotalWidth, 0, 2, null)) : null;
        int currentTotalWidth2 = currentTotalWidth + LayoutUtilKt.getWidthOrZero(trailingPlaceable);
        Measurable measurable6 = (Measurable) CollectionsKt.firstOrNull((List) list2);
        Placeable headlinePlaceable = measurable6 != null ? measurable6.mo6541measureBRTryo0(ConstraintsKt.m7878offsetNN6EwU$default(paddedLooseConstraints, -currentTotalWidth2, 0, 2, null)) : null;
        int currentTotalHeight = 0 + LayoutUtilKt.getHeightOrZero(headlinePlaceable);
        Measurable measurable7 = (Measurable) CollectionsKt.firstOrNull((List) list4);
        Placeable supportingPlaceable = measurable7 != null ? measurable7.mo6541measureBRTryo0(ConstraintsKt.m7877offsetNN6EwU(paddedLooseConstraints, -currentTotalWidth2, -currentTotalHeight)) : null;
        int currentTotalHeight2 = currentTotalHeight + LayoutUtilKt.getHeightOrZero(supportingPlaceable);
        boolean isSupportingMultiline = (supportingPlaceable == null || supportingPlaceable.get(AlignmentLineKt.getFirstBaseline()) == supportingPlaceable.get(AlignmentLineKt.getLastBaseline())) ? false : true;
        Measurable measurable8 = (Measurable) CollectionsKt.firstOrNull((List) list3);
        if (measurable8 != null) {
            placeableMo6541measureBRTryo0 = measurable8.mo6541measureBRTryo0(ConstraintsKt.m7877offsetNN6EwU(
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x01b5: INVOKE (r27v3 'placeableMo6541measureBRTryo0' androidx.compose.ui.layout.Placeable) = 
                  (r6v7 'measurable8' androidx.compose.ui.layout.Measurable)
                  (wrap long:0x01b1: INVOKE 
                  (r21v3 ?? I:??[long, double])
                  (wrap int:0x01ab: NEG (r1v13 'currentTotalWidth2' int A[D('currentTotalWidth' int)]) A[WRAPPED])
                  (wrap int:0x01ae: NEG (r2v13 'currentTotalHeight2' int A[D('currentTotalHeight' int)]) A[WRAPPED])
                 STATIC call: androidx.compose.ui.unit.ConstraintsKt.offset-NN6Ew-U(long, int, int):long A[MD:(long, int, int):long (m), WRAPPED])
                 INTERFACE call: androidx.compose.ui.layout.Measurable.measure-BRTryo0(long):androidx.compose.ui.layout.Placeable A[MD:(long):androidx.compose.ui.layout.Placeable (m)] (LINE:280) in method: androidx.compose.material3.ListItemMeasurePolicy.measure-3p2s80s(androidx.compose.ui.layout.MeasureScope, java.util.List<? extends java.util.List<? extends androidx.compose.ui.layout.Measurable>>, long):androidx.compose.ui.layout.MeasureResult, file: classes10.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r21v3 ??
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                */
            /*
                Method dump skipped, instruction units count: 631
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ListItemMeasurePolicy.mo763measure3p2s80s(androidx.compose.ui.layout.MeasureScope, java.util.List, long):androidx.compose.ui.layout.MeasureResult");
        }

        /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$maxIntrinsicHeight$1, reason: invalid class name */
        /* JADX INFO: compiled from: ListItem.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            AnonymousClass1() {
                super(2, IntrinsicMeasurable.class, "maxIntrinsicHeight", "maxIntrinsicHeight(I)I", 0);
            }

            public final Integer invoke(IntrinsicMeasurable p0, int p1) {
                return Integer.valueOf(p0.maxIntrinsicHeight(p1));
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }
        }

        @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
        public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width) {
            return calculateIntrinsicHeight($this$maxIntrinsicHeight, list, width, AnonymousClass1.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$maxIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
        /* JADX INFO: compiled from: ListItem.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* synthetic */ class C03171 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
            public static final C03171 INSTANCE = new C03171();

            C03171() {
                super(2, IntrinsicMeasurable.class, "maxIntrinsicWidth", "maxIntrinsicWidth(I)I", 0);
            }

            public final Integer invoke(IntrinsicMeasurable p0, int p1) {
                return Integer.valueOf(p0.maxIntrinsicWidth(p1));
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }
        }

        @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
        public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height) {
            return calculateIntrinsicWidth($this$maxIntrinsicWidth, list, height, C03171.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$minIntrinsicHeight$1, reason: invalid class name and case insensitive filesystem */
        /* JADX INFO: compiled from: ListItem.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* synthetic */ class C03181 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
            public static final C03181 INSTANCE = new C03181();

            C03181() {
                super(2, IntrinsicMeasurable.class, "minIntrinsicHeight", "minIntrinsicHeight(I)I", 0);
            }

            public final Integer invoke(IntrinsicMeasurable p0, int p1) {
                return Integer.valueOf(p0.minIntrinsicHeight(p1));
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }
        }

        @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
        public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width) {
            return calculateIntrinsicHeight($this$minIntrinsicHeight, list, width, C03181.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.material3.ListItemMeasurePolicy$minIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
        /* JADX INFO: compiled from: ListItem.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* synthetic */ class C03191 extends FunctionReferenceImpl implements Function2<IntrinsicMeasurable, Integer, Integer> {
            public static final C03191 INSTANCE = new C03191();

            C03191() {
                super(2, IntrinsicMeasurable.class, "minIntrinsicWidth", "minIntrinsicWidth(I)I", 0);
            }

            public final Integer invoke(IntrinsicMeasurable p0, int p1) {
                return Integer.valueOf(p0.minIntrinsicWidth(p1));
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num) {
                return invoke(intrinsicMeasurable, num.intValue());
            }
        }

        @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
        public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height) {
            return calculateIntrinsicWidth($this$minIntrinsicWidth, list, height, C03191.INSTANCE);
        }

        private final int calculateIntrinsicWidth(IntrinsicMeasureScope $this$calculateIntrinsicWidth, List<? extends List<? extends IntrinsicMeasurable>> list, int height, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
            List<? extends IntrinsicMeasurable> list2 = list.get(0);
            List<? extends IntrinsicMeasurable> list3 = list.get(1);
            List<? extends IntrinsicMeasurable> list4 = list.get(2);
            List<? extends IntrinsicMeasurable> list5 = list.get(3);
            List<? extends IntrinsicMeasurable> list6 = list.get(4);
            IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list5);
            int iIntValue = intrinsicMeasurable != null ? function2.invoke(intrinsicMeasurable, Integer.valueOf(height)).intValue() : 0;
            IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list6);
            int iIntValue2 = intrinsicMeasurable2 != null ? function2.invoke(intrinsicMeasurable2, Integer.valueOf(height)).intValue() : 0;
            IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list2);
            int iIntValue3 = intrinsicMeasurable3 != null ? function2.invoke(intrinsicMeasurable3, Integer.valueOf(height)).intValue() : 0;
            IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list3);
            int iIntValue4 = intrinsicMeasurable4 != null ? function2.invoke(intrinsicMeasurable4, Integer.valueOf(height)).intValue() : 0;
            IntrinsicMeasurable intrinsicMeasurable5 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list4);
            int iIntValue5 = intrinsicMeasurable5 != null ? function2.invoke(intrinsicMeasurable5, Integer.valueOf(height)).intValue() : 0;
            float arg0$iv = ListItemKt.getListItemStartPadding();
            float other$iv = ListItemKt.getListItemEndPadding();
            return ListItemKt.m2579calculateWidthyeHjK3Y($this$calculateIntrinsicWidth, iIntValue, iIntValue2, iIntValue3, iIntValue4, iIntValue5, $this$calculateIntrinsicWidth.mo400roundToPx0680j_4(Dp.m7905constructorimpl(arg0$iv + other$iv)), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
        }

        private final int calculateIntrinsicHeight(IntrinsicMeasureScope $this$calculateIntrinsicHeight, List<? extends List<? extends IntrinsicMeasurable>> list, int width, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2) {
            int leadingHeight;
            int trailingHeight;
            int remainingWidth;
            List<? extends IntrinsicMeasurable> list2 = list.get(0);
            List<? extends IntrinsicMeasurable> list3 = list.get(1);
            List<? extends IntrinsicMeasurable> list4 = list.get(2);
            List<? extends IntrinsicMeasurable> list5 = list.get(3);
            List<? extends IntrinsicMeasurable> list6 = list.get(4);
            float arg0$iv = ListItemKt.getListItemStartPadding();
            float other$iv = ListItemKt.getListItemEndPadding();
            int remainingWidth2 = LayoutUtilKt.subtractConstraintSafely(width, $this$calculateIntrinsicHeight.mo400roundToPx0680j_4(Dp.m7905constructorimpl(arg0$iv + other$iv)));
            IntrinsicMeasurable it = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list5);
            if (it != null) {
                leadingHeight = function2.invoke(it, Integer.valueOf(remainingWidth2)).intValue();
                remainingWidth2 = LayoutUtilKt.subtractConstraintSafely(remainingWidth2, it.maxIntrinsicWidth(Integer.MAX_VALUE));
            } else {
                leadingHeight = 0;
            }
            IntrinsicMeasurable it2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list6);
            if (it2 != null) {
                trailingHeight = function2.invoke(it2, Integer.valueOf(remainingWidth2)).intValue();
                remainingWidth = LayoutUtilKt.subtractConstraintSafely(remainingWidth2, it2.maxIntrinsicWidth(Integer.MAX_VALUE));
            } else {
                trailingHeight = 0;
                remainingWidth = remainingWidth2;
            }
            IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list3);
            int overlineHeight = intrinsicMeasurable != null ? function2.invoke(intrinsicMeasurable, Integer.valueOf(remainingWidth)).intValue() : 0;
            IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list2);
            int headlineHeight = intrinsicMeasurable2 != null ? function2.invoke(intrinsicMeasurable2, Integer.valueOf(remainingWidth)).intValue() : 0;
            IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) CollectionsKt.firstOrNull((List) list4);
            int supportingHeight = intrinsicMeasurable3 != null ? function2.invoke(intrinsicMeasurable3, Integer.valueOf(remainingWidth)).intValue() : 0;
            boolean isSupportingMultiline = ListItemKt.isSupportingMultilineHeuristic($this$calculateIntrinsicHeight, supportingHeight);
            int headlineHeight2 = headlineHeight;
            int listItemType = ListItemType.INSTANCE.m2593invokeZLSjz4$material3(overlineHeight > 0, supportingHeight > 0, isSupportingMultiline);
            float arg0$iv2 = ListItemKt.m2580verticalPaddingyh95HIg(listItemType);
            int leadingHeight2 = $this$calculateIntrinsicHeight.mo400roundToPx0680j_4(Dp.m7905constructorimpl(2 * arg0$iv2));
            return ListItemKt.m2578calculateHeightN4Jib3Y($this$calculateIntrinsicHeight, leadingHeight, trailingHeight, headlineHeight2, overlineHeight, supportingHeight, listItemType, leadingHeight2, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null));
        }
    }
