package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.collection.IntList;
import androidx.collection.IntListKt;
import androidx.collection.MutableIntList;
import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.internal.AccessibilityServiceStateProvider_androidKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.TimeInputTokens;
import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.Ref;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataValidation;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: TimePicker.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a)\u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\r\u001a+\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007¢\u0006\u0002\u0010\u0014\u001a \u0010\u0017\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0007\u001a3\u0010\u001b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0002¢\u0006\u0004\b\"\u0010#\u001aJ\u0010$\u001a\u00020\u0001*\u00020%2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001d0(H\u0082@¢\u0006\u0004\b)\u0010*\u001a1\u0010/\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020%2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0013H\u0001¢\u0006\u0002\u00100\u001a1\u00101\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020%2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0013H\u0001¢\u0006\u0002\u00100\u001a%\u00102\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u00103\u001a\u001d\u00104\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00105\u001a\u001d\u00106\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00105\u001a\u001d\u00107\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00105\u001a%\u00108\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00109\u001a%\u0010:\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u00109\u001a=\u0010;\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?H\u0003¢\u0006\u0002\u0010A\u001aQ\u0010B\u001a\u00020\u00012\u0006\u0010C\u001a\u00020\u00132\u0006\u0010D\u001a\u00020?2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00010F2\u0006\u0010\u0006\u001a\u00020\u00072\u001c\u0010G\u001a\u0018\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020\u00010H¢\u0006\u0002\bJ¢\u0006\u0002\bKH\u0003¢\u0006\u0002\u0010L\u001a\u0015\u0010M\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010N\u001a7\u0010O\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0004\bS\u0010T\u001a-\u0010U\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020%2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0013H\u0001¢\u0006\u0002\u0010V\u001a\u001c\u0010W\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020%2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a-\u0010X\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020%2\u0006\u0010P\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u0013H\u0003¢\u0006\u0002\u0010Y\u001ah\u0010Z\u001a\u00020\u00012\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010P\u001a\u00020[2\u0006\u0010\\\u001a\u00020[2\u0006\u0010]\u001a\u00020\u00102\f\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00130_2!\u0010`\u001a\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\ba\u0012\b\bb\u0012\u0004\b\b(P\u0012\u0004\u0012\u00020\u00010HH\u0002¢\u0006\u0004\bc\u0010d\u001a_\u0010e\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010P\u001a\u00020[2\u0012\u0010f\u001a\u000e\u0012\u0004\u0012\u00020[\u0012\u0004\u0012\u00020\u00010H2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010Q\u001a\u00020R2\b\b\u0002\u0010g\u001a\u00020h2\b\b\u0002\u0010i\u001a\u00020j2\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0004\bk\u0010l\u001a4\u0010m\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010n\u001a\u00020\u001d2\u0011\u0010G\u001a\r\u0012\u0004\u0012\u00020\u00010F¢\u0006\u0002\bJH\u0003¢\u0006\u0002\u0010o\u001a'\u0010p\u001a\u00020q2\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010r\u001a\u00020\u0010H\u0001¢\u0006\u0004\bs\u0010t\u001a(\u0010u\u001a\u00020\u001d2\u0006\u0010v\u001a\u00020\u001d2\u0006\u0010w\u001a\u00020\u001d2\u0006\u0010x\u001a\u00020\u00102\u0006\u0010y\u001a\u00020\u0010H\u0002\u001a\u0018\u0010z\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002\u001a\u0016\u0010\u009d\u0001\u001a\u00020\u0005*\u00020\u00052\u0007\u0010\u009d\u0001\u001a\u00020\u0013H\u0003\"\u0015\u0010\u0015\u001a\u00020\u0013*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\"\u0018\u0010\u0018\u001a\u00020\u0010*\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\"\u0018\u0010+\u001a\u00020,*\u00020%8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.\"\u001a\u0010{\u001a\u00020\t8AX\u0080\u0004¢\u0006\f\u0012\u0004\b|\u0010}\u001a\u0004\b~\u0010\u007f\"\u000f\u0010\u0080\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0081\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0082\u0001\u001a\u00030\u0083\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0084\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0085\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0086\u0001\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0087\u0001\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000f\u0010\u0088\u0001\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008c\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008d\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008e\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u008f\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0090\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0091\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0010\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0094\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0095\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0096\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0097\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0098\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u0013\u0010\u0099\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u008b\u0001\"\u001b\u0010\u009a\u0001\u001a\u00030\u008a\u0001X\u0080\u0004¢\u0006\r\n\u0003\u0010\u008b\u0001\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001¨\u0006\u009e\u0001²\u0006\u000b\u0010\u009f\u0001\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\u000b\u0010 \u0001\u001a\u00020[X\u008a\u008e\u0002²\u0006\u000b\u0010¡\u0001\u001a\u00020[X\u008a\u008e\u0002²\u0006\u000b\u0010 \u001a\u00030¢\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010£\u0001\u001a\u00020!X\u008a\u008e\u0002²\u0006\f\u0010¤\u0001\u001a\u00030¥\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010¦\u0001\u001a\u00020\u0013X\u008a\u0084\u0002"}, d2 = {"TimePicker", "", "state", "Landroidx/compose/material3/TimePickerState;", "modifier", "Landroidx/compose/ui/Modifier;", "colors", "Landroidx/compose/material3/TimePickerColors;", "layoutType", "Landroidx/compose/material3/TimePickerLayoutType;", "TimePicker-mT9BvqQ", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ILandroidx/compose/runtime/Composer;II)V", "TimeInput", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "rememberTimePickerState", "initialHour", "", "initialMinute", "is24Hour", "", "(IIZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TimePickerState;", "isPm", "(Landroidx/compose/material3/TimePickerState;)Z", "TimePickerState", "hourForDisplay", "getHourForDisplay", "(Landroidx/compose/material3/TimePickerState;)I", "moveSelector", "x", "", "y", "maxDist", TtmlNode.CENTER, "Landroidx/compose/ui/unit/IntOffset;", "moveSelector-d3b8Pxo", "(Landroidx/compose/material3/TimePickerState;FFFJ)V", "onTap", "Landroidx/compose/material3/AnalogTimePickerState;", "autoSwitchToMinute", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "onTap-uYHVD98", "(Landroidx/compose/material3/AnalogTimePickerState;FFFZJLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectorPos", "Landroidx/compose/ui/unit/DpOffset;", "getSelectorPos", "(Landroidx/compose/material3/AnalogTimePickerState;)J", "VerticalTimePicker", "(Landroidx/compose/material3/AnalogTimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;II)V", "HorizontalTimePicker", "TimeInputImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/material3/TimePickerState;Landroidx/compose/runtime/Composer;I)V", "HorizontalClockDisplay", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "VerticalClockDisplay", "ClockDisplayNumbers", "HorizontalPeriodToggle", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "VerticalPeriodToggle", "PeriodToggleImpl", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "startShape", "Landroidx/compose/ui/graphics/Shape;", "endShape", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "ToggleItem", "checked", "shape", "onClick", "Lkotlin/Function0;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/TimePickerColors;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "DisplaySeparator", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "TimeSelector", "value", "selection", "Landroidx/compose/material3/TimePickerSelectionMode;", "TimeSelector-SAnMeKU", "(Landroidx/compose/ui/Modifier;ILandroidx/compose/material3/TimePickerState;ILandroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "ClockFace", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/AnalogTimePickerState;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;I)V", "drawSelector", "ClockText", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/AnalogTimePickerState;IZLandroidx/compose/runtime/Composer;I)V", "timeInputOnChange", "Landroidx/compose/ui/text/input/TextFieldValue;", "prevValue", MetadataValidation.MAX, "userOverride", "Landroidx/compose/ui/node/Ref;", "onNewValue", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "timeInputOnChange-_K77t-0", "(ILandroidx/compose/material3/TimePickerState;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/TextFieldValue;ILandroidx/compose/ui/node/Ref;Lkotlin/jvm/functions/Function1;)V", "TimePickerTextField", "onValueChange", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "TimePickerTextField-1vLObsk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/TimePickerState;ILandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "CircularLayout", "radiusToSizeRatio", "(Landroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "numberContentDescription", "", "number", "numberContentDescription-dSwYdS4", "(IZILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "dist", "x1", "y1", "x2", "y2", "atan", "defaultTimePickerLayoutType", "getDefaultTimePickerLayoutType$annotations", "()V", "getDefaultTimePickerLayoutType", "(Landroidx/compose/runtime/Composer;I)I", "FullCircle", "HalfCircle", "QuarterCircle", "", "RadiansPerMinute", "RadiansPerHour", "SeparatorZIndex", "OuterCircleToSizeRatio", "InnerCircleToSizeRatio", "ClockDisplayBottomMargin", "Landroidx/compose/ui/unit/Dp;", "F", "ClockFaceBottomMargin", "DisplaySeparatorWidth", "SupportLabelTop", "TimeInputBottomPadding", "MaxDistance", "MinimumInteractiveSize", "Minutes", "Landroidx/collection/IntList;", "Hours", "ExtraHours", "PeriodToggleMargin", "TimePickerMaxHeight", "TimePickerMidHeight", "ClockDialMidContainerSize", "ClockDialMinContainerSize", "getClockDialMinContainerSize", "()F", "visible", "material3", "a11yServicesEnabled", "hourValue", "minuteValue", "Landroidx/compose/ui/geometry/Offset;", "parentCenter", "boundsInParent", "Landroidx/compose/ui/geometry/Rect;", "selected"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TimePickerKt {
    private static final float ClockDialMidContainerSize;
    private static final float ClockDialMinContainerSize;
    private static final float ClockDisplayBottomMargin;
    private static final float ClockFaceBottomMargin;
    private static final float DisplaySeparatorWidth;
    private static final IntList ExtraHours;
    private static final float FullCircle = 6.2831855f;
    private static final float HalfCircle = 3.1415927f;
    private static final IntList Hours;
    private static final float InnerCircleToSizeRatio;
    private static final float MaxDistance;
    private static final float MinimumInteractiveSize;
    private static final IntList Minutes;
    private static final float OuterCircleToSizeRatio;
    private static final float PeriodToggleMargin;
    private static final double QuarterCircle = 1.5707963267948966d;
    private static final float RadiansPerHour = 0.5235988f;
    private static final float RadiansPerMinute = 0.10471976f;
    private static final float SeparatorZIndex = 2.0f;
    private static final float SupportLabelTop;
    private static final float TimeInputBottomPadding;
    private static final float TimePickerMaxHeight;
    private static final float TimePickerMidHeight;

    static final Unit CircularLayout$lambda$96(Modifier modifier, float f, Function2 function2, int i, int i2, Composer composer, int i3) {
        CircularLayout(modifier, f, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ClockDisplayNumbers$lambda$35(TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        ClockDisplayNumbers(timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ClockFace$lambda$60(Modifier modifier, AnalogTimePickerState analogTimePickerState, TimePickerColors timePickerColors, boolean z, int i, Composer composer, int i2) {
        ClockFace(modifier, analogTimePickerState, timePickerColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ClockText$lambda$84(Modifier modifier, AnalogTimePickerState analogTimePickerState, int i, boolean z, int i2, Composer composer, int i3) {
        ClockText(modifier, analogTimePickerState, i, z, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    static final Unit DisplaySeparator$lambda$54(Modifier modifier, int i, Composer composer, int i2) {
        DisplaySeparator(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit HorizontalClockDisplay$lambda$31(TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        HorizontalClockDisplay(timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit HorizontalPeriodToggle$lambda$37(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        HorizontalPeriodToggle(modifier, timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit HorizontalTimePicker$lambda$15(AnalogTimePickerState analogTimePickerState, Modifier modifier, TimePickerColors timePickerColors, boolean z, int i, int i2, Composer composer, int i3) {
        HorizontalTimePicker(analogTimePickerState, modifier, timePickerColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit PeriodToggleImpl$lambda$47(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, MeasurePolicy measurePolicy, Shape shape, Shape shape2, int i, Composer composer, int i2) {
        PeriodToggleImpl(modifier, timePickerState, timePickerColors, measurePolicy, shape, shape2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TimeInput$lambda$5(TimePickerState timePickerState, Modifier modifier, TimePickerColors timePickerColors, int i, int i2, Composer composer, int i3) {
        TimeInput(timePickerState, modifier, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TimeInputImpl$lambda$28(Modifier modifier, TimePickerColors timePickerColors, TimePickerState timePickerState, int i, Composer composer, int i2) {
        TimeInputImpl(modifier, timePickerColors, timePickerState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TimePickerTextField_1vLObsk$lambda$94(Modifier modifier, TextFieldValue textFieldValue, Function1 function1, TimePickerState timePickerState, int i, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, TimePickerColors timePickerColors, int i2, int i3, Composer composer, int i4) {
        m3113TimePickerTextField1vLObsk(modifier, textFieldValue, function1, timePickerState, i, keyboardOptions, keyboardActions, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit TimePicker_mT9BvqQ$lambda$4(TimePickerState timePickerState, Modifier modifier, TimePickerColors timePickerColors, int i, int i2, int i3, Composer composer, int i4) {
        m3112TimePickermT9BvqQ(timePickerState, modifier, timePickerColors, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit TimeSelector_SAnMeKU$lambda$59(Modifier modifier, int i, TimePickerState timePickerState, int i2, TimePickerColors timePickerColors, int i3, Composer composer, int i4) {
        m3114TimeSelectorSAnMeKU(modifier, i, timePickerState, i2, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1));
        return Unit.INSTANCE;
    }

    static final Unit ToggleItem$lambda$50(boolean z, Shape shape, Function0 function0, TimePickerColors timePickerColors, Function3 function3, int i, Composer composer, int i2) {
        ToggleItem(z, shape, function0, timePickerColors, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit VerticalClockDisplay$lambda$34(TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        VerticalClockDisplay(timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit VerticalPeriodToggle$lambda$39(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, int i, Composer composer, int i2) {
        VerticalPeriodToggle(modifier, timePickerState, timePickerColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit VerticalTimePicker$lambda$11(AnalogTimePickerState analogTimePickerState, Modifier modifier, TimePickerColors timePickerColors, boolean z, int i, int i2, Composer composer, int i3) {
        VerticalTimePicker(analogTimePickerState, modifier, timePickerColors, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void getDefaultTimePickerLayoutType$annotations() {
    }

    /* JADX WARN: Code duplicated, block: B:114:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:115:0x020a  */
    /* JADX WARN: Code duplicated, block: B:118:0x0237  */
    /* JADX INFO: renamed from: TimePicker-mT9BvqQ, reason: not valid java name */
    public static final void m3112TimePickermT9BvqQ(final TimePickerState state, Modifier modifier, TimePickerColors colors, int layoutType, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors timePickerColors;
        int i2;
        Composer $composer2;
        final Modifier modifier3;
        final TimePickerColors colors2;
        final int layoutType2;
        Modifier.Companion modifier4;
        TimePickerColors colors3;
        int $dirty;
        TimePickerColors timePickerColors2;
        int layoutType3;
        State<Boolean> state2;
        TimePickerColors colors4;
        Modifier modifier5;
        Composer $composer3 = $composer.startRestartGroup(-619286452);
        ComposerKt.sourceInformation($composer3, "C(TimePicker)N(state,modifier,colors,layoutType:c#material3.TimePickerLayoutType)225@11775L35,226@11834L27,228@11885L62,230@11994L181,230@11953L222:TimePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= ($changed & 8) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                timePickerColors = colors;
                int i4 = $composer3.changed(timePickerColors) ? 256 : 128;
                $dirty2 |= i4;
            } else {
                timePickerColors = colors;
            }
            $dirty2 |= i4;
        } else {
            timePickerColors = colors;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                i2 = layoutType;
                int i5 = $composer3.changed(i2) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                i2 = layoutType;
            }
            $dirty2 |= i5;
        } else {
            i2 = layoutType;
        }
        if ($composer3.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "222@11658L8,223@11726L12");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                $dirty = $dirty2;
                modifier4 = modifier2;
                timePickerColors2 = timePickerColors;
                layoutType3 = i2;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    colors3 = timePickerColors;
                } else {
                    colors3 = TimePickerDefaults.INSTANCE.colors($composer3, 6);
                    $dirty2 &= -897;
                }
                if ((i & 8) == 0) {
                    $dirty = $dirty2;
                    timePickerColors2 = colors3;
                    layoutType3 = i2;
                } else {
                    $dirty = $dirty2 & (-7169);
                    timePickerColors2 = colors3;
                    layoutType3 = TimePickerDefaults.INSTANCE.m3097layoutTypesDNSZnc($composer3, 6);
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-619286452, $dirty, -1, "androidx.compose.material3.TimePicker (TimePicker.kt:224)");
            }
            State<Boolean> stateRememberAccessibilityServiceState = AccessibilityServiceStateProvider_androidKt.rememberAccessibilityServiceState(false, false, false, $composer3, 0, 7);
            ComposerKt.sourceInformationMarkerStart($composer3, 1866184775, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Ref();
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            Ref userOverride = (Ref) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1866186442, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4 || (($dirty & 8) != 0 && $composer3.changed(state));
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new AnalogTimePickerState(state, userOverride);
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            AnalogTimePickerState analogState = (AnalogTimePickerState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Integer numValueOf = Integer.valueOf(state.getHour());
            Integer numValueOf2 = Integer.valueOf(state.getMinute());
            ComposerKt.sourceInformationMarkerStart($composer3, 1866190049, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changedInstance(userOverride) | $composer3.changedInstance(analogState) | (($dirty & 14) == 4 || (($dirty & 8) != 0 && $composer3.changedInstance(state)));
            Object it$iv3 = $composer3.rememberedValue();
            if (!invalid$iv2) {
                state2 = stateRememberAccessibilityServiceState;
                if (it$iv3 != Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(numValueOf, numValueOf2, (Function2) it$iv3, $composer3, 0);
                if (TimePickerLayoutType.m3129equalsimpl0(layoutType3, TimePickerLayoutType.INSTANCE.m3134getVerticalQJTpgSE())) {
                    $composer3.startReplaceGroup(2017551219);
                    ComposerKt.sourceInformation($composer3, "239@12240L179");
                    colors4 = timePickerColors2;
                    modifier5 = modifier4;
                    VerticalTimePicker(analogState, modifier5, colors4, !TimePicker_mT9BvqQ$lambda$0(state2), $composer3, ($dirty & 112) | ($dirty & 896), 0);
                    $composer2 = $composer3;
                    $composer2.endReplaceGroup();
                } else {
                    colors4 = timePickerColors2;
                    modifier5 = modifier4;
                    $composer3.startReplaceGroup(2017750673);
                    ComposerKt.sourceInformation($composer3, "246@12441L181");
                    HorizontalTimePicker(analogState, modifier5, colors4, !TimePicker_mT9BvqQ$lambda$0(state2), $composer3, ($dirty & 112) | ($dirty & 896), 0);
                    $composer2 = $composer3;
                    $composer2.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                colors2 = colors4;
                layoutType2 = layoutType3;
            } else {
                state2 = stateRememberAccessibilityServiceState;
            }
            Object value$iv3 = (Function2) new TimePickerKt$TimePicker$1$1(userOverride, analogState, state, null);
            $composer3.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(numValueOf, numValueOf2, (Function2) it$iv3, $composer3, 0);
            if (TimePickerLayoutType.m3129equalsimpl0(layoutType3, TimePickerLayoutType.INSTANCE.m3134getVerticalQJTpgSE())) {
                $composer3.startReplaceGroup(2017551219);
                ComposerKt.sourceInformation($composer3, "239@12240L179");
                colors4 = timePickerColors2;
                modifier5 = modifier4;
                VerticalTimePicker(analogState, modifier5, colors4, !TimePicker_mT9BvqQ$lambda$0(state2), $composer3, ($dirty & 112) | ($dirty & 896), 0);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            } else {
                colors4 = timePickerColors2;
                modifier5 = modifier4;
                $composer3.startReplaceGroup(2017750673);
                ComposerKt.sourceInformation($composer3, "246@12441L181");
                HorizontalTimePicker(analogState, modifier5, colors4, !TimePicker_mT9BvqQ$lambda$0(state2), $composer3, ($dirty & 112) | ($dirty & 896), 0);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            colors2 = colors4;
            layoutType2 = layoutType3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors2 = timePickerColors;
            layoutType2 = i2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.TimePicker_mT9BvqQ$lambda$4(state, modifier3, colors2, layoutType2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean TimePicker_mT9BvqQ$lambda$0(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    public static final void TimeInput(final TimePickerState state, Modifier modifier, TimePickerColors colors, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        final TimePickerColors colors2;
        Composer $composer2 = $composer.startRestartGroup(-760850373);
        ComposerKt.sourceInformation($composer2, "C(TimeInput)N(state,modifier,colors)275@13528L38:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= ((i & 4) == 0 && $composer2.changed(colors)) ? 256 : 128;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "273@13510L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
            } else {
                if (i2 != 0) {
                    modifier = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    colors = TimePickerDefaults.INSTANCE.colors($composer2, 6);
                    $dirty &= -897;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-760850373, $dirty, -1, "androidx.compose.material3.TimeInput (TimePicker.kt:274)");
            }
            TimeInputImpl(modifier, colors, state, $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty << 6) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier;
            colors2 = colors;
        } else {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.TimeInput$lambda$5(state, modifier2, colors2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final TimePickerState rememberTimePickerState(final int initialHour, final int initialMinute, final boolean is24Hour, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1237715277, "C(rememberTimePickerState)N(initialHour,initialMinute,is24Hour)586@29390L14,589@29526L185,589@29472L239:TimePicker.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialHour = 0;
        }
        if ((i & 2) != 0) {
            initialMinute = 0;
        }
        if ((i & 4) != 0) {
            is24Hour = TimeFormat_androidKt.is24HourFormat($composer, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1237715277, $changed, -1, "androidx.compose.material3.rememberTimePickerState (TimePicker.kt:587)");
        }
        Object[] objArr = new Object[0];
        Saver<TimePickerStateImpl, ?> Saver = TimePickerStateImpl.INSTANCE.Saver();
        ComposerKt.sourceInformationMarkerStart($composer, -261550618, "CC(remember):TimePicker.kt#9igjgp");
        boolean z = true;
        boolean z2 = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialHour)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialMinute)) || ($changed & 48) == 32);
        if (((($changed & 896) ^ 384) <= 256 || !$composer.changed(is24Hour)) && ($changed & 384) != 256) {
            z = false;
        }
        boolean invalid$iv = z2 | z;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return TimePickerKt.rememberTimePickerState$lambda$7$lambda$6(initialHour, initialMinute, is24Hour);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        TimePickerStateImpl state = (TimePickerStateImpl) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return state;
    }

    static final TimePickerStateImpl rememberTimePickerState$lambda$7$lambda$6(int $initialHour, int $initialMinute, boolean $is24Hour) {
        return new TimePickerStateImpl($initialHour, $initialMinute, $is24Hour);
    }

    public static final boolean isPm(TimePickerState $this$isPm) {
        return $this$isPm.getHour() >= 12;
    }

    public static final TimePickerState TimePickerState(int initialHour, int initialMinute, boolean is24Hour) {
        return new TimePickerStateImpl(initialHour, initialMinute, is24Hour);
    }

    public static final int getHourForDisplay(TimePickerState $this$hourForDisplay) {
        if ($this$hourForDisplay.getIs24hour()) {
            return $this$hourForDisplay.getHour() % 24;
        }
        if ($this$hourForDisplay.getHour() % 12 == 0) {
            return 12;
        }
        return isPm($this$hourForDisplay) ? $this$hourForDisplay.getHour() - 12 : $this$hourForDisplay.getHour();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: moveSelector-d3b8Pxo, reason: not valid java name */
    public static final void m3120moveSelectord3b8Pxo(TimePickerState $this$moveSelector_u2dd3b8Pxo, float x, float y, float maxDist, long center) {
        if (TimePickerSelectionMode.m3138equalsimpl0($this$moveSelector_u2dd3b8Pxo.mo2063getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI()) && $this$moveSelector_u2dd3b8Pxo.getIs24hour()) {
            float currentDist = dist(x, y, IntOffset.m8033getXimpl(center), IntOffset.m8034getYimpl(center));
            if (isPm($this$moveSelector_u2dd3b8Pxo)) {
                $this$moveSelector_u2dd3b8Pxo.setHour($this$moveSelector_u2dd3b8Pxo.getHour() - (currentDist < maxDist ? 0 : 12));
            } else {
                $this$moveSelector_u2dd3b8Pxo.setHour($this$moveSelector_u2dd3b8Pxo.getHour() + (currentDist >= maxDist ? 0 : 12));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:27:0x00d8 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:31:0x00df  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX INFO: renamed from: onTap-uYHVD98, reason: not valid java name */
    public static final Object m3122onTapuYHVD98(AnalogTimePickerState $this$onTap_u2duYHVD98, float x, float y, float maxDist, boolean autoSwitchToMinute, long center, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        TimePickerKt$onTap$1 timePickerKt$onTap$1;
        float angle;
        AnalogTimePickerState $this$onTap_u2duYHVD99;
        boolean autoSwitchToMinute2;
        boolean autoSwitchToMinute3;
        AnalogTimePickerState $this$onTap_u2duYHVD910;
        if (continuation instanceof TimePickerKt$onTap$1) {
            timePickerKt$onTap$1 = (TimePickerKt$onTap$1) continuation;
            if ((timePickerKt$onTap$1.label & Integer.MIN_VALUE) != 0) {
                timePickerKt$onTap$1.label -= Integer.MIN_VALUE;
            } else {
                timePickerKt$onTap$1 = new TimePickerKt$onTap$1(continuation);
            }
        } else {
            timePickerKt$onTap$1 = new TimePickerKt$onTap$1(continuation);
        }
        Object $result = timePickerKt$onTap$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (timePickerKt$onTap$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                float angle2 = atan(y - IntOffset.m8034getYimpl(center), x - IntOffset.m8033getXimpl(center));
                if (TimePickerSelectionMode.m3138equalsimpl0($this$onTap_u2duYHVD98.mo2063getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI())) {
                    angle = ((float) Math.rint((angle2 / RadiansPerMinute) / 5.0f)) * 5.0f * RadiansPerMinute;
                } else {
                    angle = ((float) Math.rint(angle2 / RadiansPerHour)) * RadiansPerHour;
                }
                m3120moveSelectord3b8Pxo($this$onTap_u2duYHVD98, x, y, maxDist, center);
                timePickerKt$onTap$1.L$0 = $this$onTap_u2duYHVD98;
                timePickerKt$onTap$1.Z$0 = autoSwitchToMinute;
                timePickerKt$onTap$1.label = 1;
                if ($this$onTap_u2duYHVD98.rotateTo(angle, animationSpec, true, timePickerKt$onTap$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $this$onTap_u2duYHVD99 = $this$onTap_u2duYHVD98;
                autoSwitchToMinute2 = autoSwitchToMinute;
                if (TimePickerSelectionMode.m3138equalsimpl0($this$onTap_u2duYHVD99.mo2063getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI()) && autoSwitchToMinute2) {
                    timePickerKt$onTap$1.L$0 = $this$onTap_u2duYHVD99;
                    timePickerKt$onTap$1.Z$0 = autoSwitchToMinute2;
                    timePickerKt$onTap$1.label = 2;
                    if (DelayKt.delay(100L, timePickerKt$onTap$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    autoSwitchToMinute3 = autoSwitchToMinute2;
                    $this$onTap_u2duYHVD910 = $this$onTap_u2duYHVD99;
                    $this$onTap_u2duYHVD99 = $this$onTap_u2duYHVD910;
                    autoSwitchToMinute2 = autoSwitchToMinute3;
                }
                if (autoSwitchToMinute2) {
                    $this$onTap_u2duYHVD99.mo2065setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI());
                }
                return Unit.INSTANCE;
            case 1:
                autoSwitchToMinute2 = timePickerKt$onTap$1.Z$0;
                $this$onTap_u2duYHVD99 = (AnalogTimePickerState) timePickerKt$onTap$1.L$0;
                ResultKt.throwOnFailure($result);
                if (TimePickerSelectionMode.m3138equalsimpl0($this$onTap_u2duYHVD99.mo2063getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI())) {
                    timePickerKt$onTap$1.L$0 = $this$onTap_u2duYHVD99;
                    timePickerKt$onTap$1.Z$0 = autoSwitchToMinute2;
                    timePickerKt$onTap$1.label = 2;
                    if (DelayKt.delay(100L, timePickerKt$onTap$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    autoSwitchToMinute3 = autoSwitchToMinute2;
                    $this$onTap_u2duYHVD910 = $this$onTap_u2duYHVD99;
                    $this$onTap_u2duYHVD99 = $this$onTap_u2duYHVD910;
                    autoSwitchToMinute2 = autoSwitchToMinute3;
                }
                if (autoSwitchToMinute2) {
                    $this$onTap_u2duYHVD99.mo2065setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI());
                }
                return Unit.INSTANCE;
            case 2:
                autoSwitchToMinute3 = timePickerKt$onTap$1.Z$0;
                $this$onTap_u2duYHVD910 = (AnalogTimePickerState) timePickerKt$onTap$1.L$0;
                ResultKt.throwOnFailure($result);
                $this$onTap_u2duYHVD99 = $this$onTap_u2duYHVD910;
                autoSwitchToMinute2 = autoSwitchToMinute3;
                if (autoSwitchToMinute2) {
                    $this$onTap_u2duYHVD99.mo2065setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI());
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final long getSelectorPos(AnalogTimePickerState $this$selectorPos) {
        float arg0$iv;
        float arg0$iv2 = $this$selectorPos.m2062getCurrentDiameterD9Ej5fM();
        float other$iv = TimePickerTokens.INSTANCE.m4172getClockDialContainerSizeD9Ej5fM();
        float scale = arg0$iv2 / other$iv;
        float arg0$iv3 = TimePickerTokens.INSTANCE.m4174getClockDialSelectorHandleContainerSizeD9Ej5fM();
        float arg0$iv4 = Dp.m7905constructorimpl(Dp.m7905constructorimpl(arg0$iv3 / 2.0f) * scale);
        if ($this$selectorPos.getIs24hour() && isPm($this$selectorPos) && TimePickerSelectionMode.m3138equalsimpl0($this$selectorPos.mo2063getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI())) {
            float arg0$iv5 = $this$selectorPos.m2062getCurrentDiameterD9Ej5fM();
            float other$iv2 = InnerCircleToSizeRatio;
            arg0$iv = Dp.m7905constructorimpl(arg0$iv5 * other$iv2);
        } else {
            float arg0$iv6 = $this$selectorPos.m2062getCurrentDiameterD9Ej5fM();
            float other$iv3 = OuterCircleToSizeRatio;
            arg0$iv = Dp.m7905constructorimpl(arg0$iv6 * other$iv3);
        }
        float selectorLength = ((Dp) RangesKt.coerceAtLeast(Dp.m7903boximpl(Dp.m7905constructorimpl(arg0$iv - arg0$iv4)), Dp.m7903boximpl(Dp.m7905constructorimpl(0)))).m7919unboximpl();
        float length = Dp.m7905constructorimpl(selectorLength + arg0$iv4);
        float other$iv4 = (float) Math.cos($this$selectorPos.getCurrentAngle());
        float other$iv5 = Dp.m7905constructorimpl(length * other$iv4);
        float arg0$iv7 = $this$selectorPos.m2062getCurrentDiameterD9Ej5fM();
        float arg0$iv8 = Dp.m7905constructorimpl(other$iv5 + Dp.m7905constructorimpl(arg0$iv7 / 2));
        float other$iv6 = (float) Math.sin($this$selectorPos.getCurrentAngle());
        float other$iv7 = Dp.m7905constructorimpl(length * other$iv6);
        float arg0$iv9 = $this$selectorPos.m2062getCurrentDiameterD9Ej5fM();
        float arg0$iv10 = Dp.m7905constructorimpl(other$iv7 + Dp.m7905constructorimpl(arg0$iv9 / 2));
        long v1$iv$iv = Float.floatToRawIntBits(arg0$iv8);
        long v2$iv$iv = Float.floatToRawIntBits(arg0$iv10);
        return DpOffset.m7961constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
    }

    public static final void VerticalTimePicker(final AnalogTimePickerState state, Modifier modifier, TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors timePickerColors;
        boolean z;
        final Modifier modifier3;
        final TimePickerColors colors2;
        Modifier.Companion modifier4;
        int $dirty;
        Modifier modifier5;
        TimePickerColors colors3;
        Composer $composer2 = $composer.startRestartGroup(1249591487);
        ComposerKt.sourceInformation($composer2, "C(VerticalTimePicker)N(state,modifier,colors,autoSwitchToMinute)959@41242L27,958@41196L544:TimePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                timePickerColors = colors;
                int i3 = $composer2.changed(timePickerColors) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                timePickerColors = colors;
            }
            $dirty2 |= i3;
        } else {
            timePickerColors = colors;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
            z = autoSwitchToMinute;
        } else if (($changed & 3072) == 0) {
            z = autoSwitchToMinute;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = autoSwitchToMinute;
        }
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "955@41145L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                $dirty = $dirty2;
                modifier5 = modifier2;
                colors3 = timePickerColors;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    $dirty = $dirty2;
                    modifier5 = modifier4;
                    colors3 = timePickerColors;
                } else {
                    $dirty = $dirty2 & (-897);
                    modifier5 = modifier4;
                    colors3 = TimePickerDefaults.INSTANCE.colors($composer2, 6);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1249591487, $dirty, -1, "androidx.compose.material3.VerticalTimePicker (TimePicker.kt:957)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1733414662, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.VerticalTimePicker$lambda$9$lambda$8((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) it$iv, 1, null);
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer2, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1720573003, "C962@41347L52,963@41408L60,964@41477L191,970@41677L57:TimePicker.kt#uh7d8r");
            VerticalClockDisplay(state, colors3, $composer2, ($dirty & 14) | (($dirty >> 3) & 112));
            SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, ClockDisplayBottomMargin), $composer2, 6);
            ClockFace(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4172getClockDialContainerSizeD9Ej5fM()), state, colors3, z, $composer2, (($dirty << 3) & 112) | 6 | ($dirty & 896) | ($dirty & 7168));
            SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, ClockFaceBottomMargin), $composer2, 6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors3;
            modifier3 = modifier5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors2 = timePickerColors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.VerticalTimePicker$lambda$11(state, modifier3, colors2, autoSwitchToMinute, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit VerticalTimePicker$lambda$9$lambda$8(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
        return Unit.INSTANCE;
    }

    public static final void HorizontalTimePicker(final AnalogTimePickerState state, Modifier modifier, TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors timePickerColors;
        boolean z;
        final Modifier modifier3;
        final TimePickerColors colors2;
        Modifier.Companion modifier4;
        int $dirty;
        Modifier modifier5;
        TimePickerColors colors3;
        Composer $composer2 = $composer.startRestartGroup(1432307537);
        ComposerKt.sourceInformation($composer2, "C(HorizontalTimePicker)N(state,modifier,colors,autoSwitchToMinute)982@42004L27,981@41961L418:TimePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                timePickerColors = colors;
                int i3 = $composer2.changed(timePickerColors) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                timePickerColors = colors;
            }
            $dirty2 |= i3;
        } else {
            timePickerColors = colors;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
            z = autoSwitchToMinute;
        } else if (($changed & 3072) == 0) {
            z = autoSwitchToMinute;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = autoSwitchToMinute;
        }
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "978@41910L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                $dirty = $dirty2;
                modifier5 = modifier2;
                colors3 = timePickerColors;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    $dirty = $dirty2;
                    modifier5 = modifier4;
                    colors3 = timePickerColors;
                } else {
                    $dirty = $dirty2 & (-897);
                    modifier5 = modifier4;
                    colors3 = TimePickerDefaults.INSTANCE.colors($composer2, 6);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1432307537, $dirty, -1, "androidx.compose.material3.HorizontalTimePicker (TimePicker.kt:980)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 2058016684, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.HorizontalTimePicker$lambda$13$lambda$12((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv = SemanticsModifierKt.semantics$default(modifier5, false, (Function1) it$iv, 1, null);
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i5 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1443384930, "C985@42105L37,986@42151L59,987@42219L154:TimePicker.kt#uh7d8r");
            HorizontalClockDisplay(state, colors3, $composer2, ($dirty & 14) | (($dirty >> 3) & 112));
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, ClockDisplayBottomMargin), $composer2, 6);
            ClockFace(Modifier.INSTANCE.then(new ClockFaceSizeModifier()), state, colors3, z, $composer2, (($dirty << 3) & 112) | ($dirty & 896) | ($dirty & 7168));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors3;
            modifier3 = modifier5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors2 = timePickerColors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda37
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.HorizontalTimePicker$lambda$15(state, modifier3, colors2, autoSwitchToMinute, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit HorizontalTimePicker$lambda$13$lambda$12(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
        return Unit.INSTANCE;
    }

    private static final void TimeInputImpl(Modifier modifier, TimePickerColors colors, TimePickerState state, Composer $composer, final int $changed) {
        final Modifier modifier2;
        final TimePickerColors timePickerColors;
        Composer $composer2;
        Ref userOverride;
        MutableState hourValue$delegate;
        Function0<ComposeUiNode> function0;
        final TimePickerState timePickerState = state;
        Composer $composer3 = $composer.startRestartGroup(-475657989);
        ComposerKt.sourceInformation($composer3, "C(TimeInputImpl)N(modifier,colors,state)1002@42753L35,1002@42701L87,1005@42873L37,1005@42821L89,1007@42935L27,1009@43044L177,1009@43003L218,1017@43227L4621:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(colors) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer3.changed(timePickerState) : $composer3.changedInstance(timePickerState) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-475657989, $dirty2, -1, "androidx.compose.material3.TimeInputImpl (TimePicker.kt:997)");
            }
            Object[] objArr = new Object[0];
            Saver<TextFieldValue, Object> saver = TextFieldValue.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart($composer3, -1840782178, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(timePickerState));
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TimePickerKt.TimeInputImpl$hourTextValue(timePickerState), null, 2, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MutableState hourValue$delegate2 = RememberSaveableKt.rememberSaveable(objArr, (Saver) saver, (Function0) it$iv, $composer3, 0);
            Object[] objArr2 = new Object[0];
            Saver<TextFieldValue, Object> saver2 = TextFieldValue.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart($composer3, -1840778336, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv2 = ($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(timePickerState));
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TimePickerKt.TimeInputImpl$minuteTextValue(timePickerState), null, 2, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MutableState minuteValue$delegate = RememberSaveableKt.rememberSaveable(objArr2, (Saver) saver2, (Function0) it$iv2, $composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, -1840776362, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv3 = $composer3.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Ref();
                $composer3.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            Ref userOverride2 = (Ref) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Integer numValueOf = Integer.valueOf(timePickerState.getHour());
            Integer numValueOf2 = Integer.valueOf(timePickerState.getMinute());
            ComposerKt.sourceInformationMarkerStart($composer3, -1840772724, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv3 = $composer3.changedInstance(userOverride2) | $composer3.changed(hourValue$delegate2) | (($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(timePickerState))) | $composer3.changed(minuteValue$delegate);
            Object it$iv4 = $composer3.rememberedValue();
            if (invalid$iv3 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                userOverride = userOverride2;
                hourValue$delegate = hourValue$delegate2;
                Object value$iv4 = (Function2) new TimePickerKt$TimeInputImpl$1$1(userOverride2, timePickerState, hourValue$delegate2, minuteValue$delegate, null);
                $composer3.updateRememberedValue(value$iv4);
                it$iv4 = value$iv4;
            } else {
                hourValue$delegate = hourValue$delegate2;
                userOverride = userOverride2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(numValueOf, numValueOf2, (Function2) it$iv4, $composer3, 0);
            Modifier modifier$iv = PaddingKt.m823paddingqDBjuR0$default(modifier, 0.0f, 0.0f, 0.0f, TimeInputBottomPadding, 7, null);
            modifier2 = modifier;
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1385914133, "C1022@43428L5,1031@43794L3669,1027@43572L3891:TimePicker.kt#uh7d8r");
            TextStyle textStyle = TextStyle.m7341copyp1EtxEg$default(TypographyKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldLabelTextFont(), $composer3, 6), colors.m3095timeSelectorContentColorvNxB06k$material3(true), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m7758getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744446, null);
            timePickerColors = colors;
            MutableState hourValue$delegate3 = hourValue$delegate;
            $composer2 = $composer3;
            timePickerState = state;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{TextKt.getLocalTextStyle().provides(textStyle), CompositionLocalsKt.getLocalLayoutDirection().provides(LayoutDirection.Ltr)}, ComposableLambdaKt.rememberComposableLambda(1306700887, true, new TimePickerKt$TimeInputImpl$2$1(hourValue$delegate3, timePickerState, userOverride, timePickerColors, minuteValue$delegate), $composer3, 54), $composer3, ProvidedValue.$stable | 48);
            if (timePickerState.getIs24hour()) {
                $composer3.startReplaceGroup(-1381607893);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-1381942321);
                ComposerKt.sourceInformation($composer3, "1120@47508L324");
                Modifier modifier$iv2 = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function0 = constructor2;
                    $composer3.createNode(function0);
                } else {
                    function0 = constructor2;
                    $composer3.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1377011131, "C1121@47576L242:TimePicker.kt#uh7d8r");
                VerticalPeriodToggle(SizeKt.m867sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m4167getPeriodSelectorContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m4166getPeriodSelectorContainerHeightD9Ej5fM()), timePickerState, timePickerColors, $composer3, (($dirty2 >> 3) & 112) | 6 | (($dirty2 << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            modifier2 = modifier;
            timePickerColors = colors;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.TimeInputImpl$lambda$28(modifier2, timePickerColors, timePickerState, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$hourTextValue(TimePickerState $state) {
        return new TextFieldValue(CalendarLocale_jvmKt.toLocalString$default(getHourForDisplay($state), 2, 0, false, null, 14, null), 0L, (TextRange) null, 6, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$minuteTextValue(TimePickerState $state) {
        return new TextFieldValue(CalendarLocale_jvmKt.toLocalString$default($state.getMinute(), 2, 0, false, null, 14, null), 0L, (TextRange) null, 6, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$18(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$22(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    private static final void HorizontalClockDisplay(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer $composer3 = $composer.startRestartGroup(755539561);
        ComposerKt.sourceInformation($composer3, "C(HorizontalClockDisplay)N(state,colors)1134@47955L591:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(colors) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(755539561, $dirty, -1, "androidx.compose.material3.HorizontalClockDisplay (TimePicker.kt:1133)");
            }
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $dirty2 = $dirty;
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            $composer2 = $composer3;
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 998514658, "C1135@48014L34:TimePicker.kt#uh7d8r");
            ClockDisplayNumbers(state, colors, $composer2, ($dirty2 & 14) | ($dirty2 & 112));
            if (state.getIs24hour()) {
                $composer2.startReplaceGroup(999020143);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(998576161);
                ComposerKt.sourceInformation($composer2, "1137@48092L438");
                Modifier modifier$iv2 = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, PeriodToggleMargin, 0.0f, 0.0f, 13, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function1 = constructor2;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer2.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1710314390, "C1138@48169L347:TimePicker.kt#uh7d8r");
                HorizontalPeriodToggle(SizeKt.m867sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4178getPeriodSelectorHorizontalContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m4177getPeriodSelectorHorizontalContainerHeightD9Ej5fM()), state, colors, $composer2, (($dirty2 << 3) & 112) | 6 | (($dirty2 << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.HorizontalClockDisplay$lambda$31(state, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void VerticalClockDisplay(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer $composer3 = $composer.startRestartGroup(2054675515);
        ComposerKt.sourceInformation($composer3, "C(VerticalClockDisplay)N(state,colors)1154@48651L586:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(colors) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2054675515, $dirty, -1, "androidx.compose.material3.VerticalClockDisplay (TimePicker.kt:1153)");
            }
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $dirty2 = $dirty;
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            $composer2 = $composer3;
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1364225858, "C1155@48709L34:TimePicker.kt#uh7d8r");
            ClockDisplayNumbers(state, colors, $composer2, ($dirty2 & 14) | ($dirty2 & 112));
            if (state.getIs24hour()) {
                $composer2.startReplaceGroup(1364727499);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(1364287361);
                ComposerKt.sourceInformation($composer2, "1157@48787L434");
                Modifier modifier$iv2 = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function1 = constructor2;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer2.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -2145741896, "C1158@48866L341:TimePicker.kt#uh7d8r");
                VerticalPeriodToggle(SizeKt.m867sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4181getPeriodSelectorVerticalContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m4180getPeriodSelectorVerticalContainerHeightD9Ej5fM()), state, colors, $composer2, (($dirty2 << 3) & 112) | 6 | (($dirty2 << 3) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.VerticalClockDisplay$lambda$34(state, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void ClockDisplayNumbers(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-934561141);
        ComposerKt.sourceInformation($composer2, "C(ClockDisplayNumbers)N(state,colors)1175@49425L5,1178@49561L775,1174@49341L995:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-934561141, $dirty, -1, "androidx.compose.material3.ClockDisplayNumbers (TimePicker.kt:1173)");
            }
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{TextKt.getLocalTextStyle().provides(TypographyKt.getValue(TimePickerTokens.INSTANCE.getTimeSelectorLabelTextFont(), $composer2, 6)), CompositionLocalsKt.getLocalLayoutDirection().provides(LayoutDirection.Ltr)}, ComposableLambdaKt.rememberComposableLambda(-477913269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockDisplayNumbers.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer3, "C1179@49571L759:TimePicker.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-477913269, $changed2, -1, "androidx.compose.material3.ClockDisplayNumbers.<anonymous> (TimePicker.kt:1179)");
                    }
                    TimePickerState timePickerState = state;
                    TimePickerColors timePickerColors = colors;
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function0 = constructor;
                        $composer3.createNode(function0);
                    } else {
                        function0 = constructor;
                        $composer3.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i2 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2065726649, "C1180@49589L294,1187@49896L123,1190@50032L288:TimePicker.kt#uh7d8r");
                    TimePickerKt.m3114TimeSelectorSAnMeKU(SizeKt.m867sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4184getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m4183getTimeSelectorContainerHeightD9Ej5fM()), TimePickerKt.getHourForDisplay(timePickerState), timePickerState, TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI(), timePickerColors, $composer3, 3078);
                    TimePickerKt.DisplaySeparator(SizeKt.m867sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimePickerTokens.INSTANCE.m4180getPeriodSelectorVerticalContainerHeightD9Ej5fM()), $composer3, 6);
                    TimePickerKt.m3114TimeSelectorSAnMeKU(SizeKt.m867sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4184getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m4183getTimeSelectorContainerHeightD9Ej5fM()), timePickerState.getMinute(), timePickerState, TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI(), timePickerColors, $composer3, 3078);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.ClockDisplayNumbers$lambda$35(state, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void HorizontalPeriodToggle(Modifier modifier, TimePickerState state, TimePickerColors colors, Composer $composer, final int $changed) {
        final Modifier modifier2;
        final TimePickerState state2;
        final TimePickerColors colors2;
        Composer $composer2 = $composer.startRestartGroup(1261215927);
        ComposerKt.sourceInformation($composer2, "C(HorizontalPeriodToggle)N(modifier,state,colors)1207@50498L1014,1235@51559L5,1237@51590L207:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1261215927, $dirty, -1, "androidx.compose.material3.HorizontalPeriodToggle (TimePicker.kt:1206)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 847734445, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (MeasurePolicy) TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1.INSTANCE;
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape = (CornerBasedShape) value;
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
            PeriodToggleImpl(modifier2, state2, colors2, measurePolicy, ShapesKt.start$default(shape, null, 1, null), ShapesKt.end$default(shape, null, 1, null), $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.HorizontalPeriodToggle$lambda$37(modifier2, state2, colors2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void VerticalPeriodToggle(Modifier modifier, TimePickerState state, TimePickerColors colors, Composer $composer, final int $changed) {
        final Modifier modifier2;
        final TimePickerState state2;
        final TimePickerColors colors2;
        Composer $composer2 = $composer.startRestartGroup(-1898918107);
        ComposerKt.sourceInformation($composer2, "C(VerticalPeriodToggle)N(modifier,state,colors)1253@51957L1022,1281@53026L5,1283@53057L208:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1898918107, $dirty, -1, "androidx.compose.material3.VerticalPeriodToggle (TimePicker.kt:1252)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 500805987, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (MeasurePolicy) TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1.INSTANCE;
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape = (CornerBasedShape) value;
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
            PeriodToggleImpl(modifier2, state2, colors2, measurePolicy, ShapesKt.top$default(shape, null, 1, null), ShapesKt.bottom$default(shape, null, 1, null), $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda34
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.VerticalPeriodToggle$lambda$39(modifier2, state2, colors2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x02a6  */
    /* JADX WARN: Code duplicated, block: B:107:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:110:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:86:0x020a  */
    private static final void PeriodToggleImpl(final Modifier modifier, final TimePickerState state, final TimePickerColors colors, final MeasurePolicy measurePolicy, final Shape startShape, final Shape endShape, Composer $composer, final int $changed) {
        TimePickerColors timePickerColors;
        Shape shape;
        Shape shape2;
        int $dirty;
        boolean invalid$iv;
        boolean invalid$iv2;
        Object value$iv;
        boolean invalid$iv3;
        Object it$iv;
        Composer $composer2 = $composer.startRestartGroup(1374241901);
        ComposerKt.sourceInformation($composer2, "C(PeriodToggleImpl)N(modifier,state,colors,measurePolicy,startShape,endShape)1305@53644L5,1306@53699L41,1310@53820L128,1307@53745L1360:TimePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            timePickerColors = colors;
            $dirty2 |= $composer2.changed(timePickerColors) ? 256 : 128;
        } else {
            timePickerColors = colors;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changed(measurePolicy) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            shape = startShape;
            $dirty2 |= $composer2.changed(shape) ? 16384 : 8192;
        } else {
            shape = startShape;
        }
        if ((196608 & $changed) == 0) {
            shape2 = endShape;
            $dirty2 |= $composer2.changed(shape2) ? 131072 : 65536;
        } else {
            shape2 = endShape;
        }
        if ($composer2.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1374241901, $dirty2, -1, "androidx.compose.material3.PeriodToggleImpl (TimePicker.kt:1301)");
            }
            BorderStroke borderStroke = BorderStrokeKt.m291BorderStrokecXLIe8U(TimePickerTokens.INSTANCE.m4179getPeriodSelectorOutlineWidthD9Ej5fM(), timePickerColors.getPeriodSelectorBorderColor());
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape3 = (CornerBasedShape) value;
            Strings.Companion companion = Strings.INSTANCE;
            final String contentDescription = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_time_picker_period_toggle_description), $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 2091524749, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv4 = $composer2.changed(contentDescription);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv4 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda28
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.PeriodToggleImpl$lambda$41$lambda$40(contentDescription, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierBorder = BorderKt.border(SelectableGroupKt.selectableGroup(SemanticsModifierKt.semantics$default(modifier, false, (Function1) it$iv2, 1, null)), borderStroke, shape3);
            int $changed$iv = ($dirty2 >> 3) & 896;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifierBorder);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                $dirty = $dirty2;
            } else {
                $dirty = $dirty2;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1677874453, "C1321@54231L120,1318@54118L367,1330@54498L219,1339@54840L121,1336@54730L358:TimePicker.kt#uh7d8r");
                boolean z = !isPm(state);
                ComposerKt.sourceInformationMarkerStart($composer2, -222966916, "CC(remember):TimePicker.kt#9igjgp");
                if (($dirty & 112) != 32 || (($dirty & 64) != 0 && $composer2.changedInstance(state))) {
                    invalid$iv = true;
                } else {
                    invalid$iv = false;
                }
                invalid$iv2 = invalid$iv;
                value$iv = $composer2.rememberedValue();
                if (!invalid$iv2 || value$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda29
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TimePickerKt.PeriodToggleImpl$lambda$46$lambda$43$lambda$42(state);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                }
                Function0 factory$iv$iv = (Function0) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ToggleItem(z, shape, factory$iv$iv, timePickerColors, ComposableSingletons$TimePickerKt.INSTANCE.getLambda$1425358052$material3(), $composer2, (($dirty >> 9) & 112) | 24576 | (($dirty << 3) & 7168));
                SpacerKt.Spacer(BackgroundKt.m265backgroundbw27NRU$default(SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(LayoutIdKt.layoutId(Modifier.INSTANCE, "Spacer"), 2.0f), 0.0f, 1, null), colors.getPeriodSelectorBorderColor(), null, 2, null), $composer2, 0);
                boolean zIsPm = isPm(state);
                ComposerKt.sourceInformationMarkerStart($composer2, -222947427, "CC(remember):TimePicker.kt#9igjgp");
                if (($dirty & 112) != 32 || (($dirty & 64) != 0 && $composer2.changedInstance(state))) {
                    invalid$iv3 = true;
                } else {
                    invalid$iv3 = false;
                }
                it$iv = $composer2.rememberedValue();
                if (!invalid$iv3 || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda30
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TimePickerKt.PeriodToggleImpl$lambda$46$lambda$45$lambda$44(state);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                    it$iv = value$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ToggleItem(zIsPm, shape2, (Function0) it$iv, colors, ComposableSingletons$TimePickerKt.INSTANCE.m2313getLambda$1179219109$material3(), $composer2, (($dirty >> 12) & 112) | 24576 | (($dirty << 3) & 7168));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1677874453, "C1321@54231L120,1318@54118L367,1330@54498L219,1339@54840L121,1336@54730L358:TimePicker.kt#uh7d8r");
            boolean z2 = !isPm(state);
            ComposerKt.sourceInformationMarkerStart($composer2, -222966916, "CC(remember):TimePicker.kt#9igjgp");
            if (($dirty & 112) != 32) {
                invalid$iv = true;
            } else {
                invalid$iv = true;
            }
            invalid$iv2 = invalid$iv;
            value$iv = $composer2.rememberedValue();
            if (!invalid$iv2) {
            }
            value$iv = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return TimePickerKt.PeriodToggleImpl$lambda$46$lambda$43$lambda$42(state);
                }
            };
            $composer2.updateRememberedValue(value$iv);
            Function0 factory$iv$iv2 = (Function0) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ToggleItem(z2, shape, factory$iv$iv2, timePickerColors, ComposableSingletons$TimePickerKt.INSTANCE.getLambda$1425358052$material3(), $composer2, (($dirty >> 9) & 112) | 24576 | (($dirty << 3) & 7168));
            SpacerKt.Spacer(BackgroundKt.m265backgroundbw27NRU$default(SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(LayoutIdKt.layoutId(Modifier.INSTANCE, "Spacer"), 2.0f), 0.0f, 1, null), colors.getPeriodSelectorBorderColor(), null, 2, null), $composer2, 0);
            boolean zIsPm2 = isPm(state);
            ComposerKt.sourceInformationMarkerStart($composer2, -222947427, "CC(remember):TimePicker.kt#9igjgp");
            if (($dirty & 112) != 32) {
                invalid$iv3 = true;
            } else {
                invalid$iv3 = true;
            }
            it$iv = $composer2.rememberedValue();
            if (!invalid$iv3) {
                Object value$iv4 = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda30
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TimePickerKt.PeriodToggleImpl$lambda$46$lambda$45$lambda$44(state);
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
                it$iv = value$iv4;
            } else {
                Object value$iv5 = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda30
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TimePickerKt.PeriodToggleImpl$lambda$46$lambda$45$lambda$44(state);
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
                it$iv = value$iv5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ToggleItem(zIsPm2, shape2, (Function0) it$iv, colors, ComposableSingletons$TimePickerKt.INSTANCE.m2313getLambda$1179219109$material3(), $composer2, (($dirty >> 12) & 112) | 24576 | (($dirty << 3) & 7168));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.PeriodToggleImpl$lambda$47(modifier, state, colors, measurePolicy, startShape, endShape, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit PeriodToggleImpl$lambda$41$lambda$40(String $contentDescription, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
        SemanticsPropertiesKt.setContentDescription($this$semantics, $contentDescription);
        return Unit.INSTANCE;
    }

    static final Unit PeriodToggleImpl$lambda$46$lambda$43$lambda$42(TimePickerState $state) {
        if (isPm($state)) {
            $state.setHour($state.getHour() - 12);
        }
        return Unit.INSTANCE;
    }

    static final Unit PeriodToggleImpl$lambda$46$lambda$45$lambda$44(TimePickerState $state) {
        if (!isPm($state)) {
            $state.setHour($state.getHour() + 12);
        }
        return Unit.INSTANCE;
    }

    private static final void ToggleItem(final boolean checked, final Shape shape, final Function0<Unit> function0, final TimePickerColors colors, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Shape shape2;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1523811083);
        ComposerKt.sourceInformation($composer3, "C(ToggleItem)N(checked,shape,onClick,colors,content)1365@55539L22,1371@55730L125,1363@55431L431:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(checked) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            shape2 = shape;
            $dirty |= $composer3.changed(shape2) ? 32 : 16;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(colors) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 16384 : 8192;
        }
        if ($composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1523811083, $dirty, -1, "androidx.compose.material3.ToggleItem (TimePicker.kt:1359)");
            }
            long contentColor = colors.m3093periodSelectorContentColorvNxB06k$material3(checked);
            long containerColor = colors.m3092periodSelectorContainerColorvNxB06k$material3(checked);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(Modifier.INSTANCE, checked ? 0.0f : 1.0f), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -201383391, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.ToggleItem$lambda$49$lambda$48(checked, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ButtonKt.TextButton(function0, SemanticsModifierKt.semantics$default(modifierFillMaxSize$default, false, (Function1) it$iv, 1, null), false, shape2, ButtonDefaults.INSTANCE.m2135textButtonColorsro_MJ88(containerColor, contentColor, 0L, 0L, $composer3, 24576, 12), null, null, PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0)), null, function3, $composer3, (($dirty >> 6) & 14) | 12582912 | (($dirty << 6) & 7168) | (($dirty << 15) & 1879048192), 356);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.ToggleItem$lambda$50(checked, shape, function0, colors, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit ToggleItem$lambda$49$lambda$48(boolean $checked, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setSelected($this$semantics, $checked);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:38:0x0248  */
    public static final void DisplaySeparator(final Modifier modifier, Composer $composer, final int $changed) {
        Composer $composer2;
        boolean propagateMinConstraints$iv;
        Composer $composer3 = $composer.startRestartGroup(2100674302);
        ComposerKt.sourceInformation($composer3, "C(DisplaySeparator)N(modifier)1381@55968L7,1390@56286L2,1390@56241L172:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if ($composer3.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2100674302, $dirty, -1, "androidx.compose.material3.DisplaySeparator (TimePicker.kt:1379)");
            }
            ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localTextStyle);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            TextStyle style = TextStyle.m7341copyp1EtxEg$default((TextStyle) objConsume, 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m7758getCentere0LSkKk(), 0, 0L, null, null, new LineHeightStyle(LineHeightStyle.Alignment.INSTANCE.m7725getCenterPIaL0Z0(), LineHeightStyle.Trim.INSTANCE.m7747getBothEVpEnUU(), (DefaultConstructorMarker) null), 0, 0, null, 15695871, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 113388544, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Unit.INSTANCE;
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv = SemanticsModifierKt.clearAndSetSemantics(modifier, (Function1) it$iv);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                $composer2 = $composer3;
            } else {
                $composer2 = $composer3;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                Composer $composer$iv = $composer2;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i2 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, 2101554693, "C1391@56386L5,1391@56337L70:TimePicker.kt#uh7d8r");
                TextKt.m3072TextNvy7gAk(":", null, ColorSchemeKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSeparatorColor(), $composer$iv, 6), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, style, $composer$iv, 6, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                propagateMinConstraints$iv = ComposerKt.isTraceInProgress();
                if (propagateMinConstraints$iv) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            Composer $composer$iv2 = $composer2;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i4 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, 2101554693, "C1391@56386L5,1391@56337L70:TimePicker.kt#uh7d8r");
            TextKt.m3072TextNvy7gAk(":", null, ColorSchemeKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSeparatorColor(), $composer$iv2, 6), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, style, $composer$iv2, 6, 0, 131066);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            propagateMinConstraints$iv = ComposerKt.isTraceInProgress();
            if (propagateMinConstraints$iv) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.DisplaySeparator$lambda$54(modifier, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: TimeSelector-SAnMeKU, reason: not valid java name */
    public static final void m3114TimeSelectorSAnMeKU(final Modifier modifier, final int value, final TimePickerState state, final int selection, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2;
        int iM3369constructorimpl;
        Composer $composer3 = $composer.startRestartGroup(-1148055889);
        ComposerKt.sourceInformation($composer3, "C(TimeSelector)N(modifier,value,state,selection:c#material3.TimePickerSelectionMode,colors)1406@56730L214,1418@57168L124,1428@57503L5,1422@57312L117,1430@57548L498,1416@57084L962:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(value) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(selection) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(colors) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1148055889, $dirty2, -1, "androidx.compose.material3.TimeSelector (TimePicker.kt:1403)");
            }
            boolean selected = TimePickerSelectionMode.m3138equalsimpl0(state.mo2063getSelectionyecRtBI(), selection);
            if (TimePickerSelectionMode.m3138equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI())) {
                Strings.Companion companion = Strings.INSTANCE;
                iM3369constructorimpl = Strings.m3369constructorimpl(R.string.m3c_time_picker_hour_selection);
            } else {
                Strings.Companion companion2 = Strings.INSTANCE;
                iM3369constructorimpl = Strings.m3369constructorimpl(R.string.m3c_time_picker_minute_selection);
            }
            final String selectorContentDescription = Strings_androidKt.m3448getString2EP1pXo(iM3369constructorimpl, $composer3, 0);
            long containerColor = colors.m3094timeSelectorContainerColorvNxB06k$material3(selected);
            long contentColor = colors.m3095timeSelectorContentColorvNxB06k$material3(selected);
            ComposerKt.sourceInformationMarkerStart($composer3, 524909899, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(selectorContentDescription);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.TimeSelector_SAnMeKU$lambda$56$lambda$55(selectorContentDescription, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier, true, (Function1) it$iv);
            Shape value2 = ShapesKt.getValue(TimePickerTokens.INSTANCE.getTimeSelectorContainerShape(), $composer3, 6);
            ComposerKt.sourceInformationMarkerStart($composer3, 524914500, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 7168) == 2048) | (($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(state)));
            Object value$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TimePickerKt.TimeSelector_SAnMeKU$lambda$58$lambda$57(selection, state);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            SurfaceKt.m2930Surfaced85dljk(selected, (Function0<Unit>) value$iv2, modifierSemantics, false, value2, containerColor, 0L, 0.0f, 0.0f, (BorderStroke) null, (MutableInteractionSource) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1477282471, true, new TimePickerKt$TimeSelector$3(selection, state, value, contentColor), $composer3, 54), $composer2, 0, 48, 1992);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.TimeSelector_SAnMeKU$lambda$59(modifier, value, state, selection, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit TimeSelector_SAnMeKU$lambda$56$lambda$55(String $selectorContentDescription, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7104getRadioButtono7Vup1c());
        SemanticsPropertiesKt.setContentDescription($this$semantics, $selectorContentDescription);
        return Unit.INSTANCE;
    }

    static final Unit TimeSelector_SAnMeKU$lambda$58$lambda$57(int $selection, TimePickerState $state) {
        if (!TimePickerSelectionMode.m3138equalsimpl0($selection, $state.mo2063getSelectionyecRtBI())) {
            $state.mo2065setSelection6_8s6DQ($selection);
        }
        return Unit.INSTANCE;
    }

    public static final void ClockFace(final Modifier modifier, AnalogTimePickerState state, final TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed) {
        final AnalogTimePickerState analogTimePickerState = state;
        Composer $composer2 = $composer.startRestartGroup(-478841003);
        ComposerKt.sourceInformation($composer2, "C(ClockFace)N(modifier,state,colors,autoSwitchToMinute)1602@63170L7,1607@63371L7,1608@63386L2054,1593@62800L2640:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(analogTimePickerState) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-478841003, $dirty2, -1, "androidx.compose.material3.ClockFace (TimePicker.kt:1591)");
            }
            analogTimePickerState = state;
            CrossfadeKt.Crossfade(analogTimePickerState.getClockFaceValues(), drawSelector(BackgroundKt.m264backgroundbw27NRU(modifier, colors.getClockDialColor(), RoundedCornerShapeKt.getCircleShape()).then(new ClockDialModifier(state, autoSwitchToMinute, state.mo2063getSelectionyecRtBI(), MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer2, 6), null)), analogTimePickerState, colors), (FiniteAnimationSpec<Float>) MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer2, 6), (String) null, ComposableLambdaKt.rememberComposableLambda(747010833, true, new C03301(colors, analogTimePickerState, autoSwitchToMinute), $composer2, 54), $composer2, 24576, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.ClockFace$lambda$60(modifier, analogTimePickerState, colors, autoSwitchToMinute, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TimePicker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C03301 implements Function3<IntList, Composer, Integer, Unit> {
        final /* synthetic */ boolean $autoSwitchToMinute;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ AnalogTimePickerState $state;

        C03301(TimePickerColors timePickerColors, AnalogTimePickerState analogTimePickerState, boolean z) {
            this.$colors = timePickerColors;
            this.$state = analogTimePickerState;
            this.$autoSwitchToMinute = z;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(IntList intList, Composer composer, Integer num) {
            invoke(intList, composer, num.intValue());
            return Unit.INSTANCE;
        }

        static final Unit invoke$lambda$1$lambda$0(SemanticsPropertyReceiver $this$semantics) {
            SemanticsPropertiesKt.selectableGroup($this$semantics);
            return Unit.INSTANCE;
        }

        public final void invoke(final IntList screen, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "CN(screen)1610@63493L21,1612@63582L1852,1609@63406L2028:TimePicker.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(747010833, $changed, -1, "androidx.compose.material3.ClockFace.<anonymous> (TimePicker.kt:1609)");
            }
            Modifier modifierM865size3ABfNKs = SizeKt.m865size3ABfNKs(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m4172getClockDialContainerSizeD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart($composer, 617875526, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.C03301.invoke$lambda$1$lambda$0((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierM865size3ABfNKs, false, (Function1) it$iv, 1, null);
            float f = TimePickerKt.OuterCircleToSizeRatio;
            final TimePickerColors timePickerColors = this.$colors;
            final AnalogTimePickerState analogTimePickerState = this.$state;
            final boolean z = this.$autoSwitchToMinute;
            TimePickerKt.CircularLayout(modifierSemantics$default, f, ComposableLambdaKt.rememberComposableLambda(-99063847, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.1.2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: TimePicker.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                static final class C00641 implements Function2<Composer, Integer, Unit> {
                    final /* synthetic */ boolean $autoSwitchToMinute;
                    final /* synthetic */ IntList $screen;
                    final /* synthetic */ AnalogTimePickerState $state;

                    C00641(IntList intList, AnalogTimePickerState analogTimePickerState, boolean z) {
                        this.$screen = intList;
                        this.$state = analogTimePickerState;
                        this.$autoSwitchToMinute = z;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer, int $changed) {
                        int outerValue;
                        Composer composer = $composer;
                        ComposerKt.sourceInformation(composer, "C:TimePicker.kt#uh7d8r");
                        if (!composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                            composer.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-596940007, $changed, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1616)");
                        }
                        composer.startReplaceGroup(1866272144);
                        ComposerKt.sourceInformation(composer, "*1624@64134L41,1623@64069L277");
                        IntList this_$iv = this.$screen;
                        int i = this_$iv._size;
                        AnalogTimePickerState analogTimePickerState = this.$state;
                        IntList intList = this.$screen;
                        boolean z = this.$autoSwitchToMinute;
                        int i2 = 0;
                        while (i2 < i) {
                            final int index = i2;
                            if (!analogTimePickerState.getIs24hour() || TimePickerSelectionMode.m3138equalsimpl0(analogTimePickerState.mo2063getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI())) {
                                outerValue = intList.get(index);
                            } else {
                                outerValue = intList.get(index) % 12;
                            }
                            Modifier.Companion companion = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer, -121641742, "CC(remember):TimePicker.kt#9igjgp");
                            boolean invalid$iv = composer.changed(index);
                            Object it$iv = $composer.rememberedValue();
                            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return TimePickerKt.C03301.AnonymousClass2.C00641.invoke$lambda$2$lambda$1$lambda$0(index, (SemanticsPropertyReceiver) obj);
                                    }
                                };
                                $composer.updateRememberedValue(value$iv);
                                it$iv = value$iv;
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv, 1, null);
                            Composer composer2 = composer;
                            TimePickerKt.ClockText(modifierSemantics$default, analogTimePickerState, outerValue, z, composer2, 0);
                            composer = composer2;
                            i2++;
                            z = z;
                        }
                        composer.endReplaceGroup();
                        if (TimePickerSelectionMode.m3138equalsimpl0(this.$state.mo2063getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI()) && this.$state.getIs24hour()) {
                            composer.startReplaceGroup(2020585964);
                            ComposerKt.sourceInformation(composer, "1638@64839L553,1632@64475L917");
                            TimePickerKt.CircularLayout(BackgroundKt.m264backgroundbw27NRU(SizeKt.m865size3ABfNKs(LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutId.InnerCircle), TimePickerTokens.INSTANCE.m4172getClockDialContainerSizeD9Ej5fM()), Color.INSTANCE.m5132getTransparent0d7_KjU(), RoundedCornerShapeKt.getCircleShape()), TimePickerKt.InnerCircleToSizeRatio, ComposableLambdaKt.rememberComposableLambda(-1385767514, true, new C00652(this.$state, this.$autoSwitchToMinute), composer, 54), composer, 432, 0);
                            composer.endReplaceGroup();
                        } else {
                            composer.startReplaceGroup(2021505641);
                            composer.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    static final Unit invoke$lambda$2$lambda$1$lambda$0(int $index, SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setTraversalIndex($this$semantics, $index + 1.0f);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$2, reason: invalid class name and collision with other inner class name */
                    /* JADX INFO: compiled from: TimePicker.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                    static final class C00652 implements Function2<Composer, Integer, Unit> {
                        final /* synthetic */ boolean $autoSwitchToMinute;
                        final /* synthetic */ AnalogTimePickerState $state;

                        C00652(AnalogTimePickerState analogTimePickerState, boolean z) {
                            this.$state = analogTimePickerState;
                            this.$autoSwitchToMinute = z;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer, int $changed) {
                            Composer composer = $composer;
                            ComposerKt.sourceInformation(composer, "C*1643@65100L41,1641@64991L353:TimePicker.kt#uh7d8r");
                            if (composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1385767514, $changed, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1639)");
                                }
                                IntList this_$iv = TimePickerKt.ExtraHours;
                                int i = this_$iv._size;
                                AnalogTimePickerState analogTimePickerState = this.$state;
                                boolean z = this.$autoSwitchToMinute;
                                int i2 = 0;
                                while (i2 < i) {
                                    final int index = i2;
                                    int innerValue = TimePickerKt.ExtraHours.get(index);
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer, -1870016769, "CC(remember):TimePicker.kt#9igjgp");
                                    boolean invalid$iv = composer.changed(index);
                                    Object it$iv = $composer.rememberedValue();
                                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                        Object value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$2$$ExternalSyntheticLambda0
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                return TimePickerKt.C03301.AnonymousClass2.C00641.C00652.invoke$lambda$2$lambda$1$lambda$0(index, (SemanticsPropertyReceiver) obj);
                                            }
                                        };
                                        $composer.updateRememberedValue(value$iv);
                                        it$iv = value$iv;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer);
                                    TimePickerKt.ClockText(SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv, 1, null), analogTimePickerState, innerValue, z, composer, 0);
                                    i2++;
                                    composer = $composer;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer.skipToGroupEnd();
                        }

                        static final Unit invoke$lambda$2$lambda$1$lambda$0(int $index, SemanticsPropertyReceiver $this$semantics) {
                            SemanticsPropertiesKt.setTraversalIndex($this$semantics, 12.0f + $index);
                            return Unit.INSTANCE;
                        }
                    }
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C1615@63715L1709,1613@63596L1828:TimePicker.kt#uh7d8r");
                    if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-99063847, $changed2, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous> (TimePicker.kt:1613)");
                    }
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(timePickerColors.m3076clockDialContentColorvNxB06k$material3(false))), ComposableLambdaKt.rememberComposableLambda(-596940007, true, new C00641(screen, analogTimePickerState, z), $composer2, 54), $composer2, ProvidedValue.$stable | 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54), $composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    private static final Modifier drawSelector(Modifier $this$drawSelector, final AnalogTimePickerState state, final TimePickerColors colors) {
        return DrawModifierKt.drawWithContent($this$drawSelector, new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TimePickerKt.drawSelector$lambda$61(state, colors, (ContentDrawScope) obj);
            }
        });
    }

    static final Unit drawSelector$lambda$61(AnalogTimePickerState $state, TimePickerColors $colors, ContentDrawScope $this$drawWithContent) {
        float x$iv = $this$drawWithContent.mo406toPx0680j_4(DpOffset.m7966getXD9Ej5fM(getSelectorPos($state)));
        float y$iv = $this$drawWithContent.mo406toPx0680j_4(DpOffset.m7968getYD9Ej5fM(getSelectorPos($state)));
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long jM4844constructorimpl = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        float selectorRadius = (($this$drawWithContent.mo406toPx0680j_4(TimePickerTokens.INSTANCE.m4174getClockDialSelectorHandleContainerSizeD9Ej5fM()) / 2.0f) * $this$drawWithContent.mo400roundToPx0680j_4($state.m2062getCurrentDiameterD9Ej5fM())) / $this$drawWithContent.mo400roundToPx0680j_4(TimePickerTokens.INSTANCE.m4172getClockDialContainerSizeD9Ej5fM());
        long selectorColor = $colors.getSelectorColor();
        DrawScope.m5652drawCircleVaOC9Bg$default($this$drawWithContent, Color.INSTANCE.m5123getBlack0d7_KjU(), selectorRadius, jM4844constructorimpl, 0.0f, null, null, BlendMode.INSTANCE.m5010getClear0nO6VwU(), 56, null);
        $this$drawWithContent.drawContent();
        DrawScope.m5652drawCircleVaOC9Bg$default($this$drawWithContent, selectorColor, selectorRadius, jM4844constructorimpl, 0.0f, null, null, BlendMode.INSTANCE.m5038getXor0nO6VwU(), 56, null);
        float strokeWidth = $this$drawWithContent.mo406toPx0680j_4(TimePickerTokens.INSTANCE.m4175getClockDialSelectorTrackContainerWidthD9Ej5fM());
        float x$iv2 = ((float) Math.cos($state.getCurrentAngle())) * selectorRadius;
        float y$iv2 = ((float) Math.sin($state.getCurrentAngle())) * selectorRadius;
        long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
        long lineLength = Offset.m4856minusMKHz9U(jM4844constructorimpl, Offset.m4844constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2)));
        DrawScope.m5657drawLineNGM6Ib0$default($this$drawWithContent, selectorColor, androidx.compose.ui.geometry.SizeKt.m4931getCenteruvyYCjk($this$drawWithContent.mo5671getSizeNHjbRc()), lineLength, strokeWidth, 0, null, 0.0f, null, BlendMode.INSTANCE.m5037getSrcOver0nO6VwU(), PsExtractor.VIDEO_STREAM_MASK, null);
        DrawScope.m5652drawCircleVaOC9Bg$default($this$drawWithContent, selectorColor, $this$drawWithContent.mo406toPx0680j_4(TimePickerTokens.INSTANCE.m4173getClockDialSelectorCenterContainerSizeD9Ej5fM()) / 2.0f, androidx.compose.ui.geometry.SizeKt.m4931getCenteruvyYCjk($this$drawWithContent.mo5671getSizeNHjbRc()), 0.0f, null, null, 0, androidx.compose.material.MenuKt.InTransitionDuration, null);
        DrawScope.m5652drawCircleVaOC9Bg$default($this$drawWithContent, $colors.m3076clockDialContentColorvNxB06k$material3(true), selectorRadius, jM4844constructorimpl, 0.0f, null, null, BlendMode.INSTANCE.m5020getDstOver0nO6VwU(), 56, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockText(final Modifier modifier, final AnalogTimePickerState state, final int value, final boolean autoSwitchToMinute, Composer $composer, final int $changed) {
        Object value$iv;
        final CoroutineScope scope;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(-206784607);
        ComposerKt.sourceInformation($composer2, "C(ClockText)N(modifier,state,value,autoSwitchToMinute)1728@67678L5,1729@67724L7,1731@67805L40,1732@67870L43,1733@67940L38,1734@67995L24,1736@68057L143,1744@68267L268,1757@68741L215,1765@69135L612,1753@68613L1344:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(value) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-206784607, $dirty2, -1, "androidx.compose.material3.ClockText (TimePicker.kt:1727)");
            }
            TextStyle style = TypographyKt.getValue(TimePickerTokens.INSTANCE.getClockDialLabelTextFont(), $composer2, 6);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Density density = (Density) objConsume;
            final float fMo406toPx0680j_4 = density.mo406toPx0680j_4(MaxDistance);
            ComposerKt.sourceInformationMarkerStart($composer2, -1151501687, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m4841boximpl(Offset.INSTANCE.m4868getZeroF1C5BW0()), null, 2, null);
                $composer2.updateRememberedValue(value$iv2);
                it$iv = value$iv2;
            }
            final MutableState center$delegate = (MutableState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1151499604, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntOffset.m8024boximpl(IntOffset.INSTANCE.m8044getZeronOccac()), null, 2, null);
                $composer2.updateRememberedValue(value$iv3);
                it$iv2 = value$iv3;
            }
            final MutableState parentCenter$delegate = (MutableState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1151497369, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Rect.INSTANCE.getZero(), null, 2, null);
                $composer2.updateRememberedValue(value$iv4);
                it$iv3 = value$iv4;
            }
            final MutableState boundsInParent$delegate = (MutableState) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer2, 683737348, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2);
                $composer2.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            CoroutineScope scope2 = (CoroutineScope) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final String contentDescription = m3121numberContentDescriptiondSwYdS4(state.mo2063getSelectionyecRtBI(), state.getIs24hour(), value, $composer2, $dirty2 & 896);
            String text = CalendarLocale_jvmKt.toLocalString$default(value, 0, 0, false, null, 15, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -1151486675, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(state);
            Object it$iv4 = $composer2.rememberedValue();
            if (invalid$iv || it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv5 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(TimePickerKt.ClockText$lambda$74$lambda$73(state, density, boundsInParent$delegate));
                    }
                });
                $composer2.updateRememberedValue(value$iv5);
                it$iv4 = value$iv5;
            }
            final State selected$delegate = (State) it$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, -1151471560, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv5 = $composer2.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv6 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.ClockText$lambda$77$lambda$76(parentCenter$delegate, boundsInParent$delegate, center$delegate, (LayoutCoordinates) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv6);
                it$iv5 = value$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierFocusable$default = FocusableKt.focusable$default(SizeKt.m865size3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(OnGloballyPositionedModifierKt.onGloballyPositioned(modifier, (Function1) it$iv5)), MinimumInteractiveSize), false, null, 3, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -1151458555, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(scope2) | $composer2.changedInstance(state) | $composer2.changed(fMo406toPx0680j_4) | (($dirty2 & 7168) == 2048) | $composer2.changed(selected$delegate);
            Object it$iv6 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                scope = scope2;
                value$iv = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.ClockText$lambda$80$lambda$79(scope, state, fMo406toPx0680j_4, autoSwitchToMinute, center$delegate, parentCenter$delegate, selected$delegate, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                scope = scope2;
                value$iv = it$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv$iv = SemanticsModifierKt.semantics(modifierFocusable$default, true, (Function1) value$iv);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -866292798, "C1784@69840L48,1782@69765L186:TimePicker.kt#uh7d8r");
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -2106152649, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changed(contentDescription);
            Object value$iv7 = $composer2.rememberedValue();
            if (invalid$iv3 || value$iv7 == Composer.INSTANCE.getEmpty()) {
                value$iv7 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt.ClockText$lambda$83$lambda$82$lambda$81(contentDescription, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv7);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            TextKt.m3072TextNvy7gAk(text, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) value$iv7), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, style, $composer2, 0, 0, 131068);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerKt.ClockText$lambda$84(modifier, state, value, autoSwitchToMinute, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long ClockText$lambda$64(MutableState<Offset> mutableState) {
        MutableState<Offset> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().m4862unboximpl();
    }

    private static final void ClockText$lambda$65(MutableState<Offset> mutableState, long j) {
        mutableState.setValue(Offset.m4841boximpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long ClockText$lambda$67(MutableState<IntOffset> mutableState) {
        MutableState<IntOffset> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().m8042unboximpl();
    }

    private static final void ClockText$lambda$68(MutableState<IntOffset> mutableState, long j) {
        mutableState.setValue(IntOffset.m8024boximpl(j));
    }

    private static final Rect ClockText$lambda$70(MutableState<Rect> mutableState) {
        MutableState<Rect> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    private static final boolean ClockText$lambda$75(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    static final boolean ClockText$lambda$74$lambda$73(AnalogTimePickerState $state, Density $density, MutableState $boundsInParent$delegate) {
        long selectorPos = getSelectorPos($state);
        float x$iv = $density.mo406toPx0680j_4(DpOffset.m7966getXD9Ej5fM(selectorPos));
        float y$iv = $density.mo406toPx0680j_4(DpOffset.m7968getYD9Ej5fM(selectorPos));
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long offset = Offset.m4844constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        return ClockText$lambda$70($boundsInParent$delegate).m4878containsk4lQ0M(offset);
    }

    static final Unit ClockText$lambda$77$lambda$76(MutableState $parentCenter$delegate, MutableState $boundsInParent$delegate, MutableState $center$delegate, LayoutCoordinates it) {
        LayoutCoordinates parentCoordinates = it.getParentCoordinates();
        ClockText$lambda$68($parentCenter$delegate, parentCoordinates != null ? IntSizeKt.m8082getCenterozmzZPI(parentCoordinates.mo6549getSizeYbymL2g()) : IntOffset.INSTANCE.m8044getZeronOccac());
        $boundsInParent$delegate.setValue(LayoutCoordinatesKt.boundsInParent(it));
        ClockText$lambda$65($center$delegate, ClockText$lambda$70($boundsInParent$delegate).m4882getCenterF1C5BW0());
        return Unit.INSTANCE;
    }

    static final Unit ClockText$lambda$80$lambda$79(final CoroutineScope $scope, final AnalogTimePickerState $state, final float $maxDist, final boolean $autoSwitchToMinute, final MutableState $center$delegate, final MutableState $parentCenter$delegate, State $selected$delegate, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(TimePickerKt.ClockText$lambda$80$lambda$79$lambda$78($scope, $state, $maxDist, $autoSwitchToMinute, $center$delegate, $parentCenter$delegate));
            }
        }, 1, null);
        SemanticsPropertiesKt.setSelected($this$semantics, ClockText$lambda$75($selected$delegate));
        return Unit.INSTANCE;
    }

    static final boolean ClockText$lambda$80$lambda$79$lambda$78(CoroutineScope $scope, AnalogTimePickerState $state, float $maxDist, boolean $autoSwitchToMinute, MutableState $center$delegate, MutableState $parentCenter$delegate) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new TimePickerKt$ClockText$2$1$1$1($state, $maxDist, $autoSwitchToMinute, $center$delegate, $parentCenter$delegate, null), 3, null);
        return true;
    }

    static final Unit ClockText$lambda$83$lambda$82$lambda$81(String $contentDescription, SemanticsPropertyReceiver $this$clearAndSetSemantics) {
        SemanticsPropertiesKt.setContentDescription($this$clearAndSetSemantics, $contentDescription);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: timeInputOnChange-_K77t-0, reason: not valid java name */
    public static final void m3123timeInputOnChange_K77t0(int selection, TimePickerState state, TextFieldValue value, TextFieldValue prevValue, int max, Ref<Boolean> ref, Function1<? super TextFieldValue, Unit> function1) {
        int newValue;
        TextFieldValue textFieldValueM7574copy3r_uNRQ$default;
        int i = 0;
        ref.setValue(false);
        if (Intrinsics.areEqual(value.getText(), prevValue.getText())) {
            function1.invoke(value);
            return;
        }
        int i2 = 12;
        if (value.getText().length() == 0) {
            if (TimePickerSelectionMode.m3138equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI())) {
                if (isPm(state) && !state.getIs24hour()) {
                    i = 12;
                }
                state.setHour(i);
            } else {
                state.setMinute(0);
            }
            function1.invoke(TextFieldValue.m7574copy3r_uNRQ$default(value, "", 0L, (TextRange) null, 6, (Object) null));
            return;
        }
        try {
            if (value.getText().length() == 3 && TextRange.m7328getStartimpl(value.getSelection()) == 1) {
                newValue = CharsKt.digitToInt(value.getText().charAt(0));
            } else {
                newValue = Integer.parseInt(value.getText());
            }
            if (newValue <= max) {
                if (TimePickerSelectionMode.m3138equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI())) {
                    if (newValue != 12 || !isPm(state)) {
                        if (newValue == 12 && !isPm(state) && !state.getIs24hour()) {
                            i2 = 0;
                        } else {
                            if (!isPm(state) || state.getIs24hour()) {
                                i2 = 0;
                            }
                            i2 += newValue;
                        }
                    }
                    state.setHour(i2);
                    if (newValue > 1 && !state.getIs24hour()) {
                        state.mo2065setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI());
                    }
                } else {
                    state.setMinute(newValue);
                }
                if (value.getText().length() > 2) {
                    textFieldValueM7574copy3r_uNRQ$default = TextFieldValue.m7574copy3r_uNRQ$default(value, String.valueOf(value.getText().charAt(0)), 0L, (TextRange) null, 6, (Object) null);
                } else {
                    textFieldValueM7574copy3r_uNRQ$default = value;
                }
                function1.invoke(textFieldValueM7574copy3r_uNRQ$default);
            }
        } catch (NumberFormatException e) {
        } catch (IllegalArgumentException e2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:100:0x012c  */
    /* JADX WARN: Code duplicated, block: B:103:0x0134  */
    /* JADX WARN: Code duplicated, block: B:106:0x0155  */
    /* JADX WARN: Code duplicated, block: B:110:0x017e  */
    /* JADX WARN: Code duplicated, block: B:114:0x028b  */
    /* JADX WARN: Code duplicated, block: B:117:0x0297  */
    /* JADX WARN: Code duplicated, block: B:118:0x029b  */
    /* JADX WARN: Code duplicated, block: B:121:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:123:0x02db  */
    /* JADX WARN: Code duplicated, block: B:124:0x02de  */
    /* JADX WARN: Code duplicated, block: B:128:0x0328  */
    /* JADX WARN: Code duplicated, block: B:130:0x0353  */
    /* JADX WARN: Code duplicated, block: B:131:0x0358  */
    /* JADX WARN: Code duplicated, block: B:133:0x0390  */
    /* JADX WARN: Code duplicated, block: B:136:0x03b3  */
    /* JADX WARN: Code duplicated, block: B:137:0x03bd  */
    /* JADX WARN: Code duplicated, block: B:140:0x0438  */
    /* JADX WARN: Code duplicated, block: B:143:0x0444  */
    /* JADX WARN: Code duplicated, block: B:144:0x0448  */
    /* JADX WARN: Code duplicated, block: B:147:0x0476  */
    /* JADX WARN: Code duplicated, block: B:149:0x0488  */
    /* JADX WARN: Code duplicated, block: B:150:0x048b  */
    /* JADX WARN: Code duplicated, block: B:154:0x0503  */
    /* JADX WARN: Code duplicated, block: B:157:0x050e  */
    /* JADX WARN: Code duplicated, block: B:161:0x06bb  */
    /* JADX WARN: Code duplicated, block: B:165:0x06df  */
    /* JADX WARN: Code duplicated, block: B:166:0x06e9  */
    /* JADX WARN: Code duplicated, block: B:169:0x0769  */
    /* JADX WARN: Code duplicated, block: B:171:0x076d  */
    /* JADX WARN: Code duplicated, block: B:174:0x0776  */
    /* JADX WARN: Code duplicated, block: B:176:0x077a  */
    /* JADX WARN: Code duplicated, block: B:180:0x0785  */
    /* JADX WARN: Code duplicated, block: B:183:0x0790  */
    /* JADX WARN: Code duplicated, block: B:186:0x079d  */
    /* JADX WARN: Code duplicated, block: B:189:0x07be  */
    /* JADX WARN: Code duplicated, block: B:191:0x07c4  */
    /* JADX WARN: Code duplicated, block: B:194:0x07d2  */
    /* JADX WARN: Code duplicated, block: B:196:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x0109  */
    /* JADX WARN: Code duplicated, block: B:92:0x010b  */
    /* JADX WARN: Code duplicated, block: B:95:0x0114 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:96:0x0116  */
    /* JADX WARN: Code duplicated, block: B:97:0x011f  */
    /* JADX WARN: Code duplicated, block: B:99:0x0123  */
    /* JADX INFO: renamed from: TimePickerTextField-1vLObsk, reason: not valid java name */
    public static final void m3113TimePickerTextField1vLObsk(final Modifier modifier, final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, TimePickerState state, int selection, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, final TimePickerColors colors, Composer $composer, final int $changed, final int i) {
        KeyboardOptions keyboardOptions2;
        KeyboardActions keyboardActions2;
        int i2;
        boolean z;
        Composer $composer2;
        final TimePickerState timePickerState;
        final KeyboardOptions keyboardOptions3;
        final KeyboardActions keyboardActions3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        KeyboardOptions keyboardOptions4;
        KeyboardActions keyboardActions4;
        Object it$iv;
        Object it$iv2;
        FocusRequester focusRequester;
        boolean selected;
        int compositeKeyHash$iv$iv;
        CompositionLocalMap localMap$iv$iv;
        Function0<ComposeUiNode> constructor;
        int $dirty;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        CompositionLocalMap localMap$iv$iv2;
        Composer $composer$iv;
        int i3;
        Composer $composer3;
        int iM3369constructorimpl;
        int compositeKeyHash$iv$iv2;
        CompositionLocalMap localMap$iv$iv3;
        Modifier materialized$iv$iv;
        Function0<ComposeUiNode> constructor2;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        Composer $composer4;
        CompositionLocalMap localMap$iv$iv4;
        Function0<ComposeUiNode> function0;
        boolean invalid$iv;
        Object value$iv;
        Modifier materialized$iv$iv2;
        Object it$iv3;
        int iM3369constructorimpl2;
        boolean z2;
        boolean invalid$iv2;
        Object it$iv4;
        int minute;
        final int i4 = selection;
        Composer $composer5 = $composer.startRestartGroup(1299172990);
        ComposerKt.sourceInformation($composer5, "C(TimePickerTextField)N(modifier,value,onValueChange,state,selection:c#material3.TimePickerSelectionMode,keyboardOptions,keyboardActions,colors)1867@72322L39,1868@72387L29,1870@72477L243,1876@72773L3414,1962@76225L103,1962@76193L135:TimePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer5.changed(modifier) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer5.changed(value) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer5.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= ($changed & 4096) == 0 ? $composer5.changed(state) : $composer5.changedInstance(state) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer5.changed(i4) ? 16384 : 8192;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            keyboardOptions2 = keyboardOptions;
        } else if ((196608 & $changed) == 0) {
            keyboardOptions2 = keyboardOptions;
            $dirty2 |= $composer5.changed(keyboardOptions2) ? 131072 : 65536;
        } else {
            keyboardOptions2 = keyboardOptions;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            keyboardActions2 = keyboardActions;
        } else if ((1572864 & $changed) == 0) {
            keyboardActions2 = keyboardActions;
            $dirty2 |= $composer5.changed(keyboardActions2) ? 1048576 : 524288;
        } else {
            keyboardActions2 = keyboardActions;
        }
        if ((i & 128) == 0) {
            if ((12582912 & $changed) == 0) {
                i2 = $composer5.changed(colors) ? 8388608 : 4194304;
            }
            if ((4793491 & $dirty2) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if ($composer5.shouldExecute(z, $dirty2 & 1)) {
                if (i5 != 0) {
                    keyboardOptions4 = KeyboardOptions.INSTANCE.getDefault();
                } else {
                    keyboardOptions4 = keyboardOptions2;
                }
                if (i6 != 0) {
                    keyboardActions4 = KeyboardActions.INSTANCE.getDefault();
                } else {
                    keyboardActions4 = keyboardActions2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1299172990, $dirty2, -1, "androidx.compose.material3.TimePickerTextField (TimePicker.kt:1866)");
                }
                ComposerKt.sourceInformationMarkerStart($composer5, -1330225659, "CC(remember):TimePicker.kt#9igjgp");
                it$iv = $composer5.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = InteractionSourceKt.MutableInteractionSource();
                    $composer5.updateRememberedValue(value$iv2);
                    it$iv = value$iv2;
                }
                final MutableInteractionSource interactionSource = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer5);
                ComposerKt.sourceInformationMarkerStart($composer5, -1330223589, "CC(remember):TimePicker.kt#9igjgp");
                it$iv2 = $composer5.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new FocusRequester();
                    $composer5.updateRememberedValue(value$iv3);
                    it$iv2 = value$iv3;
                }
                focusRequester = (FocusRequester) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer5);
                final TextFieldColors textFieldColors = OutlinedTextFieldDefaults.INSTANCE.m2708colors0hiis_0(colors.m3095timeSelectorContentColorvNxB06k$material3(true), 0L, 0L, 0L, colors.m3094timeSelectorContainerColorvNxB06k$material3(true), colors.m3094timeSelectorContainerColorvNxB06k$material3(true), 0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer5, 0, 0, 0, 0, 3072, 2147483598, 4095);
                selected = TimePickerSelectionMode.m3138equalsimpl0(i4, state.mo2063getSelectionyecRtBI());
                int $changed$iv = $dirty2 & 14;
                ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                int $changed$iv$iv = ($changed$iv << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer5, modifier);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                $dirty = $dirty2;
                ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer5.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer5.startReusableNode();
                if ($composer5.getInserting()) {
                    $composer5.createNode(constructor);
                } else {
                    $composer5.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer5);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    localMap$iv$iv2 = localMap$iv$iv;
                } else {
                    localMap$iv$iv2 = localMap$iv$iv;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i7 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer5, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i8 = (($changed$iv >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer5, 2023499365, "C1893@73347L240,1901@73597L2050,1948@75744L2,1950@75783L244,1957@76094L5,1958@76165L5,1947@75657L524:TimePicker.kt#uh7d8r");
                    if (selected) {
                        $composer$iv = $composer5;
                        i3 = i4;
                        $composer3 = $composer5;
                        $composer3.startReplaceGroup(2023879982);
                        $composer3.endReplaceGroup();
                    } else {
                        $composer5.startReplaceGroup(2023424468);
                        ComposerKt.sourceInformation($composer5, "1878@72840L450");
                        Modifier modifierM867sizeVpY3zN4 = SizeKt.m867sizeVpY3zN4(Modifier.INSTANCE, TimeInputTokens.INSTANCE.m4170getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m4169getTimeFieldContainerHeightD9Ej5fM());
                        if (TimePickerSelectionMode.m3138equalsimpl0(i4, TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI())) {
                            minute = getHourForDisplay(state);
                        } else {
                            minute = state.getMinute();
                        }
                        $composer$iv = $composer5;
                        m3114TimeSelectorSAnMeKU(modifierM867sizeVpY3zN4, minute, state, i4, colors, $composer5, (($dirty >> 3) & 896) | 6 | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344));
                        i3 = i4;
                        $composer3 = $composer5;
                        $composer3.endReplaceGroup();
                    }
                    if (TimePickerSelectionMode.m3138equalsimpl0(i3, TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI())) {
                        Strings.Companion companion = Strings.INSTANCE;
                        iM3369constructorimpl = Strings.m3369constructorimpl(R.string.m3c_time_picker_minute_text_field);
                    } else {
                        Strings.Companion companion2 = Strings.INSTANCE;
                        iM3369constructorimpl = Strings.m3369constructorimpl(R.string.m3c_time_picker_hour_text_field);
                    }
                    final String contentDescription = Strings_androidKt.m3448getString2EP1pXo(iM3369constructorimpl, $composer3, 0);
                    Modifier modifier$iv = visible(Modifier.INSTANCE, selected);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv2 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                    materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        $composer3.createNode(constructor2);
                    } else {
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                    $composer4 = $composer3;
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                        localMap$iv$iv4 = localMap$iv$iv3;
                        function0 = constructor2;
                    } else {
                        localMap$iv$iv4 = localMap$iv$iv3;
                        function0 = constructor2;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i9 = ($changed$iv$iv$iv2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1787852671, "C1908@73940L48,1912@74190L7,1919@74491L11,1920@74559L11,1924@74721L916,1902@73643L1994:TimePicker.kt#uh7d8r");
                        Modifier modifierM867sizeVpY3zN5 = SizeKt.m867sizeVpY3zN4(FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester), TimeInputTokens.INSTANCE.m4170getTimeFieldContainerWidthD9Ej5fM(), TimeInputTokens.INSTANCE.m4169getTimeFieldContainerHeightD9Ej5fM());
                        ComposerKt.sourceInformationMarkerStart($composer4, 1443153546, 
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x04ef: INVOKE 
                              (r55v1 '$composer4' androidx.compose.runtime.Composer A[D('$composer' androidx.compose.runtime.Composer)])
                              (1443153546 int)
                              (r120v1 ?? I:??[OBJECT, ARRAY])
                             STATIC call: androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(androidx.compose.runtime.Composer, int, java.lang.String):void A[MD:(androidx.compose.runtime.Composer, int, java.lang.String):void (m)] (LINE:1909) in method: androidx.compose.material3.TimePickerKt.TimePickerTextField-1vLObsk(androidx.compose.ui.Modifier, androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit>, androidx.compose.material3.TimePickerState, int, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, androidx.compose.material3.TimePickerColors, androidx.compose.runtime.Composer, int, int):void, file: classes10.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r120v1 ??
                            	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                            */
                        /*
                            Method dump skipped, instruction units count: 2023
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.m3113TimePickerTextField1vLObsk(androidx.compose.ui.Modifier, androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.material3.TimePickerState, int, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, androidx.compose.material3.TimePickerColors, androidx.compose.runtime.Composer, int, int):void");
                    }

                    static final Unit TimePickerTextField_1vLObsk$lambda$92$lambda$89$lambda$88$lambda$87(String $contentDescription, SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setContentDescription($this$semantics, $contentDescription);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static final void CircularLayout(Modifier modifier, final float radiusToSizeRatio, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
                        Modifier modifier2;
                        final Modifier modifier3;
                        Modifier.Companion modifier4;
                        Composer $composer2 = $composer.startRestartGroup(-1041042571);
                        ComposerKt.sourceInformation($composer2, "C(CircularLayout)N(modifier,radiusToSizeRatio,content)1979@76688L1660,1979@76641L1707:TimePicker.kt#uh7d8r");
                        int $dirty = $changed;
                        int i2 = i & 1;
                        if (i2 != 0) {
                            $dirty |= 6;
                            modifier2 = modifier;
                        } else if (($changed & 6) == 0) {
                            modifier2 = modifier;
                            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
                        } else {
                            modifier2 = modifier;
                        }
                        if ((i & 2) != 0) {
                            $dirty |= 48;
                        } else if (($changed & 48) == 0) {
                            $dirty |= $composer2.changed(radiusToSizeRatio) ? 32 : 16;
                        }
                        if ((i & 4) != 0) {
                            $dirty |= 384;
                        } else if (($changed & 384) == 0) {
                            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
                        }
                        int $dirty2 = $dirty;
                        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
                            $composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                        } else {
                            if (i2 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1041042571, $dirty2, -1, "androidx.compose.material3.CircularLayout (TimePicker.kt:1978)");
                            }
                            ComposerKt.sourceInformationMarkerStart($composer2, 285478289, "CC(remember):TimePicker.kt#9igjgp");
                            boolean invalid$iv = ($dirty2 & 112) == 32;
                            Object it$iv = $composer2.rememberedValue();
                            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                Object value$iv = (MeasurePolicy) new TimePickerKt$CircularLayout$1$1(radiusToSizeRatio);
                                $composer2.updateRememberedValue(value$iv);
                                it$iv = value$iv;
                            }
                            MeasurePolicy measurePolicy = (MeasurePolicy) it$iv;
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            int $changed$iv = (($dirty2 >> 6) & 14) | (($dirty2 << 3) & 112);
                            Modifier modifier$iv = modifier4;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
                            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
                            Modifier modifier5 = modifier4;
                            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                                $composer2.createNode(constructor);
                            } else {
                                $composer2.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            function2.invoke($composer2, Integer.valueOf(($changed$iv$iv >> 6) & 14));
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier5;
                        }
                        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerKt$$ExternalSyntheticLambda33
                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(Object obj, Object obj2) {
                                    return TimePickerKt.CircularLayout$lambda$96(modifier3, radiusToSizeRatio, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }

                    /* JADX INFO: renamed from: numberContentDescription-dSwYdS4, reason: not valid java name */
                    public static final String m3121numberContentDescriptiondSwYdS4(int selection, boolean is24Hour, int number, Composer $composer, int $changed) {
                        int id;
                        ComposerKt.sourceInformationMarkerStart($composer, 194237364, "C(numberContentDescription)N(selection:c#material3.TimePickerSelectionMode,is24Hour,number)2029@78784L21:TimePicker.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(194237364, $changed, -1, "androidx.compose.material3.numberContentDescription (TimePicker.kt:2019)");
                        }
                        if (TimePickerSelectionMode.m3138equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI())) {
                            Strings.Companion companion = Strings.INSTANCE;
                            id = Strings.m3369constructorimpl(R.string.m3c_time_picker_minute_suffix);
                        } else if (is24Hour) {
                            Strings.Companion companion2 = Strings.INSTANCE;
                            id = Strings.m3369constructorimpl(R.string.m3c_time_picker_hour_24h_suffix);
                        } else {
                            Strings.Companion companion3 = Strings.INSTANCE;
                            id = Strings.m3369constructorimpl(R.string.m3c_time_picker_hour_suffix);
                        }
                        String strM3449getStringqBjtwXw = Strings_androidKt.m3449getStringqBjtwXw(id, new Object[]{Integer.valueOf(number)}, $composer, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        return strM3449getStringqBjtwXw;
                    }

                    private static final float dist(float x1, float y1, int x2, int y2) {
                        float x = x2 - x1;
                        float y = y2 - y1;
                        return (float) Math.hypot(x, y);
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static final float atan(float y, float x) {
                        float ret = ((float) Math.atan2(y, x)) - 1.5707964f;
                        return ret < 0.0f ? FullCircle + ret : ret;
                    }

                    public static final int getDefaultTimePickerLayoutType(Composer $composer, int $changed) {
                        ComposerKt.sourceInformationMarkerStart($composer, 435687004, "C(<get-defaultTimePickerLayoutType>)2051@79436L29:TimePicker.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(435687004, $changed, -1, "androidx.compose.material3.<get-defaultTimePickerLayoutType> (TimePicker.kt:2051)");
                        }
                        int iDefaultTimePickerLayoutType = TimePicker_androidKt.defaultTimePickerLayoutType($composer, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        return iDefaultTimePickerLayoutType;
                    }

                    static {
                        float arg0$iv = Dp.m7905constructorimpl(101);
                        float other$iv = TimePickerTokens.INSTANCE.m4172getClockDialContainerSizeD9Ej5fM();
                        OuterCircleToSizeRatio = arg0$iv / other$iv;
                        float arg0$iv2 = Dp.m7905constructorimpl(69);
                        float other$iv2 = TimePickerTokens.INSTANCE.m4172getClockDialContainerSizeD9Ej5fM();
                        InnerCircleToSizeRatio = arg0$iv2 / other$iv2;
                        ClockDisplayBottomMargin = Dp.m7905constructorimpl(36);
                        ClockFaceBottomMargin = Dp.m7905constructorimpl(24);
                        DisplaySeparatorWidth = Dp.m7905constructorimpl(24);
                        SupportLabelTop = Dp.m7905constructorimpl(7);
                        TimeInputBottomPadding = Dp.m7905constructorimpl(24);
                        MaxDistance = Dp.m7905constructorimpl(74);
                        MinimumInteractiveSize = Dp.m7905constructorimpl(48);
                        Minutes = IntListKt.intListOf(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55);
                        Hours = IntListKt.intListOf(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
                        MutableIntList $this$ExtraHours_u24lambda_u2498 = new MutableIntList(Hours._size);
                        IntList this_$iv = Hours;
                        int[] content$iv = this_$iv.content;
                        int i = this_$iv._size;
                        for (int i$iv = 0; i$iv < i; i$iv++) {
                            int it = content$iv[i$iv];
                            $this$ExtraHours_u24lambda_u2498.add((it % 12) + 12);
                        }
                        ExtraHours = $this$ExtraHours_u24lambda_u2498;
                        PeriodToggleMargin = Dp.m7905constructorimpl(12);
                        TimePickerMaxHeight = Dp.m7905constructorimpl(384);
                        TimePickerMidHeight = Dp.m7905constructorimpl(330);
                        ClockDialMidContainerSize = Dp.m7905constructorimpl(238);
                        ClockDialMinContainerSize = Dp.m7905constructorimpl(200);
                    }

                    public static final float getClockDialMinContainerSize() {
                        return ClockDialMinContainerSize;
                    }

                    private static final Modifier visible(Modifier $this$visible, final boolean visible) {
                        return $this$visible.then(new VisibleModifier(visible, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TimePickerKt$visible$$inlined$debugInspectorInfo$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                                invoke2(inspectorInfo);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(InspectorInfo $this$null) {
                                $this$null.setName("visible");
                                $this$null.getProperties().set("visible", Boolean.valueOf(visible));
                            }
                        } : InspectableValueKt.getNoInspectorInfo()));
                    }
                }
