package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;

/* JADX INFO: loaded from: classes12.dex */
public class BottomNavigationMenuView extends NavigationBarMenuView {
    private final int activeItemMaxWidth;
    private final int activeItemMinWidth;
    private final int inactiveItemMaxWidth;
    private final int inactiveItemMinWidth;
    private final int itemHeight;
    private boolean itemHorizontalTranslationEnabled;
    private int[] tempChildWidths;

    public BottomNavigationMenuView(Context context) {
        super(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
        params.gravity = 17;
        setLayoutParams(params);
        Resources res = getResources();
        this.inactiveItemMaxWidth = res.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
        this.inactiveItemMinWidth = res.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
        this.activeItemMaxWidth = res.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
        this.activeItemMinWidth = res.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_min_width);
        this.itemHeight = res.getDimensionPixelSize(R.dimen.design_bottom_navigation_height);
        this.tempChildWidths = new int[5];
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        MenuBuilder menu = getMenu();
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int visibleCount = menu.getVisibleItems().size();
        int totalCount = getChildCount();
        int heightSpec = View.MeasureSpec.makeMeasureSpec(this.itemHeight, 1073741824);
        int i2 = 8;
        if (isShifting(getLabelVisibilityMode(), visibleCount) && isItemHorizontalTranslationEnabled()) {
            View activeChild = getChildAt(getSelectedItemPosition());
            int activeItemWidth = this.activeItemMinWidth;
            if (activeChild.getVisibility() != 8) {
                activeChild.measure(View.MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, Integer.MIN_VALUE), heightSpec);
                activeItemWidth = Math.max(activeItemWidth, activeChild.getMeasuredWidth());
            }
            int inactiveCount = visibleCount - (activeChild.getVisibility() != 8 ? 1 : 0);
            int activeMaxAvailable = width - (this.inactiveItemMinWidth * inactiveCount);
            int activeWidth = Math.min(activeMaxAvailable, Math.min(activeItemWidth, this.activeItemMaxWidth));
            int inactiveMaxAvailable = (width - activeWidth) / (inactiveCount == 0 ? 1 : inactiveCount);
            int inactiveWidth = Math.min(inactiveMaxAvailable, this.inactiveItemMaxWidth);
            int extra = (width - activeWidth) - (inactiveWidth * inactiveCount);
            int i3 = 0;
            i = 0;
            while (i3 < totalCount) {
                if (getChildAt(i3).getVisibility() != i2) {
                    this.tempChildWidths[i3] = i3 == getSelectedItemPosition() ? activeWidth : inactiveWidth;
                    if (extra > 0) {
                        int[] iArr = this.tempChildWidths;
                        iArr[i3] = iArr[i3] + 1;
                        extra--;
                    }
                } else {
                    this.tempChildWidths[i3] = 0;
                }
                i3++;
                i2 = 8;
            }
        } else {
            int i4 = 1;
            i = 0;
            int maxAvailable = width / (visibleCount == 0 ? i4 : visibleCount);
            int childWidth = Math.min(maxAvailable, this.activeItemMaxWidth);
            int extra2 = width - (childWidth * visibleCount);
            for (int i5 = 0; i5 < totalCount; i5++) {
                if (getChildAt(i5).getVisibility() != 8) {
                    this.tempChildWidths[i5] = childWidth;
                    if (extra2 > 0) {
                        int[] iArr2 = this.tempChildWidths;
                        iArr2[i5] = iArr2[i5] + 1;
                        extra2--;
                    }
                } else {
                    this.tempChildWidths[i5] = i;
                }
            }
        }
        int totalWidth = 0;
        for (int i6 = 0; i6 < totalCount; i6++) {
            View child = getChildAt(i6);
            if (child.getVisibility() != 8) {
                child.measure(View.MeasureSpec.makeMeasureSpec(this.tempChildWidths[i6], 1073741824), heightSpec);
                ViewGroup.LayoutParams params = child.getLayoutParams();
                params.width = child.getMeasuredWidth();
                totalWidth += child.getMeasuredWidth();
            }
        }
        int i7 = i;
        setMeasuredDimension(View.resolveSizeAndState(totalWidth, View.MeasureSpec.makeMeasureSpec(totalWidth, 1073741824), i7), View.resolveSizeAndState(this.itemHeight, heightSpec, i7));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        int width = right - left;
        int height = bottom - top;
        int used = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                if (ViewCompat.getLayoutDirection(this) == 1) {
                    child.layout((width - used) - child.getMeasuredWidth(), 0, width - used, height);
                } else {
                    child.layout(used, 0, child.getMeasuredWidth() + used, height);
                }
                used += child.getMeasuredWidth();
            }
        }
    }

    public void setItemHorizontalTranslationEnabled(boolean itemHorizontalTranslationEnabled) {
        this.itemHorizontalTranslationEnabled = itemHorizontalTranslationEnabled;
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.itemHorizontalTranslationEnabled;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    protected NavigationBarItemView createNavigationBarItemView(Context context) {
        return new BottomNavigationItemView(context);
    }
}
