package androidx.compose.runtime.snapshots;

import androidx.autofill.HintConstants;
import com.cloudinary.metadata.MetadataValidation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: SnapshotIdSet.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u0000 ,2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001,B5\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\n\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003\u0012\u000e\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u00002\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00002\n\u0010\u0010\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0013J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000J\u0013\u0010\u001a\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001bH\u0096\u0002J>\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00002+\u0010\u001e\u001a'\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00000\u001fH\u0082\bJ!\u0010#\u001a\u00020$2\u0016\u0010%\u001a\u0012\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020$0&H\u0086\bJ\u001b\u0010'\u001a\u00060\u0002j\u0002`\u00032\n\u0010(\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010)J\b\u0010*\u001a\u00020+H\u0016R\u000e\u0010\u0004\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0018\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006-"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "upperSet", "lowerSet", "lowerBound", "belowBound", "", "Landroidx/compose/runtime/snapshots/SnapshotIdArray;", "<init>", "(JJJ[J)V", "J", "[J", "get", "", "id", "(J)Z", "set", "(J)Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "clear", "andNot", "ids", MetadataValidation.AndValidator.AND, "or", "bits", "iterator", "", "fastFold", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "acc", "fastForEach", "", "block", "Lkotlin/Function1;", "lowest", "default", "(J)J", "toString", "", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SnapshotIdSet implements Iterable<Long>, KMappedMarker {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SnapshotIdSet EMPTY = new SnapshotIdSet(0, 0, 0, null);
    private final long[] belowBound;
    private final long lowerBound;
    private final long lowerSet;
    private final long upperSet;

    private SnapshotIdSet(long upperSet, long lowerSet, long lowerBound, long[] belowBound) {
        this.upperSet = upperSet;
        this.lowerSet = lowerSet;
        this.lowerBound = lowerBound;
        this.belowBound = belowBound;
    }

    public final boolean get(long id) {
        long[] it;
        long other$iv = this.lowerBound;
        long offset = id - other$iv;
        int other$iv2 = Intrinsics.compare(offset, 0);
        if (other$iv2 >= 0) {
            int other$iv3 = Intrinsics.compare(offset, 64);
            if (other$iv3 < 0) {
                int $i$f$toInt = (int) offset;
                return ((1 << $i$f$toInt) & this.lowerSet) != 0;
            }
        }
        int other$iv4 = Intrinsics.compare(offset, 64);
        if (other$iv4 >= 0) {
            int other$iv5 = Intrinsics.compare(offset, 128);
            if (other$iv5 < 0) {
                int $i$f$toInt2 = (int) offset;
                return ((1 << ($i$f$toInt2 + (-64))) & this.upperSet) != 0;
            }
        }
        int other$iv6 = Intrinsics.compare(offset, 0);
        return other$iv6 <= 0 && (it = this.belowBound) != null && SnapshotId_jvmKt.binarySearch(it, id) >= 0;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0045  */
    /* JADX WARN: Code duplicated, block: B:13:0x0052  */
    /* JADX WARN: Code duplicated, block: B:15:0x005d  */
    /* JADX WARN: Code duplicated, block: B:17:0x0069  */
    /* JADX WARN: Code duplicated, block: B:19:0x007b  */
    /* JADX WARN: Code duplicated, block: B:20:0x007f  */
    /* JADX WARN: Code duplicated, block: B:22:0x008a  */
    /* JADX WARN: Code duplicated, block: B:24:0x0090  */
    /* JADX WARN: Code duplicated, block: B:26:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:30:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:32:0x00e6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:33:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:37:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:39:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:40:0x010a  */
    /* JADX WARN: Code duplicated, block: B:43:0x0115  */
    /* JADX WARN: Code duplicated, block: B:47:0x0124 A[LOOP:0: B:28:0x00d8->B:47:0x0124, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:51:0x013e  */
    /* JADX WARN: Code duplicated, block: B:54:0x0145  */
    /* JADX WARN: Code duplicated, block: B:57:0x0153  */
    /* JADX WARN: Code duplicated, block: B:58:0x0156  */
    /* JADX WARN: Code duplicated, block: B:60:0x015e  */
    /* JADX WARN: Code duplicated, block: B:62:0x0173  */
    /* JADX WARN: Code duplicated, block: B:64:0x017a  */
    /* JADX WARN: Code duplicated, block: B:67:0x011b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x0131 A[SYNTHETIC] */
    public final SnapshotIdSet set(long id) {
        int other$iv;
        int other$iv2;
        long[] array;
        int location;
        long newLowerSet;
        long newLowerBound;
        SnapshotIdArrayBuilder snapshotIdArrayBuilder;
        long it;
        long newUpperSet;
        long other$iv3;
        long $this$compareTo$iv;
        long newLowerBound2;
        long newLowerSet2;
        long[] array2;
        long newLowerBound3;
        int i;
        int bitOffset;
        int other$iv4;
        long mask;
        long other$iv5 = this.lowerBound;
        long offset = id - other$iv5;
        int other$iv6 = Intrinsics.compare(offset, 0);
        if (other$iv6 >= 0) {
            int other$iv7 = Intrinsics.compare(offset, 64);
            if (other$iv7 < 0) {
                long $this$toInt$iv = (int) offset;
                long mask2 = 1 << $this$toInt$iv;
                if ((this.lowerSet & mask2) == 0) {
                    return new SnapshotIdSet(this.upperSet, this.lowerSet | mask2, this.lowerBound, this.belowBound);
                }
            } else {
                other$iv = Intrinsics.compare(offset, 64);
                if (other$iv >= 0) {
                    other$iv4 = Intrinsics.compare(offset, 128);
                    if (other$iv4 < 0) {
                        int $i$f$toInt = (int) offset;
                        mask = 1 << ($i$f$toInt - 64);
                        if ((this.upperSet & mask) == 0) {
                            return new SnapshotIdSet(this.upperSet | mask, this.lowerSet, this.lowerBound, this.belowBound);
                        }
                    } else {
                        other$iv2 = Intrinsics.compare(offset, 128);
                        if (other$iv2 >= 0) {
                            array = this.belowBound;
                            if (array == null) {
                                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, new long[]{id});
                            }
                            location = SnapshotId_jvmKt.binarySearch(array, id);
                            if (location < 0) {
                                int insertLocation = -(location + 1);
                                long[] newBelowBound = SnapshotId_jvmKt.withIdInsertedAt(array, insertLocation, id);
                                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, newBelowBound);
                            }
                        } else if (!get(id)) {
                            long newUpperSet2 = this.upperSet;
                            newLowerSet = this.lowerSet;
                            newLowerBound = this.lowerBound;
                            snapshotIdArrayBuilder = null;
                            long $this$div$iv = id + ((long) 1);
                            long $this$times$iv = $this$div$iv / ((long) 64);
                            it = $this$times$iv * ((long) 64);
                            if (Intrinsics.compare(it, 0) < 0) {
                                long $this$plus$iv = Long.MAX_VALUE - ((long) 128);
                                it = $this$plus$iv + ((long) 1);
                            }
                            newUpperSet = newUpperSet2;
                            while (true) {
                                other$iv3 = it;
                                $this$compareTo$iv = newLowerBound;
                                if (Intrinsics.compare($this$compareTo$iv, other$iv3) < 0) {
                                    newLowerBound2 = newLowerBound;
                                    newLowerSet2 = newLowerSet;
                                    break;
                                }
                                if (newLowerSet != 0) {
                                    if (snapshotIdArrayBuilder == null) {
                                        snapshotIdArrayBuilder = new SnapshotIdArrayBuilder(this.belowBound);
                                    }
                                    i = 0;
                                    while (i < 64) {
                                        bitOffset = i;
                                        if (((1 << bitOffset) & newLowerSet) != 0) {
                                            long $this$plus$iv2 = newLowerBound;
                                            snapshotIdArrayBuilder.add($this$plus$iv2 + ((long) bitOffset));
                                        }
                                        i++;
                                        newLowerBound = newLowerBound;
                                    }
                                    newLowerBound3 = newLowerBound;
                                } else {
                                    newLowerBound3 = newLowerBound;
                                }
                                if (newUpperSet == 0) {
                                    long newLowerBound4 = it;
                                    newLowerBound2 = newLowerBound4;
                                    newLowerSet2 = 0;
                                    break;
                                }
                                newLowerSet = newUpperSet;
                                newUpperSet = 0;
                                long $this$plus$iv3 = newLowerBound3;
                                newLowerBound = $this$plus$iv3 + ((long) 64);
                            }
                            if (snapshotIdArrayBuilder != null || (array2 = snapshotIdArrayBuilder.toArray()) == null) {
                                array2 = this.belowBound;
                            }
                            return new SnapshotIdSet(newUpperSet, newLowerSet2, newLowerBound2, array2).set(id);
                        }
                    }
                } else {
                    other$iv2 = Intrinsics.compare(offset, 128);
                    if (other$iv2 >= 0) {
                        array = this.belowBound;
                        if (array == null) {
                            return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, new long[]{id});
                        }
                        location = SnapshotId_jvmKt.binarySearch(array, id);
                        if (location < 0) {
                            int insertLocation2 = -(location + 1);
                            long[] newBelowBound2 = SnapshotId_jvmKt.withIdInsertedAt(array, insertLocation2, id);
                            return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, newBelowBound2);
                        }
                    } else if (!get(id)) {
                        long newUpperSet3 = this.upperSet;
                        newLowerSet = this.lowerSet;
                        newLowerBound = this.lowerBound;
                        snapshotIdArrayBuilder = null;
                        long $this$div$iv2 = id + ((long) 1);
                        long $this$times$iv2 = $this$div$iv2 / ((long) 64);
                        it = $this$times$iv2 * ((long) 64);
                        if (Intrinsics.compare(it, 0) < 0) {
                            long $this$plus$iv4 = Long.MAX_VALUE - ((long) 128);
                            it = $this$plus$iv4 + ((long) 1);
                        }
                        newUpperSet = newUpperSet3;
                        while (true) {
                            other$iv3 = it;
                            $this$compareTo$iv = newLowerBound;
                            if (Intrinsics.compare($this$compareTo$iv, other$iv3) < 0) {
                                newLowerBound2 = newLowerBound;
                                newLowerSet2 = newLowerSet;
                                break;
                            }
                            if (newLowerSet != 0) {
                                if (snapshotIdArrayBuilder == null) {
                                    snapshotIdArrayBuilder = new SnapshotIdArrayBuilder(this.belowBound);
                                }
                                i = 0;
                                while (i < 64) {
                                    bitOffset = i;
                                    if (((1 << bitOffset) & newLowerSet) != 0) {
                                        long $this$plus$iv5 = newLowerBound;
                                        snapshotIdArrayBuilder.add($this$plus$iv5 + ((long) bitOffset));
                                    }
                                    i++;
                                    newLowerBound = newLowerBound;
                                }
                                newLowerBound3 = newLowerBound;
                            } else {
                                newLowerBound3 = newLowerBound;
                            }
                            if (newUpperSet == 0) {
                                long newLowerBound5 = it;
                                newLowerBound2 = newLowerBound5;
                                newLowerSet2 = 0;
                                break;
                            }
                            newLowerSet = newUpperSet;
                            newUpperSet = 0;
                            long $this$plus$iv6 = newLowerBound3;
                            newLowerBound = $this$plus$iv6 + ((long) 64);
                        }
                        if (snapshotIdArrayBuilder != null) {
                            array2 = this.belowBound;
                        } else {
                            array2 = this.belowBound;
                        }
                        return new SnapshotIdSet(newUpperSet, newLowerSet2, newLowerBound2, array2).set(id);
                    }
                }
            }
        } else {
            other$iv = Intrinsics.compare(offset, 64);
            if (other$iv >= 0) {
                other$iv4 = Intrinsics.compare(offset, 128);
                if (other$iv4 < 0) {
                    int $i$f$toInt2 = (int) offset;
                    mask = 1 << ($i$f$toInt2 - 64);
                    if ((this.upperSet & mask) == 0) {
                        return new SnapshotIdSet(this.upperSet | mask, this.lowerSet, this.lowerBound, this.belowBound);
                    }
                } else {
                    other$iv2 = Intrinsics.compare(offset, 128);
                    if (other$iv2 >= 0) {
                        array = this.belowBound;
                        if (array == null) {
                            return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, new long[]{id});
                        }
                        location = SnapshotId_jvmKt.binarySearch(array, id);
                        if (location < 0) {
                            int insertLocation3 = -(location + 1);
                            long[] newBelowBound3 = SnapshotId_jvmKt.withIdInsertedAt(array, insertLocation3, id);
                            return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, newBelowBound3);
                        }
                    } else if (!get(id)) {
                        long newUpperSet4 = this.upperSet;
                        newLowerSet = this.lowerSet;
                        newLowerBound = this.lowerBound;
                        snapshotIdArrayBuilder = null;
                        long $this$div$iv3 = id + ((long) 1);
                        long $this$times$iv3 = $this$div$iv3 / ((long) 64);
                        it = $this$times$iv3 * ((long) 64);
                        if (Intrinsics.compare(it, 0) < 0) {
                            long $this$plus$iv7 = Long.MAX_VALUE - ((long) 128);
                            it = $this$plus$iv7 + ((long) 1);
                        }
                        newUpperSet = newUpperSet4;
                        while (true) {
                            other$iv3 = it;
                            $this$compareTo$iv = newLowerBound;
                            if (Intrinsics.compare($this$compareTo$iv, other$iv3) < 0) {
                                newLowerBound2 = newLowerBound;
                                newLowerSet2 = newLowerSet;
                                break;
                            }
                            if (newLowerSet != 0) {
                                if (snapshotIdArrayBuilder == null) {
                                    snapshotIdArrayBuilder = new SnapshotIdArrayBuilder(this.belowBound);
                                }
                                i = 0;
                                while (i < 64) {
                                    bitOffset = i;
                                    if (((1 << bitOffset) & newLowerSet) != 0) {
                                        long $this$plus$iv8 = newLowerBound;
                                        snapshotIdArrayBuilder.add($this$plus$iv8 + ((long) bitOffset));
                                    }
                                    i++;
                                    newLowerBound = newLowerBound;
                                }
                                newLowerBound3 = newLowerBound;
                            } else {
                                newLowerBound3 = newLowerBound;
                            }
                            if (newUpperSet == 0) {
                                long newLowerBound6 = it;
                                newLowerBound2 = newLowerBound6;
                                newLowerSet2 = 0;
                                break;
                            }
                            newLowerSet = newUpperSet;
                            newUpperSet = 0;
                            long $this$plus$iv9 = newLowerBound3;
                            newLowerBound = $this$plus$iv9 + ((long) 64);
                        }
                        if (snapshotIdArrayBuilder != null) {
                            array2 = this.belowBound;
                        } else {
                            array2 = this.belowBound;
                        }
                        return new SnapshotIdSet(newUpperSet, newLowerSet2, newLowerBound2, array2).set(id);
                    }
                }
            } else {
                other$iv2 = Intrinsics.compare(offset, 128);
                if (other$iv2 >= 0) {
                    array = this.belowBound;
                    if (array == null) {
                        return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, new long[]{id});
                    }
                    location = SnapshotId_jvmKt.binarySearch(array, id);
                    if (location < 0) {
                        int insertLocation4 = -(location + 1);
                        long[] newBelowBound4 = SnapshotId_jvmKt.withIdInsertedAt(array, insertLocation4, id);
                        return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, newBelowBound4);
                    }
                } else if (!get(id)) {
                    long newUpperSet5 = this.upperSet;
                    newLowerSet = this.lowerSet;
                    newLowerBound = this.lowerBound;
                    snapshotIdArrayBuilder = null;
                    long $this$div$iv4 = id + ((long) 1);
                    long $this$times$iv4 = $this$div$iv4 / ((long) 64);
                    it = $this$times$iv4 * ((long) 64);
                    if (Intrinsics.compare(it, 0) < 0) {
                        long $this$plus$iv10 = Long.MAX_VALUE - ((long) 128);
                        it = $this$plus$iv10 + ((long) 1);
                    }
                    newUpperSet = newUpperSet5;
                    while (true) {
                        other$iv3 = it;
                        $this$compareTo$iv = newLowerBound;
                        if (Intrinsics.compare($this$compareTo$iv, other$iv3) < 0) {
                            newLowerBound2 = newLowerBound;
                            newLowerSet2 = newLowerSet;
                            break;
                        }
                        if (newLowerSet != 0) {
                            if (snapshotIdArrayBuilder == null) {
                                snapshotIdArrayBuilder = new SnapshotIdArrayBuilder(this.belowBound);
                            }
                            i = 0;
                            while (i < 64) {
                                bitOffset = i;
                                if (((1 << bitOffset) & newLowerSet) != 0) {
                                    long $this$plus$iv11 = newLowerBound;
                                    snapshotIdArrayBuilder.add($this$plus$iv11 + ((long) bitOffset));
                                }
                                i++;
                                newLowerBound = newLowerBound;
                            }
                            newLowerBound3 = newLowerBound;
                        } else {
                            newLowerBound3 = newLowerBound;
                        }
                        if (newUpperSet == 0) {
                            long newLowerBound7 = it;
                            newLowerBound2 = newLowerBound7;
                            newLowerSet2 = 0;
                            break;
                        }
                        newLowerSet = newUpperSet;
                        newUpperSet = 0;
                        long $this$plus$iv12 = newLowerBound3;
                        newLowerBound = $this$plus$iv12 + ((long) 64);
                    }
                    if (snapshotIdArrayBuilder != null) {
                        array2 = this.belowBound;
                    } else {
                        array2 = this.belowBound;
                    }
                    return new SnapshotIdSet(newUpperSet, newLowerSet2, newLowerBound2, array2).set(id);
                }
            }
        }
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0043  */
    /* JADX WARN: Code duplicated, block: B:13:0x004e  */
    /* JADX WARN: Code duplicated, block: B:15:0x0059  */
    /* JADX WARN: Code duplicated, block: B:17:0x0066  */
    /* JADX WARN: Code duplicated, block: B:19:0x0079  */
    /* JADX WARN: Code duplicated, block: B:20:0x007c  */
    /* JADX WARN: Code duplicated, block: B:22:0x0086  */
    /* JADX WARN: Code duplicated, block: B:29:0x00a5 A[ADDED_TO_REGION, REMOVE] */
    public final SnapshotIdSet clear(long id) {
        int other$iv;
        int other$iv2;
        long[] array;
        int location;
        int other$iv3;
        long mask;
        long other$iv4 = this.lowerBound;
        long offset = id - other$iv4;
        int other$iv5 = Intrinsics.compare(offset, 0);
        if (other$iv5 >= 0) {
            int other$iv6 = Intrinsics.compare(offset, 64);
            if (other$iv6 < 0) {
                long $this$toInt$iv = (int) offset;
                long mask2 = 1 << $this$toInt$iv;
                if ((this.lowerSet & mask2) != 0) {
                    return new SnapshotIdSet(this.upperSet, this.lowerSet & (~mask2), this.lowerBound, this.belowBound);
                }
            } else {
                other$iv = Intrinsics.compare(offset, 64);
                if (other$iv >= 0) {
                    other$iv3 = Intrinsics.compare(offset, 128);
                    if (other$iv3 < 0) {
                        long $this$toInt$iv2 = ((int) offset) - 64;
                        mask = 1 << $this$toInt$iv2;
                        if ((this.upperSet & mask) != 0) {
                            return new SnapshotIdSet(this.upperSet & (~mask), this.lowerSet, this.lowerBound, this.belowBound);
                        }
                    } else {
                        other$iv2 = Intrinsics.compare(offset, 0);
                        if (other$iv2 < 0 && (array = this.belowBound) != null && (location = SnapshotId_jvmKt.binarySearch(array, id)) >= 0) {
                            return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, SnapshotId_jvmKt.withIdRemovedAt(array, location));
                        }
                    }
                } else {
                    other$iv2 = Intrinsics.compare(offset, 0);
                    if (other$iv2 < 0) {
                        return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, SnapshotId_jvmKt.withIdRemovedAt(array, location));
                    }
                }
            }
        } else {
            other$iv = Intrinsics.compare(offset, 64);
            if (other$iv >= 0) {
                other$iv3 = Intrinsics.compare(offset, 128);
                if (other$iv3 < 0) {
                    long $this$toInt$iv3 = ((int) offset) - 64;
                    mask = 1 << $this$toInt$iv3;
                    if ((this.upperSet & mask) != 0) {
                        return new SnapshotIdSet(this.upperSet & (~mask), this.lowerSet, this.lowerBound, this.belowBound);
                    }
                } else {
                    other$iv2 = Intrinsics.compare(offset, 0);
                    if (other$iv2 < 0) {
                        return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, SnapshotId_jvmKt.withIdRemovedAt(array, location));
                    }
                }
            } else {
                other$iv2 = Intrinsics.compare(offset, 0);
                if (other$iv2 < 0) {
                    return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, SnapshotId_jvmKt.withIdRemovedAt(array, location));
                }
            }
        }
        return this;
    }

    public final SnapshotIdSet andNot(SnapshotIdSet ids) {
        long j;
        if (ids == EMPTY) {
            return this;
        }
        if (this == EMPTY) {
            return EMPTY;
        }
        if (ids.lowerBound == this.lowerBound && ids.belowBound == this.belowBound) {
            return new SnapshotIdSet((~ids.upperSet) & this.upperSet, (~ids.lowerSet) & this.lowerSet, this.lowerBound, this.belowBound);
        }
        SnapshotIdSet initial$iv = this;
        SnapshotIdSet accumulator$iv = initial$iv;
        long[] $this$forEach$iv$iv$iv = ids.belowBound;
        if ($this$forEach$iv$iv$iv != null) {
            int length = $this$forEach$iv$iv$iv.length;
            int i = 0;
            while (i < length) {
                long value$iv$iv$iv = $this$forEach$iv$iv$iv[i];
                SnapshotIdSet previous = accumulator$iv;
                accumulator$iv = previous.clear(value$iv$iv$iv);
                i++;
                initial$iv = initial$iv;
            }
        }
        long j2 = 1;
        if (ids.lowerSet != 0) {
            int index$iv$iv = 0;
            while (index$iv$iv < 64) {
                if ((ids.lowerSet & (j2 << index$iv$iv)) != 0) {
                    long $this$plus$iv$iv$iv = ids.lowerBound;
                    int other$iv$iv$iv = index$iv$iv;
                    long element$iv = $this$plus$iv$iv$iv + ((long) other$iv$iv$iv);
                    SnapshotIdSet previous2 = accumulator$iv;
                    accumulator$iv = previous2.clear(element$iv);
                }
                index$iv$iv++;
                j2 = j2;
            }
            j = j2;
        } else {
            j = 1;
        }
        if (ids.upperSet != 0) {
            for (int index$iv$iv2 = 0; index$iv$iv2 < 64; index$iv$iv2++) {
                if ((ids.upperSet & (j << index$iv$iv2)) != 0) {
                    long $this$plus$iv$iv$iv2 = ids.lowerBound;
                    int other$iv$iv$iv2 = index$iv$iv2;
                    long element$iv2 = $this$plus$iv$iv$iv2 + ((long) other$iv$iv$iv2) + ((long) 64);
                    SnapshotIdSet previous3 = accumulator$iv;
                    accumulator$iv = previous3.clear(element$iv2);
                }
            }
        }
        return accumulator$iv;
    }

    public final SnapshotIdSet and(SnapshotIdSet ids) {
        long j;
        if (!Intrinsics.areEqual(ids, EMPTY) && !Intrinsics.areEqual(this, EMPTY)) {
            if (ids.lowerBound == this.lowerBound && ids.belowBound == this.belowBound) {
                long newUpper = this.upperSet & ids.upperSet;
                long newLower = this.lowerSet & ids.lowerSet;
                if (newUpper == 0 && newLower == 0 && this.belowBound == null) {
                    return EMPTY;
                }
                return new SnapshotIdSet(ids.upperSet & this.upperSet, ids.lowerSet & this.lowerSet, this.lowerBound, this.belowBound);
            }
            int i = 0;
            if (this.belowBound == null) {
                SnapshotIdSet previous = EMPTY;
                long[] $this$forEach$iv$iv$iv = this.belowBound;
                if ($this$forEach$iv$iv$iv != null) {
                    int length = $this$forEach$iv$iv$iv.length;
                    while (i < length) {
                        long value$iv$iv$iv = $this$forEach$iv$iv$iv[i];
                        SnapshotIdSet previous2 = previous;
                        if (ids.get(value$iv$iv$iv)) {
                            previous2 = previous2.set(value$iv$iv$iv);
                        }
                        previous = previous2;
                        i++;
                    }
                    j = 1;
                } else {
                    j = 1;
                }
                if (this.lowerSet != r3) {
                    for (int index$iv$iv = 0; index$iv$iv < 64; index$iv$iv++) {
                        if ((this.lowerSet & (j << index$iv$iv)) != 0) {
                            long $this$plus$iv$iv$iv = this.lowerBound;
                            int other$iv$iv$iv = index$iv$iv;
                            long element$iv = $this$plus$iv$iv$iv + ((long) other$iv$iv$iv);
                            SnapshotIdSet previous3 = previous;
                            if (ids.get(element$iv)) {
                                previous3 = previous3.set(element$iv);
                            }
                            previous = previous3;
                        }
                    }
                }
                if (this.upperSet != r3) {
                    for (int index$iv$iv2 = 0; index$iv$iv2 < 64; index$iv$iv2++) {
                        if ((this.upperSet & (j << index$iv$iv2)) != r3) {
                            long $this$plus$iv$iv$iv2 = this.lowerBound;
                            int other$iv$iv$iv2 = index$iv$iv2;
                            long element$iv2 = $this$plus$iv$iv$iv2 + ((long) other$iv$iv$iv2) + ((long) 64);
                            SnapshotIdSet previous4 = previous;
                            if (ids.get(element$iv2)) {
                                previous4 = previous4.set(element$iv2);
                            }
                            previous = previous4;
                        }
                    }
                }
                return previous;
            }
            SnapshotIdSet initial$iv = EMPTY;
            SnapshotIdSet this_$iv = ids;
            SnapshotIdSet previous5 = initial$iv;
            long[] $this$forEach$iv$iv$iv2 = this_$iv.belowBound;
            if ($this$forEach$iv$iv$iv2 != null) {
                int length2 = $this$forEach$iv$iv$iv2.length;
                while (i < length2) {
                    long value$iv$iv$iv2 = $this$forEach$iv$iv$iv2[i];
                    SnapshotIdSet previous6 = previous5;
                    SnapshotIdSet initial$iv2 = initial$iv;
                    if (get(value$iv$iv$iv2)) {
                        previous6 = previous6.set(value$iv$iv$iv2);
                    }
                    previous5 = previous6;
                    i++;
                    this_$iv = this_$iv;
                    initial$iv = initial$iv2;
                }
            }
            if (this_$iv.lowerSet != 0) {
                for (int index$iv$iv3 = 0; index$iv$iv3 < 64; index$iv$iv3++) {
                    if ((this_$iv.lowerSet & (1 << index$iv$iv3)) != 0) {
                        long $this$plus$iv$iv$iv3 = this_$iv.lowerBound;
                        int other$iv$iv$iv3 = index$iv$iv3;
                        long element$iv3 = $this$plus$iv$iv$iv3 + ((long) other$iv$iv$iv3);
                        SnapshotIdSet previous7 = previous5;
                        if (get(element$iv3)) {
                            previous7 = previous7.set(element$iv3);
                        }
                        previous5 = previous7;
                    }
                }
            }
            if (this_$iv.upperSet == 0) {
                return previous5;
            }
            for (int index$iv$iv4 = 0; index$iv$iv4 < 64; index$iv$iv4++) {
                if ((this_$iv.upperSet & (1 << index$iv$iv4)) != 0) {
                    long $this$plus$iv$iv$iv4 = this_$iv.lowerBound;
                    int other$iv$iv$iv4 = index$iv$iv4;
                    long element$iv4 = $this$plus$iv$iv$iv4 + ((long) other$iv$iv$iv4) + ((long) 64);
                    SnapshotIdSet previous8 = previous5;
                    if (get(element$iv4)) {
                        previous8 = previous8.set(element$iv4);
                    }
                    previous5 = previous8;
                }
            }
            return previous5;
        }
        return EMPTY;
    }

    public final SnapshotIdSet or(SnapshotIdSet bits) {
        SnapshotIdSet this_$iv$iv;
        long j;
        long j2;
        if (bits == EMPTY) {
            return this;
        }
        if (this == EMPTY) {
            return bits;
        }
        if (bits.lowerBound == this.lowerBound && bits.belowBound == this.belowBound) {
            return new SnapshotIdSet(bits.upperSet | this.upperSet, bits.lowerSet | this.lowerSet, this.lowerBound, this.belowBound);
        }
        if (this.belowBound != null) {
            Object accumulator$iv = this;
            SnapshotIdSet this_$iv$iv2 = bits;
            long[] $this$forEach$iv$iv$iv = this_$iv$iv2.belowBound;
            if ($this$forEach$iv$iv$iv != null) {
                int length = $this$forEach$iv$iv$iv.length;
                Object accumulator$iv2 = accumulator$iv;
                int i = 0;
                while (i < length) {
                    long value$iv$iv$iv = $this$forEach$iv$iv$iv[i];
                    SnapshotIdSet previous = (SnapshotIdSet) accumulator$iv2;
                    accumulator$iv2 = previous.set(value$iv$iv$iv);
                    i++;
                    this_$iv$iv2 = this_$iv$iv2;
                }
                this_$iv$iv = this_$iv$iv2;
                accumulator$iv = accumulator$iv2;
            } else {
                this_$iv$iv = this_$iv$iv2;
            }
            if (this_$iv$iv.lowerSet != 0) {
                for (int index$iv$iv = 0; index$iv$iv < 64; index$iv$iv++) {
                    if ((this_$iv$iv.lowerSet & (1 << index$iv$iv)) != 0) {
                        long $this$plus$iv$iv$iv = this_$iv$iv.lowerBound;
                        int other$iv$iv$iv = index$iv$iv;
                        long element$iv = $this$plus$iv$iv$iv + ((long) other$iv$iv$iv);
                        SnapshotIdSet previous2 = (SnapshotIdSet) accumulator$iv;
                        accumulator$iv = previous2.set(element$iv);
                    }
                }
            }
            if (this_$iv$iv.upperSet != 0) {
                for (int index$iv$iv2 = 0; index$iv$iv2 < 64; index$iv$iv2++) {
                    if ((this_$iv$iv.upperSet & (1 << index$iv$iv2)) != 0) {
                        long $this$plus$iv$iv$iv2 = this_$iv$iv.lowerBound;
                        int other$iv$iv$iv2 = index$iv$iv2;
                        long element$iv2 = $this$plus$iv$iv$iv2 + ((long) other$iv$iv$iv2) + ((long) 64);
                        SnapshotIdSet previous3 = (SnapshotIdSet) accumulator$iv;
                        accumulator$iv = previous3.set(element$iv2);
                    }
                }
            }
            return (SnapshotIdSet) accumulator$iv;
        }
        SnapshotIdSet snapshotIdSet = bits;
        long[] $this$forEach$iv$iv$iv2 = this.belowBound;
        if ($this$forEach$iv$iv$iv2 != null) {
            SnapshotIdSet snapshotIdSet2 = snapshotIdSet;
            for (long value$iv$iv$iv2 : $this$forEach$iv$iv$iv2) {
                SnapshotIdSet previous4 = snapshotIdSet2;
                snapshotIdSet2 = previous4.set(value$iv$iv$iv2);
            }
            j = 0;
            j2 = 1;
            snapshotIdSet = snapshotIdSet2;
        } else {
            j = 0;
            j2 = 1;
        }
        if (this.lowerSet != j) {
            for (int index$iv$iv3 = 0; index$iv$iv3 < 64; index$iv$iv3++) {
                if ((this.lowerSet & (j2 << index$iv$iv3)) != j) {
                    long $this$plus$iv$iv$iv3 = this.lowerBound;
                    int other$iv$iv$iv3 = index$iv$iv3;
                    long element$iv3 = $this$plus$iv$iv$iv3 + ((long) other$iv$iv$iv3);
                    SnapshotIdSet previous5 = snapshotIdSet;
                    snapshotIdSet = previous5.set(element$iv3);
                }
            }
        }
        if (this.upperSet == j) {
            return snapshotIdSet;
        }
        for (int index$iv$iv4 = 0; index$iv$iv4 < 64; index$iv$iv4++) {
            if ((this.upperSet & (j2 << index$iv$iv4)) != j) {
                long $this$plus$iv$iv$iv4 = this.lowerBound;
                int other$iv$iv$iv4 = index$iv$iv4;
                long element$iv4 = $this$plus$iv$iv$iv4 + ((long) other$iv$iv$iv4) + ((long) 64);
                SnapshotIdSet previous6 = snapshotIdSet;
                snapshotIdSet = previous6.set(element$iv4);
            }
        }
        return snapshotIdSet;
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotIdSet.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "", "Landroidx/compose/runtime/snapshots/SnapshotId;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1", f = "SnapshotIdSet.kt", i = {0, 0, 1, 1, 2, 2}, l = {252, 256, 263}, m = "invokeSuspend", n = {"$this$sequence", "$this$forEach$iv", "$this$sequence", "index", "$this$sequence", "index"}, s = {"L$0", "L$1", "L$0", "I$0", "L$0", "I$0"}, v = 1)
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Long>, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = SnapshotIdSet.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super Long> sequenceScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:13:0x006f  */
        /* JADX WARN: Code duplicated, block: B:15:0x008a A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:16:0x008b  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x008b -> B:17:0x008e). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00d8 -> B:31:0x00dd). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00dc -> B:31:0x00dd). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0102 -> B:44:0x012b). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0127 -> B:44:0x012b). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r23) {
            /*
                Method dump skipped, instruction units count: 318
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotIdSet.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Long> iterator() {
        return SequencesKt.sequence(new AnonymousClass1(null)).iterator();
    }

    private final SnapshotIdSet fastFold(SnapshotIdSet initial, Function2<? super SnapshotIdSet, ? super Long, SnapshotIdSet> operation) {
        long j;
        Object accumulator = initial;
        long[] $this$forEach$iv$iv = this.belowBound;
        if ($this$forEach$iv$iv != null) {
            for (long value$iv$iv : $this$forEach$iv$iv) {
                accumulator = operation.invoke(accumulator, Long.valueOf(value$iv$iv));
            }
        }
        long j2 = 0;
        if (this.lowerSet != 0) {
            int index$iv = 0;
            while (index$iv < 64) {
                if ((this.lowerSet & (1 << index$iv)) != j2) {
                    long $this$plus$iv$iv = this.lowerBound;
                    int other$iv$iv = index$iv;
                    long element = $this$plus$iv$iv + ((long) other$iv$iv);
                    accumulator = operation.invoke(accumulator, Long.valueOf(element));
                }
                index$iv++;
                j2 = j2;
            }
            j = j2;
        } else {
            j = 0;
        }
        if (this.upperSet != j) {
            for (int index$iv2 = 0; index$iv2 < 64; index$iv2++) {
                if ((this.upperSet & (1 << index$iv2)) != j) {
                    long $this$plus$iv$iv2 = this.lowerBound;
                    int other$iv$iv2 = index$iv2;
                    long element2 = $this$plus$iv$iv2 + ((long) other$iv$iv2) + ((long) 64);
                    accumulator = operation.invoke(accumulator, Long.valueOf(element2));
                }
            }
        }
        return (SnapshotIdSet) accumulator;
    }

    public final void fastForEach(Function1<? super Long, Unit> block) {
        long[] $this$forEach$iv = this.belowBound;
        if ($this$forEach$iv != null) {
            for (long value$iv : $this$forEach$iv) {
                block.invoke(Long.valueOf(value$iv));
            }
        }
        if (this.lowerSet != 0) {
            for (int index = 0; index < 64; index++) {
                if ((this.lowerSet & (1 << index)) != 0) {
                    long $this$plus$iv = this.lowerBound;
                    int other$iv = index;
                    block.invoke(Long.valueOf($this$plus$iv + ((long) other$iv)));
                }
            }
        }
        if (this.upperSet != 0) {
            for (int index2 = 0; index2 < 64; index2++) {
                if ((this.upperSet & (1 << index2)) != 0) {
                    long $this$plus$iv2 = this.lowerBound;
                    int other$iv2 = index2;
                    block.invoke(Long.valueOf($this$plus$iv2 + ((long) other$iv2) + ((long) 64)));
                }
            }
        }
    }

    public final long lowest(long j) {
        long[] belowBound = this.belowBound;
        if (belowBound != null) {
            return belowBound[0];
        }
        if (this.lowerSet != 0) {
            long $this$plus$iv = this.lowerBound;
            int other$iv = Long.numberOfTrailingZeros(this.lowerSet);
            return $this$plus$iv + ((long) other$iv);
        }
        if (this.upperSet != 0) {
            long $this$plus$iv2 = this.lowerBound;
            int other$iv2 = Long.numberOfTrailingZeros(this.upperSet);
            return $this$plus$iv2 + ((long) 64) + ((long) other$iv2);
        }
        return j;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append(super.toString()).append(" [");
        SnapshotIdSet $this$map$iv = this;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            long it = ((Number) item$iv$iv).longValue();
            destination$iv$iv.add(String.valueOf(it));
        }
        return sbAppend.append(ListUtilsKt.fastJoinToString$default((List) destination$iv$iv, null, null, null, 0, null, null, 63, null)).append(AbstractJsonLexerKt.END_LIST).toString();
    }

    /* JADX INFO: compiled from: SnapshotIdSet.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdSet$Companion;", "", "<init>", "()V", "EMPTY", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "getEMPTY", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SnapshotIdSet getEMPTY() {
            return SnapshotIdSet.EMPTY;
        }
    }
}
