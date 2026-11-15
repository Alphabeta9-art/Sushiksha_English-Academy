package androidx.compose.ui.graphics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PathGeometry.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004*\u00020\u00022\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u001a\u0014\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u001a\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"computeDirection", "Landroidx/compose/ui/graphics/Path$Direction;", "Landroidx/compose/ui/graphics/Path;", "divide", "", "contours", "reverse", "destination", "floatCountForType", "", "type", "Landroidx/compose/ui/graphics/PathSegment$Type;", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PathGeometryKt {

    /* JADX INFO: compiled from: PathGeometry.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PathSegment.Type.values().length];
            try {
                iArr[PathSegment.Type.Move.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[PathSegment.Type.Line.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[PathSegment.Type.Quadratic.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[PathSegment.Type.Conic.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[PathSegment.Type.Cubic.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[PathSegment.Type.Close.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[PathSegment.Type.Done.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x005e  */
    /* JADX WARN: Code duplicated, block: B:31:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:32:0x00f2  */
    public static final Path.Direction computeDirection(Path $this$computeDirection) {
        boolean first = true;
        PathIterator iterator = $this$computeDirection.iterator();
        float[] points = new float[8];
        float area = 0.0f;
        float startX = 0.0f;
        float startY = 0.0f;
        float endY = 0.0f;
        PathSegment.Type type = PathIterator.next$default(iterator, points, 0, 2, null);
        float endX = 0.0f;
        while (type != PathSegment.Type.Done) {
            switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    if (!first) {
                        if (area >= 0.0f) {
                            return Path.Direction.Clockwise;
                        }
                        return Path.Direction.CounterClockwise;
                    }
                    first = false;
                    float startX2 = points[0];
                    startX = startX2;
                    startY = points[1];
                    type = PathIterator.next$default(iterator, points, 0, 2, null);
                    break;
                    break;
                case 2:
                    float x0 = points[0];
                    float y0 = points[1];
                    float x1 = points[2];
                    float y1 = points[3];
                    area += BezierKt.cubicArea(x0, y0, x0, y0, x1, y1, x1, y1);
                    endX = x1;
                    endY = y1;
                    type = PathIterator.next$default(iterator, points, 0, 2, null);
                    break;
                case 3:
                    float x2 = points[0];
                    float y2 = points[1];
                    float x3 = points[2];
                    float y3 = points[3];
                    float x4 = points[4];
                    float y4 = points[5];
                    float c1x = x2 + ((x3 - x2) * 0.6666667f);
                    float c1y = y2 + ((y3 - y2) * 0.6666667f);
                    float c2x = x4 + ((x3 - x4) * 0.6666667f);
                    float c2y = y4 + ((y3 - y4) * 0.6666667f);
                    area += BezierKt.cubicArea(x2, y2, c1x, c1y, c2x, c2y, x4, y4);
                    endX = x4;
                    endY = y4;
                    type = PathIterator.next$default(iterator, points, 0, 2, null);
                    break;
                case 4:
                    break;
                case 5:
                    area += BezierKt.cubicArea(points[0], points[1], points[2], points[3], points[4], points[5], points[6], points[7]);
                    float endX2 = points[6];
                    endX = endX2;
                    endY = points[7];
                    type = PathIterator.next$default(iterator, points, 0, 2, null);
                    break;
                case 6:
                    float b$iv = startX;
                    float $this$closeTo$iv = endX;
                    if (Math.abs($this$closeTo$iv - b$iv) < 1.05E-6f) {
                        float b$iv2 = startY;
                        float $this$closeTo$iv2 = endY;
                        if (!(Math.abs($this$closeTo$iv2 - b$iv2) < 1.05E-6f)) {
                            area += BezierKt.cubicArea(endX, endY, endX, endY, startX, startY, startX, startY);
                            float endX3 = startX;
                            endX = endX3;
                            endY = startY;
                        }
                    } else {
                        area += BezierKt.cubicArea(endX, endY, endX, endY, startX, startY, startX, startY);
                        float endX4 = startX;
                        endX = endX4;
                        endY = startY;
                    }
                    type = PathIterator.next$default(iterator, points, 0, 2, null);
                    break;
                case 7:
                    if (area >= 0.0f) {
                        return Path.Direction.Clockwise;
                    }
                    return Path.Direction.CounterClockwise;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        if (area >= 0.0f) {
            return Path.Direction.Clockwise;
        }
        return Path.Direction.CounterClockwise;
    }

    public static /* synthetic */ List divide$default(Path path, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = new ArrayList();
        }
        return divide(path, list);
    }

    public static final List<Path> divide(Path $this$divide, List<Path> list) {
        Path path = AndroidPath_androidKt.Path();
        boolean first = true;
        boolean isEmpty = true;
        PathIterator iterator = $this$divide.iterator();
        float[] points = new float[8];
        PathSegment.Type type = PathIterator.next$default(iterator, points, 0, 2, null);
        Path path2 = path;
        while (type != PathSegment.Type.Done) {
            switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    if (!first && !isEmpty) {
                        list.add(path2);
                        path2 = AndroidPath_androidKt.Path();
                    }
                    path2.moveTo(points[0], points[1]);
                    isEmpty = true;
                    first = false;
                    break;
                case 2:
                    path2.lineTo(points[2], points[3]);
                    isEmpty = false;
                    break;
                case 3:
                    path2.quadraticTo(points[2], points[3], points[4], points[5]);
                    isEmpty = false;
                    break;
                case 4:
                case 7:
                    continue;
                case 5:
                    path2.cubicTo(points[2], points[3], points[4], points[5], points[6], points[7]);
                    isEmpty = false;
                    break;
                case 6:
                    path2.close();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            type = PathIterator.next$default(iterator, points, 0, 2, null);
        }
        if (!first && !isEmpty) {
            list.add(path2);
        }
        return list;
    }

    public static /* synthetic */ Path reverse$default(Path path, Path path2, int i, Object obj) {
        if ((i & 1) != 0) {
            path2 = AndroidPath_androidKt.Path();
        }
        return reverse(path, path2);
    }

    public static final Path reverse(Path $this$reverse, Path destination) {
        float[] points;
        boolean insertMove;
        int dataIndex;
        PathIterator iterator = $this$reverse.iterator();
        float[] points2 = null;
        int count = iterator.calculateSize(false);
        ArrayList segments = new ArrayList(count);
        ArrayList data = new ArrayList(count);
        float[] points3 = new float[8];
        for (PathSegment.Type type = PathIterator.next$default(iterator, points3, 0, 2, null); type != PathSegment.Type.Done; type = PathIterator.next$default(iterator, points3, 0, 2, null)) {
            segments.add(type);
            if (type != PathSegment.Type.Close) {
                float[] fArrCopyOf = Arrays.copyOf(points3, floatCountForType(type));
                Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
                data.add(fArrCopyOf);
            }
        }
        boolean insertMove2 = true;
        int dataIndex2 = data.size();
        boolean insertClose = false;
        int i = segments.size() - 1;
        while (-1 < i) {
            if (insertMove2) {
                int dataIndex3 = dataIndex2 - 1;
                float[] points4 = (float[]) data.get(dataIndex3);
                int offset = ArraysKt.getLastIndex(points4);
                destination.moveTo(points4[offset - 1], points4[offset]);
                points = points4;
                insertMove = false;
                dataIndex = dataIndex3;
            } else {
                float[] points5 = (float[]) data.get(dataIndex2);
                points = points5;
                insertMove = insertMove2;
                dataIndex = dataIndex2;
            }
            switch (WhenMappings.$EnumSwitchMapping$0[((PathSegment.Type) segments.get(i)).ordinal()]) {
                case 1:
                    i = i;
                    if (insertClose) {
                        destination.close();
                        insertClose = false;
                    }
                    insertMove2 = true;
                    continue;
                    dataIndex2 = dataIndex;
                    i--;
                    points2 = points2;
                    break;
                case 2:
                    i = i;
                    destination.lineTo(points[points2], points[1]);
                    dataIndex--;
                    insertMove2 = insertMove;
                    continue;
                    dataIndex2 = dataIndex;
                    i--;
                    points2 = points2;
                    break;
                case 3:
                    i = i;
                    destination.quadraticTo(points[2], points[3], points[points2], points[1]);
                    dataIndex--;
                    insertMove2 = insertMove;
                    continue;
                    dataIndex2 = dataIndex;
                    i--;
                    points2 = points2;
                    break;
                case 4:
                    break;
                case 5:
                    i = i;
                    destination.cubicTo(points[4], points[5], points[2], points[3], points[points2], points[1]);
                    dataIndex--;
                    insertMove2 = insertMove;
                    continue;
                    dataIndex2 = dataIndex;
                    i--;
                    points2 = points2;
                    break;
                case 6:
                    insertClose = true;
                    i = i;
                    insertMove2 = insertMove;
                    continue;
                    dataIndex2 = dataIndex;
                    i--;
                    points2 = points2;
                    break;
                case 7:
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            insertMove2 = insertMove;
            dataIndex2 = dataIndex;
            i--;
            points2 = points2;
        }
        if (insertClose) {
            destination.close();
        }
        return destination;
    }

    private static final int floatCountForType(PathSegment.Type type) {
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 6;
            case 4:
                return 8;
            case 5:
                return 8;
            case 6:
            case 7:
                return 0;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
