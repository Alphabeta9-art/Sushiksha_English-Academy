package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSetKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.ArchiveParams;
import com.cloudinary.metadata.MetadataValidation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0003KLMB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0082\u0001\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\t\u001a\u00020\n2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*J\u0010\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u0003H\u0002J\u0006\u0010-\u001a\u00020\u0019J\b\u0010.\u001a\u00020\u0019H\u0002J1\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u00028\u00002\u0006\u00101\u001a\u00020\f2\u0012\b\u0002\u00102\u001a\f0\bR\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002¢\u0006\u0002\u00103J\u001f\u00104\u001a\u00020\u00192\u0006\u00100\u001a\u00028\u00002\b\b\u0002\u00105\u001a\u00020!H\u0002¢\u0006\u0002\u00106J\u0018\u00107\u001a\u0004\u0018\u00010\u00152\u0006\u0010,\u001a\u00020\u00032\u0006\u00108\u001a\u00020\fJ\u0019\u00109\u001a\u00020\f*\u00020:2\u0006\u00100\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010;R$\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\u0003\u0012\u000e\u0012\f0\bR\b\u0012\u0004\u0012\u00028\u00000\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010<\u001a\u00020=8F¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0011\u0010@\u001a\u00020A¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0018\u0010D\u001a\u00020!*\u00028\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0018\u00101\u001a\u00020\f*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0018\u0010I\u001a\u00020\f*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010H¨\u0006N"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;", "", "<init>", "()V", "keyToItemInfoMap", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;", "keyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "firstVisibleIndex", "", "movingAwayKeys", "Landroidx/collection/MutableScatterSet;", "movingInFromStartBound", "", "movingInFromEndBound", "movingAwayToStartBound", "movingAwayToEndBound", "disappearingItems", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "displayingNode", "Landroidx/compose/ui/node/DrawModifierNode;", "onMeasured", "", "consumedScroll", "layoutWidth", "layoutHeight", "positionedItems", "itemProvider", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItemProvider;", "isVertical", "", "isLookingAhead", "laneCount", "hasLookaheadOccurred", "layoutMinOffset", "layoutMaxOffset", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "removeInfoForKey", "key", "reset", "releaseAnimations", "initializeAnimation", "item", "mainAxisOffset", "itemInfo", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;ILandroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;)V", "startPlacementAnimationsIfNeeded", "isMovingAway", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;Z)V", "getAnimation", "placeableIndex", "updateAndReturnOffsetFor", "", "([ILandroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)I", "minSizeToFitDisappearingItems", "Landroidx/compose/ui/unit/IntSize;", "getMinSizeToFitDisappearingItems-YbymL2g", "()J", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "hasAnimations", "getHasAnimations", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)Z", "getMainAxisOffset", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)I", "crossAxisOffset", "getCrossAxisOffset", "ItemInfo", "DisplayingDisappearingItemsElement", "DisplayingDisappearingItemsNode", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LazyLayoutItemAnimator<T extends LazyLayoutMeasuredItem> {
    public static final int $stable = 8;
    private DrawModifierNode displayingNode;
    private int firstVisibleIndex;
    private LazyLayoutKeyIndexMap keyIndexMap;
    private final MutableScatterMap<Object, LazyLayoutItemAnimator<T>.ItemInfo> keyToItemInfoMap = ScatterMapKt.mutableScatterMapOf();
    private final MutableScatterSet<Object> movingAwayKeys = ScatterSetKt.mutableScatterSetOf();
    private final List<T> movingInFromStartBound = new ArrayList();
    private final List<T> movingInFromEndBound = new ArrayList();
    private final List<T> movingAwayToStartBound = new ArrayList();
    private final List<T> movingAwayToEndBound = new ArrayList();
    private final List<LazyLayoutItemAnimation> disappearingItems = new ArrayList();
    private final Modifier modifier = new DisplayingDisappearingItemsElement(this);

    /* JADX WARN: Code duplicated, block: B:202:0x05ca A[LOOP:12: B:192:0x05aa->B:202:0x05ca, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:213:0x05e8  */
    /* JADX WARN: Code duplicated, block: B:215:0x0605  */
    /* JADX WARN: Code duplicated, block: B:216:0x060c  */
    /* JADX WARN: Code duplicated, block: B:300:0x05c8 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasured(int consumedScroll, int layoutWidth, int layoutHeight, List<T> positionedItems, final LazyLayoutKeyIndexMap keyIndexMap, LazyLayoutMeasuredItemProvider<T> itemProvider, boolean isVertical, boolean isLookingAhead, int laneCount, boolean hasLookaheadOccurred, int layoutMinOffset, int layoutMaxOffset, CoroutineScope coroutineScope, GraphicsContext graphicsContext) {
        boolean z;
        long jM8027constructorimpl;
        int[] iArr;
        int i;
        LazyLayoutItemAnimator<T>.ItemInfo itemInfo;
        int layoutMinOffset2;
        boolean z2;
        int i2;
        int i3;
        boolean z3;
        boolean z4;
        boolean zAdd;
        int i4;
        int i5;
        boolean z5;
        List<T> list;
        int i6;
        int i7;
        int i8;
        int i9;
        LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr;
        int i10;
        LazyLayoutItemAnimation lazyLayoutItemAnimation;
        int i11;
        int i12;
        int i13;
        int i14;
        long[] jArr;
        int i15 = laneCount;
        final LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap = this.keyIndexMap;
        this.keyIndexMap = keyIndexMap;
        int i16 = 0;
        int size = positionedItems.size();
        while (true) {
            if (i16 < size) {
                if (getHasAnimations(positionedItems.get(i16))) {
                    z = true;
                    break;
                }
                i16++;
            } else {
                z = false;
                break;
            }
        }
        boolean z6 = z;
        if (!z6 && this.keyToItemInfoMap.isEmpty()) {
            releaseAnimations();
            return;
        }
        int i17 = this.firstVisibleIndex;
        LazyLayoutMeasuredItem lazyLayoutMeasuredItem = (LazyLayoutMeasuredItem) CollectionsKt.firstOrNull((List) positionedItems);
        this.firstVisibleIndex = lazyLayoutMeasuredItem != null ? lazyLayoutMeasuredItem.getIndex() : 0;
        if (isVertical) {
            jM8027constructorimpl = IntOffset.m8027constructorimpl((((long) 0) << 32) | (((long) consumedScroll) & 4294967295L));
        } else {
            jM8027constructorimpl = IntOffset.m8027constructorimpl((((long) consumedScroll) << 32) | (((long) 0) & 4294967295L));
        }
        long j = jM8027constructorimpl;
        boolean z7 = isLookingAhead || !hasLookaheadOccurred;
        MutableScatterMap<Object, LazyLayoutItemAnimator<T>.ItemInfo> mutableScatterMap = this.keyToItemInfoMap;
        Object[] objArr = mutableScatterMap.keys;
        int i18 = 0;
        long[] jArr2 = mutableScatterMap.metadata;
        int length = jArr2.length - 2;
        int i19 = 0;
        if (0 <= length) {
            while (true) {
                long j2 = jArr2[i19];
                int i20 = i18;
                if ((j2 & ((~j2) << 7) & (-9187201950435737472L)) == -9187201950435737472L) {
                    jArr = jArr2;
                } else {
                    int i21 = 8 - ((~(i19 - length)) >>> 31);
                    int i22 = 0;
                    while (i22 < i21) {
                        if ((j2 & 255) < 128) {
                            this.movingAwayKeys.add(objArr[(i19 << 3) + i22]);
                        }
                        j2 >>= 8;
                        i22++;
                        jArr2 = jArr2;
                    }
                    jArr = jArr2;
                    if (i21 != 8) {
                        break;
                    }
                }
                if (i19 == length) {
                    break;
                }
                i19++;
                jArr2 = jArr;
                i18 = i20;
            }
        }
        List<T> list2 = positionedItems;
        int i23 = 0;
        int i24 = 0;
        int size2 = list2.size();
        while (i24 < size2) {
            T t = list2.get(i24);
            this.movingAwayKeys.remove(t.getKey());
            if (getHasAnimations(t)) {
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo2 = this.keyToItemInfoMap.get(t.getKey());
                int index = lazyLayoutKeyIndexMap != null ? lazyLayoutKeyIndexMap.getIndex(t.getKey()) : -1;
                boolean z8 = index == -1 && lazyLayoutKeyIndexMap != null;
                if (itemInfo2 == null) {
                    boolean z9 = z8;
                    LazyLayoutItemAnimator<T>.ItemInfo itemInfo3 = new ItemInfo();
                    ItemInfo.updateAnimation$default(itemInfo3, t, coroutineScope, graphicsContext, layoutMinOffset, layoutMaxOffset, 0, 32, null);
                    list = list2;
                    i6 = i23;
                    i7 = i24;
                    this.keyToItemInfoMap.set(t.getKey(), itemInfo3);
                    if (t.getIndex() == index || index == -1) {
                        int i25 = i17;
                        long jMo932getOffsetBjo55l4 = t.mo932getOffsetBjo55l4(0);
                        initializeAnimation(t, t.getIsVertical() ? IntOffset.m8034getYimpl(jMo932getOffsetBjo55l4) : IntOffset.m8033getXimpl(jMo932getOffsetBjo55l4), itemInfo3);
                        if (z9) {
                            for (LazyLayoutItemAnimation lazyLayoutItemAnimation2 : itemInfo3.getAnimations()) {
                                if (lazyLayoutItemAnimation2 != null) {
                                    lazyLayoutItemAnimation2.animateAppearance();
                                    Unit unit = Unit.INSTANCE;
                                }
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                        i9 = i25;
                        i8 = size2;
                    } else {
                        int i26 = i17;
                        Boolean.valueOf(index < i26 ? this.movingInFromStartBound.add(t) : this.movingInFromEndBound.add(t));
                        i9 = i26;
                        i8 = size2;
                    }
                } else {
                    boolean z10 = z8;
                    list = list2;
                    i6 = i23;
                    i7 = i24;
                    int i27 = i17;
                    if (z7) {
                        ItemInfo.updateAnimation$default(itemInfo2, t, coroutineScope, graphicsContext, layoutMinOffset, layoutMaxOffset, 0, 32, null);
                        LazyLayoutItemAnimation[] animations = itemInfo2.getAnimations();
                        int i28 = 0;
                        int length2 = animations.length;
                        int i29 = 0;
                        while (i29 < length2) {
                            LazyLayoutItemAnimation lazyLayoutItemAnimation3 = animations[i29];
                            if (lazyLayoutItemAnimation3 == null) {
                                i11 = i29;
                                i12 = i27;
                                i13 = i28;
                                i14 = size2;
                            } else {
                                i11 = i29;
                                i12 = i27;
                                i13 = i28;
                                i14 = size2;
                                if (!IntOffset.m8032equalsimpl0(lazyLayoutItemAnimation3.getRawOffset(), LazyLayoutItemAnimation.INSTANCE.m987getNotInitializednOccac())) {
                                    lazyLayoutItemAnimation3.m986setRawOffsetgyyYBs(IntOffset.m8037plusqkQi6aY(lazyLayoutItemAnimation3.getRawOffset(), j));
                                }
                            }
                            i29 = i11 + 1;
                            i27 = i12;
                            size2 = i14;
                            i28 = i13;
                        }
                        i9 = i27;
                        i8 = size2;
                        if (z10) {
                            LazyLayoutItemAnimation[] animations2 = itemInfo2.getAnimations();
                            int i30 = 0;
                            int length3 = animations2.length;
                            int i31 = 0;
                            while (i31 < length3) {
                                LazyLayoutItemAnimation lazyLayoutItemAnimation4 = animations2[i31];
                                if (lazyLayoutItemAnimation4 == null) {
                                    lazyLayoutItemAnimationArr = animations2;
                                    i10 = i30;
                                } else {
                                    if (!lazyLayoutItemAnimation4.isDisappearanceAnimationInProgress()) {
                                        lazyLayoutItemAnimationArr = animations2;
                                        i10 = i30;
                                        lazyLayoutItemAnimation = lazyLayoutItemAnimation4;
                                    } else {
                                        lazyLayoutItemAnimationArr = animations2;
                                        i10 = i30;
                                        lazyLayoutItemAnimation = lazyLayoutItemAnimation4;
                                        this.disappearingItems.remove(lazyLayoutItemAnimation);
                                        DrawModifierNode drawModifierNode = this.displayingNode;
                                        if (drawModifierNode != null) {
                                            DrawModifierNodeKt.invalidateDraw(drawModifierNode);
                                            Unit unit3 = Unit.INSTANCE;
                                        }
                                    }
                                    lazyLayoutItemAnimation.animateAppearance();
                                }
                                i31++;
                                animations2 = lazyLayoutItemAnimationArr;
                                i30 = i10;
                            }
                        }
                        startPlacementAnimationsIfNeeded$default(this, t, false, 2, null);
                    } else {
                        i9 = i27;
                        i8 = size2;
                    }
                    Unit unit4 = Unit.INSTANCE;
                }
            } else {
                list = list2;
                i6 = i23;
                i7 = i24;
                i8 = size2;
                i9 = i17;
                removeInfoForKey(t.getKey());
                Unit unit5 = Unit.INSTANCE;
            }
            i24 = i7 + 1;
            i17 = i9;
            size2 = i8;
            list2 = list;
            i23 = i6;
        }
        int i32 = 2;
        int[] iArr2 = new int[i15];
        if (!z7 || lazyLayoutKeyIndexMap == null) {
            iArr = iArr2;
            i = 2;
        } else {
            if (this.movingInFromStartBound.isEmpty()) {
                iArr = iArr2;
                i = 2;
            } else {
                List<T> list3 = this.movingInFromStartBound;
                if (list3.size() > 1) {
                    CollectionsKt.sortWith(list3, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortByDescending$1
                        @Override // java.util.Comparator
                        public final int compare(T t2, T t3) {
                            LazyLayoutMeasuredItem it = (LazyLayoutMeasuredItem) t3;
                            LazyLayoutMeasuredItem it2 = (LazyLayoutMeasuredItem) t2;
                            return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(it.getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(it2.getKey())));
                        }
                    });
                }
                List<T> list4 = this.movingInFromStartBound;
                int i33 = 0;
                int size3 = list4.size();
                while (i33 < size3) {
                    int i34 = i33;
                    T t2 = list4.get(i33);
                    int i35 = i32;
                    initializeAnimation$default(this, t2, layoutMinOffset - updateAndReturnOffsetFor(iArr2, t2), null, 4, null);
                    startPlacementAnimationsIfNeeded$default(this, t2, false, i35, null);
                    i33 = i34 + 1;
                    i32 = i35;
                    size3 = size3;
                    iArr2 = iArr2;
                }
                int[] iArr3 = iArr2;
                i = i32;
                ArraysKt.fill$default(iArr3, 0, 0, 0, 6, (Object) null);
                iArr = iArr3;
            }
            if (!this.movingInFromEndBound.isEmpty()) {
                List<T> list5 = this.movingInFromEndBound;
                if (list5.size() > 1) {
                    CollectionsKt.sortWith(list5, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t3, T t4) {
                            LazyLayoutMeasuredItem it = (LazyLayoutMeasuredItem) t3;
                            LazyLayoutMeasuredItem it2 = (LazyLayoutMeasuredItem) t4;
                            return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(it.getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(it2.getKey())));
                        }
                    });
                }
                List<T> list6 = this.movingInFromEndBound;
                int i36 = 0;
                for (int size4 = list6.size(); i36 < size4; size4 = size4) {
                    List<T> list7 = list6;
                    T t3 = list6.get(i36);
                    initializeAnimation$default(this, t3, (layoutMaxOffset + updateAndReturnOffsetFor(iArr, t3)) - t3.getMainAxisSizeWithSpacings(), null, 4, null);
                    startPlacementAnimationsIfNeeded$default(this, t3, false, i, null);
                    i36++;
                    list6 = list7;
                }
                ArraysKt.fill$default(iArr, 0, 0, 0, 6, (Object) null);
            }
        }
        MutableScatterSet<Object> mutableScatterSet = this.movingAwayKeys;
        Object[] objArr2 = mutableScatterSet.elements;
        MutableScatterSet<Object> mutableScatterSet2 = mutableScatterSet;
        int i37 = 0;
        long[] jArr3 = mutableScatterSet2.metadata;
        int length4 = jArr3.length - 2;
        int i38 = 0;
        if (0 <= length4) {
            while (true) {
                long j3 = jArr3[i38];
                Object[] objArr3 = objArr2;
                MutableScatterSet<Object> mutableScatterSet3 = mutableScatterSet2;
                int i39 = i37;
                long[] jArr4 = jArr3;
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) == -9187201950435737472L) {
                    z2 = z6;
                } else {
                    int i40 = 8;
                    int i41 = 8 - ((~(i38 - length4)) >>> 31);
                    int i42 = 0;
                    while (i42 < i41) {
                        if (((j3 & 255) < 128 ? 1 : 0) != 0) {
                            i2 = i40;
                            Object obj = objArr3[(i38 << 3) + i42];
                            i3 = i42;
                            LazyLayoutItemAnimator<T>.ItemInfo itemInfo4 = this.keyToItemInfoMap.get(obj);
                            if (itemInfo4 == 0) {
                                z3 = z6;
                            } else {
                                int index2 = keyIndexMap.getIndex(obj);
                                z3 = z6;
                                itemInfo4.setSpan(Math.min(i15, itemInfo4.getSpan()));
                                itemInfo4.setLane(Math.min(i15 - itemInfo4.getSpan(), itemInfo4.getLane()));
                                if (index2 == -1) {
                                    LazyLayoutItemAnimation[] animations3 = itemInfo4.getAnimations();
                                    int length5 = animations3.length;
                                    int i43 = 0;
                                    int i44 = 0;
                                    int i45 = 0;
                                    while (i45 < length5) {
                                        LazyLayoutItemAnimation lazyLayoutItemAnimation5 = animations3[i45];
                                        int i46 = i43 + 1;
                                        if (lazyLayoutItemAnimation5 != null) {
                                            if (lazyLayoutItemAnimation5.isDisappearanceAnimationInProgress()) {
                                                i44 = 1;
                                                Unit unit6 = Unit.INSTANCE;
                                            } else if (lazyLayoutItemAnimation5.isDisappearanceAnimationFinished()) {
                                                lazyLayoutItemAnimation5.release();
                                                itemInfo4.getAnimations()[i43] = null;
                                                this.disappearingItems.remove(lazyLayoutItemAnimation5);
                                                DrawModifierNode drawModifierNode2 = this.displayingNode;
                                                if (drawModifierNode2 != null) {
                                                    DrawModifierNodeKt.invalidateDraw(drawModifierNode2);
                                                    Unit unit7 = Unit.INSTANCE;
                                                }
                                            } else {
                                                if (lazyLayoutItemAnimation5.getLayer() != null) {
                                                    lazyLayoutItemAnimation5.animateDisappearance();
                                                }
                                                if (lazyLayoutItemAnimation5.isDisappearanceAnimationInProgress()) {
                                                    this.disappearingItems.add(lazyLayoutItemAnimation5);
                                                    DrawModifierNode drawModifierNode3 = this.displayingNode;
                                                    if (drawModifierNode3 != null) {
                                                        DrawModifierNodeKt.invalidateDraw(drawModifierNode3);
                                                        Unit unit8 = Unit.INSTANCE;
                                                    }
                                                    i44 = 1;
                                                } else {
                                                    lazyLayoutItemAnimation5.release();
                                                    itemInfo4.getAnimations()[i43] = null;
                                                }
                                                Unit unit9 = Unit.INSTANCE;
                                            }
                                        }
                                        i45++;
                                        i43 = i46;
                                        length5 = length5;
                                        index2 = index2;
                                    }
                                    if (i44 == 0) {
                                        removeInfoForKey(obj);
                                    }
                                    Unit unit10 = Unit.INSTANCE;
                                } else {
                                    Constraints constraints = itemInfo4.getConstraints();
                                    Intrinsics.checkNotNull(constraints);
                                    LazyLayoutMeasuredItem lazyLayoutMeasuredItemMo934getAndMeasurehBUhpc = itemProvider.mo934getAndMeasurehBUhpc(index2, itemInfo4.getLane(), itemInfo4.getSpan(), constraints.getValue());
                                    lazyLayoutMeasuredItemMo934getAndMeasurehBUhpc.setNonScrollableItem(true);
                                    LazyLayoutItemAnimation[] animations4 = itemInfo4.getAnimations();
                                    int length6 = animations4.length;
                                    int i47 = 0;
                                    while (true) {
                                        if (i47 < length6) {
                                            LazyLayoutItemAnimation lazyLayoutItemAnimation6 = animations4[i47];
                                            if (lazyLayoutItemAnimation6 != null) {
                                                i4 = length6;
                                                i5 = i47;
                                                z5 = lazyLayoutItemAnimation6.isPlacementAnimationInProgress();
                                                if (z5) {
                                                    z4 = true;
                                                    break;
                                                } else {
                                                    i47 = i5 + 1;
                                                    length6 = i4;
                                                }
                                            } else {
                                                i4 = length6;
                                                i5 = i47;
                                            }
                                            if (z5) {
                                                z4 = true;
                                                break;
                                            } else {
                                                i47 = i5 + 1;
                                                length6 = i4;
                                            }
                                        } else {
                                            z4 = false;
                                            break;
                                        }
                                    }
                                    if (!z4) {
                                        if (lazyLayoutKeyIndexMap != null && index2 == lazyLayoutKeyIndexMap.getIndex(obj)) {
                                            removeInfoForKey(obj);
                                            Unit unit11 = Unit.INSTANCE;
                                        } else {
                                            itemInfo4.updateAnimation(lazyLayoutMeasuredItemMo934getAndMeasurehBUhpc, coroutineScope, graphicsContext, layoutMinOffset, layoutMaxOffset, itemInfo4.getCrossAxisOffset());
                                            if (index2 < this.firstVisibleIndex) {
                                                zAdd = this.movingAwayToStartBound.add((T) lazyLayoutMeasuredItemMo934getAndMeasurehBUhpc);
                                            } else {
                                                zAdd = this.movingAwayToEndBound.add((T) lazyLayoutMeasuredItemMo934getAndMeasurehBUhpc);
                                            }
                                            Boolean.valueOf(zAdd);
                                        }
                                    } else {
                                        itemInfo4.updateAnimation(lazyLayoutMeasuredItemMo934getAndMeasurehBUhpc, coroutineScope, graphicsContext, layoutMinOffset, layoutMaxOffset, itemInfo4.getCrossAxisOffset());
                                        if (index2 < this.firstVisibleIndex) {
                                            zAdd = this.movingAwayToStartBound.add((T) lazyLayoutMeasuredItemMo934getAndMeasurehBUhpc);
                                        } else {
                                            zAdd = this.movingAwayToEndBound.add((T) lazyLayoutMeasuredItemMo934getAndMeasurehBUhpc);
                                        }
                                        Boolean.valueOf(zAdd);
                                    }
                                }
                            }
                        } else {
                            i2 = i40;
                            i3 = i42;
                            z3 = z6;
                        }
                        j3 >>= i2;
                        i42 = i3 + 1;
                        i15 = laneCount;
                        i40 = i2;
                        z6 = z3;
                    }
                    z2 = z6;
                    if (i41 != i40) {
                        break;
                    }
                }
                if (i38 == length4) {
                    break;
                }
                i38++;
                i15 = laneCount;
                i37 = i39;
                jArr3 = jArr4;
                objArr2 = objArr3;
                mutableScatterSet2 = mutableScatterSet3;
                z6 = z2;
            }
        }
        if (!this.movingAwayToStartBound.isEmpty()) {
            List<T> list8 = this.movingAwayToStartBound;
            if (list8.size() > 1) {
                CollectionsKt.sortWith(list8, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortByDescending$2
                    @Override // java.util.Comparator
                    public final int compare(T t4, T t5) {
                        LazyLayoutMeasuredItem it = (LazyLayoutMeasuredItem) t5;
                        LazyLayoutMeasuredItem it2 = (LazyLayoutMeasuredItem) t4;
                        return ComparisonsKt.compareValues(Integer.valueOf(keyIndexMap.getIndex(it.getKey())), Integer.valueOf(keyIndexMap.getIndex(it2.getKey())));
                    }
                });
            }
            List<T> list9 = this.movingAwayToStartBound;
            int i48 = 0;
            int i49 = 0;
            int size5 = list9.size();
            while (i49 < size5) {
                T t4 = list9.get(i49);
                List<T> list10 = list9;
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo5 = this.keyToItemInfoMap.get(t4.getKey());
                Intrinsics.checkNotNull(itemInfo5);
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo6 = itemInfo5;
                int iUpdateAndReturnOffsetFor = updateAndReturnOffsetFor(iArr, t4);
                if (isLookingAhead) {
                    itemInfo = itemInfo6;
                    layoutMinOffset2 = getMainAxisOffset((LazyLayoutMeasuredItem) CollectionsKt.first((List) positionedItems));
                } else {
                    itemInfo = itemInfo6;
                    layoutMinOffset2 = itemInfo.getLayoutMinOffset();
                }
                int i50 = i48;
                int i51 = i49;
                int i52 = size5;
                t4.position(layoutMinOffset2 - iUpdateAndReturnOffsetFor, itemInfo.getCrossAxisOffset(), layoutWidth, layoutHeight);
                if (z7) {
                    startPlacementAnimationsIfNeeded(t4, true);
                }
                i49 = i51 + 1;
                list9 = list10;
                i48 = i50;
                size5 = i52;
            }
            ArraysKt.fill$default(iArr, 0, 0, 0, 6, (Object) null);
        }
        if (!this.movingAwayToEndBound.isEmpty()) {
            List<T> list11 = this.movingAwayToEndBound;
            if (list11.size() > 1) {
                CollectionsKt.sortWith(list11, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortBy$2
                    @Override // java.util.Comparator
                    public final int compare(T t5, T t6) {
                        LazyLayoutMeasuredItem it = (LazyLayoutMeasuredItem) t5;
                        LazyLayoutMeasuredItem it2 = (LazyLayoutMeasuredItem) t6;
                        return ComparisonsKt.compareValues(Integer.valueOf(keyIndexMap.getIndex(it.getKey())), Integer.valueOf(keyIndexMap.getIndex(it2.getKey())));
                    }
                });
            }
            List<T> list12 = this.movingAwayToEndBound;
            int i53 = 0;
            int i54 = 0;
            int size6 = list12.size();
            while (i54 < size6) {
                T t5 = list12.get(i54);
                List<T> list13 = list12;
                int i55 = i53;
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo7 = this.keyToItemInfoMap.get(t5.getKey());
                Intrinsics.checkNotNull(itemInfo7);
                LazyLayoutItemAnimator<T>.ItemInfo itemInfo8 = itemInfo7;
                t5.position((itemInfo8.getLayoutMaxOffset() - t5.getMainAxisSizeWithSpacings()) + updateAndReturnOffsetFor(iArr, t5), itemInfo8.getCrossAxisOffset(), layoutWidth, layoutHeight);
                if (z7) {
                    startPlacementAnimationsIfNeeded(t5, true);
                }
                i54++;
                list12 = list13;
                i53 = i55;
            }
        }
        List<T> list14 = this.movingAwayToStartBound;
        CollectionsKt.reverse(list14);
        Unit unit12 = Unit.INSTANCE;
        positionedItems.addAll(0, list14);
        positionedItems.addAll(this.movingAwayToEndBound);
        this.movingInFromStartBound.clear();
        this.movingInFromEndBound.clear();
        this.movingAwayToStartBound.clear();
        this.movingAwayToEndBound.clear();
        this.movingAwayKeys.clear();
    }

    private final void removeInfoForKey(Object key) {
        LazyLayoutItemAnimation[] animations;
        LazyLayoutItemAnimator<T>.ItemInfo itemInfoRemove = this.keyToItemInfoMap.remove(key);
        if (itemInfoRemove == null || (animations = itemInfoRemove.getAnimations()) == null) {
            return;
        }
        for (LazyLayoutItemAnimation lazyLayoutItemAnimation : animations) {
            if (lazyLayoutItemAnimation != null) {
                lazyLayoutItemAnimation.release();
            }
        }
    }

    public final void reset() {
        releaseAnimations();
        this.keyIndexMap = null;
        this.firstVisibleIndex = -1;
    }

    private final void releaseAnimations() {
        ScatterMap this_$iv;
        int $i$f$forEachValue;
        if (this.keyToItemInfoMap.isNotEmpty()) {
            ScatterMap this_$iv2 = this.keyToItemInfoMap;
            int $i$f$forEachValue2 = 0;
            Object[] v$iv = this_$iv2.values;
            long[] m$iv$iv = this_$iv2.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                        this_$iv = this_$iv2;
                        $i$f$forEachValue = $i$f$forEachValue2;
                    } else {
                        int i = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv = 0;
                        while (j$iv$iv < bitCount$iv$iv) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (value$iv$iv$iv < 128) {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                ItemInfo it = (ItemInfo) v$iv[index$iv$iv];
                                for (LazyLayoutItemAnimation lazyLayoutItemAnimation : it.getAnimations()) {
                                    if (lazyLayoutItemAnimation != null) {
                                        lazyLayoutItemAnimation.release();
                                    }
                                }
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv++;
                            i = i;
                            this_$iv2 = this_$iv2;
                            $i$f$forEachValue2 = $i$f$forEachValue2;
                        }
                        this_$iv = this_$iv2;
                        $i$f$forEachValue = $i$f$forEachValue2;
                        if (bitCount$iv$iv != i) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    this_$iv2 = this_$iv;
                    $i$f$forEachValue2 = $i$f$forEachValue;
                }
            }
            this.keyToItemInfoMap.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void initializeAnimation$default(LazyLayoutItemAnimator lazyLayoutItemAnimator, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, int i, ItemInfo itemInfo, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            LazyLayoutItemAnimator<T>.ItemInfo itemInfo2 = lazyLayoutItemAnimator.keyToItemInfoMap.get(lazyLayoutMeasuredItem.getKey());
            Intrinsics.checkNotNull(itemInfo2);
            itemInfo = itemInfo2;
        }
        lazyLayoutItemAnimator.initializeAnimation(lazyLayoutMeasuredItem, i, itemInfo);
    }

    private final void initializeAnimation(T item, int mainAxisOffset, LazyLayoutItemAnimator<T>.ItemInfo itemInfo) {
        long targetFirstPlaceableOffset;
        T t = item;
        int i = 0;
        long firstPlaceableOffset = t.mo932getOffsetBjo55l4(0);
        if (t.getIsVertical()) {
            targetFirstPlaceableOffset = IntOffset.m8029copyiSbpLlY$default(firstPlaceableOffset, 0, mainAxisOffset, 1, null);
        } else {
            targetFirstPlaceableOffset = IntOffset.m8029copyiSbpLlY$default(firstPlaceableOffset, mainAxisOffset, 0, 2, null);
        }
        LazyLayoutItemAnimation[] animations = itemInfo.getAnimations();
        int index$iv = 0;
        int length = animations.length;
        while (i < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = animations[i];
            int index$iv2 = index$iv + 1;
            if (lazyLayoutItemAnimation != null) {
                long diffToFirstPlaceableOffset = IntOffset.m8036minusqkQi6aY(t.mo932getOffsetBjo55l4(index$iv), firstPlaceableOffset);
                lazyLayoutItemAnimation.m986setRawOffsetgyyYBs(IntOffset.m8037plusqkQi6aY(targetFirstPlaceableOffset, diffToFirstPlaceableOffset));
            }
            i++;
            t = item;
            index$iv = index$iv2;
        }
    }

    static /* synthetic */ void startPlacementAnimationsIfNeeded$default(LazyLayoutItemAnimator lazyLayoutItemAnimator, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        lazyLayoutItemAnimator.startPlacementAnimationsIfNeeded(lazyLayoutMeasuredItem, z);
    }

    private final void startPlacementAnimationsIfNeeded(T item, boolean isMovingAway) {
        LazyLayoutItemAnimator<T>.ItemInfo itemInfo = this.keyToItemInfoMap.get(item.getKey());
        Intrinsics.checkNotNull(itemInfo);
        LazyLayoutItemAnimator<T>.ItemInfo itemInfo2 = itemInfo;
        LazyLayoutItemAnimation[] animations = itemInfo2.getAnimations();
        int index$iv = 0;
        int length = animations.length;
        int i = 0;
        while (i < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = animations[i];
            int index$iv2 = index$iv + 1;
            if (lazyLayoutItemAnimation != null) {
                long newTarget = item.mo932getOffsetBjo55l4(index$iv);
                long currentTarget = lazyLayoutItemAnimation.getRawOffset();
                if (!IntOffset.m8032equalsimpl0(currentTarget, LazyLayoutItemAnimation.INSTANCE.m987getNotInitializednOccac()) && !IntOffset.m8032equalsimpl0(currentTarget, newTarget)) {
                    lazyLayoutItemAnimation.m979animatePlacementDeltaar5cAso(IntOffset.m8036minusqkQi6aY(newTarget, currentTarget), isMovingAway);
                }
                lazyLayoutItemAnimation.m986setRawOffsetgyyYBs(newTarget);
            } else {
                animations = animations;
            }
            i++;
            index$iv = index$iv2;
            animations = animations;
            itemInfo2 = itemInfo2;
        }
    }

    public final LazyLayoutItemAnimation getAnimation(Object key, int placeableIndex) {
        LazyLayoutItemAnimation[] animations;
        LazyLayoutItemAnimator<T>.ItemInfo itemInfo = this.keyToItemInfoMap.get(key);
        if (itemInfo == null || (animations = itemInfo.getAnimations()) == null) {
            return null;
        }
        return animations[placeableIndex];
    }

    private final int updateAndReturnOffsetFor(int[] $this$updateAndReturnOffsetFor, T t) {
        int lane = t.getLane();
        int span = t.getSpan();
        int maxOffset = 0;
        int i = lane + span;
        for (int i2 = lane; i2 < i; i2++) {
            $this$updateAndReturnOffsetFor[i2] = $this$updateAndReturnOffsetFor[i2] + t.getMainAxisSizeWithSpacings();
            maxOffset = Math.max(maxOffset, $this$updateAndReturnOffsetFor[i2]);
        }
        return maxOffset;
    }

    /* JADX INFO: renamed from: getMinSizeToFitDisappearingItems-YbymL2g, reason: not valid java name */
    public final long m988getMinSizeToFitDisappearingItemsYbymL2g() {
        long size = IntSize.INSTANCE.m8081getZeroYbymL2g();
        List<LazyLayoutItemAnimation> list = this.disappearingItems;
        int size2 = list.size();
        for (int index$iv = 0; index$iv < size2; index$iv++) {
            Object item$iv = list.get(index$iv);
            LazyLayoutItemAnimation it = (LazyLayoutItemAnimation) item$iv;
            GraphicsLayer layer = it.getLayer();
            if (layer != null) {
                long size3 = size;
                int width$iv = Math.max((int) (size >> 32), IntOffset.m8033getXimpl(it.getRawOffset()) + ((int) (layer.getSize() >> 32)));
                int height$iv = Math.max((int) (size3 & 4294967295L), IntOffset.m8034getYimpl(it.getRawOffset()) + ((int) (layer.getSize() & 4294967295L)));
                size = IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
            }
        }
        return size;
    }

    public final Modifier getModifier() {
        return this.modifier;
    }

    private final boolean getHasAnimations(T t) {
        int placeablesCount = t.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            int index = i;
            if (LazyLayoutItemAnimatorKt.getSpecs(t.getParentData(index)) != null) {
                return true;
            }
        }
        return false;
    }

    private final int getMainAxisOffset(LazyLayoutMeasuredItem $this$mainAxisOffset) {
        long it = $this$mainAxisOffset.mo932getOffsetBjo55l4(0);
        return $this$mainAxisOffset.getIsVertical() ? IntOffset.m8034getYimpl(it) : IntOffset.m8033getXimpl(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCrossAxisOffset(LazyLayoutMeasuredItem $this$crossAxisOffset) {
        long it = $this$crossAxisOffset.mo932getOffsetBjo55l4(0);
        return !$this$crossAxisOffset.getIsVertical() ? IntOffset.m8034getYimpl(it) : IntOffset.m8033getXimpl(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J=\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00122\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010+R0\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u0014\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u001e\u0010 \u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u001e\u0010\"\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0014¨\u0006,"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;", "", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "value", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "animations", "getAnimations", "()[Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "[Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "getConstraints-DWUhwKw", "()Landroidx/compose/ui/unit/Constraints;", "setConstraints-_Sx5XlM", "(Landroidx/compose/ui/unit/Constraints;)V", "crossAxisOffset", "", "getCrossAxisOffset", "()I", "setCrossAxisOffset", "(I)V", "lane", "getLane", "setLane", TtmlNode.TAG_SPAN, "getSpan", "setSpan", "isRunningPlacement", "", "()Z", "layoutMinOffset", "getLayoutMinOffset", "layoutMaxOffset", "getLayoutMaxOffset", "updateAnimation", "", "positionedItem", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;III)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    final class ItemInfo {
        private Constraints constraints;
        private int crossAxisOffset;
        private int lane;
        private int layoutMaxOffset;
        private int layoutMinOffset;
        private LazyLayoutItemAnimation[] animations = LazyLayoutItemAnimatorKt.EmptyArray;
        private int span = 1;

        public ItemInfo() {
        }

        public final LazyLayoutItemAnimation[] getAnimations() {
            return this.animations;
        }

        /* JADX INFO: renamed from: getConstraints-DWUhwKw, reason: not valid java name and from getter */
        public final Constraints getConstraints() {
            return this.constraints;
        }

        /* JADX INFO: renamed from: setConstraints-_Sx5XlM, reason: not valid java name */
        public final void m991setConstraints_Sx5XlM(Constraints constraints) {
            this.constraints = constraints;
        }

        public final int getCrossAxisOffset() {
            return this.crossAxisOffset;
        }

        public final void setCrossAxisOffset(int i) {
            this.crossAxisOffset = i;
        }

        public final int getLane() {
            return this.lane;
        }

        public final void setLane(int i) {
            this.lane = i;
        }

        public final int getSpan() {
            return this.span;
        }

        public final void setSpan(int i) {
            this.span = i;
        }

        private final boolean isRunningPlacement() {
            LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr = this.animations;
            int length = lazyLayoutItemAnimationArr.length;
            for (int i = 0; i < length; i++) {
                LazyLayoutItemAnimation lazyLayoutItemAnimation = lazyLayoutItemAnimationArr[i];
                if (lazyLayoutItemAnimation != null && lazyLayoutItemAnimation.getIsRunningMovingAwayAnimation()) {
                    return true;
                }
            }
            return false;
        }

        public final int getLayoutMinOffset() {
            return this.layoutMinOffset;
        }

        public final int getLayoutMaxOffset() {
            return this.layoutMaxOffset;
        }

        public static /* synthetic */ void updateAnimation$default(ItemInfo itemInfo, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int i, int i2, int i3, int i4, Object obj) {
            int crossAxisOffset;
            if ((i4 & 32) != 0) {
                crossAxisOffset = LazyLayoutItemAnimator.this.getCrossAxisOffset(lazyLayoutMeasuredItem);
            } else {
                crossAxisOffset = i3;
            }
            itemInfo.updateAnimation(lazyLayoutMeasuredItem, coroutineScope, graphicsContext, i, i2, crossAxisOffset);
        }

        public final void updateAnimation(T positionedItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int layoutMinOffset, int layoutMaxOffset, int crossAxisOffset) {
            if (!isRunningPlacement()) {
                this.layoutMinOffset = layoutMinOffset;
                this.layoutMaxOffset = layoutMaxOffset;
            }
            int length = this.animations.length;
            for (int i = positionedItem.getPlaceablesCount(); i < length; i++) {
                LazyLayoutItemAnimation lazyLayoutItemAnimation = this.animations[i];
                if (lazyLayoutItemAnimation != null) {
                    lazyLayoutItemAnimation.release();
                }
            }
            if (this.animations.length != positionedItem.getPlaceablesCount()) {
                Object[] objArrCopyOf = Arrays.copyOf(this.animations, positionedItem.getPlaceablesCount());
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
                this.animations = (LazyLayoutItemAnimation[]) objArrCopyOf;
            }
            this.constraints = Constraints.m7845boximpl(positionedItem.getConstraints());
            this.crossAxisOffset = crossAxisOffset;
            this.lane = positionedItem.getLane();
            this.span = positionedItem.getSpan();
            int placeablesCount = positionedItem.getPlaceablesCount();
            final LazyLayoutItemAnimator<T> lazyLayoutItemAnimator = LazyLayoutItemAnimator.this;
            for (int i2 = 0; i2 < placeablesCount; i2++) {
                int index = i2;
                LazyLayoutAnimationSpecsNode specs = LazyLayoutItemAnimatorKt.getSpecs(positionedItem.getParentData(index));
                if (specs == null) {
                    LazyLayoutItemAnimation lazyLayoutItemAnimation2 = this.animations[index];
                    if (lazyLayoutItemAnimation2 != null) {
                        lazyLayoutItemAnimation2.release();
                    }
                    this.animations[index] = null;
                } else {
                    LazyLayoutItemAnimation it = this.animations[index];
                    if (it == null) {
                        it = new LazyLayoutItemAnimation(coroutineScope, graphicsContext, new Function0() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$ItemInfo$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return LazyLayoutItemAnimator.ItemInfo.updateAnimation$lambda$0$0(lazyLayoutItemAnimator);
                            }
                        });
                        this.animations[index] = it;
                    }
                    it.setFadeInSpec(specs.getFadeInSpec());
                    it.setPlacementSpec(specs.getPlacementSpec());
                    it.setFadeOutSpec(specs.getFadeOutSpec());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit updateAnimation$lambda$0$0(LazyLayoutItemAnimator this$0) {
            DrawModifierNode drawModifierNode = this$0.displayingNode;
            if (drawModifierNode != null) {
                DrawModifierNodeKt.invalidateDraw(drawModifierNode);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0016J\f\u0010\u000b\u001a\u00020\t*\u00020\fH\u0016J\r\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0004HÂ\u0003J\u0017\u0010\u000e\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsNode;", "animator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", ArchiveParams.MODE_CREATE, "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "component1", "copy", MetadataValidation.EQUALS, "", "other", "", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class DisplayingDisappearingItemsElement extends ModifierNodeElement<DisplayingDisappearingItemsNode> {
        private final LazyLayoutItemAnimator<?> animator;

        private final LazyLayoutItemAnimator<?> component1() {
            return this.animator;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DisplayingDisappearingItemsElement copy$default(DisplayingDisappearingItemsElement displayingDisappearingItemsElement, LazyLayoutItemAnimator lazyLayoutItemAnimator, int i, Object obj) {
            if ((i & 1) != 0) {
                lazyLayoutItemAnimator = displayingDisappearingItemsElement.animator;
            }
            return displayingDisappearingItemsElement.copy(lazyLayoutItemAnimator);
        }

        public final DisplayingDisappearingItemsElement copy(LazyLayoutItemAnimator<?> animator) {
            return new DisplayingDisappearingItemsElement(animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DisplayingDisappearingItemsElement) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsElement) other).animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return this.animator.hashCode();
        }

        public String toString() {
            return "DisplayingDisappearingItemsElement(animator=" + this.animator + ')';
        }

        public DisplayingDisappearingItemsElement(LazyLayoutItemAnimator<?> lazyLayoutItemAnimator) {
            this.animator = lazyLayoutItemAnimator;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* JADX INFO: renamed from: create */
        public DisplayingDisappearingItemsNode getNode() {
            return new DisplayingDisappearingItemsNode(this.animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(DisplayingDisappearingItemsNode node) {
            node.setAnimator(this.animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
            $this$inspectableProperties.setName("DisplayingDisappearingItemsElement");
        }
    }

    /* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0012\u0010\f\u001a\u00020\b2\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004J\r\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0004HÂ\u0003J\u0017\u0010\u000e\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "animator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "onAttach", "onDetach", "setAnimator", "component1", "copy", MetadataValidation.EQUALS, "", "other", "", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final /* data */ class DisplayingDisappearingItemsNode extends Modifier.Node implements DrawModifierNode {
        private LazyLayoutItemAnimator<?> animator;

        private final LazyLayoutItemAnimator<?> component1() {
            return this.animator;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DisplayingDisappearingItemsNode copy$default(DisplayingDisappearingItemsNode displayingDisappearingItemsNode, LazyLayoutItemAnimator lazyLayoutItemAnimator, int i, Object obj) {
            if ((i & 1) != 0) {
                lazyLayoutItemAnimator = displayingDisappearingItemsNode.animator;
            }
            return displayingDisappearingItemsNode.copy(lazyLayoutItemAnimator);
        }

        public final DisplayingDisappearingItemsNode copy(LazyLayoutItemAnimator<?> animator) {
            return new DisplayingDisappearingItemsNode(animator);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DisplayingDisappearingItemsNode) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsNode) other).animator);
        }

        public int hashCode() {
            return this.animator.hashCode();
        }

        public String toString() {
            return "DisplayingDisappearingItemsNode(animator=" + this.animator + ')';
        }

        public DisplayingDisappearingItemsNode(LazyLayoutItemAnimator<?> lazyLayoutItemAnimator) {
            this.animator = lazyLayoutItemAnimator;
        }

        @Override // androidx.compose.ui.node.DrawModifierNode
        public void draw(ContentDrawScope $this$draw) {
            List $this$fastForEach$iv = ((LazyLayoutItemAnimator) this.animator).disappearingItems;
            int size = $this$fastForEach$iv.size();
            int index$iv = 0;
            while (index$iv < size) {
                Object item$iv = $this$fastForEach$iv.get(index$iv);
                LazyLayoutItemAnimation it = (LazyLayoutItemAnimation) item$iv;
                GraphicsLayer layer = it.getLayer();
                if (layer != null) {
                    float x = IntOffset.m8033getXimpl(it.getFinalOffset());
                    float y = IntOffset.m8034getYimpl(it.getFinalOffset());
                    ContentDrawScope $this$translate$iv = $this$draw;
                    float left$iv = x - IntOffset.m8033getXimpl(layer.getTopLeft());
                    float top$iv = y - IntOffset.m8034getYimpl(layer.getTopLeft());
                    $this$translate$iv.getDrawContext().getTransform().translate(left$iv, top$iv);
                    try {
                        GraphicsLayerKt.drawLayer($this$translate$iv, layer);
                        $this$translate$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                    } catch (Throwable th) {
                        $this$translate$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                        throw th;
                    }
                }
                index$iv++;
                $this$fastForEach$iv = $this$fastForEach$iv;
            }
            $this$draw.drawContent();
        }

        @Override // androidx.compose.ui.Modifier.Node
        public void onAttach() {
            ((LazyLayoutItemAnimator) this.animator).displayingNode = this;
        }

        @Override // androidx.compose.ui.Modifier.Node
        public void onDetach() {
            this.animator.reset();
        }

        public final void setAnimator(LazyLayoutItemAnimator<?> animator) {
            if (!Intrinsics.areEqual(this.animator, animator) && getNode().getIsAttached()) {
                this.animator.reset();
                ((LazyLayoutItemAnimator) animator).displayingNode = this;
                this.animator = animator;
            }
        }
    }
}
