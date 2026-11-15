package androidx.compose.material3;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.tokens.AssistChipTokens;
import androidx.compose.material3.tokens.FilterChipTokens;
import androidx.compose.material3.tokens.InputChipTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.SuggestionChipTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Chip.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a¨\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001a¦\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0018\u001a¨\u0001\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001a¦\u0001\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0018\u001a°\u0001\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u001c2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\u001e\u001a°\u0001\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u001c2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\u001e\u001aÇ\u0001\u0010 \u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010!\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u001c2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\"\u001a\u0091\u0001\u0010#\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010$\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010%\u001a\u008f\u0001\u0010#\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010$\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010&\u001a\u0091\u0001\u0010'\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010$\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010%\u001a\u008f\u0001\u0010'\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010$\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010&\u001a¸\u0001\u0010(\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\b\u001a\u00020\t2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0003¢\u0006\u0004\b1\u00102\u001aÍ\u0001\u00103\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\b\u001a\u00020\t2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010)\u001a\u00020*2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010!\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001c2\b\u0010\u0010\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0003¢\u0006\u0004\b4\u00105\u001a\u0091\u0001\u00106\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010!\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u00107\u001a\u00020,2\u0006\u00108\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0003¢\u0006\u0004\b9\u0010:\u001a\u0091\u0001\u0010;\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010!\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u00107\u001a\u00020,2\u0006\u00108\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0003¢\u0006\u0004\b<\u0010:\u001aN\u0010=\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010!\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u00107\u001a\u00020,H\u0003¢\u0006\u0004\b>\u0010?\u001a9\u0010@\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0006\u00108\u001a\u00020,H\u0003¢\u0006\u0004\bA\u0010B\u001a%\u0010C\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001HE0D\"\u0004\b\u0000\u0010E2\b\u0010F\u001a\u0004\u0018\u0001HEH\u0003¢\u0006\u0002\u0010G\u001a&\u0010L\u001a\u0002002\b\b\u0002\u0010M\u001a\u00020\t2\b\b\u0002\u0010N\u001a\u00020\t2\b\b\u0002\u0010O\u001a\u00020\tH\u0002\"\u0018\u0010H\u001a\u00020\u000f*\u00020I8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010K\"\u0010\u0010P\u001a\u00020.X\u0082\u0004¢\u0006\u0004\n\u0002\u0010Q\"\u000e\u0010R\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010S\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010T\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010U\u001a\u00020VX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010W\u001a\u00020VX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010X\u001a\u00020VX\u0082T¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"AssistChip", "", "onClick", "Lkotlin/Function0;", "label", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", ChipKt.LeadingIconLayoutId, ChipKt.TrailingIconLayoutId, "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/ChipColors;", "elevation", "Landroidx/compose/material3/ChipElevation;", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/material3/ChipBorder;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/material3/ChipBorder;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "ElevatedAssistChip", "FilterChip", "selected", "Landroidx/compose/material3/SelectableChipColors;", "Landroidx/compose/material3/SelectableChipElevation;", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "ElevatedFilterChip", "InputChip", "avatar", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "SuggestionChip", "icon", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/material3/ChipBorder;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "ElevatedSuggestionChip", "Chip", "labelTextStyle", "Landroidx/compose/ui/text/TextStyle;", "labelColor", "Landroidx/compose/ui/graphics/Color;", "minHeight", "Landroidx/compose/ui/unit/Dp;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "Chip-nkUnTEs", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;JLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SelectableChip", "SelectableChip-u0RnIRE", "(ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "ChipContent", "leadingIconColor", "trailingIconColor", "ChipContent-fe0OD_I", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;JLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)V", "AnimatingChipContent", "AnimatingChipContent-fe0OD_I", "leadingContent", "leadingContent-XO-JAsU", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JLandroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "trailingContent", "trailingContent-RPmYEkk", "(Lkotlin/jvm/functions/Function2;JLandroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "rememberRetainedState", "Landroidx/compose/runtime/State;", ExifInterface.GPS_DIRECTION_TRUE, "targetValue", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "defaultSuggestionChipColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultSuggestionChipColors", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/ChipColors;", "inputChipPadding", "hasAvatar", "hasLeadingIcon", "hasTrailingIcon", "HorizontalElementsPadding", "F", "AssistChipPadding", "FilterChipPadding", "SuggestionChipPadding", "LeadingIconLayoutId", "", "LabelLayoutId", "TrailingIconLayoutId", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ChipKt {
    private static final String LabelLayoutId = "label";
    private static final String LeadingIconLayoutId = "leadingIcon";
    private static final String TrailingIconLayoutId = "trailingIcon";
    private static final float HorizontalElementsPadding = Dp.m7905constructorimpl(8);
    private static final PaddingValues AssistChipPadding = PaddingKt.m814PaddingValuesYgX7TsA$default(HorizontalElementsPadding, 0.0f, 2, null);
    private static final PaddingValues FilterChipPadding = PaddingKt.m814PaddingValuesYgX7TsA$default(HorizontalElementsPadding, 0.0f, 2, null);
    private static final PaddingValues SuggestionChipPadding = PaddingKt.m814PaddingValuesYgX7TsA$default(HorizontalElementsPadding, 0.0f, 2, null);

    static final Unit AnimatingChipContent_fe0OD_I$lambda$24(Function2 function2, TextStyle textStyle, long j, Function2 function3, Function2 function4, Function2 function5, long j2, long j3, float f, PaddingValues paddingValues, int i, Composer composer, int i2) {
        m2188AnimatingChipContentfe0OD_I(function2, textStyle, j, function3, function4, function5, j2, j3, f, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit AssistChip$lambda$0(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function3, Function2 function4, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        AssistChip((Function0<Unit>) function0, (Function2<? super Composer, ? super Integer, Unit>) function2, modifier, z, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, shape, chipColors, chipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit AssistChip$lambda$2(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function3, Function2 function4, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        AssistChip(function0, function2, modifier, z, function3, function4, shape, chipColors, chipElevation, chipBorder, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit ChipContent_fe0OD_I$lambda$23(Function2 function2, TextStyle textStyle, long j, Function2 function3, Function2 function4, Function2 function5, long j2, long j3, float f, PaddingValues paddingValues, int i, Composer composer, int i2) {
        m2190ChipContentfe0OD_I(function2, textStyle, j, function3, function4, function5, j2, j3, f, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Chip_nkUnTEs$lambda$18(Modifier modifier, Function0 function0, boolean z, Function2 function2, TextStyle textStyle, long j, Function2 function3, Function2 function4, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, float f, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        m2189ChipnkUnTEs(modifier, function0, z, function2, textStyle, j, function3, function4, shape, chipColors, chipElevation, borderStroke, f, paddingValues, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    static final Unit ElevatedAssistChip$lambda$3(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function3, Function2 function4, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        ElevatedAssistChip((Function0<Unit>) function0, (Function2<? super Composer, ? super Integer, Unit>) function2, modifier, z, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, shape, chipColors, chipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit ElevatedAssistChip$lambda$5(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function3, Function2 function4, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        ElevatedAssistChip(function0, function2, modifier, z, function3, function4, shape, chipColors, chipElevation, chipBorder, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit ElevatedFilterChip$lambda$7(boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function3, Function2 function4, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        ElevatedFilterChip(z, function0, function2, modifier, z2, function3, function4, shape, selectableChipColors, selectableChipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit ElevatedSuggestionChip$lambda$12(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function3, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        ElevatedSuggestionChip((Function0<Unit>) function0, (Function2<? super Composer, ? super Integer, Unit>) function2, modifier, z, (Function2<? super Composer, ? super Integer, Unit>) function3, shape, chipColors, chipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ElevatedSuggestionChip$lambda$14(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function3, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        ElevatedSuggestionChip(function0, function2, modifier, z, function3, shape, chipColors, chipElevation, chipBorder, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit FilterChip$lambda$6(boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function3, Function2 function4, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        FilterChip(z, function0, function2, modifier, z2, function3, function4, shape, selectableChipColors, selectableChipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit InputChip$lambda$8(boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function3, Function2 function4, Function2 function5, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        InputChip(z, function0, function2, modifier, z2, function3, function4, function5, shape, selectableChipColors, selectableChipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit SelectableChip_u0RnIRE$lambda$22(boolean z, Modifier modifier, Function0 function0, boolean z2, Function2 function2, TextStyle textStyle, Function2 function3, Function2 function4, Function2 function5, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, float f, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        m2191SelectableChipu0RnIRE(z, modifier, function0, z2, function2, textStyle, function3, function4, function5, shape, selectableChipColors, selectableChipElevation, borderStroke, f, paddingValues, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    static final Unit SuggestionChip$lambda$11(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function3, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        SuggestionChip(function0, function2, modifier, z, function3, shape, chipColors, chipElevation, chipBorder, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SuggestionChip$lambda$9(Function0 function0, Function2 function2, Modifier modifier, boolean z, Function2 function3, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        SuggestionChip((Function0<Unit>) function0, (Function2<? super Composer, ? super Integer, Unit>) function2, modifier, z, (Function2<? super Composer, ? super Integer, Unit>) function3, shape, chipColors, chipElevation, borderStroke, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void AssistChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        ChipColors colors2;
        int $dirty;
        Composer $composer2;
        final Shape shape2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final ChipColors colors3;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        int $dirty2;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final boolean enabled2;
        final ChipElevation elevation2;
        Shape shape3;
        int $dirty3;
        ChipColors colors4;
        int $dirty4;
        int i2;
        ChipElevation elevation3;
        boolean enabled3;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        BorderStroke border4;
        Function2<? super Composer, ? super Integer, Unit> function10;
        ChipElevation elevation4;
        int $dirty5;
        Modifier modifier4;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Shape shape4;
        Composer $composer3 = $composer.startRestartGroup(1192083339);
        ComposerKt.sourceInformation($composer3, "C(AssistChip)N(onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)151@7455L5,146@7287L542:Chip.kt#uh7d8r");
        int $dirty6 = $changed;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty6 |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
            function5 = function2;
        } else if (($changed & 48) == 0) {
            function5 = function2;
            $dirty6 |= $composer3.changedInstance(function5) ? 32 : 16;
        } else {
            function5 = function2;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty6 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty6 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty6 |= 24576;
            function6 = function3;
        } else if (($changed & 24576) == 0) {
            function6 = function3;
            $dirty6 |= $composer3.changedInstance(function6) ? 16384 : 8192;
        } else {
            function6 = function3;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function7 = function4;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function7 = function4;
            $dirty6 |= $composer3.changedInstance(function7) ? 131072 : 65536;
        } else {
            function7 = function4;
        }
        if (($changed & 1572864) == 0) {
            $dirty6 |= ((i & 64) == 0 && $composer3.changed(shape)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 8388608 : 4194304;
                $dirty6 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty6 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            $dirty6 |= ((i & 256) == 0 && $composer3.changed(elevation)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty6 |= ((i & 512) == 0 && $composer3.changed(border)) ? 536870912 : 268435456;
        }
        int $dirty1 = $changed1;
        int $dirty7 = i & 1024;
        if ($dirty7 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 4 : 2;
        }
        int $dirty8 = $dirty1;
        int $dirty9 = $dirty6;
        if ($composer3.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty8 & 3) == 2) ? false : true, $dirty9 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "140@7003L5,141@7054L18,142@7125L21,143@7195L25");
            $dirty = $dirty8;
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i4 != 0 ? true : z;
                if (i5 != 0) {
                    function6 = null;
                }
                Function2<? super Composer, ? super Integer, Unit> function12 = i6 != 0 ? null : function7;
                if ((i & 64) != 0) {
                    $dirty3 = $dirty9 & (-3670017);
                    shape3 = AssistChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape3 = shape;
                    $dirty3 = $dirty9;
                }
                if ((i & 128) != 0) {
                    colors4 = AssistChipDefaults.INSTANCE.assistChipColors($composer3, 6);
                    $dirty4 = $dirty3 & (-29360129);
                } else {
                    colors4 = colors2;
                    $dirty4 = $dirty3;
                }
                if ((i & 256) != 0) {
                    i2 = 6;
                    $dirty4 &= -234881025;
                    elevation3 = AssistChipDefaults.INSTANCE.m2099assistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    i2 = 6;
                    elevation3 = elevation;
                }
                if ((i & 512) != 0) {
                    enabled3 = enabled4;
                    border3 = AssistChipDefaults.INSTANCE.m2097assistChipBorderh1eTWw(enabled3, 0L, 0L, 0.0f, $composer3, (($dirty4 >> 9) & 14) | 24576, 14);
                    $dirty4 &= -1879048193;
                } else {
                    enabled3 = enabled4;
                    border3 = border;
                }
                if ($dirty7 != 0) {
                    border4 = border3;
                    interactionSource3 = null;
                    function10 = function6;
                    elevation4 = elevation3;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier4 = modifier5;
                    function11 = function12;
                    shape4 = shape3;
                } else {
                    interactionSource3 = interactionSource;
                    border4 = border3;
                    function10 = function6;
                    elevation4 = elevation3;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier4 = modifier5;
                    function11 = function12;
                    shape4 = shape3;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty10 = (i & 64) != 0 ? $dirty9 & (-3670017) : $dirty9;
                if ((i & 128) != 0) {
                    $dirty10 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty10 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty10 &= -1879048193;
                }
                shape4 = shape;
                elevation4 = elevation;
                border4 = border;
                interactionSource3 = interactionSource;
                $dirty5 = $dirty10;
                i2 = 6;
                function11 = function7;
                enabled3 = z;
                function10 = function6;
                $dirty = $dirty;
                modifier4 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1192083339, $dirty5, $dirty, "androidx.compose.material3.AssistChip (Chip.kt:146)");
            }
            boolean enabled5 = enabled3;
            ChipColors colors5 = colors2;
            $composer2 = $composer3;
            m2189ChipnkUnTEs(modifier4, function0, enabled5, function5, TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer3, i2), colors2.m2179labelColorvNxB06k$material3(enabled3), function10, function11, shape4, colors5, elevation4, border4, AssistChipDefaults.INSTANCE.m2102getHeightD9Ej5fM(), AssistChipPadding, interactionSource3, $composer2, (($dirty5 >> 6) & 14) | (($dirty5 << 3) & 112) | (($dirty5 >> 3) & 896) | (($dirty5 << 6) & 7168) | (($dirty5 << 6) & 3670016) | (($dirty5 << 6) & 29360128) | (($dirty5 << 6) & 234881024) | (($dirty5 << 6) & 1879048192), (($dirty5 >> 24) & 14) | 3456 | (($dirty5 >> 24) & 112) | (($dirty << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled2 = enabled5;
            function8 = function10;
            function9 = function11;
            shape2 = shape4;
            colors3 = colors5;
            elevation2 = elevation4;
            border2 = border4;
            interactionSource2 = interactionSource3;
            $dirty2 = $dirty5;
        } else {
            $dirty = $dirty8;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            border2 = border;
            interactionSource2 = interactionSource;
            colors3 = colors2;
            modifier3 = modifier2;
            function8 = function6;
            $dirty2 = $dirty9;
            function9 = function7;
            enabled2 = z;
            elevation2 = elevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.AssistChip$lambda$0(function0, function2, modifier3, enabled2, function8, function9, shape2, colors3, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with AssistChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "AssistChip(onClick, label, modifier, enabled,leadingIcon, trailingIcon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void AssistChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 leadingIcon, Function2 trailingIcon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2 function2;
        Modifier modifier2;
        boolean enabled2;
        Function2 leadingIcon2;
        Function2 function3;
        ChipColors colors2;
        int $dirty;
        Composer $composer2;
        final Shape shape2;
        final ChipBorder border2;
        final MutableInteractionSource interactionSource2;
        final ChipColors colors3;
        final Modifier modifier3;
        final Function2 leadingIcon3;
        int $dirty2;
        final Function2 trailingIcon2;
        final boolean enabled3;
        final ChipElevation elevation2;
        Shape shape3;
        int $dirty3;
        ChipColors colors4;
        int $dirty4;
        int i2;
        Composer $composer3;
        ChipElevation elevation3;
        ChipBorder border3;
        MutableInteractionSource interactionSource3;
        ChipElevation elevation4;
        int $dirty5;
        Function2 leadingIcon4;
        Function2 trailingIcon3;
        Shape shape4;
        Modifier modifier4;
        State<BorderStroke> stateBorderStroke$material3;
        Composer $composer4 = $composer.startRestartGroup(1930061919);
        ComposerKt.sourceInformation($composer4, "C(AssistChip)N(onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)236@11549L5,231@11381L572:Chip.kt#uh7d8r");
        int $dirty6 = $changed;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty6 |= $composer4.changedInstance(onClick) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty6 |= $composer4.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty6 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer4.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty6 |= 24576;
            leadingIcon2 = leadingIcon;
        } else if (($changed & 24576) == 0) {
            leadingIcon2 = leadingIcon;
            $dirty6 |= $composer4.changedInstance(leadingIcon2) ? 16384 : 8192;
        } else {
            leadingIcon2 = leadingIcon;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function3 = trailingIcon;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function3 = trailingIcon;
            $dirty6 |= $composer4.changedInstance(function3) ? 131072 : 65536;
        } else {
            function3 = trailingIcon;
        }
        if (($changed & 1572864) == 0) {
            $dirty6 |= ((i & 64) == 0 && $composer4.changed(shape)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                int i7 = $composer4.changed(colors2) ? 8388608 : 4194304;
                $dirty6 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty6 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            $dirty6 |= ((i & 256) == 0 && $composer4.changed(elevation)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty6 |= ((i & 512) == 0 && $composer4.changed(border)) ? 536870912 : 268435456;
        }
        int $dirty1 = $changed1;
        int $dirty7 = i & 1024;
        if ($dirty7 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer4.changed(interactionSource) ? 4 : 2;
        }
        int $dirty8 = $dirty1;
        int $dirty9 = $dirty6;
        if ($composer4.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty8 & 3) == 2) ? false : true, $dirty9 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "225@11072L5,226@11123L18,227@11194L21,228@11262L18,229@11332L39");
            $dirty = $dirty8;
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i4 != 0 ? true : enabled2;
                if (i5 != 0) {
                    leadingIcon2 = null;
                }
                Function2 trailingIcon4 = i6 != 0 ? null : function3;
                if ((i & 64) != 0) {
                    $dirty3 = $dirty9 & (-3670017);
                    shape3 = AssistChipDefaults.INSTANCE.getShape($composer4, 6);
                } else {
                    shape3 = shape;
                    $dirty3 = $dirty9;
                }
                if ((i & 128) != 0) {
                    colors4 = AssistChipDefaults.INSTANCE.assistChipColors($composer4, 6);
                    $dirty4 = $dirty3 & (-29360129);
                } else {
                    colors4 = colors2;
                    $dirty4 = $dirty3;
                }
                if ((i & 256) != 0) {
                    $composer3 = $composer4;
                    i2 = 6;
                    $dirty4 &= -234881025;
                    elevation3 = AssistChipDefaults.INSTANCE.m2099assistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    i2 = 6;
                    $composer3 = $composer4;
                    elevation3 = elevation;
                }
                if ((i & 512) != 0) {
                    $composer4 = $composer3;
                    border3 = AssistChipDefaults.INSTANCE.m2096assistChipBorderd_3_b6Q(0L, 0L, 0.0f, $composer4, 3072, 7);
                    $dirty4 &= -1879048193;
                } else {
                    $composer4 = $composer3;
                    border3 = border;
                }
                if ($dirty7 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer4, -636337050, "CC(remember):Chip.kt#9igjgp");
                    Composer $this$cache$iv = $composer4;
                    Object it$iv = $this$cache$iv.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $this$cache$iv.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    interactionSource3 = (MutableInteractionSource) it$iv;
                    elevation4 = elevation3;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    leadingIcon4 = leadingIcon2;
                    trailingIcon3 = trailingIcon4;
                    shape4 = shape3;
                    modifier4 = modifier5;
                } else {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    leadingIcon4 = leadingIcon2;
                    trailingIcon3 = trailingIcon4;
                    shape4 = shape3;
                    modifier4 = modifier5;
                }
            } else {
                $composer4.skipToGroupEnd();
                int $dirty10 = (i & 64) != 0 ? $dirty9 & (-3670017) : $dirty9;
                if ((i & 128) != 0) {
                    $dirty10 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty10 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty10 &= -1879048193;
                }
                shape4 = shape;
                elevation4 = elevation;
                border3 = border;
                interactionSource3 = interactionSource;
                i2 = 6;
                trailingIcon3 = function3;
                leadingIcon4 = leadingIcon2;
                $dirty5 = $dirty10;
                modifier4 = modifier2;
                $dirty = $dirty;
            }
            $composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1930061919, $dirty5, $dirty, "androidx.compose.material3.AssistChip (Chip.kt:231)");
            }
            TextStyle value = TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer4, i2);
            long jM2179labelColorvNxB06k$material3 = colors2.m2179labelColorvNxB06k$material3(enabled2);
            if (border3 == null) {
                $composer4.startReplaceGroup(1748832781);
                $composer4.endReplaceGroup();
                stateBorderStroke$material3 = null;
            } else {
                $composer4.startReplaceGroup(-636322700);
                ComposerKt.sourceInformation($composer4, "243@11781L21");
                stateBorderStroke$material3 = border3.borderStroke$material3(enabled2, $composer4, (($dirty5 >> 9) & 14) | (($dirty5 >> 24) & 112));
                $composer4.endReplaceGroup();
            }
            ChipColors colors5 = colors2;
            $composer2 = $composer4;
            boolean enabled5 = enabled2;
            m2189ChipnkUnTEs(modifier4, onClick, enabled5, function2, value, jM2179labelColorvNxB06k$material3, leadingIcon4, trailingIcon3, shape4, colors5, elevation4, stateBorderStroke$material3 != null ? stateBorderStroke$material3.getValue() : null, AssistChipDefaults.INSTANCE.m2102getHeightD9Ej5fM(), AssistChipPadding, interactionSource3, $composer2, (($dirty5 >> 6) & 14) | (($dirty5 << 3) & 112) | (($dirty5 >> 3) & 896) | (($dirty5 << 6) & 7168) | (($dirty5 << 6) & 3670016) | (($dirty5 << 6) & 29360128) | (($dirty5 << 6) & 234881024) | (($dirty5 << 6) & 1879048192), (($dirty5 >> 24) & 14) | 3456 | (($dirty << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border3;
            modifier3 = modifier4;
            enabled3 = enabled5;
            leadingIcon3 = leadingIcon4;
            trailingIcon2 = trailingIcon3;
            shape2 = shape4;
            colors3 = colors5;
            elevation2 = elevation4;
            interactionSource2 = interactionSource3;
            $dirty2 = $dirty5;
        } else {
            $dirty = $dirty8;
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            border2 = border;
            interactionSource2 = interactionSource;
            colors3 = colors2;
            modifier3 = modifier2;
            leadingIcon3 = leadingIcon2;
            $dirty2 = $dirty9;
            trailingIcon2 = function3;
            enabled3 = enabled2;
            elevation2 = elevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.AssistChip$lambda$2(onClick, label, modifier3, enabled3, leadingIcon3, trailingIcon2, shape2, colors3, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ElevatedAssistChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        boolean enabled2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        ChipColors colors2;
        int $dirty;
        Composer $composer2;
        final Shape shape2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final ChipColors colors3;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        int $dirty2;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final boolean enabled3;
        final ChipElevation elevation2;
        Shape shape3;
        int $dirty3;
        ChipColors colors4;
        int $dirty4;
        int i2;
        ChipElevation elevation3;
        MutableInteractionSource interactionSource3;
        ChipElevation elevation4;
        BorderStroke border3;
        Function2<? super Composer, ? super Integer, Unit> function10;
        int $dirty5;
        Modifier modifier4;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Shape shape4;
        Composer $composer3 = $composer.startRestartGroup(2028863105);
        ComposerKt.sourceInformation($composer3, "C(ElevatedAssistChip)N(onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)310@15241L5,305@15073L542:Chip.kt#uh7d8r");
        int $dirty6 = $changed;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty6 |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
            function5 = function2;
        } else if (($changed & 48) == 0) {
            function5 = function2;
            $dirty6 |= $composer3.changedInstance(function5) ? 32 : 16;
        } else {
            function5 = function2;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty6 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty6 |= 24576;
            function6 = function3;
        } else if (($changed & 24576) == 0) {
            function6 = function3;
            $dirty6 |= $composer3.changedInstance(function6) ? 16384 : 8192;
        } else {
            function6 = function3;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function7 = function4;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function7 = function4;
            $dirty6 |= $composer3.changedInstance(function7) ? 131072 : 65536;
        } else {
            function7 = function4;
        }
        if (($changed & 1572864) == 0) {
            $dirty6 |= ((i & 64) == 0 && $composer3.changed(shape)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 8388608 : 4194304;
                $dirty6 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty6 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            $dirty6 |= ((i & 256) == 0 && $composer3.changed(elevation)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty1 = $changed1;
        int $dirty7 = i & 512;
        if ($dirty7 != 0) {
            $dirty6 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty6 |= $composer3.changed(border) ? 536870912 : 268435456;
        }
        int i8 = i & 1024;
        if (i8 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 4 : 2;
        }
        int $dirty8 = $dirty1;
        int $dirty9 = $dirty6;
        if ($composer3.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty8 & 3) == 2) ? false : true, $dirty9 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "299@14813L5,300@14864L26,301@14943L29");
            $dirty = $dirty8;
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i4 != 0 ? true : enabled2;
                if (i5 != 0) {
                    function6 = null;
                }
                Function2<? super Composer, ? super Integer, Unit> function12 = i6 != 0 ? null : function7;
                if ((i & 64) != 0) {
                    $dirty3 = $dirty9 & (-3670017);
                    shape3 = AssistChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape3 = shape;
                    $dirty3 = $dirty9;
                }
                if ((i & 128) != 0) {
                    colors4 = AssistChipDefaults.INSTANCE.elevatedAssistChipColors($composer3, 6);
                    $dirty4 = $dirty3 & (-29360129);
                } else {
                    colors4 = colors2;
                    $dirty4 = $dirty3;
                }
                if ((i & 256) != 0) {
                    i2 = 6;
                    elevation3 = AssistChipDefaults.INSTANCE.m2101elevatedAssistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty4 &= -234881025;
                } else {
                    i2 = 6;
                    elevation3 = elevation;
                }
                BorderStroke border4 = $dirty7 != 0 ? null : border;
                if (i8 != 0) {
                    elevation4 = elevation3;
                    border3 = border4;
                    interactionSource3 = null;
                    function10 = function6;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier4 = modifier5;
                    function11 = function12;
                    shape4 = shape3;
                } else {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    border3 = border4;
                    function10 = function6;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier4 = modifier5;
                    function11 = function12;
                    shape4 = shape3;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty10 = (i & 64) != 0 ? $dirty9 & (-3670017) : $dirty9;
                if ((i & 128) != 0) {
                    $dirty10 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty10 &= -234881025;
                }
                shape4 = shape;
                elevation4 = elevation;
                border3 = border;
                interactionSource3 = interactionSource;
                $dirty5 = $dirty10;
                i2 = 6;
                function11 = function7;
                $dirty = $dirty;
                function10 = function6;
                modifier4 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2028863105, $dirty5, $dirty, "androidx.compose.material3.ElevatedAssistChip (Chip.kt:305)");
            }
            ChipColors colors5 = colors2;
            $composer2 = $composer3;
            boolean enabled5 = enabled2;
            m2189ChipnkUnTEs(modifier4, function0, enabled5, function5, TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer3, i2), colors2.m2179labelColorvNxB06k$material3(enabled2), function10, function11, shape4, colors5, elevation4, border3, AssistChipDefaults.INSTANCE.m2102getHeightD9Ej5fM(), AssistChipPadding, interactionSource3, $composer2, (($dirty5 >> 6) & 14) | (($dirty5 << 3) & 112) | (($dirty5 >> 3) & 896) | (($dirty5 << 6) & 7168) | (($dirty5 << 6) & 3670016) | (($dirty5 << 6) & 29360128) | (($dirty5 << 6) & 234881024) | (($dirty5 << 6) & 1879048192), (($dirty5 >> 24) & 14) | 3456 | (($dirty5 >> 24) & 112) | (($dirty << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled3 = enabled5;
            function8 = function10;
            function9 = function11;
            shape2 = shape4;
            colors3 = colors5;
            elevation2 = elevation4;
            border2 = border3;
            interactionSource2 = interactionSource3;
            $dirty2 = $dirty5;
        } else {
            $dirty = $dirty8;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            border2 = border;
            interactionSource2 = interactionSource;
            colors3 = colors2;
            modifier3 = modifier2;
            function8 = function6;
            $dirty2 = $dirty9;
            function9 = function7;
            enabled3 = enabled2;
            elevation2 = elevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ElevatedAssistChip$lambda$3(function0, function2, modifier3, enabled3, function8, function9, shape2, colors3, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with ElevatedAssistChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "ElevatedAssistChip(onClick, label, modifier, enabled,leadingIcon, trailingIcon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void ElevatedAssistChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 leadingIcon, Function2 trailingIcon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2 function2;
        Modifier modifier2;
        boolean enabled2;
        Function2 leadingIcon2;
        Function2 function3;
        ChipColors colors2;
        int $dirty;
        Composer $composer2;
        final Shape shape2;
        final ChipBorder border2;
        final MutableInteractionSource interactionSource2;
        final ChipColors colors3;
        final Modifier modifier3;
        final Function2 leadingIcon3;
        int $dirty2;
        final Function2 trailingIcon2;
        final boolean enabled3;
        final ChipElevation elevation2;
        Shape shape3;
        int $dirty3;
        ChipColors colors4;
        int $dirty4;
        int i2;
        ChipElevation elevation3;
        ChipBorder border3;
        MutableInteractionSource interactionSource3;
        ChipElevation elevation4;
        Function2 leadingIcon4;
        int $dirty5;
        Modifier modifier4;
        Function2 trailingIcon3;
        Shape shape4;
        State<BorderStroke> stateBorderStroke$material3;
        Composer $composer3 = $composer.startRestartGroup(-759567147);
        ComposerKt.sourceInformation($composer3, "C(ElevatedAssistChip)N(onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)394@19326L5,389@19158L572:Chip.kt#uh7d8r");
        int $dirty6 = $changed;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty6 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty6 |= $composer3.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty6 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty6 |= 24576;
            leadingIcon2 = leadingIcon;
        } else if (($changed & 24576) == 0) {
            leadingIcon2 = leadingIcon;
            $dirty6 |= $composer3.changedInstance(leadingIcon2) ? 16384 : 8192;
        } else {
            leadingIcon2 = leadingIcon;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function3 = trailingIcon;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function3 = trailingIcon;
            $dirty6 |= $composer3.changedInstance(function3) ? 131072 : 65536;
        } else {
            function3 = trailingIcon;
        }
        if (($changed & 1572864) == 0) {
            $dirty6 |= ((i & 64) == 0 && $composer3.changed(shape)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 8388608 : 4194304;
                $dirty6 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty6 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            $dirty6 |= ((i & 256) == 0 && $composer3.changed(elevation)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty1 = $changed1;
        int $dirty7 = i & 512;
        if ($dirty7 != 0) {
            $dirty6 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty6 |= $composer3.changed(border) ? 536870912 : 268435456;
        }
        int i8 = i & 1024;
        if (i8 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 4 : 2;
        }
        int $dirty8 = $dirty1;
        int $dirty9 = $dirty6;
        if ($composer3.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty8 & 3) == 2) ? false : true, $dirty9 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "383@18866L5,384@18917L26,385@18996L29,387@19109L39");
            $dirty = $dirty8;
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i4 != 0 ? true : enabled2;
                if (i5 != 0) {
                    leadingIcon2 = null;
                }
                Function2 trailingIcon4 = i6 != 0 ? null : function3;
                if ((i & 64) != 0) {
                    $dirty3 = $dirty9 & (-3670017);
                    shape3 = AssistChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape3 = shape;
                    $dirty3 = $dirty9;
                }
                if ((i & 128) != 0) {
                    colors4 = AssistChipDefaults.INSTANCE.elevatedAssistChipColors($composer3, 6);
                    $dirty4 = $dirty3 & (-29360129);
                } else {
                    colors4 = colors2;
                    $dirty4 = $dirty3;
                }
                if ((i & 256) != 0) {
                    i2 = 6;
                    elevation3 = AssistChipDefaults.INSTANCE.m2101elevatedAssistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty4 &= -234881025;
                } else {
                    i2 = 6;
                    elevation3 = elevation;
                }
                border3 = $dirty7 != 0 ? null : border;
                if (i8 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 200442940, "CC(remember):Chip.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    elevation4 = elevation3;
                    interactionSource3 = (MutableInteractionSource) it$iv;
                    leadingIcon4 = leadingIcon2;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier4 = modifier5;
                    trailingIcon3 = trailingIcon4;
                    shape4 = shape3;
                } else {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    leadingIcon4 = leadingIcon2;
                    enabled2 = enabled4;
                    colors2 = colors4;
                    $dirty5 = $dirty4;
                    modifier4 = modifier5;
                    trailingIcon3 = trailingIcon4;
                    shape4 = shape3;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty10 = (i & 64) != 0 ? $dirty9 & (-3670017) : $dirty9;
                if ((i & 128) != 0) {
                    $dirty10 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty10 &= -234881025;
                }
                shape4 = shape;
                elevation4 = elevation;
                interactionSource3 = interactionSource;
                $dirty5 = $dirty10;
                i2 = 6;
                trailingIcon3 = function3;
                $dirty = $dirty;
                border3 = border;
                leadingIcon4 = leadingIcon2;
                modifier4 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-759567147, $dirty5, $dirty, "androidx.compose.material3.ElevatedAssistChip (Chip.kt:389)");
            }
            TextStyle value = TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer3, i2);
            long jM2179labelColorvNxB06k$material3 = colors2.m2179labelColorvNxB06k$material3(enabled2);
            float fM2102getHeightD9Ej5fM = AssistChipDefaults.INSTANCE.m2102getHeightD9Ej5fM();
            PaddingValues paddingValues = AssistChipPadding;
            if (border3 == null) {
                $composer3.startReplaceGroup(1919297975);
                $composer3.endReplaceGroup();
                stateBorderStroke$material3 = null;
            } else {
                $composer3.startReplaceGroup(200460170);
                ComposerKt.sourceInformation($composer3, "403@19648L21");
                stateBorderStroke$material3 = border3.borderStroke$material3(enabled2, $composer3, (($dirty5 >> 9) & 14) | (($dirty5 >> 24) & 112));
                $composer3.endReplaceGroup();
            }
            ChipColors colors5 = colors2;
            $composer2 = $composer3;
            boolean enabled5 = enabled2;
            m2189ChipnkUnTEs(modifier4, onClick, enabled5, function2, value, jM2179labelColorvNxB06k$material3, leadingIcon4, trailingIcon3, shape4, colors5, elevation4, stateBorderStroke$material3 != null ? stateBorderStroke$material3.getValue() : null, fM2102getHeightD9Ej5fM, paddingValues, interactionSource3, $composer2, (($dirty5 >> 6) & 14) | (($dirty5 << 3) & 112) | (($dirty5 >> 3) & 896) | (($dirty5 << 6) & 7168) | (($dirty5 << 6) & 3670016) | (($dirty5 << 6) & 29360128) | (($dirty5 << 6) & 234881024) | (($dirty5 << 6) & 1879048192), (($dirty5 >> 24) & 14) | 3456 | (($dirty << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border3;
            modifier3 = modifier4;
            enabled3 = enabled5;
            leadingIcon3 = leadingIcon4;
            trailingIcon2 = trailingIcon3;
            shape2 = shape4;
            colors3 = colors5;
            elevation2 = elevation4;
            interactionSource2 = interactionSource3;
            $dirty2 = $dirty5;
        } else {
            $dirty = $dirty8;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            border2 = border;
            interactionSource2 = interactionSource;
            colors3 = colors2;
            modifier3 = modifier2;
            leadingIcon3 = leadingIcon2;
            $dirty2 = $dirty9;
            trailingIcon2 = function3;
            enabled3 = enabled2;
            elevation2 = elevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ElevatedAssistChip$lambda$5(onClick, label, modifier3, enabled3, leadingIcon3, trailingIcon2, shape2, colors3, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void FilterChip(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Shape shape, SelectableChipColors colors, SelectableChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function0<Unit> function1;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        boolean z2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Shape shape2;
        MutableInteractionSource mutableInteractionSource;
        int $dirty1;
        Composer $composer2;
        final SelectableChipElevation elevation2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final Shape shape3;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final boolean enabled2;
        final Modifier modifier3;
        final SelectableChipColors colors2;
        int i2;
        boolean enabled3;
        int $dirty;
        Shape shape4;
        SelectableChipColors colors3;
        int $dirty2;
        int $dirty3;
        int i3;
        SelectableChipElevation elevation3;
        int $dirty4;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        SelectableChipElevation elevation4;
        int $dirty5;
        BorderStroke border4;
        boolean enabled4;
        int $dirty6;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Shape shape5;
        SelectableChipColors colors4;
        Modifier modifier4;
        Composer $composer3 = $composer.startRestartGroup(-1385473344);
        ComposerKt.sourceInformation($composer3, "C(FilterChip)N(selected,onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)480@23562L5,474@23355L555:Chip.kt#uh7d8r");
        int $dirty7 = $changed;
        int $dirty8 = $changed1;
        if ((i & 1) != 0) {
            $dirty7 |= 6;
            z = selected;
        } else if (($changed & 6) == 0) {
            z = selected;
            $dirty7 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if ((i & 2) != 0) {
            $dirty7 |= 48;
            function1 = function0;
        } else if (($changed & 48) == 0) {
            function1 = function0;
            $dirty7 |= $composer3.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        if ((i & 4) != 0) {
            $dirty7 |= 384;
            function5 = function2;
        } else if (($changed & 384) == 0) {
            function5 = function2;
            $dirty7 |= $composer3.changedInstance(function5) ? 256 : 128;
        } else {
            function5 = function2;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty7 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty7 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty7 |= 24576;
            z2 = enabled;
        } else if (($changed & 24576) == 0) {
            z2 = enabled;
            $dirty7 |= $composer3.changed(z2) ? 16384 : 8192;
        } else {
            z2 = enabled;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty7 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function6 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function6 = function3;
            $dirty7 |= $composer3.changedInstance(function6) ? 131072 : 65536;
        } else {
            function6 = function3;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty7 |= 1572864;
            function7 = function4;
        } else if (($changed & 1572864) == 0) {
            function7 = function4;
            $dirty7 |= $composer3.changedInstance(function7) ? 1048576 : 524288;
        } else {
            function7 = function4;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                shape2 = shape;
                int i8 = $composer3.changed(shape2) ? 8388608 : 4194304;
                $dirty7 |= i8;
            } else {
                shape2 = shape;
            }
            $dirty7 |= i8;
        } else {
            shape2 = shape;
        }
        if (($changed & 100663296) == 0) {
            $dirty7 |= ((i & 256) == 0 && $composer3.changed(colors)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty7 |= ((i & 512) == 0 && $composer3.changed(elevation)) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty8 |= ((i & 1024) == 0 && $composer3.changed(border)) ? 4 : 2;
        }
        int $dirty9 = $dirty8;
        int $dirty10 = i & 2048;
        if ($dirty10 != 0) {
            $dirty1 = $dirty9 | 48;
            mutableInteractionSource = interactionSource;
        } else if (($changed1 & 48) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty1 = $dirty9 | ($composer3.changed(mutableInteractionSource) ? 32 : 16);
        } else {
            mutableInteractionSource = interactionSource;
            $dirty1 = $dirty9;
        }
        int $dirty11 = $dirty7;
        if ($composer3.shouldExecute((($dirty7 & 306783379) == 306783378 && ($dirty1 & 19) == 18) ? false : true, $dirty11 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "468@23041L5,469@23102L18,470@23183L21,471@23253L35");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                if (i5 != 0) {
                    enabled3 = true;
                    i2 = $dirty10;
                } else {
                    i2 = $dirty10;
                    enabled3 = z2;
                }
                Function2<? super Composer, ? super Integer, Unit> function12 = i6 != 0 ? null : function6;
                Function2<? super Composer, ? super Integer, Unit> function13 = i7 != 0 ? null : function7;
                if ((i & 128) != 0) {
                    $dirty = $dirty11 & (-29360129);
                    shape4 = FilterChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty11;
                    shape4 = shape2;
                }
                if ((i & 256) != 0) {
                    colors3 = FilterChipDefaults.INSTANCE.filterChipColors($composer3, 6);
                    $dirty2 = $dirty & (-234881025);
                } else {
                    colors3 = colors;
                    $dirty2 = $dirty;
                }
                if ((i & 512) != 0) {
                    $dirty3 = 6;
                    i3 = i2;
                    elevation3 = FilterChipDefaults.INSTANCE.m2460filterChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty4 = $dirty2 & (-1879048193);
                } else {
                    $dirty3 = 6;
                    i3 = i2;
                    elevation3 = elevation;
                    $dirty4 = $dirty2;
                }
                if ((i & 1024) != 0) {
                    border3 = FilterChipDefaults.INSTANCE.m2458filterChipBorder_7El2pE(enabled3, z, 0L, 0L, 0L, 0L, 0.0f, 0.0f, $composer3, (($dirty4 >> 12) & 14) | 100663296 | (($dirty4 << 3) & 112), 252);
                    $dirty1 &= -15;
                } else {
                    border3 = border;
                }
                if (i3 != 0) {
                    elevation4 = elevation3;
                    $dirty5 = $dirty4;
                    border4 = border3;
                    interactionSource3 = null;
                    enabled4 = enabled3;
                    $dirty6 = $dirty1;
                    function10 = function12;
                    function11 = function13;
                    shape5 = shape4;
                    colors4 = colors3;
                    modifier4 = modifier5;
                } else {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    $dirty5 = $dirty4;
                    border4 = border3;
                    enabled4 = enabled3;
                    $dirty6 = $dirty1;
                    function10 = function12;
                    function11 = function13;
                    shape5 = shape4;
                    colors4 = colors3;
                    modifier4 = modifier5;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty12 = (i & 128) != 0 ? $dirty11 & (-29360129) : $dirty11;
                if ((i & 256) != 0) {
                    $dirty12 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty12 &= -1879048193;
                }
                if ((i & 1024) != 0) {
                    $dirty1 &= -15;
                }
                $dirty5 = $dirty12;
                $dirty3 = 6;
                colors4 = colors;
                elevation4 = elevation;
                border4 = border;
                interactionSource3 = mutableInteractionSource;
                enabled4 = z2;
                function10 = function6;
                function11 = function7;
                shape5 = shape2;
                modifier4 = modifier2;
                $dirty6 = $dirty1;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1385473344, $dirty5, $dirty6, "androidx.compose.material3.FilterChip (Chip.kt:474)");
            }
            $composer2 = $composer3;
            m2191SelectableChipu0RnIRE(selected, modifier4, function1, enabled4, function5, TypographyKt.getValue(FilterChipTokens.INSTANCE.getLabelTextFont(), $composer3, $dirty3), function10, null, function11, shape5, colors4, elevation4, border4, FilterChipDefaults.INSTANCE.m2461getHeightD9Ej5fM(), FilterChipPadding, interactionSource3, $composer2, ($dirty5 & 14) | 12582912 | (($dirty5 >> 6) & 112) | (($dirty5 << 3) & 896) | (($dirty5 >> 3) & 7168) | (($dirty5 << 6) & 57344) | (($dirty5 << 3) & 3670016) | (($dirty5 << 6) & 234881024) | (($dirty5 << 6) & 1879048192), (($dirty5 >> 24) & 14) | 27648 | (($dirty5 >> 24) & 112) | (($dirty6 << 6) & 896) | (($dirty6 << 12) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled2 = enabled4;
            function9 = function10;
            function8 = function11;
            shape3 = shape5;
            colors2 = colors4;
            elevation2 = elevation4;
            border2 = border4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            elevation2 = elevation;
            border2 = border;
            interactionSource2 = interactionSource;
            shape3 = shape2;
            function8 = function7;
            function9 = function6;
            enabled2 = z2;
            modifier3 = modifier2;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.FilterChip$lambda$6(selected, function0, function2, modifier3, enabled2, function9, function8, shape3, colors2, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ElevatedFilterChip(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Shape shape, SelectableChipColors colors, SelectableChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function0<Unit> function1;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Shape shape2;
        int $dirty1;
        int $dirty;
        Composer $composer2;
        final SelectableChipElevation elevation2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final Shape shape3;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final boolean enabled2;
        final Modifier modifier3;
        final SelectableChipColors colors2;
        int $dirty2;
        Shape shape4;
        SelectableChipColors colors3;
        int $dirty3;
        int i2;
        SelectableChipElevation elevation3;
        MutableInteractionSource interactionSource3;
        SelectableChipElevation elevation4;
        BorderStroke border3;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        boolean enabled3;
        int $dirty4;
        int i3;
        SelectableChipColors colors4;
        Shape shape5;
        Modifier modifier4;
        Composer $composer3 = $composer.startRestartGroup(1533553846);
        ComposerKt.sourceInformation($composer3, "C(ElevatedFilterChip)N(selected,onClick,label,modifier,enabled,leadingIcon,trailingIcon,shape,colors,elevation,border,interactionSource)562@27590L5,556@27383L555:Chip.kt#uh7d8r");
        int $dirty5 = $changed;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty5 |= $composer3.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
            function1 = function0;
        } else if (($changed & 48) == 0) {
            function1 = function0;
            $dirty5 |= $composer3.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        if ((i & 4) != 0) {
            $dirty5 |= 384;
            function5 = function2;
        } else if (($changed & 384) == 0) {
            function5 = function2;
            $dirty5 |= $composer3.changedInstance(function5) ? 256 : 128;
        } else {
            function5 = function2;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty5 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty5 |= 24576;
            z = enabled;
        } else if (($changed & 24576) == 0) {
            z = enabled;
            $dirty5 |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = enabled;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function6 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function6 = function3;
            $dirty5 |= $composer3.changedInstance(function6) ? 131072 : 65536;
        } else {
            function6 = function3;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty5 |= 1572864;
            function7 = function4;
        } else if (($changed & 1572864) == 0) {
            function7 = function4;
            $dirty5 |= $composer3.changedInstance(function7) ? 1048576 : 524288;
        } else {
            function7 = function4;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                shape2 = shape;
                int i8 = $composer3.changed(shape2) ? 8388608 : 4194304;
                $dirty5 |= i8;
            } else {
                shape2 = shape;
            }
            $dirty5 |= i8;
        } else {
            shape2 = shape;
        }
        if (($changed & 100663296) == 0) {
            $dirty5 |= ((i & 256) == 0 && $composer3.changed(colors)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty5 |= ((i & 512) == 0 && $composer3.changed(elevation)) ? 536870912 : 268435456;
        }
        int $dirty6 = i & 1024;
        if ($dirty6 != 0) {
            $dirty1 = $changed1 | 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 = $changed1 | ($composer3.changed(border) ? 4 : 2);
        } else {
            $dirty1 = $changed1;
        }
        int i9 = i & 2048;
        if (i9 != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 32 : 16;
        }
        int $dirty7 = $dirty1;
        int $dirty8 = $dirty5;
        if ($composer3.shouldExecute((($dirty5 & 306783379) == 306783378 && ($dirty7 & 19) == 18) ? false : true, $dirty8 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "550@27103L5,551@27164L26,552@27253L29");
            $dirty = $dirty7;
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i5 != 0 ? true : z;
                Function2<? super Composer, ? super Integer, Unit> function12 = i6 != 0 ? null : function6;
                Function2<? super Composer, ? super Integer, Unit> function13 = i7 != 0 ? null : function7;
                if ((i & 128) != 0) {
                    $dirty2 = $dirty8 & (-29360129);
                    shape4 = FilterChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty2 = $dirty8;
                    shape4 = shape2;
                }
                if ((i & 256) != 0) {
                    colors3 = FilterChipDefaults.INSTANCE.elevatedFilterChipColors($composer3, 6);
                    $dirty3 = $dirty2 & (-234881025);
                } else {
                    colors3 = colors;
                    $dirty3 = $dirty2;
                }
                if ((i & 512) != 0) {
                    i2 = 6;
                    elevation3 = FilterChipDefaults.INSTANCE.m2457elevatedFilterChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty3 &= -1879048193;
                } else {
                    i2 = 6;
                    elevation3 = elevation;
                }
                BorderStroke border4 = $dirty6 != 0 ? null : border;
                if (i9 != 0) {
                    elevation4 = elevation3;
                    border3 = border4;
                    interactionSource3 = null;
                    function10 = function12;
                    function11 = function13;
                    enabled3 = enabled4;
                    $dirty4 = $dirty3;
                    i3 = 1533553846;
                    colors4 = colors3;
                    shape5 = shape4;
                    modifier4 = modifier5;
                } else {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    border3 = border4;
                    function10 = function12;
                    function11 = function13;
                    enabled3 = enabled4;
                    $dirty4 = $dirty3;
                    i3 = 1533553846;
                    colors4 = colors3;
                    shape5 = shape4;
                    modifier4 = modifier5;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty9 = (i & 128) != 0 ? $dirty8 & (-29360129) : $dirty8;
                if ((i & 256) != 0) {
                    $dirty9 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty9 &= -1879048193;
                }
                colors4 = colors;
                elevation4 = elevation;
                border3 = border;
                interactionSource3 = interactionSource;
                $dirty4 = $dirty9;
                i2 = 6;
                function10 = function6;
                function11 = function7;
                shape5 = shape2;
                modifier4 = modifier2;
                $dirty = $dirty;
                i3 = 1533553846;
                enabled3 = z;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty4, $dirty, "androidx.compose.material3.ElevatedFilterChip (Chip.kt:556)");
            }
            $composer2 = $composer3;
            m2191SelectableChipu0RnIRE(selected, modifier4, function1, enabled3, function5, TypographyKt.getValue(FilterChipTokens.INSTANCE.getLabelTextFont(), $composer3, i2), function10, null, function11, shape5, colors4, elevation4, border3, FilterChipDefaults.INSTANCE.m2461getHeightD9Ej5fM(), FilterChipPadding, interactionSource3, $composer2, ($dirty4 & 14) | 12582912 | (($dirty4 >> 6) & 112) | (($dirty4 << 3) & 896) | (($dirty4 >> 3) & 7168) | (($dirty4 << 6) & 57344) | (($dirty4 << 3) & 3670016) | (($dirty4 << 6) & 234881024) | (($dirty4 << 6) & 1879048192), (($dirty4 >> 24) & 14) | 27648 | (($dirty4 >> 24) & 112) | (($dirty << 6) & 896) | (($dirty << 12) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled2 = enabled3;
            function9 = function10;
            function8 = function11;
            shape3 = shape5;
            colors2 = colors4;
            elevation2 = elevation4;
            border2 = border3;
            interactionSource2 = interactionSource3;
        } else {
            $dirty = $dirty7;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            elevation2 = elevation;
            border2 = border;
            interactionSource2 = interactionSource;
            shape3 = shape2;
            function8 = function7;
            function9 = function6;
            enabled2 = z;
            modifier3 = modifier2;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ElevatedFilterChip$lambda$7(selected, function0, function2, modifier3, enabled2, function9, function8, shape3, colors2, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void InputChip(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Shape shape, SelectableChipColors colors, SelectableChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function0<Unit> function1;
        Modifier modifier2;
        boolean z2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Composer $composer2;
        final Shape shape2;
        final SelectableChipColors colors2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final Modifier modifier3;
        final boolean enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final SelectableChipElevation elevation2;
        Modifier.Companion modifier4;
        boolean enabled3;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Shape shape3;
        int $dirty;
        SelectableChipColors colors3;
        int $dirty2;
        int $dirty3;
        SelectableChipElevation elevation3;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        SelectableChipElevation elevation4;
        BorderStroke border4;
        boolean enabled4;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        Shape shape4;
        SelectableChipColors colors4;
        int $dirty4;
        Modifier modifier5;
        boolean z3;
        Function2 shapedAvatar;
        Composer $composer3 = $composer.startRestartGroup(-1975409271);
        ComposerKt.sourceInformation($composer3, "C(InputChip)N(selected,onClick,label,modifier,enabled,leadingIcon,avatar,trailingIcon,shape,colors,elevation,border,interactionSource)673@32653L5,667@32447L747:Chip.kt#uh7d8r");
        int $dirty5 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
            z = selected;
        } else if (($changed & 6) == 0) {
            z = selected;
            $dirty5 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
            function1 = function0;
        } else if (($changed & 48) == 0) {
            function1 = function0;
            $dirty5 |= $composer3.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        if ((i & 4) != 0) {
            $dirty5 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty5 |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty5 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty5 |= 24576;
            z2 = enabled;
        } else if (($changed & 24576) == 0) {
            z2 = enabled;
            $dirty5 |= $composer3.changed(z2) ? 16384 : 8192;
        } else {
            z2 = enabled;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function6 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function6 = function3;
            $dirty5 |= $composer3.changedInstance(function6) ? 131072 : 65536;
        } else {
            function6 = function3;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty5 |= 1572864;
            function7 = function4;
        } else if (($changed & 1572864) == 0) {
            function7 = function4;
            $dirty5 |= $composer3.changedInstance(function7) ? 1048576 : 524288;
        } else {
            function7 = function4;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty5 |= 12582912;
            function8 = function5;
        } else if (($changed & 12582912) == 0) {
            function8 = function5;
            $dirty5 |= $composer3.changedInstance(function8) ? 8388608 : 4194304;
        } else {
            function8 = function5;
        }
        if (($changed & 100663296) == 0) {
            $dirty5 |= ((i & 256) == 0 && $composer3.changed(shape)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty5 |= ((i & 512) == 0 && $composer3.changed(colors)) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= ((i & 1024) == 0 && $composer3.changed(elevation)) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= ((i & 2048) == 0 && $composer3.changed(border)) ? 32 : 16;
        }
        int i7 = i & 4096;
        if (i7 != 0) {
            $dirty1 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 256 : 128;
        }
        int $dirty6 = $dirty5;
        if ($composer3.shouldExecute((($dirty5 & 306783379) == 306783378 && ($dirty1 & 147) == 146) ? false : true, $dirty6 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "640@31314L5,641@31374L17,642@31453L20,643@31521L34");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty7 = (i & 256) != 0 ? $dirty6 & (-234881025) : $dirty6;
                if ((i & 512) != 0) {
                    $dirty7 &= -1879048193;
                }
                if ((i & 1024) != 0) {
                    $dirty1 &= -15;
                }
                if ((i & 2048) != 0) {
                    $dirty1 &= -113;
                }
                $dirty4 = $dirty7;
                $dirty3 = 6;
                shape4 = shape;
                colors4 = colors;
                elevation4 = elevation;
                border4 = border;
                interactionSource3 = interactionSource;
                modifier5 = modifier2;
                enabled4 = z2;
                function15 = function6;
                function16 = function8;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i3 == 0) {
                    enabled3 = z2;
                } else {
                    enabled3 = true;
                }
                if (i4 == 0) {
                    function12 = function6;
                } else {
                    function12 = null;
                }
                if (i5 == 0) {
                    function13 = function7;
                } else {
                    function13 = null;
                }
                if (i6 == 0) {
                    function14 = function8;
                } else {
                    function14 = null;
                }
                if ((i & 256) == 0) {
                    shape3 = shape;
                    $dirty = $dirty6;
                } else {
                    $dirty = $dirty6 & (-234881025);
                    shape3 = InputChipDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 512) == 0) {
                    colors3 = colors;
                    $dirty2 = $dirty;
                } else {
                    colors3 = InputChipDefaults.INSTANCE.inputChipColors($composer3, 6);
                    $dirty2 = $dirty & (-1879048193);
                }
                if ((i & 1024) != 0) {
                    $dirty3 = 6;
                    elevation3 = InputChipDefaults.INSTANCE.m2542inputChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty1 &= -15;
                } else {
                    $dirty3 = 6;
                    elevation3 = elevation;
                }
                if ((i & 2048) == 0) {
                    border3 = border;
                } else {
                    border3 = InputChipDefaults.INSTANCE.m2540inputChipBorder_7El2pE(enabled3, z, 0L, 0L, 0L, 0L, 0.0f, 0.0f, $composer3, (($dirty2 >> 12) & 14) | 100663296 | (($dirty2 << 3) & 112), 252);
                    $dirty1 &= -113;
                }
                if (i7 == 0) {
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    border4 = border3;
                    enabled4 = enabled3;
                    function15 = function12;
                    function7 = function13;
                    function16 = function14;
                    shape4 = shape3;
                    colors4 = colors3;
                    $dirty4 = $dirty2;
                    modifier5 = modifier4;
                } else {
                    elevation4 = elevation3;
                    border4 = border3;
                    interactionSource3 = null;
                    enabled4 = enabled3;
                    function15 = function12;
                    function7 = function13;
                    function16 = function14;
                    shape4 = shape3;
                    colors4 = colors3;
                    $dirty4 = $dirty2;
                    modifier5 = modifier4;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1975409271, $dirty4, $dirty1, "androidx.compose.material3.InputChip (Chip.kt:645)");
            }
            if (function7 != null) {
                $composer3.startReplaceGroup(225175787);
                ComposerKt.sourceInformation($composer3, "651@31963L5,653@32016L420");
                float avatarOpacity = enabled4 ? 1.0f : InputChipTokens.INSTANCE.getDisabledAvatarOpacity();
                Shape avatarShape = ShapesKt.getValue(InputChipTokens.INSTANCE.getAvatarShape(), $composer3, $dirty3);
                z3 = true;
                Function2 shapedAvatar2 = ComposableLambdaKt.rememberComposableLambda(-570452295, true, new AnonymousClass1(avatarOpacity, avatarShape, function7), $composer3, 54);
                $composer3.endReplaceGroup();
                shapedAvatar = shapedAvatar2;
            } else {
                z3 = true;
                $composer3.startReplaceGroup(225773529);
                $composer3.endReplaceGroup();
                shapedAvatar = null;
            }
            TextStyle value = TypographyKt.getValue(InputChipTokens.INSTANCE.getLabelTextFont(), $composer3, $dirty3);
            float fM2538getHeightD9Ej5fM = InputChipDefaults.INSTANCE.m2538getHeightD9Ej5fM();
            boolean z4 = shapedAvatar != null ? z3 : false;
            boolean z5 = function15 != null ? z3 : false;
            if (function16 == null) {
                z3 = false;
            }
            $composer2 = $composer3;
            m2191SelectableChipu0RnIRE(selected, modifier5, function1, enabled4, function2, value, function15, shapedAvatar, function16, shape4, colors4, elevation4, border4, fM2538getHeightD9Ej5fM, inputChipPadding(z4, z5, z3), interactionSource3, $composer2, ($dirty4 & 14) | (($dirty4 >> 6) & 112) | (($dirty4 << 3) & 896) | (($dirty4 >> 3) & 7168) | (($dirty4 << 6) & 57344) | (($dirty4 << 3) & 3670016) | (($dirty4 << 3) & 234881024) | (($dirty4 << 3) & 1879048192), (($dirty4 >> 27) & 14) | 3072 | (($dirty1 << 3) & 112) | (($dirty1 << 3) & 896) | (($dirty1 << 9) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function10 = function7;
            modifier3 = modifier5;
            enabled2 = enabled4;
            function9 = function15;
            function11 = function16;
            shape2 = shape4;
            colors2 = colors4;
            elevation2 = elevation4;
            border2 = border4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            colors2 = colors;
            border2 = border;
            interactionSource2 = interactionSource;
            modifier3 = modifier2;
            enabled2 = z2;
            function9 = function6;
            function10 = function7;
            function11 = function8;
            elevation2 = elevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.InputChip$lambda$8(selected, function0, function2, modifier3, enabled2, function9, function10, function11, shape2, colors2, elevation2, border2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$InputChip$1, reason: invalid class name */
    /* JADX INFO: compiled from: Chip.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ Function2<Composer, Integer, Unit> $avatar;
        final /* synthetic */ float $avatarOpacity;
        final /* synthetic */ Shape $avatarShape;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(float f, Shape shape, Function2<? super Composer, ? super Integer, Unit> function2) {
            this.$avatarOpacity = f;
            this.$avatarShape = shape;
            this.$avatar = function2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "C656@32117L180,654@32034L388:Chip.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-570452295, $changed, -1, "androidx.compose.material3.InputChip.<anonymous> (Chip.kt:654)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1551161805, "CC(remember):Chip.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.$avatarOpacity) | $composer.changed(this.$avatarShape);
            final float f = this.$avatarOpacity;
            final Shape shape = this.$avatarShape;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.ChipKt$InputChip$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ChipKt.AnonymousClass1.invoke$lambda$1$lambda$0(f, shape, (GraphicsLayerScope) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier$iv = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) it$iv);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            Function2<Composer, Integer, Unit> function2 = this.$avatar;
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1351092324, "C663@32396L8:Chip.kt#uh7d8r");
            function2.invoke($composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        static final Unit invoke$lambda$1$lambda$0(float $avatarOpacity, Shape $avatarShape, GraphicsLayerScope $this$graphicsLayer) {
            $this$graphicsLayer.setAlpha($avatarOpacity);
            $this$graphicsLayer.setShape($avatarShape);
            $this$graphicsLayer.setClip(true);
            return Unit.INSTANCE;
        }
    }

    public static final void SuggestionChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function3, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        Function2<? super Composer, ? super Integer, Unit> function4;
        final Modifier modifier2;
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Shape shape2;
        ChipColors colors2;
        ChipElevation elevation2;
        Composer $composer2;
        final boolean enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final Shape shape3;
        final ChipColors colors3;
        final ChipElevation elevation3;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        int $dirty;
        Shape shape4;
        ChipColors colors4;
        int $dirty2;
        int i2;
        boolean enabled3;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        BorderStroke border4;
        Function2<? super Composer, ? super Integer, Unit> function7;
        int $dirty3;
        ChipElevation elevation4;
        Shape shape5;
        boolean enabled4;
        Composer $composer3 = $composer.startRestartGroup(-252243183);
        ComposerKt.sourceInformation($composer3, "C(SuggestionChip)N(onClick,label,modifier,enabled,icon,shape,colors,elevation,border,interactionSource)751@36361L5,746@36189L539:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function1 = function0;
        } else if (($changed & 6) == 0) {
            function1 = function0;
            $dirty4 |= $composer3.changedInstance(function1) ? 4 : 2;
        } else {
            function1 = function0;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function4 = function2;
        } else if (($changed & 48) == 0) {
            function4 = function2;
            $dirty4 |= $composer3.changedInstance(function4) ? 32 : 16;
        } else {
            function4 = function2;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty4 |= 24576;
            function5 = function3;
        } else if (($changed & 24576) == 0) {
            function5 = function3;
            $dirty4 |= $composer3.changedInstance(function5) ? 16384 : 8192;
        } else {
            function5 = function3;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i6 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty4 |= i6;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i6;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty4 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty4 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                elevation2 = elevation;
                int i8 = $composer3.changed(elevation2) ? 8388608 : 4194304;
                $dirty4 |= i8;
            } else {
                elevation2 = elevation;
            }
            $dirty4 |= i8;
        } else {
            elevation2 = elevation;
        }
        if (($changed & 100663296) == 0) {
            $dirty4 |= ((i & 256) == 0 && $composer3.changed(border)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty4 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty4 |= $composer3.changed(interactionSource) ? 536870912 : 268435456;
        }
        int $dirty5 = $dirty4;
        if ($composer3.shouldExecute(($dirty4 & 306783379) != 306783378, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "740@35881L5,741@35936L22,742@36015L25,743@36093L29");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled5 = i4 != 0 ? true : z;
                Function2<? super Composer, ? super Integer, Unit> function8 = i5 != 0 ? null : function5;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape4 = SuggestionChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty5;
                    shape4 = shape2;
                }
                if ((i & 64) != 0) {
                    colors4 = SuggestionChipDefaults.INSTANCE.suggestionChipColors($composer3, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors4 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    i2 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m2928suggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    i2 = 6;
                }
                if ((i & 256) != 0) {
                    enabled3 = enabled5;
                    border3 = SuggestionChipDefaults.INSTANCE.m2926suggestionChipBorderh1eTWw(enabled3, 0L, 0L, 0.0f, $composer3, (($dirty2 >> 9) & 14) | 24576, 14);
                    $dirty2 &= -234881025;
                } else {
                    enabled3 = enabled5;
                    border3 = border;
                }
                if (i9 != 0) {
                    border4 = border3;
                    interactionSource3 = null;
                    colors2 = colors4;
                    function7 = function8;
                    $dirty3 = $dirty2;
                    modifier2 = modifier3;
                    elevation4 = elevation2;
                    shape5 = shape4;
                    enabled4 = enabled3;
                } else {
                    modifier2 = modifier3;
                    interactionSource3 = interactionSource;
                    border4 = border3;
                    colors2 = colors4;
                    function7 = function8;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    shape5 = shape4;
                    enabled4 = enabled3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty5 &= -234881025;
                }
                border4 = border;
                interactionSource3 = interactionSource;
                i2 = 6;
                function7 = function5;
                shape5 = shape2;
                elevation4 = elevation2;
                $dirty3 = $dirty5;
                enabled4 = z;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-252243183, $dirty3, -1, "androidx.compose.material3.SuggestionChip (Chip.kt:746)");
            }
            $composer2 = $composer3;
            ChipColors colors5 = colors2;
            Function2<? super Composer, ? super Integer, Unit> function9 = function4;
            Modifier modifier4 = modifier2;
            m2189ChipnkUnTEs(modifier4, function1, enabled4, function9, TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer3, i2), colors2.m2179labelColorvNxB06k$material3(enabled4), function7, null, shape5, colors5, elevation4, border4, SuggestionChipDefaults.INSTANCE.m2923getHeightD9Ej5fM(), SuggestionChipPadding, interactionSource3, $composer2, (($dirty3 >> 6) & 14) | 12582912 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (($dirty3 << 9) & 1879048192), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 21) & 112) | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            enabled2 = enabled4;
            function6 = function7;
            shape3 = shape5;
            colors3 = colors5;
            elevation3 = elevation4;
            border2 = border4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            enabled2 = z;
            function6 = function5;
            shape3 = shape2;
            colors3 = colors2;
            elevation3 = elevation2;
            border2 = border;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.SuggestionChip$lambda$9(function0, function2, modifier2, enabled2, function6, shape3, colors3, elevation3, border2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with SuggestionChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "SuggestionChip(onClick, label, modifier, enabled, icon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void SuggestionChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 icon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0 function0;
        Function2 function2;
        final Modifier modifier2;
        boolean z;
        Function2 function3;
        Shape shape2;
        ChipColors colors2;
        ChipElevation elevation2;
        Composer $composer2;
        final boolean enabled2;
        final Function2 icon2;
        final Shape shape3;
        final ChipColors colors3;
        final ChipElevation elevation3;
        final ChipBorder border2;
        final MutableInteractionSource interactionSource2;
        int $dirty;
        Shape shape4;
        ChipColors colors4;
        int $dirty2;
        int i2;
        Composer $composer3;
        ChipBorder border3;
        MutableInteractionSource interactionSource3;
        Function2 icon3;
        int $dirty3;
        ChipElevation elevation4;
        boolean enabled3;
        Shape shape5;
        State<BorderStroke> stateBorderStroke$material3;
        Composer $composer4 = $composer.startRestartGroup(270460261);
        ComposerKt.sourceInformation($composer4, "C(SuggestionChip)N(onClick,label,modifier,enabled,icon,shape,colors,elevation,border,interactionSource)833@40281L5,828@40109L569:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function0 = onClick;
        } else if (($changed & 6) == 0) {
            function0 = onClick;
            $dirty4 |= $composer4.changedInstance(function0) ? 4 : 2;
        } else {
            function0 = onClick;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty4 |= $composer4.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer4.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty4 |= 24576;
            function3 = icon;
        } else if (($changed & 24576) == 0) {
            function3 = icon;
            $dirty4 |= $composer4.changedInstance(function3) ? 16384 : 8192;
        } else {
            function3 = icon;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i6 = $composer4.changed(shape2) ? 131072 : 65536;
                $dirty4 |= i6;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i6;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i7 = $composer4.changed(colors2) ? 1048576 : 524288;
                $dirty4 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty4 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                elevation2 = elevation;
                int i8 = $composer4.changed(elevation2) ? 8388608 : 4194304;
                $dirty4 |= i8;
            } else {
                elevation2 = elevation;
            }
            $dirty4 |= i8;
        } else {
            elevation2 = elevation;
        }
        if (($changed & 100663296) == 0) {
            $dirty4 |= ((i & 256) == 0 && $composer4.changed(border)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty4 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty4 |= $composer4.changed(interactionSource) ? 536870912 : 268435456;
        }
        int $dirty5 = $dirty4;
        if ($composer4.shouldExecute(($dirty4 & 306783379) != 306783378, $dirty5 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "822@39776L5,823@39831L22,824@39910L25,825@39986L22,826@40060L39");
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i4 != 0 ? true : z;
                Function2 icon4 = i5 != 0 ? null : function3;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape4 = SuggestionChipDefaults.INSTANCE.getShape($composer4, 6);
                } else {
                    $dirty = $dirty5;
                    shape4 = shape2;
                }
                if ((i & 64) != 0) {
                    colors4 = SuggestionChipDefaults.INSTANCE.suggestionChipColors($composer4, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors4 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    $composer3 = $composer4;
                    i2 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m2928suggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    i2 = 6;
                    $composer3 = $composer4;
                }
                if ((i & 256) != 0) {
                    $composer4 = $composer3;
                    border3 = SuggestionChipDefaults.INSTANCE.m2925suggestionChipBorderd_3_b6Q(0L, 0L, 0.0f, $composer4, 3072, 7);
                    $dirty2 &= -234881025;
                } else {
                    $composer4 = $composer3;
                    border3 = border;
                }
                if (i9 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer4, -2080664820, "CC(remember):Chip.kt#9igjgp");
                    Composer $this$cache$iv = $composer4;
                    Object it$iv = $this$cache$iv.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $this$cache$iv.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    modifier2 = modifier3;
                    interactionSource3 = (MutableInteractionSource) it$iv;
                    colors2 = colors4;
                    icon3 = icon4;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                } else {
                    modifier2 = modifier3;
                    interactionSource3 = interactionSource;
                    colors2 = colors4;
                    icon3 = icon4;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty5 &= -234881025;
                }
                border3 = border;
                interactionSource3 = interactionSource;
                i2 = 6;
                icon3 = function3;
                shape5 = shape2;
                elevation4 = elevation2;
                $dirty3 = $dirty5;
                enabled3 = z;
            }
            $composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(270460261, $dirty3, -1, "androidx.compose.material3.SuggestionChip (Chip.kt:828)");
            }
            TextStyle value = TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer4, i2);
            long jM2179labelColorvNxB06k$material3 = colors2.m2179labelColorvNxB06k$material3(enabled3);
            if (border3 == null) {
                $composer4.startReplaceGroup(-75666041);
                $composer4.endReplaceGroup();
                stateBorderStroke$material3 = null;
            } else {
                $composer4.startReplaceGroup(-2080650822);
                ComposerKt.sourceInformation($composer4, "840@40498L21");
                stateBorderStroke$material3 = border3.borderStroke$material3(enabled3, $composer4, (($dirty3 >> 9) & 14) | (($dirty3 >> 21) & 112));
                $composer4.endReplaceGroup();
            }
            $composer2 = $composer4;
            ChipColors colors5 = colors2;
            Function2 function4 = function2;
            Modifier modifier4 = modifier2;
            m2189ChipnkUnTEs(modifier4, function0, enabled3, function4, value, jM2179labelColorvNxB06k$material3, icon3, null, shape5, colors5, elevation4, stateBorderStroke$material3 != null ? stateBorderStroke$material3.getValue() : null, SuggestionChipDefaults.INSTANCE.m2923getHeightD9Ej5fM(), SuggestionChipPadding, interactionSource3, $composer2, (($dirty3 >> 6) & 14) | 12582912 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (($dirty3 << 9) & 1879048192), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border3;
            modifier2 = modifier4;
            enabled2 = enabled3;
            icon2 = icon3;
            shape3 = shape5;
            colors3 = colors5;
            elevation3 = elevation4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            enabled2 = z;
            icon2 = function3;
            shape3 = shape2;
            colors3 = colors2;
            elevation3 = elevation2;
            border2 = border;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.SuggestionChip$lambda$11(onClick, label, modifier2, enabled2, icon2, shape3, colors3, elevation3, border2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ElevatedSuggestionChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function3, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        Function2<? super Composer, ? super Integer, Unit> function4;
        final Modifier modifier2;
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Shape shape2;
        ChipColors colors2;
        ChipElevation elevation2;
        Composer $composer2;
        final boolean enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final Shape shape3;
        final ChipColors colors3;
        final ChipElevation elevation3;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        int $dirty;
        Shape shape4;
        ChipColors colors4;
        int $dirty2;
        int i2;
        MutableInteractionSource interactionSource3;
        BorderStroke border3;
        Function2<? super Composer, ? super Integer, Unit> function7;
        int $dirty3;
        ChipElevation elevation4;
        boolean enabled3;
        Shape shape5;
        Composer $composer3 = $composer.startRestartGroup(-894435833);
        ComposerKt.sourceInformation($composer3, "C(ElevatedSuggestionChip)N(onClick,label,modifier,enabled,icon,shape,colors,elevation,border,interactionSource)904@43788L5,899@43616L539:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function1 = function0;
        } else if (($changed & 6) == 0) {
            function1 = function0;
            $dirty4 |= $composer3.changedInstance(function1) ? 4 : 2;
        } else {
            function1 = function0;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function4 = function2;
        } else if (($changed & 48) == 0) {
            function4 = function2;
            $dirty4 |= $composer3.changedInstance(function4) ? 32 : 16;
        } else {
            function4 = function2;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty4 |= 24576;
            function5 = function3;
        } else if (($changed & 24576) == 0) {
            function5 = function3;
            $dirty4 |= $composer3.changedInstance(function5) ? 16384 : 8192;
        } else {
            function5 = function3;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i6 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty4 |= i6;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i6;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty4 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty4 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                elevation2 = elevation;
                int i8 = $composer3.changed(elevation2) ? 8388608 : 4194304;
                $dirty4 |= i8;
            } else {
                elevation2 = elevation;
            }
            $dirty4 |= i8;
        } else {
            elevation2 = elevation;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty4 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty4 |= $composer3.changed(border) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty4 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty4 |= $composer3.changed(interactionSource) ? 536870912 : 268435456;
        }
        int $dirty5 = $dirty4;
        if ($composer3.shouldExecute(($dirty4 & 306783379) != 306783378, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "893@43340L5,894@43395L30,895@43482L33");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i4 != 0 ? true : z;
                Function2<? super Composer, ? super Integer, Unit> function8 = i5 != 0 ? null : function5;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape4 = SuggestionChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty5;
                    shape4 = shape2;
                }
                if ((i & 64) != 0) {
                    colors4 = SuggestionChipDefaults.INSTANCE.elevatedSuggestionChipColors($composer3, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors4 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    i2 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m2922elevatedSuggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    i2 = 6;
                }
                BorderStroke border4 = i9 != 0 ? null : border;
                if (i10 != 0) {
                    modifier2 = modifier3;
                    border3 = border4;
                    interactionSource3 = null;
                    colors2 = colors4;
                    function7 = function8;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                } else {
                    modifier2 = modifier3;
                    interactionSource3 = interactionSource;
                    border3 = border4;
                    colors2 = colors4;
                    function7 = function8;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                border3 = border;
                interactionSource3 = interactionSource;
                i2 = 6;
                function7 = function5;
                shape5 = shape2;
                elevation4 = elevation2;
                $dirty3 = $dirty5;
                enabled3 = z;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-894435833, $dirty3, -1, "androidx.compose.material3.ElevatedSuggestionChip (Chip.kt:899)");
            }
            $composer2 = $composer3;
            ChipColors colors5 = colors2;
            Function2<? super Composer, ? super Integer, Unit> function9 = function4;
            Modifier modifier4 = modifier2;
            m2189ChipnkUnTEs(modifier4, function1, enabled3, function9, TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer3, i2), colors2.m2179labelColorvNxB06k$material3(enabled3), function7, null, shape5, colors5, elevation4, border3, SuggestionChipDefaults.INSTANCE.m2923getHeightD9Ej5fM(), SuggestionChipPadding, interactionSource3, $composer2, (($dirty3 >> 6) & 14) | 12582912 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (($dirty3 << 9) & 1879048192), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 21) & 112) | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            enabled2 = enabled3;
            function6 = function7;
            shape3 = shape5;
            colors3 = colors5;
            elevation3 = elevation4;
            border2 = border3;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            enabled2 = z;
            function6 = function5;
            shape3 = shape2;
            colors3 = colors2;
            elevation3 = elevation2;
            border2 = border;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ElevatedSuggestionChip$lambda$12(function0, function2, modifier2, enabled2, function6, shape3, colors3, elevation3, border2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with ElevatedSuggestionChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "ElevatedSuggestionChip(onClick, label, modifier, enabled, icon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void ElevatedSuggestionChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 icon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0 function0;
        Function2 function2;
        final Modifier modifier2;
        boolean z;
        Function2 function3;
        Shape shape2;
        ChipColors colors2;
        ChipElevation elevation2;
        Composer $composer2;
        final boolean enabled2;
        final Function2 icon2;
        final Shape shape3;
        final ChipColors colors3;
        final ChipElevation elevation3;
        final ChipBorder border2;
        final MutableInteractionSource interactionSource2;
        int $dirty;
        Shape shape4;
        ChipColors colors4;
        int $dirty2;
        int i2;
        ChipBorder border3;
        MutableInteractionSource interactionSource3;
        Function2 icon3;
        int $dirty3;
        ChipElevation elevation4;
        boolean enabled3;
        Shape shape5;
        State<BorderStroke> stateBorderStroke$material3;
        Composer $composer3 = $composer.startRestartGroup(1306662363);
        ComposerKt.sourceInformation($composer3, "C(ElevatedSuggestionChip)N(onClick,label,modifier,enabled,icon,shape,colors,elevation,border,interactionSource)985@47676L5,980@47504L569:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function0 = onClick;
        } else if (($changed & 6) == 0) {
            function0 = onClick;
            $dirty4 |= $composer3.changedInstance(function0) ? 4 : 2;
        } else {
            function0 = onClick;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty4 |= $composer3.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty4 |= 24576;
            function3 = icon;
        } else if (($changed & 24576) == 0) {
            function3 = icon;
            $dirty4 |= $composer3.changedInstance(function3) ? 16384 : 8192;
        } else {
            function3 = icon;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i6 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty4 |= i6;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i6;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty4 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty4 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                elevation2 = elevation;
                int i8 = $composer3.changed(elevation2) ? 8388608 : 4194304;
                $dirty4 |= i8;
            } else {
                elevation2 = elevation;
            }
            $dirty4 |= i8;
        } else {
            elevation2 = elevation;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty4 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty4 |= $composer3.changed(border) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty4 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty4 |= $composer3.changed(interactionSource) ? 536870912 : 268435456;
        }
        int $dirty5 = $dirty4;
        if ($composer3.shouldExecute(($dirty4 & 306783379) != 306783378, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "974@47196L5,975@47251L30,976@47338L33,978@47455L39");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i4 != 0 ? true : z;
                Function2 icon4 = i5 != 0 ? null : function3;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape4 = SuggestionChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty5;
                    shape4 = shape2;
                }
                if ((i & 64) != 0) {
                    colors4 = SuggestionChipDefaults.INSTANCE.elevatedSuggestionChipColors($composer3, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors4 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    i2 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m2922elevatedSuggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    i2 = 6;
                }
                border3 = i9 != 0 ? null : border;
                if (i10 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 1572109794, "CC(remember):Chip.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    modifier2 = modifier3;
                    interactionSource3 = (MutableInteractionSource) it$iv;
                    colors2 = colors4;
                    icon3 = icon4;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                } else {
                    modifier2 = modifier3;
                    interactionSource3 = interactionSource;
                    colors2 = colors4;
                    icon3 = icon4;
                    $dirty3 = $dirty2;
                    elevation4 = elevation2;
                    enabled3 = enabled4;
                    shape5 = shape4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                border3 = border;
                interactionSource3 = interactionSource;
                i2 = 6;
                icon3 = function3;
                shape5 = shape2;
                elevation4 = elevation2;
                $dirty3 = $dirty5;
                enabled3 = z;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1306662363, $dirty3, -1, "androidx.compose.material3.ElevatedSuggestionChip (Chip.kt:980)");
            }
            TextStyle value = TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer3, i2);
            long jM2179labelColorvNxB06k$material3 = colors2.m2179labelColorvNxB06k$material3(enabled3);
            float fM2923getHeightD9Ej5fM = SuggestionChipDefaults.INSTANCE.m2923getHeightD9Ej5fM();
            PaddingValues paddingValues = SuggestionChipPadding;
            if (border3 == null) {
                $composer3.startReplaceGroup(1491294513);
                $composer3.endReplaceGroup();
                stateBorderStroke$material3 = null;
            } else {
                $composer3.startReplaceGroup(1572126928);
                ComposerKt.sourceInformation($composer3, "994@47991L21");
                stateBorderStroke$material3 = border3.borderStroke$material3(enabled3, $composer3, (($dirty3 >> 9) & 14) | (($dirty3 >> 21) & 112));
                $composer3.endReplaceGroup();
            }
            $composer2 = $composer3;
            ChipColors colors5 = colors2;
            Function2 function4 = function2;
            Modifier modifier4 = modifier2;
            m2189ChipnkUnTEs(modifier4, function0, enabled3, function4, value, jM2179labelColorvNxB06k$material3, icon3, null, shape5, colors5, elevation4, stateBorderStroke$material3 != null ? stateBorderStroke$material3.getValue() : null, fM2923getHeightD9Ej5fM, paddingValues, interactionSource3, $composer2, (($dirty3 >> 6) & 14) | 12582912 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (($dirty3 << 9) & 1879048192), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border3;
            modifier2 = modifier4;
            enabled2 = enabled3;
            icon2 = icon3;
            shape3 = shape5;
            colors3 = colors5;
            elevation3 = elevation4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            enabled2 = z;
            icon2 = function3;
            shape3 = shape2;
            colors3 = colors2;
            elevation3 = elevation2;
            border2 = border;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ElevatedSuggestionChip$lambda$14(onClick, label, modifier2, enabled2, icon2, shape3, colors3, elevation3, border2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: Chip-nkUnTEs, reason: not valid java name */
    private static final void m2189ChipnkUnTEs(final Modifier modifier, final Function0<Unit> function0, final boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, final long labelColor, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Shape shape, final ChipColors colors, final ChipElevation elevation, final BorderStroke border, final float minHeight, final PaddingValues paddingValues, final MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1) {
        TextStyle textStyle;
        long j;
        BorderStroke borderStroke;
        Composer $composer2;
        int $dirty;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer3 = $composer.startRestartGroup(892465622);
        ComposerKt.sourceInformation($composer3, "C(Chip)N(modifier,onClick,enabled,label,labelTextStyle,labelColor:c#ui.graphics.Color,leadingIcon,trailingIcon,shape,colors,elevation,border,minHeight:c#ui.unit.Dp,paddingValues,interactionSource)1962@97212L22,1969@97509L478,1960@97138L849:Chip.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            textStyle = labelTextStyle;
            $dirty2 |= $composer3.changed(textStyle) ? 16384 : 8192;
        } else {
            textStyle = labelTextStyle;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            j = labelColor;
            $dirty2 |= $composer3.changed(j) ? 131072 : 65536;
        } else {
            j = labelColor;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(shape) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer3.changed(colors) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(elevation) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            borderStroke = border;
            $dirty1 |= $composer3.changed(borderStroke) ? 32 : 16;
        } else {
            borderStroke = border;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changed(minHeight) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer3.changed(paddingValues) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 16384 : 8192;
        }
        int $dirty3 = $dirty1;
        if ($composer3.shouldExecute(((306783379 & $dirty2) == 306783378 && ($dirty3 & 9363) == 9362) ? false : true, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(892465622, $dirty2, $dirty3, "androidx.compose.material3.Chip (Chip.kt:1957)");
            }
            if (interactionSource == null) {
                $composer3.startReplaceGroup(1596346437);
                ComposerKt.sourceInformation($composer3, "1959@97094L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -1333978275, "CC(remember):Chip.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                mutableInteractionSource = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $dirty2 = $dirty2;
                $composer3.startReplaceGroup(-1333978926);
                $composer3.endReplaceGroup();
                mutableInteractionSource = interactionSource;
            }
            MutableInteractionSource interactionSource2 = mutableInteractionSource;
            ComposerKt.sourceInformationMarkerStart($composer3, -1333974516, "CC(remember):Chip.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ChipKt.Chip_nkUnTEs$lambda$17$lambda$16((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            State<Dp> stateShadowElevation$material3 = null;
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier, false, (Function1) it$iv2, 1, null);
            long jM2169containerColorvNxB06k$material3 = colors.m2169containerColorvNxB06k$material3(enabled);
            if (elevation == null) {
                $composer3.startReplaceGroup(1596621344);
            } else {
                $composer3.startReplaceGroup(-1333969407);
                ComposerKt.sourceInformation($composer3, "1966@97371L43");
                stateShadowElevation$material3 = elevation.shadowElevation$material3(enabled, interactionSource2, $composer3, (($dirty2 >> 6) & 14) | (($dirty3 << 6) & 896));
            }
            $composer3.endReplaceGroup();
            final TextStyle textStyle2 = textStyle;
            final long j2 = j;
            $dirty = $dirty2;
            SurfaceKt.m2932Surfaceo_FOJdg(function0, modifierSemantics$default, enabled, shape, jM2169containerColorvNxB06k$material3, 0L, 0.0f, stateShadowElevation$material3 != null ? stateShadowElevation$material3.getValue().m7919unboximpl() : Dp.m7905constructorimpl(0), borderStroke, interactionSource2, ComposableLambdaKt.rememberComposableLambda(-70915349, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$Chip$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C1970@97519L462:Chip.kt#uh7d8r");
                    if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-70915349, $changed2, -1, "androidx.compose.material3.Chip.<anonymous> (Chip.kt:1970)");
                        }
                        ChipKt.m2190ChipContentfe0OD_I(function2, textStyle2, j2, function3, null, function4, colors.m2180leadingIconContentColorvNxB06k$material3(enabled), colors.m2181trailingIconContentColorvNxB06k$material3(enabled), minHeight, paddingValues, $composer4, 24576);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, (($dirty >> 3) & 14) | ($dirty & 896) | (($dirty >> 15) & 7168) | (($dirty3 << 21) & 234881024), 6, 96);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $dirty = $dirty2;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.Chip_nkUnTEs$lambda$18(modifier, function0, enabled, function2, labelTextStyle, labelColor, function3, function4, shape, colors, elevation, border, minHeight, paddingValues, interactionSource, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Chip_nkUnTEs$lambda$17$lambda$16(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7099getButtono7Vup1c());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: SelectableChip-u0RnIRE, reason: not valid java name */
    private static final void m2191SelectableChipu0RnIRE(final boolean selected, final Modifier modifier, final Function0<Unit> function0, final boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Shape shape, final SelectableChipColors colors, final SelectableChipElevation elevation, final BorderStroke border, final float minHeight, final PaddingValues paddingValues, final MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1) {
        TextStyle textStyle;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Composer $composer2;
        MutableInteractionSource mutableInteractionSource;
        State<Dp> stateShadowElevation$material3;
        Composer $composer3 = $composer.startRestartGroup(1786844928);
        ComposerKt.sourceInformation($composer3, "C(SelectableChip)N(selected,modifier,onClick,enabled,label,labelTextStyle,leadingIcon,avatar,trailingIcon,shape,colors,elevation,border,minHeight:c#ui.unit.Dp,paddingValues,interactionSource)2009@98774L24,2016@99083L670,2006@98671L1082:Chip.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(selected) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            textStyle = labelTextStyle;
            $dirty |= $composer3.changed(textStyle) ? 131072 : 65536;
        } else {
            textStyle = labelTextStyle;
        }
        if (($changed & 1572864) == 0) {
            function6 = function3;
            $dirty |= $composer3.changedInstance(function6) ? 1048576 : 524288;
        } else {
            function6 = function3;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changedInstance(function5) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changed(shape) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(colors) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changed(elevation) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changed(border) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer3.changed(minHeight) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer3.changed(paddingValues) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 131072 : 65536;
        }
        if (!$composer3.shouldExecute((($dirty & 306783379) == 306783378 && (74899 & $dirty1) == 74898) ? false : true, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1786844928, $dirty, $dirty1, "androidx.compose.material3.SelectableChip (Chip.kt:2003)");
            }
            if (interactionSource == null) {
                $composer3.startReplaceGroup(73215547);
                ComposerKt.sourceInformation($composer3, "2005@98627L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -828922201, "CC(remember):Chip.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                mutableInteractionSource = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $dirty1 = $dirty1;
                $composer3.startReplaceGroup(-828922852);
                $composer3.endReplaceGroup();
                mutableInteractionSource = interactionSource;
            }
            MutableInteractionSource interactionSource2 = mutableInteractionSource;
            ComposerKt.sourceInformationMarkerStart($composer3, -828917512, "CC(remember):Chip.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ChipKt.SelectableChip_u0RnIRE$lambda$21$lambda$20((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier, false, (Function1) it$iv2, 1, null);
            long jM2825containerColorWaAFU9c$material3 = colors.m2825containerColorWaAFU9c$material3(enabled, selected);
            if (elevation == null) {
                $composer3.startReplaceGroup(73531126);
                $composer3.endReplaceGroup();
                stateShadowElevation$material3 = null;
            } else {
                $composer3.startReplaceGroup(-828912021);
                ComposerKt.sourceInformation($composer3, "2013@98945L43");
                stateShadowElevation$material3 = elevation.shadowElevation$material3(enabled, interactionSource2, $composer3, (($dirty >> 9) & 14) | (($dirty1 << 3) & 896));
                $composer3.endReplaceGroup();
            }
            final TextStyle textStyle2 = textStyle;
            final Function2<? super Composer, ? super Integer, Unit> function7 = function6;
            int $dirty2 = $dirty;
            SurfaceKt.m2930Surfaced85dljk(selected, function0, modifierSemantics$default, enabled, shape, jM2825containerColorWaAFU9c$material3, 0L, 0.0f, stateShadowElevation$material3 != null ? stateShadowElevation$material3.getValue().m7919unboximpl() : Dp.m7905constructorimpl(0), border, interactionSource2, ComposableLambdaKt.rememberComposableLambda(-990050154, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$SelectableChip$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C2019@99228L519:Chip.kt#uh7d8r");
                    if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-990050154, $changed2, -1, "androidx.compose.material3.SelectableChip.<anonymous> (Chip.kt:2019)");
                        }
                        ChipKt.m2188AnimatingChipContentfe0OD_I(function2, textStyle2, colors.m2827labelColorWaAFU9c$material3(enabled, selected), function7, function4, function5, colors.m2828leadingIconContentColorWaAFU9c$material3(enabled, selected), colors.m2829trailingIconContentColorWaAFU9c$material3(enabled, selected), minHeight, paddingValues, $composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, ($dirty2 & 14) | (($dirty2 >> 3) & 112) | ($dirty2 & 7168) | (($dirty2 >> 15) & 57344) | (($dirty1 << 21) & 1879048192), 48, 192);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.SelectableChip_u0RnIRE$lambda$22(selected, modifier, function0, enabled, function2, labelTextStyle, function3, function4, function5, shape, colors, elevation, border, minHeight, paddingValues, interactionSource, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit SelectableChip_u0RnIRE$lambda$21$lambda$20(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7101getCheckboxo7Vup1c());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: ChipContent-fe0OD_I, reason: not valid java name */
    public static final void m2190ChipContentfe0OD_I(final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, long labelColor, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final long leadingIconColor, final long trailingIconColor, final float minHeight, final PaddingValues paddingValues, Composer $composer, final int $changed) {
        long j;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        long j2;
        long j3;
        char c;
        Composer $composer2 = $composer.startRestartGroup(1105630840);
        ComposerKt.sourceInformation($composer2, "C(ChipContent)N(label,labelTextStyle,labelColor:c#ui.graphics.Color,leadingIcon,avatar,trailingIcon,leadingIconColor:c#ui.graphics.Color,trailingIconColor:c#ui.graphics.Color,minHeight:c#ui.unit.Dp,paddingValues)2056@100480L1721,2053@100353L1848:Chip.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(labelTextStyle) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            j = labelColor;
            $dirty |= $composer2.changed(j) ? 256 : 128;
        } else {
            j = labelColor;
        }
        if (($changed & 3072) == 0) {
            function6 = function3;
            $dirty |= $composer2.changedInstance(function6) ? 2048 : 1024;
        } else {
            function6 = function3;
        }
        if (($changed & 24576) == 0) {
            function7 = function4;
            $dirty |= $composer2.changedInstance(function7) ? 16384 : 8192;
        } else {
            function7 = function4;
        }
        if ((196608 & $changed) == 0) {
            function8 = function5;
            $dirty |= $composer2.changedInstance(function8) ? 131072 : 65536;
        } else {
            function8 = function5;
        }
        if ((1572864 & $changed) == 0) {
            j2 = leadingIconColor;
            $dirty |= $composer2.changed(j2) ? 1048576 : 524288;
        } else {
            j2 = leadingIconColor;
        }
        if ((12582912 & $changed) == 0) {
            j3 = trailingIconColor;
            $dirty |= $composer2.changed(j3) ? 8388608 : 4194304;
        } else {
            j3 = trailingIconColor;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changed(minHeight) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer2.changed(paddingValues) ? 536870912 : 268435456;
        }
        if (!$composer2.shouldExecute(($dirty & 306783379) != 306783378, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                c = 1;
                ComposerKt.traceEventStart(1105630840, $dirty, -1, "androidx.compose.material3.ChipContent (Chip.kt:2052)");
            } else {
                c = 1;
            }
            ProvidedValue[] providedValueArr = new ProvidedValue[2];
            providedValueArr[0] = ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(j));
            providedValueArr[c] = TextKt.getLocalTextStyle().provides(labelTextStyle);
            final Function2<? super Composer, ? super Integer, Unit> function9 = function6;
            final Function2<? super Composer, ? super Integer, Unit> function10 = function7;
            final Function2<? super Composer, ? super Integer, Unit> function11 = function8;
            final long j4 = j2;
            final long j5 = j3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.rememberComposableLambda(-2130105544, c, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$ChipContent$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:49:0x0291  */
                /* JADX WARN: Code duplicated, block: B:50:0x02a3  */
                /* JADX WARN: Code duplicated, block: B:68:0x0409  */
                /* JADX WARN: Code duplicated, block: B:70:0x046e  */
                /* JADX WARN: Code duplicated, block: B:73:0x047a  */
                /* JADX WARN: Code duplicated, block: B:74:0x0480  */
                /* JADX WARN: Code duplicated, block: B:84:0x051e  */
                /* JADX WARN: Code duplicated, block: B:85:0x0530  */
                /* JADX WARN: Code duplicated, block: B:87:0x0554  */
                /* JADX WARN: Code duplicated, block: B:90:0x0573  */
                /* JADX WARN: Code duplicated, block: B:93:? A[RETURN, SYNTHETIC] */
                public final void invoke(Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    Function2<Composer, Integer, Unit> function12;
                    String str;
                    String str2;
                    int i;
                    Function2 leadingContent;
                    Function0<ComposeUiNode> function1;
                    int $changed$iv;
                    int compositeKeyHash$iv$iv;
                    Function0<ComposeUiNode> constructor;
                    Function0<ComposeUiNode> function13;
                    Composer $this$Layout_u24lambda_u240$iv$iv;
                    Function2 trailingContent;
                    ComposerKt.sourceInformation($composer3, "C2093@102146L38,2057@100490L1705:Chip.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2130105544, $changed2, -1, "androidx.compose.material3.ChipContent.<anonymous> (Chip.kt:2057)");
                    }
                    Modifier modifierPadding = PaddingKt.padding(SizeKt.m850defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, minHeight, 1, null), paddingValues);
                    ComposerKt.sourceInformationMarkerStart($composer3, 1668025758, "CC(remember):Chip.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new ChipLayoutMeasurePolicy();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ChipLayoutMeasurePolicy chipLayoutMeasurePolicy = (ChipLayoutMeasurePolicy) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Function2<Composer, Integer, Unit> function14 = function10;
                    Function2<Composer, Integer, Unit> function15 = function9;
                    Function2<Composer, Integer, Unit> function16 = function11;
                    long j6 = j4;
                    Function2<Composer, Integer, Unit> function17 = function2;
                    long j7 = j5;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifierPadding);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv = ((384 << 6) & 896) | 6;
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
                    Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, chipLayoutMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                        $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                        $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2 = ($changed$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -411001482, "C2073@101229L326:Chip.kt#uh7d8r");
                    if (function14 == null && function15 == null) {
                        $composer3.startReplaceGroup(-410471693);
                        $composer3.endReplaceGroup();
                        str2 = "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh";
                        str = "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo";
                        i = -1323940314;
                    } else {
                        $composer3.startReplaceGroup(-410987750);
                        ComposerKt.sourceInformation($composer3, "2061@100697L497");
                        Modifier modifier$iv = LayoutIdKt.layoutId(Modifier.INSTANCE, "leadingIcon");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv2 = (54 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                        Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function0 = constructor3;
                            $composer3.createNode(function0);
                        } else {
                            function0 = constructor3;
                            $composer3.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                            function12 = function14;
                        } else {
                            function12 = function14;
                            if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i3 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i4 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1737582560, "C2066@100955L53:Chip.kt#uh7d8r");
                            str = "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo";
                            str2 = "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh";
                            i = -1323940314;
                            leadingContent = ChipKt.m2196leadingContentXOJAsU(function12, function15, j6, $composer3, 0);
                            if (leadingContent != null) {
                                $composer3.startReplaceGroup(-1737425918);
                                ComposerKt.sourceInformation($composer3, "2068@101099L16");
                                leadingContent.invoke($composer3, 0);
                                $composer3.endReplaceGroup();
                            } else {
                                $composer3.startReplaceGroup(-1737349038);
                                $composer3.endReplaceGroup();
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceGroup();
                        }
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i5 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        int i6 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1737582560, "C2066@100955L53:Chip.kt#uh7d8r");
                        str = "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo";
                        str2 = "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh";
                        i = -1323940314;
                        leadingContent = ChipKt.m2196leadingContentXOJAsU(function12, function15, j6, $composer3, 0);
                        if (leadingContent != null) {
                            $composer3.startReplaceGroup(-1737425918);
                            ComposerKt.sourceInformation($composer3, "2068@101099L16");
                            leadingContent.invoke($composer3, 0);
                            $composer3.endReplaceGroup();
                        } else {
                            $composer3.startReplaceGroup(-1737349038);
                            $composer3.endReplaceGroup();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceGroup();
                    }
                    Modifier modifier$iv$iv = PaddingKt.m820paddingVpY3zN4(LayoutIdKt.layoutId(Modifier.INSTANCE, MetadataField.LABEL), ChipKt.HorizontalElementsPadding, Dp.m7905constructorimpl(0));
                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    int $changed$iv$iv3 = (438 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, i, str2);
                    int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv$iv);
                    Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor4;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor4;
                        $composer3.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                        $changed$iv = 438;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i7 = ($changed$iv$iv$iv2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        int i8 = (($changed$iv >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1437742697, "C2078@101527L7:Chip.kt#uh7d8r");
                        function17.invoke($composer3, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (function16 != null) {
                            $composer3.startReplaceGroup(-410073157);
                            ComposerKt.sourceInformation($composer3, "2081@101620L464");
                            Modifier modifier$iv2 = LayoutIdKt.layoutId(Modifier.INSTANCE, "trailingIcon");
                            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, str);
                            MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                            int $changed$iv$iv4 = (54 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, str2);
                            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                            CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv3 = (($changed$iv$iv4 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer3.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                                function13 = constructor;
                                $composer3.createNode(function13);
                            } else {
                                function13 = constructor;
                                $composer3.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                            int i9 = ($changed$iv$iv$iv3 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                            int i10 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1073567083, "C2085@101848L48:Chip.kt#uh7d8r");
                            trailingContent = ChipKt.m2197trailingContentRPmYEkk(function16, j7, $composer3, 0);
                            if (trailingContent != null) {
                                $composer3.startReplaceGroup(1073690090);
                                ComposerKt.sourceInformation($composer3, "2087@101988L17");
                                trailingContent.invoke($composer3, 0);
                                $composer3.endReplaceGroup();
                            } else {
                                $composer3.startReplaceGroup(1073767931);
                                $composer3.endReplaceGroup();
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endReplaceGroup();
                        } else {
                            $composer3.startReplaceGroup(-409588813);
                            $composer3.endReplaceGroup();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    $changed$iv = 438;
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i11 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    int i12 = (($changed$iv >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1437742697, "C2078@101527L7:Chip.kt#uh7d8r");
                    function17.invoke($composer3, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (function16 != null) {
                        $composer3.startReplaceGroup(-410073157);
                        ComposerKt.sourceInformation($composer3, "2081@101620L464");
                        Modifier modifier$iv3 = LayoutIdKt.layoutId(Modifier.INSTANCE, "trailingIcon");
                        Alignment contentAlignment$iv3 = Alignment.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, str);
                        MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                        int $changed$iv$iv5 = (54 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, str2);
                        compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv4 = (($changed$iv$iv5 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function13 = constructor;
                            $composer3.createNode(function13);
                        } else {
                            function13 = constructor;
                            $composer3.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash5);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                        int i13 = ($changed$iv$iv$iv4 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                        int i14 = ((54 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1073567083, "C2085@101848L48:Chip.kt#uh7d8r");
                        trailingContent = ChipKt.m2197trailingContentRPmYEkk(function16, j7, $composer3, 0);
                        if (trailingContent != null) {
                            $composer3.startReplaceGroup(1073690090);
                            ComposerKt.sourceInformation($composer3, "2087@101988L17");
                            trailingContent.invoke($composer3, 0);
                            $composer3.endReplaceGroup();
                        } else {
                            $composer3.startReplaceGroup(1073767931);
                            $composer3.endReplaceGroup();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceGroup();
                    } else {
                        $composer3.startReplaceGroup(-409588813);
                        $composer3.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
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
            final long j6 = j;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.ChipContent_fe0OD_I$lambda$23(function2, labelTextStyle, j6, function3, function4, function5, leadingIconColor, trailingIconColor, minHeight, paddingValues, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: AnimatingChipContent-fe0OD_I, reason: not valid java name */
    public static final void m2188AnimatingChipContentfe0OD_I(final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, long labelColor, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final long leadingIconColor, final long trailingIconColor, final float minHeight, final PaddingValues paddingValues, Composer $composer, final int $changed) {
        long j;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        long j2;
        long j3;
        char c;
        Composer $composer2 = $composer.startRestartGroup(-2070754602);
        ComposerKt.sourceInformation($composer2, "C(AnimatingChipContent)N(label,labelTextStyle,labelColor:c#ui.graphics.Color,leadingIcon,avatar,trailingIcon,leadingIconColor:c#ui.graphics.Color,trailingIconColor:c#ui.graphics.Color,minHeight:c#ui.unit.Dp,paddingValues)2118@102851L4086,2115@102724L4213:Chip.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(labelTextStyle) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            j = labelColor;
            $dirty |= $composer2.changed(j) ? 256 : 128;
        } else {
            j = labelColor;
        }
        if (($changed & 3072) == 0) {
            function6 = function3;
            $dirty |= $composer2.changedInstance(function6) ? 2048 : 1024;
        } else {
            function6 = function3;
        }
        if (($changed & 24576) == 0) {
            function7 = function4;
            $dirty |= $composer2.changedInstance(function7) ? 16384 : 8192;
        } else {
            function7 = function4;
        }
        if ((196608 & $changed) == 0) {
            function8 = function5;
            $dirty |= $composer2.changedInstance(function8) ? 131072 : 65536;
        } else {
            function8 = function5;
        }
        if ((1572864 & $changed) == 0) {
            j2 = leadingIconColor;
            $dirty |= $composer2.changed(j2) ? 1048576 : 524288;
        } else {
            j2 = leadingIconColor;
        }
        if ((12582912 & $changed) == 0) {
            j3 = trailingIconColor;
            $dirty |= $composer2.changed(j3) ? 8388608 : 4194304;
        } else {
            j3 = trailingIconColor;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changed(minHeight) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer2.changed(paddingValues) ? 536870912 : 268435456;
        }
        if (!$composer2.shouldExecute(($dirty & 306783379) != 306783378, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                c = 1;
                ComposerKt.traceEventStart(-2070754602, $dirty, -1, "androidx.compose.material3.AnimatingChipContent (Chip.kt:2114)");
            } else {
                c = 1;
            }
            ProvidedValue[] providedValueArr = new ProvidedValue[2];
            providedValueArr[0] = ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(j));
            providedValueArr[c] = TextKt.getLocalTextStyle().provides(labelTextStyle);
            final Function2<? super Composer, ? super Integer, Unit> function9 = function6;
            final Function2<? super Composer, ? super Integer, Unit> function10 = function7;
            final Function2<? super Composer, ? super Integer, Unit> function11 = function8;
            final long j4 = j2;
            final long j5 = j3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.rememberComposableLambda(-668234218, c, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$AnimatingChipContent$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer3, "C2120@102988L14,2121@103063L14,2122@103137L16,2123@103216L16,2197@106882L38,2124@103241L3690:Chip.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-668234218, $changed2, -1, "androidx.compose.material3.AnimatingChipContent.<anonymous> (Chip.kt:2120)");
                    }
                    FiniteAnimationSpec fadeInSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.SlowEffects, $composer3, 6);
                    FiniteAnimationSpec fadeOutSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer3, 6);
                    FiniteAnimationSpec expandSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer3, 6);
                    FiniteAnimationSpec shrinkSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer3, 6);
                    Modifier modifierPadding = PaddingKt.padding(SizeKt.m850defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, minHeight, 1, null), paddingValues);
                    ComposerKt.sourceInformationMarkerStart($composer3, 2072139932, "CC(remember):Chip.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new ChipLayoutMeasurePolicy();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ChipLayoutMeasurePolicy chipLayoutMeasurePolicy = (ChipLayoutMeasurePolicy) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    final Function2<Composer, Integer, Unit> function12 = function10;
                    final Function2<Composer, Integer, Unit> function13 = function9;
                    final Function2<Composer, Integer, Unit> function14 = function11;
                    final long j6 = j4;
                    Function2<Composer, Integer, Unit> function15 = function2;
                    final long j7 = j5;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifierPadding);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv = ((384 << 6) & 896) | 6;
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
                    Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, chipLayoutMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                        $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                        $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i = ($changed$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -564817408, "C2141@104124L826,2128@103442L1508,2158@104967L362,2179@106014L824,2168@105407L1431:Chip.kt#uh7d8r");
                    AnimatedVisibilityKt.AnimatedVisibility((function12 == null && function13 == null) ? false : true, LayoutIdKt.layoutId(Modifier.INSTANCE, "leadingIcon"), EnterExitTransitionKt.expandHorizontally$default(expandSpec, Alignment.INSTANCE.getStart(), false, null, 12, null).plus(EnterExitTransitionKt.fadeIn$default(fadeInSpec, 0.0f, 2, null)), EnterExitTransitionKt.shrinkHorizontally$default(shrinkSpec, Alignment.INSTANCE.getStart(), false, null, 12, null).plus(EnterExitTransitionKt.fadeOut$default(fadeOutSpec, 0.0f, 2, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(687705959, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$AnimatingChipContent$1$2$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                            invoke(animatedVisibilityScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Code duplicated, block: B:22:0x013b  */
                        /* JADX WARN: Code duplicated, block: B:23:0x0142  */
                        /* JADX WARN: Code duplicated, block: B:26:0x0174  */
                        /* JADX WARN: Code duplicated, block: B:28:? A[RETURN, SYNTHETIC] */
                        public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer4, int $changed3) {
                            Function0<ComposeUiNode> function1;
                            int $changed$iv;
                            Function2 function16;
                            ComposerKt.sourceInformation($composer4, "C2147@104553L53,2146@104488L144,2150@104654L278:Chip.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(687705959, $changed3, -1, "androidx.compose.material3.AnimatingChipContent.<anonymous>.<anonymous>.<anonymous> (Chip.kt:2145)");
                            }
                            State leadingContentRetainedState = ChipKt.rememberRetainedState(ChipKt.m2196leadingContentXOJAsU(function12, function13, j6, $composer4, 0), $composer4, 0);
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv2 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                function1 = constructor2;
                                $composer4.createNode(function1);
                            } else {
                                function1 = constructor2;
                                $composer4.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer4);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                                $changed$iv = 48;
                                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                }
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                int i2 = ($changed$iv$iv$iv >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                int i3 = (($changed$iv >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer4, -1538137033, "C:Chip.kt#uh7d8r");
                                function16 = (Function2) leadingContentRetainedState.getValue();
                                if (function16 == null) {
                                    $composer4.startReplaceGroup(-1538103400);
                                } else {
                                    $composer4.startReplaceGroup(-326710903);
                                    ComposerKt.sourceInformation($composer4, "2154@104875L8");
                                    function16.invoke($composer4, 0);
                                }
                                $composer4.endReplaceGroup();
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                            $changed$iv = 48;
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i4 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                            int i5 = (($changed$iv >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1538137033, "C:Chip.kt#uh7d8r");
                            function16 = (Function2) leadingContentRetainedState.getValue();
                            if (function16 == null) {
                                $composer4.startReplaceGroup(-1538103400);
                            } else {
                                $composer4.startReplaceGroup(-326710903);
                                ComposerKt.sourceInformation($composer4, "2154@104875L8");
                                function16.invoke($composer4, 0);
                            }
                            $composer4.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54), $composer3, 196656, 16);
                    Modifier modifier$iv = PaddingKt.m821paddingVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, MetadataField.LABEL), ChipKt.HorizontalElementsPadding, 0.0f, 2, null);
                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    int $changed$iv$iv2 = (438 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
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
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i3 = ((438 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 75607137, "C2164@105301L7:Chip.kt#uh7d8r");
                    function15.invoke($composer3, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    AnimatedVisibilityKt.AnimatedVisibility(function14 != null, LayoutIdKt.layoutId(Modifier.INSTANCE, "trailingIcon"), EnterExitTransitionKt.expandHorizontally$default(expandSpec, Alignment.INSTANCE.getEnd(), false, null, 12, null).plus(EnterExitTransitionKt.fadeIn$default(fadeInSpec, 0.0f, 2, null)), EnterExitTransitionKt.shrinkHorizontally$default(shrinkSpec, Alignment.INSTANCE.getEnd(), false, null, 12, null).plus(EnterExitTransitionKt.fadeOut$default(fadeOutSpec, 0.0f, 2, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1905252304, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$AnimatingChipContent$1$2$3
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                            invoke(animatedVisibilityScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer4, int $changed3) {
                            Function0<ComposeUiNode> function1;
                            ComposerKt.sourceInformation($composer4, "C2185@106445L48,2184@106380L139,2188@106541L279:Chip.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1905252304, $changed3, -1, "androidx.compose.material3.AnimatingChipContent.<anonymous>.<anonymous>.<anonymous> (Chip.kt:2183)");
                            }
                            State trailingContentRetainedState = ChipKt.rememberRetainedState(ChipKt.m2197trailingContentRPmYEkk(function14, j7, $composer4, 0), $composer4, 0);
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                            ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Modifier modifier$iv2 = Modifier.INSTANCE;
                            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv3 = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
                            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                function1 = constructor3;
                                $composer4.createNode(function1);
                            } else {
                                function1 = constructor3;
                                $composer4.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer4);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                            int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i5 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -2101817907, "C:Chip.kt#uh7d8r");
                            Function2 function16 = (Function2) trailingContentRetainedState.getValue();
                            if (function16 == null) {
                                $composer4.startReplaceGroup(-2101783313);
                            } else {
                                $composer4.startReplaceGroup(-344894126);
                                ComposerKt.sourceInformation($composer4, "2192@106763L8");
                                function16.invoke($composer4, 0);
                            }
                            $composer4.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54), $composer3, 196656, 16);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
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
            final long j6 = j;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ChipKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.AnimatingChipContent_fe0OD_I$lambda$24(function2, labelTextStyle, j6, function3, function4, function5, leadingIconColor, trailingIconColor, minHeight, paddingValues, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: leadingContent-XO-JAsU, reason: not valid java name */
    public static final Function2<Composer, Integer, Unit> m2196leadingContentXOJAsU(Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final long leadingIconColor, Composer $composer, int $changed) {
        Function2 function2RememberComposableLambda;
        ComposerKt.sourceInformationMarkerStart($composer, 1330309098, "C(leadingContent)N(avatar,leadingIcon,leadingIconColor:c#ui.graphics.Color):Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1330309098, $changed, -1, "androidx.compose.material3.leadingContent (Chip.kt:2212)");
        }
        if (function2 != null) {
            $composer.startReplaceGroup(-1473203984);
            $composer.endReplaceGroup();
            function2RememberComposableLambda = function2;
        } else if (function3 != null) {
            $composer.startReplaceGroup(1575390813);
            ComposerKt.sourceInformation($composer, "2215@107394L183");
            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-237350650, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$leadingContent$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C2216@107412L151:Chip.kt#uh7d8r");
                    if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-237350650, $changed2, -1, "androidx.compose.material3.leadingContent.<anonymous> (Chip.kt:2216)");
                    }
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(leadingIconColor)), function3, $composer2, ProvidedValue.$stable);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(1575618259);
            $composer.endReplaceGroup();
            function2RememberComposableLambda = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return function2RememberComposableLambda;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: trailingContent-RPmYEkk, reason: not valid java name */
    public static final Function2<Composer, Integer, Unit> m2197trailingContentRPmYEkk(final Function2<? super Composer, ? super Integer, Unit> function2, final long trailingIconColor, Composer $composer, int $changed) {
        ComposableLambda composableLambdaRememberComposableLambda;
        ComposerKt.sourceInformationMarkerStart($composer, -165980551, "C(trailingContent)N(trailingIcon,trailingIconColor:c#ui.graphics.Color):Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-165980551, $changed, -1, "androidx.compose.material3.trailingContent (Chip.kt:2231)");
        }
        if (function2 != null) {
            $composer.startReplaceGroup(-1219055576);
            ComposerKt.sourceInformation($composer, "2232@107914L165");
            composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-566924201, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$trailingContent$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C2233@107928L141:Chip.kt#uh7d8r");
                    if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-566924201, $changed2, -1, "androidx.compose.material3.trailingContent.<anonymous> (Chip.kt:2233)");
                    }
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(trailingIconColor)), function2, $composer2, ProvidedValue.$stable);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-1218863531);
            $composer.endReplaceGroup();
            composableLambdaRememberComposableLambda = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return composableLambdaRememberComposableLambda;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> State<T> rememberRetainedState(T t, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -618198444, "C(rememberRetainedState)N(targetValue)2249@108434L40:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-618198444, $changed, -1, "androidx.compose.material3.rememberRetainedState (Chip.kt:2248)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1383214468, "CC(remember):Chip.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        MutableState retainedState = (MutableState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (t != null) {
            retainedState.setValue(t);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return retainedState;
    }

    public static final ChipColors getDefaultSuggestionChipColors(ColorScheme $this$defaultSuggestionChipColors) {
        ChipColors it = $this$defaultSuggestionChipColors.getDefaultSuggestionChipColorsCached();
        if (it != null) {
            return it;
        }
        long jM5132getTransparent0d7_KjU = Color.INSTANCE.m5132getTransparent0d7_KjU();
        long jFromToken = ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getLabelTextColor());
        long jFromToken2 = ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getLeadingIconColor());
        long jM5133getUnspecified0d7_KjU = Color.INSTANCE.m5133getUnspecified0d7_KjU();
        long jM5132getTransparent0d7_KjU2 = Color.INSTANCE.m5132getTransparent0d7_KjU();
        long jFromToken3 = ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getDisabledLabelTextColor());
        long jM5095copywmQWz5c = Color.m5095copywmQWz5c(jFromToken3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken3) : SuggestionChipTokens.INSTANCE.getDisabledLabelTextOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken3) : 0.0f);
        long jFromToken4 = ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getDisabledLeadingIconColor());
        ChipColors it2 = new ChipColors(jM5132getTransparent0d7_KjU, jFromToken, jFromToken2, jM5133getUnspecified0d7_KjU, jM5132getTransparent0d7_KjU2, jM5095copywmQWz5c, Color.m5095copywmQWz5c(jFromToken4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken4) : SuggestionChipTokens.INSTANCE.getDisabledLeadingIconOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken4) : 0.0f), Color.INSTANCE.m5133getUnspecified0d7_KjU(), null);
        $this$defaultSuggestionChipColors.setDefaultSuggestionChipColorsCached$material3(it2);
        return it2;
    }

    static /* synthetic */ PaddingValues inputChipPadding$default(boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        return inputChipPadding(z, z2, z3);
    }

    private static final PaddingValues inputChipPadding(boolean hasAvatar, boolean hasLeadingIcon, boolean hasTrailingIcon) {
        float start = (hasAvatar || !hasLeadingIcon) ? Dp.m7905constructorimpl(4) : Dp.m7905constructorimpl(8);
        float end = hasTrailingIcon ? Dp.m7905constructorimpl(8) : Dp.m7905constructorimpl(4);
        return PaddingKt.m816PaddingValuesa9UjIt4$default(start, 0.0f, end, 0.0f, 10, null);
    }
}
