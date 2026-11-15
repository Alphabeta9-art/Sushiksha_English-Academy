package androidx.constraintlayout.widget;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import androidx.media3.extractor.text.ttml.TtmlNode;

/* JADX INFO: loaded from: classes10.dex */
public class ConstraintProperties {
    public static final int BASELINE = 5;
    public static final int BOTTOM = 4;
    public static final int END = 7;
    public static final int LEFT = 1;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    public static final int START = 6;
    public static final int TOP = 3;
    public static final int UNSET = -1;
    public static final int WRAP_CONTENT = -2;
    ConstraintLayout.LayoutParams mParams;
    View mView;

    public ConstraintProperties center(int firstID, int firstSide, int firstMargin, int secondId, int secondSide, int secondMargin, float bias) {
        if (firstMargin < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (secondMargin < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (bias <= 0.0f || bias > 1.0f) {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
        }
        if (firstSide == 1 || firstSide == 2) {
            connect(1, firstID, firstSide, firstMargin);
            connect(2, secondId, secondSide, secondMargin);
            this.mParams.horizontalBias = bias;
        } else if (firstSide == 6 || firstSide == 7) {
            connect(6, firstID, firstSide, firstMargin);
            connect(7, secondId, secondSide, secondMargin);
            this.mParams.horizontalBias = bias;
        } else {
            connect(3, firstID, firstSide, firstMargin);
            connect(4, secondId, secondSide, secondMargin);
            this.mParams.verticalBias = bias;
        }
        return this;
    }

    public ConstraintProperties centerHorizontally(int leftId, int leftSide, int leftMargin, int rightId, int rightSide, int rightMargin, float bias) {
        connect(1, leftId, leftSide, leftMargin);
        connect(2, rightId, rightSide, rightMargin);
        this.mParams.horizontalBias = bias;
        return this;
    }

    public ConstraintProperties centerHorizontallyRtl(int startId, int startSide, int startMargin, int endId, int endSide, int endMargin, float bias) {
        connect(6, startId, startSide, startMargin);
        connect(7, endId, endSide, endMargin);
        this.mParams.horizontalBias = bias;
        return this;
    }

    public ConstraintProperties centerVertically(int topId, int topSide, int topMargin, int bottomId, int bottomSide, int bottomMargin, float bias) {
        connect(3, topId, topSide, topMargin);
        connect(4, bottomId, bottomSide, bottomMargin);
        this.mParams.verticalBias = bias;
        return this;
    }

    public ConstraintProperties centerHorizontally(int toView) {
        if (toView == 0) {
            center(0, 1, 0, 0, 2, 0, 0.5f);
        } else {
            center(toView, 2, 0, toView, 1, 0, 0.5f);
        }
        return this;
    }

    public ConstraintProperties centerHorizontallyRtl(int toView) {
        if (toView == 0) {
            center(0, 6, 0, 0, 7, 0, 0.5f);
        } else {
            center(toView, 7, 0, toView, 6, 0, 0.5f);
        }
        return this;
    }

    public ConstraintProperties centerVertically(int toView) {
        if (toView == 0) {
            center(0, 3, 0, 0, 4, 0, 0.5f);
        } else {
            center(toView, 4, 0, toView, 3, 0, 0.5f);
        }
        return this;
    }

    public ConstraintProperties removeConstraints(int anchor) {
        switch (anchor) {
            case 1:
                ConstraintLayout.LayoutParams layoutParams = this.mParams;
                ConstraintLayout.LayoutParams layoutParams2 = this.mParams;
                layoutParams.leftToRight = -1;
                ConstraintLayout.LayoutParams layoutParams3 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams4 = this.mParams;
                layoutParams3.leftToLeft = -1;
                ConstraintLayout.LayoutParams layoutParams5 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams6 = this.mParams;
                layoutParams5.leftMargin = -1;
                ConstraintLayout.LayoutParams layoutParams7 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams8 = this.mParams;
                layoutParams7.goneLeftMargin = Integer.MIN_VALUE;
                return this;
            case 2:
                ConstraintLayout.LayoutParams layoutParams9 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams10 = this.mParams;
                layoutParams9.rightToRight = -1;
                ConstraintLayout.LayoutParams layoutParams11 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams12 = this.mParams;
                layoutParams11.rightToLeft = -1;
                ConstraintLayout.LayoutParams layoutParams13 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams14 = this.mParams;
                layoutParams13.rightMargin = -1;
                ConstraintLayout.LayoutParams layoutParams15 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams16 = this.mParams;
                layoutParams15.goneRightMargin = Integer.MIN_VALUE;
                return this;
            case 3:
                ConstraintLayout.LayoutParams layoutParams17 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams18 = this.mParams;
                layoutParams17.topToBottom = -1;
                ConstraintLayout.LayoutParams layoutParams19 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams20 = this.mParams;
                layoutParams19.topToTop = -1;
                ConstraintLayout.LayoutParams layoutParams21 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams22 = this.mParams;
                layoutParams21.topMargin = -1;
                ConstraintLayout.LayoutParams layoutParams23 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams24 = this.mParams;
                layoutParams23.goneTopMargin = Integer.MIN_VALUE;
                return this;
            case 4:
                ConstraintLayout.LayoutParams layoutParams25 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams26 = this.mParams;
                layoutParams25.bottomToTop = -1;
                ConstraintLayout.LayoutParams layoutParams27 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams28 = this.mParams;
                layoutParams27.bottomToBottom = -1;
                ConstraintLayout.LayoutParams layoutParams29 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams30 = this.mParams;
                layoutParams29.bottomMargin = -1;
                ConstraintLayout.LayoutParams layoutParams31 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams32 = this.mParams;
                layoutParams31.goneBottomMargin = Integer.MIN_VALUE;
                return this;
            case 5:
                ConstraintLayout.LayoutParams layoutParams33 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams34 = this.mParams;
                layoutParams33.baselineToBaseline = -1;
                return this;
            case 6:
                ConstraintLayout.LayoutParams layoutParams35 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams36 = this.mParams;
                layoutParams35.startToEnd = -1;
                ConstraintLayout.LayoutParams layoutParams37 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams38 = this.mParams;
                layoutParams37.startToStart = -1;
                ConstraintLayout.LayoutParams layoutParams39 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams40 = this.mParams;
                layoutParams39.setMarginStart(-1);
                ConstraintLayout.LayoutParams layoutParams41 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams42 = this.mParams;
                layoutParams41.goneStartMargin = Integer.MIN_VALUE;
                return this;
            case 7:
                ConstraintLayout.LayoutParams layoutParams43 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams44 = this.mParams;
                layoutParams43.endToStart = -1;
                ConstraintLayout.LayoutParams layoutParams45 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams46 = this.mParams;
                layoutParams45.endToEnd = -1;
                ConstraintLayout.LayoutParams layoutParams47 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams48 = this.mParams;
                layoutParams47.setMarginEnd(-1);
                ConstraintLayout.LayoutParams layoutParams49 = this.mParams;
                ConstraintLayout.LayoutParams layoutParams50 = this.mParams;
                layoutParams49.goneEndMargin = Integer.MIN_VALUE;
                return this;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public ConstraintProperties margin(int anchor, int value) {
        switch (anchor) {
            case 1:
                this.mParams.leftMargin = value;
                return this;
            case 2:
                this.mParams.rightMargin = value;
                return this;
            case 3:
                this.mParams.topMargin = value;
                return this;
            case 4:
                this.mParams.bottomMargin = value;
                return this;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                this.mParams.setMarginStart(value);
                return this;
            case 7:
                this.mParams.setMarginEnd(value);
                return this;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public ConstraintProperties goneMargin(int anchor, int value) {
        switch (anchor) {
            case 1:
                this.mParams.goneLeftMargin = value;
                return this;
            case 2:
                this.mParams.goneRightMargin = value;
                return this;
            case 3:
                this.mParams.goneTopMargin = value;
                return this;
            case 4:
                this.mParams.goneBottomMargin = value;
                return this;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                this.mParams.goneStartMargin = value;
                return this;
            case 7:
                this.mParams.goneEndMargin = value;
                return this;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public ConstraintProperties horizontalBias(float bias) {
        this.mParams.horizontalBias = bias;
        return this;
    }

    public ConstraintProperties verticalBias(float bias) {
        this.mParams.verticalBias = bias;
        return this;
    }

    public ConstraintProperties dimensionRatio(String ratio) {
        this.mParams.dimensionRatio = ratio;
        return this;
    }

    public ConstraintProperties visibility(int visibility) {
        this.mView.setVisibility(visibility);
        return this;
    }

    public ConstraintProperties alpha(float alpha) {
        this.mView.setAlpha(alpha);
        return this;
    }

    public ConstraintProperties elevation(float elevation) {
        int i = Build.VERSION.SDK_INT;
        this.mView.setElevation(elevation);
        return this;
    }

    public ConstraintProperties rotation(float rotation) {
        this.mView.setRotation(rotation);
        return this;
    }

    public ConstraintProperties rotationX(float rotationX) {
        this.mView.setRotationX(rotationX);
        return this;
    }

    public ConstraintProperties rotationY(float rotationY) {
        this.mView.setRotationY(rotationY);
        return this;
    }

    public ConstraintProperties scaleX(float scaleX) {
        this.mView.setScaleY(scaleX);
        return this;
    }

    public ConstraintProperties scaleY(float scaleY) {
        return this;
    }

    public ConstraintProperties transformPivotX(float transformPivotX) {
        this.mView.setPivotX(transformPivotX);
        return this;
    }

    public ConstraintProperties transformPivotY(float transformPivotY) {
        this.mView.setPivotY(transformPivotY);
        return this;
    }

    public ConstraintProperties transformPivot(float transformPivotX, float transformPivotY) {
        this.mView.setPivotX(transformPivotX);
        this.mView.setPivotY(transformPivotY);
        return this;
    }

    public ConstraintProperties translationX(float translationX) {
        this.mView.setTranslationX(translationX);
        return this;
    }

    public ConstraintProperties translationY(float translationY) {
        this.mView.setTranslationY(translationY);
        return this;
    }

    public ConstraintProperties translation(float translationX, float translationY) {
        this.mView.setTranslationX(translationX);
        this.mView.setTranslationY(translationY);
        return this;
    }

    public ConstraintProperties translationZ(float translationZ) {
        int i = Build.VERSION.SDK_INT;
        this.mView.setTranslationZ(translationZ);
        return this;
    }

    public ConstraintProperties constrainHeight(int height) {
        this.mParams.height = height;
        return this;
    }

    public ConstraintProperties constrainWidth(int width) {
        this.mParams.width = width;
        return this;
    }

    public ConstraintProperties constrainMaxHeight(int height) {
        this.mParams.matchConstraintMaxHeight = height;
        return this;
    }

    public ConstraintProperties constrainMaxWidth(int width) {
        this.mParams.matchConstraintMaxWidth = width;
        return this;
    }

    public ConstraintProperties constrainMinHeight(int height) {
        this.mParams.matchConstraintMinHeight = height;
        return this;
    }

    public ConstraintProperties constrainMinWidth(int width) {
        this.mParams.matchConstraintMinWidth = width;
        return this;
    }

    public ConstraintProperties constrainDefaultHeight(int height) {
        this.mParams.matchConstraintDefaultHeight = height;
        return this;
    }

    public ConstraintProperties constrainDefaultWidth(int width) {
        this.mParams.matchConstraintDefaultWidth = width;
        return this;
    }

    public ConstraintProperties horizontalWeight(float weight) {
        this.mParams.horizontalWeight = weight;
        return this;
    }

    public ConstraintProperties verticalWeight(float weight) {
        this.mParams.verticalWeight = weight;
        return this;
    }

    public ConstraintProperties horizontalChainStyle(int chainStyle) {
        this.mParams.horizontalChainStyle = chainStyle;
        return this;
    }

    public ConstraintProperties verticalChainStyle(int chainStyle) {
        this.mParams.verticalChainStyle = chainStyle;
        return this;
    }

    public ConstraintProperties addToHorizontalChain(int leftId, int rightId) {
        connect(1, leftId, leftId == 0 ? 1 : 2, 0);
        connect(2, rightId, rightId == 0 ? 2 : 1, 0);
        if (leftId != 0) {
            View leftView = ((ViewGroup) this.mView.getParent()).findViewById(leftId);
            ConstraintProperties leftProp = new ConstraintProperties(leftView);
            leftProp.connect(2, this.mView.getId(), 1, 0);
        }
        if (rightId != 0) {
            View rightView = ((ViewGroup) this.mView.getParent()).findViewById(rightId);
            ConstraintProperties rightProp = new ConstraintProperties(rightView);
            rightProp.connect(1, this.mView.getId(), 2, 0);
        }
        return this;
    }

    public ConstraintProperties addToHorizontalChainRTL(int leftId, int rightId) {
        connect(6, leftId, leftId == 0 ? 6 : 7, 0);
        connect(7, rightId, rightId == 0 ? 7 : 6, 0);
        if (leftId != 0) {
            View leftView = ((ViewGroup) this.mView.getParent()).findViewById(leftId);
            ConstraintProperties leftProp = new ConstraintProperties(leftView);
            leftProp.connect(7, this.mView.getId(), 6, 0);
        }
        if (rightId != 0) {
            View rightView = ((ViewGroup) this.mView.getParent()).findViewById(rightId);
            ConstraintProperties rightProp = new ConstraintProperties(rightView);
            rightProp.connect(6, this.mView.getId(), 7, 0);
        }
        return this;
    }

    public ConstraintProperties addToVerticalChain(int topId, int bottomId) {
        connect(3, topId, topId == 0 ? 3 : 4, 0);
        connect(4, bottomId, bottomId == 0 ? 4 : 3, 0);
        if (topId != 0) {
            View topView = ((ViewGroup) this.mView.getParent()).findViewById(topId);
            ConstraintProperties topProp = new ConstraintProperties(topView);
            topProp.connect(4, this.mView.getId(), 3, 0);
        }
        if (bottomId != 0) {
            View bottomView = ((ViewGroup) this.mView.getParent()).findViewById(bottomId);
            ConstraintProperties bottomProp = new ConstraintProperties(bottomView);
            bottomProp.connect(3, this.mView.getId(), 4, 0);
        }
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x003f  */
    /* JADX WARN: Code duplicated, block: B:12:0x0046  */
    /* JADX WARN: Code duplicated, block: B:14:0x004a  */
    /* JADX WARN: Code duplicated, block: B:17:0x004f  */
    /* JADX WARN: Code duplicated, block: B:19:0x0057  */
    /* JADX WARN: Code duplicated, block: B:20:0x005f  */
    /* JADX WARN: Code duplicated, block: B:22:0x0067  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code duplicated, block: B:9:0x003b  */
    public ConstraintProperties removeFromVerticalChain() {
        ConstraintProperties topProp;
        ConstraintProperties bottomProp;
        int i;
        int i2;
        int topId = this.mParams.topToBottom;
        int bottomId = this.mParams.bottomToTop;
        ConstraintLayout.LayoutParams layoutParams = this.mParams;
        if (topId == -1) {
            ConstraintLayout.LayoutParams layoutParams2 = this.mParams;
            if (bottomId != -1) {
                View topView = ((ViewGroup) this.mView.getParent()).findViewById(topId);
                topProp = new ConstraintProperties(topView);
                View bottomView = ((ViewGroup) this.mView.getParent()).findViewById(bottomId);
                bottomProp = new ConstraintProperties(bottomView);
                ConstraintLayout.LayoutParams layoutParams3 = this.mParams;
                if (topId != -1) {
                    ConstraintLayout.LayoutParams layoutParams4 = this.mParams;
                    if (bottomId != -1) {
                        topProp.connect(4, bottomId, 3, 0);
                        bottomProp.connect(3, topId, 4, 0);
                    } else {
                        ConstraintLayout.LayoutParams layoutParams5 = this.mParams;
                        if (topId == -1) {
                            ConstraintLayout.LayoutParams layoutParams6 = this.mParams;
                            if (bottomId != -1) {
                                i = this.mParams.bottomToBottom;
                                ConstraintLayout.LayoutParams layoutParams7 = this.mParams;
                                if (i != -1) {
                                    topProp.connect(4, this.mParams.bottomToBottom, 4, 0);
                                } else {
                                    i2 = this.mParams.topToTop;
                                    ConstraintLayout.LayoutParams layoutParams8 = this.mParams;
                                    if (i2 != -1) {
                                        bottomProp.connect(3, this.mParams.topToTop, 3, 0);
                                    }
                                }
                            }
                        } else {
                            i = this.mParams.bottomToBottom;
                            ConstraintLayout.LayoutParams layoutParams9 = this.mParams;
                            if (i != -1) {
                                topProp.connect(4, this.mParams.bottomToBottom, 4, 0);
                            } else {
                                i2 = this.mParams.topToTop;
                                ConstraintLayout.LayoutParams layoutParams10 = this.mParams;
                                if (i2 != -1) {
                                    bottomProp.connect(3, this.mParams.topToTop, 3, 0);
                                }
                            }
                        }
                    }
                } else {
                    ConstraintLayout.LayoutParams layoutParams11 = this.mParams;
                    if (topId == -1) {
                        ConstraintLayout.LayoutParams layoutParams12 = this.mParams;
                        if (bottomId != -1) {
                            i = this.mParams.bottomToBottom;
                            ConstraintLayout.LayoutParams layoutParams13 = this.mParams;
                            if (i != -1) {
                                topProp.connect(4, this.mParams.bottomToBottom, 4, 0);
                            } else {
                                i2 = this.mParams.topToTop;
                                ConstraintLayout.LayoutParams layoutParams14 = this.mParams;
                                if (i2 != -1) {
                                    bottomProp.connect(3, this.mParams.topToTop, 3, 0);
                                }
                            }
                        }
                    } else {
                        i = this.mParams.bottomToBottom;
                        ConstraintLayout.LayoutParams layoutParams15 = this.mParams;
                        if (i != -1) {
                            topProp.connect(4, this.mParams.bottomToBottom, 4, 0);
                        } else {
                            i2 = this.mParams.topToTop;
                            ConstraintLayout.LayoutParams layoutParams16 = this.mParams;
                            if (i2 != -1) {
                                bottomProp.connect(3, this.mParams.topToTop, 3, 0);
                            }
                        }
                    }
                }
            }
        } else {
            View topView2 = ((ViewGroup) this.mView.getParent()).findViewById(topId);
            topProp = new ConstraintProperties(topView2);
            View bottomView2 = ((ViewGroup) this.mView.getParent()).findViewById(bottomId);
            bottomProp = new ConstraintProperties(bottomView2);
            ConstraintLayout.LayoutParams layoutParams17 = this.mParams;
            if (topId != -1) {
                ConstraintLayout.LayoutParams layoutParams18 = this.mParams;
                if (bottomId != -1) {
                    topProp.connect(4, bottomId, 3, 0);
                    bottomProp.connect(3, topId, 4, 0);
                } else {
                    ConstraintLayout.LayoutParams layoutParams19 = this.mParams;
                    if (topId == -1) {
                        ConstraintLayout.LayoutParams layoutParams110 = this.mParams;
                        if (bottomId != -1) {
                            i = this.mParams.bottomToBottom;
                            ConstraintLayout.LayoutParams layoutParams111 = this.mParams;
                            if (i != -1) {
                                topProp.connect(4, this.mParams.bottomToBottom, 4, 0);
                            } else {
                                i2 = this.mParams.topToTop;
                                ConstraintLayout.LayoutParams layoutParams112 = this.mParams;
                                if (i2 != -1) {
                                    bottomProp.connect(3, this.mParams.topToTop, 3, 0);
                                }
                            }
                        }
                    } else {
                        i = this.mParams.bottomToBottom;
                        ConstraintLayout.LayoutParams layoutParams113 = this.mParams;
                        if (i != -1) {
                            topProp.connect(4, this.mParams.bottomToBottom, 4, 0);
                        } else {
                            i2 = this.mParams.topToTop;
                            ConstraintLayout.LayoutParams layoutParams114 = this.mParams;
                            if (i2 != -1) {
                                bottomProp.connect(3, this.mParams.topToTop, 3, 0);
                            }
                        }
                    }
                }
            } else {
                ConstraintLayout.LayoutParams layoutParams115 = this.mParams;
                if (topId == -1) {
                    ConstraintLayout.LayoutParams layoutParams116 = this.mParams;
                    if (bottomId != -1) {
                        i = this.mParams.bottomToBottom;
                        ConstraintLayout.LayoutParams layoutParams117 = this.mParams;
                        if (i != -1) {
                            topProp.connect(4, this.mParams.bottomToBottom, 4, 0);
                        } else {
                            i2 = this.mParams.topToTop;
                            ConstraintLayout.LayoutParams layoutParams118 = this.mParams;
                            if (i2 != -1) {
                                bottomProp.connect(3, this.mParams.topToTop, 3, 0);
                            }
                        }
                    }
                } else {
                    i = this.mParams.bottomToBottom;
                    ConstraintLayout.LayoutParams layoutParams119 = this.mParams;
                    if (i != -1) {
                        topProp.connect(4, this.mParams.bottomToBottom, 4, 0);
                    } else {
                        i2 = this.mParams.topToTop;
                        ConstraintLayout.LayoutParams layoutParams1110 = this.mParams;
                        if (i2 != -1) {
                            bottomProp.connect(3, this.mParams.topToTop, 3, 0);
                        }
                    }
                }
            }
        }
        removeConstraints(3);
        removeConstraints(4);
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025  */
    /* JADX WARN: Code duplicated, block: B:13:0x004b  */
    /* JADX WARN: Code duplicated, block: B:15:0x004f  */
    /* JADX WARN: Code duplicated, block: B:16:0x0056  */
    /* JADX WARN: Code duplicated, block: B:18:0x005a  */
    /* JADX WARN: Code duplicated, block: B:21:0x005f  */
    /* JADX WARN: Code duplicated, block: B:23:0x0067  */
    /* JADX WARN: Code duplicated, block: B:24:0x006f  */
    /* JADX WARN: Code duplicated, block: B:26:0x0077  */
    /* JADX WARN: Code duplicated, block: B:28:0x0085  */
    /* JADX WARN: Code duplicated, block: B:30:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:32:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:33:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:35:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:38:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:40:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:41:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:43:0x00d9  */
    public ConstraintProperties removeFromHorizontalChain() {
        ConstraintProperties leftProp;
        ConstraintProperties rightProp;
        int i;
        int i2;
        ConstraintProperties startProp;
        ConstraintProperties endProp;
        int i3;
        int i4;
        int leftId = this.mParams.leftToRight;
        int rightId = this.mParams.rightToLeft;
        ConstraintLayout.LayoutParams layoutParams = this.mParams;
        if (leftId == -1) {
            ConstraintLayout.LayoutParams layoutParams2 = this.mParams;
            if (rightId != -1) {
                View leftView = ((ViewGroup) this.mView.getParent()).findViewById(leftId);
                leftProp = new ConstraintProperties(leftView);
                View rightView = ((ViewGroup) this.mView.getParent()).findViewById(rightId);
                rightProp = new ConstraintProperties(rightView);
                ConstraintLayout.LayoutParams layoutParams3 = this.mParams;
                if (leftId != -1) {
                    ConstraintLayout.LayoutParams layoutParams4 = this.mParams;
                    if (rightId != -1) {
                        leftProp.connect(2, rightId, 1, 0);
                        rightProp.connect(1, leftId, 2, 0);
                    } else {
                        ConstraintLayout.LayoutParams layoutParams5 = this.mParams;
                        if (leftId == -1) {
                            ConstraintLayout.LayoutParams layoutParams6 = this.mParams;
                            if (rightId != -1) {
                                i = this.mParams.rightToRight;
                                ConstraintLayout.LayoutParams layoutParams7 = this.mParams;
                                if (i != -1) {
                                    leftProp.connect(2, this.mParams.rightToRight, 2, 0);
                                } else {
                                    i2 = this.mParams.leftToLeft;
                                    ConstraintLayout.LayoutParams layoutParams8 = this.mParams;
                                    if (i2 != -1) {
                                        rightProp.connect(1, this.mParams.leftToLeft, 1, 0);
                                    }
                                }
                            }
                        } else {
                            i = this.mParams.rightToRight;
                            ConstraintLayout.LayoutParams layoutParams9 = this.mParams;
                            if (i != -1) {
                                leftProp.connect(2, this.mParams.rightToRight, 2, 0);
                            } else {
                                i2 = this.mParams.leftToLeft;
                                ConstraintLayout.LayoutParams layoutParams10 = this.mParams;
                                if (i2 != -1) {
                                    rightProp.connect(1, this.mParams.leftToLeft, 1, 0);
                                }
                            }
                        }
                    }
                } else {
                    ConstraintLayout.LayoutParams layoutParams11 = this.mParams;
                    if (leftId == -1) {
                        ConstraintLayout.LayoutParams layoutParams12 = this.mParams;
                        if (rightId != -1) {
                            i = this.mParams.rightToRight;
                            ConstraintLayout.LayoutParams layoutParams13 = this.mParams;
                            if (i != -1) {
                                leftProp.connect(2, this.mParams.rightToRight, 2, 0);
                            } else {
                                i2 = this.mParams.leftToLeft;
                                ConstraintLayout.LayoutParams layoutParams14 = this.mParams;
                                if (i2 != -1) {
                                    rightProp.connect(1, this.mParams.leftToLeft, 1, 0);
                                }
                            }
                        }
                    } else {
                        i = this.mParams.rightToRight;
                        ConstraintLayout.LayoutParams layoutParams15 = this.mParams;
                        if (i != -1) {
                            leftProp.connect(2, this.mParams.rightToRight, 2, 0);
                        } else {
                            i2 = this.mParams.leftToLeft;
                            ConstraintLayout.LayoutParams layoutParams16 = this.mParams;
                            if (i2 != -1) {
                                rightProp.connect(1, this.mParams.leftToLeft, 1, 0);
                            }
                        }
                    }
                }
                removeConstraints(1);
                removeConstraints(2);
            } else {
                int startId = this.mParams.startToEnd;
                int endId = this.mParams.endToStart;
                ConstraintLayout.LayoutParams layoutParams17 = this.mParams;
                if (startId == -1) {
                    ConstraintLayout.LayoutParams layoutParams18 = this.mParams;
                    if (endId != -1) {
                        View startView = ((ViewGroup) this.mView.getParent()).findViewById(startId);
                        startProp = new ConstraintProperties(startView);
                        View endView = ((ViewGroup) this.mView.getParent()).findViewById(endId);
                        endProp = new ConstraintProperties(endView);
                        ConstraintLayout.LayoutParams layoutParams19 = this.mParams;
                        if (startId != -1) {
                            ConstraintLayout.LayoutParams layoutParams20 = this.mParams;
                            if (endId != -1) {
                                startProp.connect(7, endId, 6, 0);
                                endProp.connect(6, leftId, 7, 0);
                            } else {
                                ConstraintLayout.LayoutParams layoutParams21 = this.mParams;
                                if (leftId == -1) {
                                    ConstraintLayout.LayoutParams layoutParams22 = this.mParams;
                                    if (endId != -1) {
                                        i3 = this.mParams.rightToRight;
                                        ConstraintLayout.LayoutParams layoutParams23 = this.mParams;
                                        if (i3 != -1) {
                                            startProp.connect(7, this.mParams.rightToRight, 7, 0);
                                        } else {
                                            i4 = this.mParams.leftToLeft;
                                            ConstraintLayout.LayoutParams layoutParams24 = this.mParams;
                                            if (i4 != -1) {
                                                endProp.connect(6, this.mParams.leftToLeft, 6, 0);
                                            }
                                        }
                                    }
                                } else {
                                    i3 = this.mParams.rightToRight;
                                    ConstraintLayout.LayoutParams layoutParams25 = this.mParams;
                                    if (i3 != -1) {
                                        startProp.connect(7, this.mParams.rightToRight, 7, 0);
                                    } else {
                                        i4 = this.mParams.leftToLeft;
                                        ConstraintLayout.LayoutParams layoutParams26 = this.mParams;
                                        if (i4 != -1) {
                                            endProp.connect(6, this.mParams.leftToLeft, 6, 0);
                                        }
                                    }
                                }
                            }
                        } else {
                            ConstraintLayout.LayoutParams layoutParams27 = this.mParams;
                            if (leftId == -1) {
                                ConstraintLayout.LayoutParams layoutParams28 = this.mParams;
                                if (endId != -1) {
                                    i3 = this.mParams.rightToRight;
                                    ConstraintLayout.LayoutParams layoutParams29 = this.mParams;
                                    if (i3 != -1) {
                                        startProp.connect(7, this.mParams.rightToRight, 7, 0);
                                    } else {
                                        i4 = this.mParams.leftToLeft;
                                        ConstraintLayout.LayoutParams layoutParams210 = this.mParams;
                                        if (i4 != -1) {
                                            endProp.connect(6, this.mParams.leftToLeft, 6, 0);
                                        }
                                    }
                                }
                            } else {
                                i3 = this.mParams.rightToRight;
                                ConstraintLayout.LayoutParams layoutParams211 = this.mParams;
                                if (i3 != -1) {
                                    startProp.connect(7, this.mParams.rightToRight, 7, 0);
                                } else {
                                    i4 = this.mParams.leftToLeft;
                                    ConstraintLayout.LayoutParams layoutParams212 = this.mParams;
                                    if (i4 != -1) {
                                        endProp.connect(6, this.mParams.leftToLeft, 6, 0);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    View startView2 = ((ViewGroup) this.mView.getParent()).findViewById(startId);
                    startProp = new ConstraintProperties(startView2);
                    View endView2 = ((ViewGroup) this.mView.getParent()).findViewById(endId);
                    endProp = new ConstraintProperties(endView2);
                    ConstraintLayout.LayoutParams layoutParams110 = this.mParams;
                    if (startId != -1) {
                        ConstraintLayout.LayoutParams layoutParams213 = this.mParams;
                        if (endId != -1) {
                            startProp.connect(7, endId, 6, 0);
                            endProp.connect(6, leftId, 7, 0);
                        } else {
                            ConstraintLayout.LayoutParams layoutParams214 = this.mParams;
                            if (leftId == -1) {
                                ConstraintLayout.LayoutParams layoutParams215 = this.mParams;
                                if (endId != -1) {
                                    i3 = this.mParams.rightToRight;
                                    ConstraintLayout.LayoutParams layoutParams216 = this.mParams;
                                    if (i3 != -1) {
                                        startProp.connect(7, this.mParams.rightToRight, 7, 0);
                                    } else {
                                        i4 = this.mParams.leftToLeft;
                                        ConstraintLayout.LayoutParams layoutParams217 = this.mParams;
                                        if (i4 != -1) {
                                            endProp.connect(6, this.mParams.leftToLeft, 6, 0);
                                        }
                                    }
                                }
                            } else {
                                i3 = this.mParams.rightToRight;
                                ConstraintLayout.LayoutParams layoutParams218 = this.mParams;
                                if (i3 != -1) {
                                    startProp.connect(7, this.mParams.rightToRight, 7, 0);
                                } else {
                                    i4 = this.mParams.leftToLeft;
                                    ConstraintLayout.LayoutParams layoutParams219 = this.mParams;
                                    if (i4 != -1) {
                                        endProp.connect(6, this.mParams.leftToLeft, 6, 0);
                                    }
                                }
                            }
                        }
                    } else {
                        ConstraintLayout.LayoutParams layoutParams2110 = this.mParams;
                        if (leftId == -1) {
                            ConstraintLayout.LayoutParams layoutParams2111 = this.mParams;
                            if (endId != -1) {
                                i3 = this.mParams.rightToRight;
                                ConstraintLayout.LayoutParams layoutParams2112 = this.mParams;
                                if (i3 != -1) {
                                    startProp.connect(7, this.mParams.rightToRight, 7, 0);
                                } else {
                                    i4 = this.mParams.leftToLeft;
                                    ConstraintLayout.LayoutParams layoutParams2113 = this.mParams;
                                    if (i4 != -1) {
                                        endProp.connect(6, this.mParams.leftToLeft, 6, 0);
                                    }
                                }
                            }
                        } else {
                            i3 = this.mParams.rightToRight;
                            ConstraintLayout.LayoutParams layoutParams2114 = this.mParams;
                            if (i3 != -1) {
                                startProp.connect(7, this.mParams.rightToRight, 7, 0);
                            } else {
                                i4 = this.mParams.leftToLeft;
                                ConstraintLayout.LayoutParams layoutParams2115 = this.mParams;
                                if (i4 != -1) {
                                    endProp.connect(6, this.mParams.leftToLeft, 6, 0);
                                }
                            }
                        }
                    }
                }
                removeConstraints(6);
                removeConstraints(7);
            }
        } else {
            View leftView2 = ((ViewGroup) this.mView.getParent()).findViewById(leftId);
            leftProp = new ConstraintProperties(leftView2);
            View rightView2 = ((ViewGroup) this.mView.getParent()).findViewById(rightId);
            rightProp = new ConstraintProperties(rightView2);
            ConstraintLayout.LayoutParams layoutParams30 = this.mParams;
            if (leftId != -1) {
                ConstraintLayout.LayoutParams layoutParams31 = this.mParams;
                if (rightId != -1) {
                    leftProp.connect(2, rightId, 1, 0);
                    rightProp.connect(1, leftId, 2, 0);
                } else {
                    ConstraintLayout.LayoutParams layoutParams111 = this.mParams;
                    if (leftId == -1) {
                        ConstraintLayout.LayoutParams layoutParams112 = this.mParams;
                        if (rightId != -1) {
                            i = this.mParams.rightToRight;
                            ConstraintLayout.LayoutParams layoutParams113 = this.mParams;
                            if (i != -1) {
                                leftProp.connect(2, this.mParams.rightToRight, 2, 0);
                            } else {
                                i2 = this.mParams.leftToLeft;
                                ConstraintLayout.LayoutParams layoutParams114 = this.mParams;
                                if (i2 != -1) {
                                    rightProp.connect(1, this.mParams.leftToLeft, 1, 0);
                                }
                            }
                        }
                    } else {
                        i = this.mParams.rightToRight;
                        ConstraintLayout.LayoutParams layoutParams115 = this.mParams;
                        if (i != -1) {
                            leftProp.connect(2, this.mParams.rightToRight, 2, 0);
                        } else {
                            i2 = this.mParams.leftToLeft;
                            ConstraintLayout.LayoutParams layoutParams116 = this.mParams;
                            if (i2 != -1) {
                                rightProp.connect(1, this.mParams.leftToLeft, 1, 0);
                            }
                        }
                    }
                }
            } else {
                ConstraintLayout.LayoutParams layoutParams117 = this.mParams;
                if (leftId == -1) {
                    ConstraintLayout.LayoutParams layoutParams118 = this.mParams;
                    if (rightId != -1) {
                        i = this.mParams.rightToRight;
                        ConstraintLayout.LayoutParams layoutParams119 = this.mParams;
                        if (i != -1) {
                            leftProp.connect(2, this.mParams.rightToRight, 2, 0);
                        } else {
                            i2 = this.mParams.leftToLeft;
                            ConstraintLayout.LayoutParams layoutParams1110 = this.mParams;
                            if (i2 != -1) {
                                rightProp.connect(1, this.mParams.leftToLeft, 1, 0);
                            }
                        }
                    }
                } else {
                    i = this.mParams.rightToRight;
                    ConstraintLayout.LayoutParams layoutParams1111 = this.mParams;
                    if (i != -1) {
                        leftProp.connect(2, this.mParams.rightToRight, 2, 0);
                    } else {
                        i2 = this.mParams.leftToLeft;
                        ConstraintLayout.LayoutParams layoutParams1112 = this.mParams;
                        if (i2 != -1) {
                            rightProp.connect(1, this.mParams.leftToLeft, 1, 0);
                        }
                    }
                }
            }
            removeConstraints(1);
            removeConstraints(2);
        }
        return this;
    }

    public ConstraintProperties connect(int startSide, int endID, int endSide, int margin) {
        switch (startSide) {
            case 1:
                if (endSide == 1) {
                    this.mParams.leftToLeft = endID;
                    ConstraintLayout.LayoutParams layoutParams = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams2 = this.mParams;
                    layoutParams.leftToRight = -1;
                } else if (endSide == 2) {
                    this.mParams.leftToRight = endID;
                    ConstraintLayout.LayoutParams layoutParams3 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams4 = this.mParams;
                    layoutParams3.leftToLeft = -1;
                } else {
                    throw new IllegalArgumentException("Left to " + sideToString(endSide) + " undefined");
                }
                this.mParams.leftMargin = margin;
                return this;
            case 2:
                if (endSide == 1) {
                    this.mParams.rightToLeft = endID;
                    ConstraintLayout.LayoutParams layoutParams5 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams6 = this.mParams;
                    layoutParams5.rightToRight = -1;
                } else if (endSide == 2) {
                    this.mParams.rightToRight = endID;
                    ConstraintLayout.LayoutParams layoutParams7 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams8 = this.mParams;
                    layoutParams7.rightToLeft = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
                this.mParams.rightMargin = margin;
                return this;
            case 3:
                if (endSide == 3) {
                    this.mParams.topToTop = endID;
                    ConstraintLayout.LayoutParams layoutParams9 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams10 = this.mParams;
                    layoutParams9.topToBottom = -1;
                    ConstraintLayout.LayoutParams layoutParams11 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams12 = this.mParams;
                    layoutParams11.baselineToBaseline = -1;
                    ConstraintLayout.LayoutParams layoutParams13 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams14 = this.mParams;
                    layoutParams13.baselineToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams15 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams16 = this.mParams;
                    layoutParams15.baselineToBottom = -1;
                } else if (endSide == 4) {
                    this.mParams.topToBottom = endID;
                    ConstraintLayout.LayoutParams layoutParams17 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams18 = this.mParams;
                    layoutParams17.topToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams19 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams20 = this.mParams;
                    layoutParams19.baselineToBaseline = -1;
                    ConstraintLayout.LayoutParams layoutParams21 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams22 = this.mParams;
                    layoutParams21.baselineToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams23 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams24 = this.mParams;
                    layoutParams23.baselineToBottom = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
                this.mParams.topMargin = margin;
                return this;
            case 4:
                if (endSide == 4) {
                    this.mParams.bottomToBottom = endID;
                    ConstraintLayout.LayoutParams layoutParams25 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams26 = this.mParams;
                    layoutParams25.bottomToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams27 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams28 = this.mParams;
                    layoutParams27.baselineToBaseline = -1;
                    ConstraintLayout.LayoutParams layoutParams29 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams30 = this.mParams;
                    layoutParams29.baselineToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams31 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams32 = this.mParams;
                    layoutParams31.baselineToBottom = -1;
                } else if (endSide == 3) {
                    this.mParams.bottomToTop = endID;
                    ConstraintLayout.LayoutParams layoutParams33 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams34 = this.mParams;
                    layoutParams33.bottomToBottom = -1;
                    ConstraintLayout.LayoutParams layoutParams35 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams36 = this.mParams;
                    layoutParams35.baselineToBaseline = -1;
                    ConstraintLayout.LayoutParams layoutParams37 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams38 = this.mParams;
                    layoutParams37.baselineToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams39 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams40 = this.mParams;
                    layoutParams39.baselineToBottom = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
                this.mParams.bottomMargin = margin;
                return this;
            case 5:
                if (endSide == 5) {
                    this.mParams.baselineToBaseline = endID;
                    ConstraintLayout.LayoutParams layoutParams41 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams42 = this.mParams;
                    layoutParams41.bottomToBottom = -1;
                    ConstraintLayout.LayoutParams layoutParams43 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams44 = this.mParams;
                    layoutParams43.bottomToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams45 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams46 = this.mParams;
                    layoutParams45.topToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams47 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams48 = this.mParams;
                    layoutParams47.topToBottom = -1;
                }
                if (endSide == 3) {
                    this.mParams.baselineToTop = endID;
                    ConstraintLayout.LayoutParams layoutParams49 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams50 = this.mParams;
                    layoutParams49.bottomToBottom = -1;
                    ConstraintLayout.LayoutParams layoutParams51 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams52 = this.mParams;
                    layoutParams51.bottomToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams53 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams54 = this.mParams;
                    layoutParams53.topToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams55 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams56 = this.mParams;
                    layoutParams55.topToBottom = -1;
                } else if (endSide == 4) {
                    this.mParams.baselineToBottom = endID;
                    ConstraintLayout.LayoutParams layoutParams57 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams58 = this.mParams;
                    layoutParams57.bottomToBottom = -1;
                    ConstraintLayout.LayoutParams layoutParams59 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams60 = this.mParams;
                    layoutParams59.bottomToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams61 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams62 = this.mParams;
                    layoutParams61.topToTop = -1;
                    ConstraintLayout.LayoutParams layoutParams63 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams64 = this.mParams;
                    layoutParams63.topToBottom = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
                this.mParams.baselineMargin = margin;
                return this;
            case 6:
                if (endSide == 6) {
                    this.mParams.startToStart = endID;
                    ConstraintLayout.LayoutParams layoutParams65 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams66 = this.mParams;
                    layoutParams65.startToEnd = -1;
                } else if (endSide == 7) {
                    this.mParams.startToEnd = endID;
                    ConstraintLayout.LayoutParams layoutParams67 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams68 = this.mParams;
                    layoutParams67.startToStart = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
                int i = Build.VERSION.SDK_INT;
                this.mParams.setMarginStart(margin);
                return this;
            case 7:
                if (endSide == 7) {
                    this.mParams.endToEnd = endID;
                    ConstraintLayout.LayoutParams layoutParams69 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams70 = this.mParams;
                    layoutParams69.endToStart = -1;
                } else if (endSide == 6) {
                    this.mParams.endToStart = endID;
                    ConstraintLayout.LayoutParams layoutParams71 = this.mParams;
                    ConstraintLayout.LayoutParams layoutParams72 = this.mParams;
                    layoutParams71.endToEnd = -1;
                } else {
                    throw new IllegalArgumentException("right to " + sideToString(endSide) + " undefined");
                }
                int i2 = Build.VERSION.SDK_INT;
                this.mParams.setMarginEnd(margin);
                return this;
            default:
                throw new IllegalArgumentException(sideToString(startSide) + " to " + sideToString(endSide) + " unknown");
        }
    }

    private String sideToString(int side) {
        switch (side) {
            case 1:
                return TtmlNode.LEFT;
            case 2:
                return TtmlNode.RIGHT;
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return TtmlNode.START;
            case 7:
                return TtmlNode.END;
            default:
                return "undefined";
        }
    }

    public ConstraintProperties(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof ConstraintLayout.LayoutParams) {
            this.mParams = (ConstraintLayout.LayoutParams) params;
            this.mView = view;
            return;
        }
        throw new RuntimeException("Only children of ConstraintLayout.LayoutParams supported");
    }

    public void apply() {
    }
}
