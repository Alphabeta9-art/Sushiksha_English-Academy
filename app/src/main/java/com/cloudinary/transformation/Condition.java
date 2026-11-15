package com.cloudinary.transformation;

import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import com.cloudinary.Transformation;

/* JADX INFO: loaded from: classes11.dex */
public class Condition extends BaseExpression<Condition> {
    public Condition() {
    }

    public Condition(String conditionStr) {
        this();
        if (conditionStr != null) {
            this.expressions.add(normalize(conditionStr));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cloudinary.transformation.BaseExpression
    public Condition newInstance() {
        return new Condition();
    }

    protected Condition predicate(String name, String operator, Object value) {
        if (OPERATORS.containsKey(operator)) {
            operator = OPERATORS.get(operator);
        }
        this.expressions.add(String.format("%s_%s_%s", name, operator, value));
        return this;
    }

    public Transformation then() {
        getParent().ifCondition(serialize());
        return getParent();
    }

    public Condition width(String operator, Object value) {
        return predicate("w", operator, value);
    }

    public Condition height(String operator, Object value) {
        return predicate(CmcdHeadersFactory.STREAMING_FORMAT_HLS, operator, value);
    }

    public Condition aspectRatio(String operator, Object value) {
        return predicate("ar", operator, value);
    }

    public Condition duration(String operator, Object value) {
        return predicate("du", operator, value);
    }

    public Condition initialDuration(String operator, Object value) {
        return predicate("idu", operator, value);
    }

    @Deprecated
    public Condition faces(String operator, Object value) {
        return faceCount(operator, value);
    }

    public Condition faceCount(String operator, Object value) {
        return predicate("fc", operator, value);
    }

    @Deprecated
    public Condition pages(String operator, Object value) {
        return pageCount(operator, value);
    }

    public Condition pageCount(String operator, Object value) {
        return predicate("pc", operator, value);
    }
}
