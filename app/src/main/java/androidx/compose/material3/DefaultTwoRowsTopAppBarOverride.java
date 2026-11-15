package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0017¢\u0006\u0002\u0010\u0007¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/DefaultTwoRowsTopAppBarOverride;", "Landroidx/compose/material3/TwoRowsTopAppBarOverride;", "<init>", "()V", "TwoRowsTopAppBar", "", "Landroidx/compose/material3/TwoRowsTopAppBarOverrideScope;", "(Landroidx/compose/material3/TwoRowsTopAppBarOverrideScope;Landroidx/compose/runtime/Composer;I)V", "material3", "hideTopRowSemantics", ""}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DefaultTwoRowsTopAppBarOverride implements TwoRowsTopAppBarOverride {
    public static final int $stable = 0;
    public static final DefaultTwoRowsTopAppBarOverride INSTANCE = new DefaultTwoRowsTopAppBarOverride();

    static final Unit TwoRowsTopAppBar$lambda$28(DefaultTwoRowsTopAppBarOverride defaultTwoRowsTopAppBarOverride, TwoRowsTopAppBarOverrideScope twoRowsTopAppBarOverrideScope, int i, Composer composer, int i2) {
        defaultTwoRowsTopAppBarOverride.TwoRowsTopAppBar(twoRowsTopAppBarOverrideScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private DefaultTwoRowsTopAppBarOverride() {
    }

    /* JADX WARN: Failed to apply debug info
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v13 ??, new type: boolean
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyDebugInfo(TypeUpdate.java:77)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchDebugInfoByOffset(DebugInfoApplyVisitor.java:107)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:83)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
    Caused by: java.lang.NullPointerException
     */
    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v13 ??, new type: boolean
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:186)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:245)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
        Caused by: java.lang.NullPointerException
        */
    @Override // androidx.compose.material3.TwoRowsTopAppBarOverride
    public void TwoRowsTopAppBar(androidx.compose.material3.TwoRowsTopAppBarOverrideScope r99, androidx.compose.runtime.Composer r100, int r101) {
        /*
            Method dump skipped, instruction units count: 1928
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DefaultTwoRowsTopAppBarOverride.TwoRowsTopAppBar(androidx.compose.material3.TwoRowsTopAppBarOverrideScope, androidx.compose.runtime.Composer, int):void");
    }

    static final float TwoRowsTopAppBar$lambda$5$lambda$4(TwoRowsTopAppBarOverrideScope $this_TwoRowsTopAppBar) {
        TopAppBarState state;
        TopAppBarScrollBehavior scrollBehavior = $this_TwoRowsTopAppBar.getScrollBehavior();
        if (scrollBehavior == null || (state = scrollBehavior.getState()) == null) {
            return 0.0f;
        }
        return state.getCollapsedFraction();
    }

    static final float TwoRowsTopAppBar$lambda$10$lambda$9(Function0 $colorTransitionFraction) {
        return 1.0f - ((Number) $colorTransitionFraction.invoke()).floatValue();
    }

    private static final boolean TwoRowsTopAppBar$lambda$13(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    static final boolean TwoRowsTopAppBar$lambda$12$lambda$11(Function0 $colorTransitionFraction) {
        return ((Number) $colorTransitionFraction.invoke()).floatValue() < 0.5f;
    }

    static final Unit TwoRowsTopAppBar$lambda$15$lambda$14(TwoRowsTopAppBarOverrideScope $this_TwoRowsTopAppBar, float delta) {
        TopAppBarState state = $this_TwoRowsTopAppBar.getScrollBehavior().getState();
        state.setHeightOffset(state.getHeightOffset() + delta);
        return Unit.INSTANCE;
    }

    static final Unit TwoRowsTopAppBar$lambda$18$lambda$17(Function0 $appBarContainerColor, DrawScope $this$drawBehind) {
        DrawScope.m5665drawRectnJ9OG0$default($this$drawBehind, ((Color) $appBarContainerColor.invoke()).m5107unboximpl(), 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
        return Unit.INSTANCE;
    }

    static final Unit TwoRowsTopAppBar$lambda$20$lambda$19(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
        return Unit.INSTANCE;
    }

    static final float TwoRowsTopAppBar$lambda$27$lambda$26$lambda$23$lambda$22() {
        return 0.0f;
    }

    static final float TwoRowsTopAppBar$lambda$27$lambda$26$lambda$25$lambda$24(TwoRowsTopAppBarOverrideScope $this_TwoRowsTopAppBar) {
        TopAppBarState state;
        TopAppBarScrollBehavior scrollBehavior = $this_TwoRowsTopAppBar.getScrollBehavior();
        if (scrollBehavior == null || (state = scrollBehavior.getState()) == null) {
            return 0.0f;
        }
        return state.getHeightOffset();
    }
}
