package androidx.compose.ui.focus;

import android.graphics.Rect;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.media3.extractor.ts.TsExtractor;
import kotlin.Metadata;

/* JADX INFO: compiled from: FocusInteropUtils.android.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007*\u00020\u0005H\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u000bH\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0000\u001a\u0014\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0000\u001a%\u0010\u0015\u001a\u00020\u0016*\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u0000¢\u0006\u0002\u0010\u0019\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"tempCoordinates", "", "tempRect", "Landroid/graphics/Rect;", "toFocusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "androidDirection", "", "toAndroidFocusDirection", "toAndroidFocusDirection-3ESFkO8", "(I)Ljava/lang/Integer;", "Landroidx/compose/ui/input/key/KeyEvent;", "toFocusDirection-ZmokQxo", "(Landroid/view/KeyEvent;)Landroidx/compose/ui/focus/FocusDirection;", "toLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "androidLayoutDirection", "calculateFocusRectRelativeTo", "Landroidx/compose/ui/geometry/Rect;", "Landroid/view/View;", "view", "requestInteropFocus", "", "direction", "rect", "(Landroid/view/View;Ljava/lang/Integer;Landroid/graphics/Rect;)Z", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FocusInteropUtils_androidKt {
    private static final int[] tempCoordinates = new int[2];
    private static final Rect tempRect = new Rect();

    public static final FocusDirection toFocusDirection(int androidDirection) {
        switch (androidDirection) {
            case 1:
                return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4736getPreviousdhqQ8s());
            case 2:
                return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4735getNextdhqQ8s());
            case 17:
                return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4734getLeftdhqQ8s());
            case 33:
                return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4738getUpdhqQ8s());
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4737getRightdhqQ8s());
            case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4731getDowndhqQ8s());
            default:
                return null;
        }
    }

    /* JADX INFO: renamed from: toAndroidFocusDirection-3ESFkO8, reason: not valid java name */
    public static final Integer m4739toAndroidFocusDirection3ESFkO8(int i) {
        if (FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4738getUpdhqQ8s())) {
            return 33;
        }
        if (FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4731getDowndhqQ8s())) {
            return Integer.valueOf(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
        }
        if (FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4734getLeftdhqQ8s())) {
            return 17;
        }
        if (FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4737getRightdhqQ8s())) {
            return 66;
        }
        if (FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4735getNextdhqQ8s())) {
            return 2;
        }
        return FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4736getPreviousdhqQ8s()) ? 1 : null;
    }

    /* JADX INFO: renamed from: toFocusDirection-ZmokQxo, reason: not valid java name */
    public static final FocusDirection m4740toFocusDirectionZmokQxo(KeyEvent keyEvent) {
        long jM6253getKeyZmokQxo = KeyEvent_androidKt.m6253getKeyZmokQxo(keyEvent);
        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6105getNavigatePreviousEK5gGoQ())) {
            return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4736getPreviousdhqQ8s());
        }
        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6103getNavigateNextEK5gGoQ())) {
            return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4735getNextdhqQ8s());
        }
        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6181getTabEK5gGoQ())) {
            return FocusDirection.m4724boximpl(KeyEvent_androidKt.m6259isShiftPressedZmokQxo(keyEvent) ? FocusDirection.INSTANCE.m4736getPreviousdhqQ8s() : FocusDirection.INSTANCE.m4735getNextdhqQ8s());
        }
        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6022getDirectionRightEK5gGoQ())) {
            return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4737getRightdhqQ8s());
        }
        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6021getDirectionLeftEK5gGoQ())) {
            return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4734getLeftdhqQ8s());
        }
        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6023getDirectionUpEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6134getPageUpEK5gGoQ())) {
            return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4738getUpdhqQ8s());
        }
        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6018getDirectionDownEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6133getPageDownEK5gGoQ())) {
            return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4731getDowndhqQ8s());
        }
        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6017getDirectionCenterEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6031getEnterEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6123getNumPadEnterEK5gGoQ())) {
            return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4732getEnterdhqQ8s());
        }
        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m5960getBackEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6034getEscapeEK5gGoQ())) {
            return FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4733getExitdhqQ8s());
        }
        return null;
    }

    public static final LayoutDirection toLayoutDirection(int androidLayoutDirection) {
        switch (androidLayoutDirection) {
            case 0:
                return LayoutDirection.Ltr;
            case 1:
                return LayoutDirection.Rtl;
            default:
                return null;
        }
    }

    public static final androidx.compose.ui.geometry.Rect calculateFocusRectRelativeTo(View $this$calculateFocusRectRelativeTo, View view) {
        $this$calculateFocusRectRelativeTo.getLocationInWindow(tempCoordinates);
        int xInWindow = tempCoordinates[0];
        int yInWindow = tempCoordinates[1];
        view.getLocationInWindow(tempCoordinates);
        int targetX = tempCoordinates[0];
        int targetY = tempCoordinates[1];
        float x = xInWindow - targetX;
        float y = yInWindow - targetY;
        $this$calculateFocusRectRelativeTo.getFocusedRect(tempRect);
        return new androidx.compose.ui.geometry.Rect(tempRect.left + x, tempRect.top + y, tempRect.left + x + tempRect.width(), tempRect.top + y + tempRect.height());
    }

    public static final boolean requestInteropFocus(View $this$requestInteropFocus, Integer direction, Rect rect) {
        if (direction == null) {
            return $this$requestInteropFocus.requestFocus();
        }
        if (!($this$requestInteropFocus instanceof ViewGroup)) {
            return $this$requestInteropFocus.requestFocus(direction.intValue(), rect);
        }
        if (((ViewGroup) $this$requestInteropFocus).isFocused()) {
            return true;
        }
        if (((ViewGroup) $this$requestInteropFocus).isFocusable() && !((ViewGroup) $this$requestInteropFocus).hasFocus()) {
            return ((ViewGroup) $this$requestInteropFocus).requestFocus(direction.intValue(), rect);
        }
        if ($this$requestInteropFocus instanceof AndroidComposeView) {
            return ((AndroidComposeView) $this$requestInteropFocus).requestFocus(direction.intValue(), rect);
        }
        if (rect != null) {
            View viewFindNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect((ViewGroup) $this$requestInteropFocus, rect, direction.intValue());
            return viewFindNextFocusFromRect != null ? viewFindNextFocusFromRect.requestFocus(direction.intValue(), rect) : ((ViewGroup) $this$requestInteropFocus).requestFocus(direction.intValue(), rect);
        }
        View focusedView = ((ViewGroup) $this$requestInteropFocus).hasFocus() ? ((ViewGroup) $this$requestInteropFocus).findFocus() : null;
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup) $this$requestInteropFocus, focusedView, direction.intValue());
        return viewFindNextFocus != null ? viewFindNextFocus.requestFocus(direction.intValue()) : $this$requestInteropFocus.requestFocus(direction.intValue());
    }
}
