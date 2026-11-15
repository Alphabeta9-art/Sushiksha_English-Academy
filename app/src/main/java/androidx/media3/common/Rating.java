package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes11.dex */
public abstract class Rating implements Bundleable {
    static final int RATING_TYPE_HEART = 0;
    static final int RATING_TYPE_PERCENTAGE = 1;
    static final int RATING_TYPE_STAR = 2;
    static final int RATING_TYPE_THUMB = 3;
    static final int RATING_TYPE_UNSET = -1;
    static final float RATING_UNSET = -1.0f;
    static final String FIELD_RATING_TYPE = Util.intToStringMaxRadix(0);
    public static final Bundleable.Creator<Rating> CREATOR = new Bundleable.Creator() { // from class: androidx.media3.common.Rating$$ExternalSyntheticLambda0
        @Override // androidx.media3.common.Bundleable.Creator
        public final Bundleable fromBundle(Bundle bundle) {
            return Rating.fromBundle(bundle);
        }
    };

    public abstract boolean isRated();

    Rating() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Rating fromBundle(Bundle bundle) {
        int ratingType = bundle.getInt(FIELD_RATING_TYPE, -1);
        switch (ratingType) {
            case 0:
                return (Rating) HeartRating.CREATOR.fromBundle(bundle);
            case 1:
                return (Rating) PercentageRating.CREATOR.fromBundle(bundle);
            case 2:
                return (Rating) StarRating.CREATOR.fromBundle(bundle);
            case 3:
                return (Rating) ThumbRating.CREATOR.fromBundle(bundle);
            default:
                throw new IllegalArgumentException("Unknown RatingType: " + ratingType);
        }
    }
}
