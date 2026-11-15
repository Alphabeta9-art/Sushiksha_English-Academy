package com.cloudinary.metadata;

import com.cloudinary.utils.ObjectUtils;
import java.util.Date;
import java.util.List;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;

/* JADX INFO: loaded from: classes11.dex */
public abstract class MetadataValidation extends JSONObject {
    public static final String EQUALS = "equals";
    public static final String GREATER_THAN = "greater_than";
    public static final String LESS_THAN = "less_than";
    public static final String MAX = "max";
    public static final String MIN = "min";
    public static final String STRLEN = "strlen";
    public static final String TYPE = "type";
    public static final String VALUE = "value";

    public static class AndValidator extends MetadataValidation {
        public static final String AND = "and";

        public AndValidator(List<MetadataValidation> rules) {
            put("type", AND);
            put("rules", new JSONArray(rules.toArray()));
        }
    }

    public static class StringLength extends MetadataValidation {
        public StringLength(Integer min, Integer max) {
            put("type", MetadataValidation.STRLEN);
            put(MetadataValidation.MIN, min);
            put(MetadataValidation.MAX, max);
        }
    }

    public static abstract class ComparisonRule<T> extends MetadataValidation {
        public ComparisonRule(String type, T value) {
            this(type, value, null);
        }

        public ComparisonRule(String type, T value, Boolean equals) {
            put("type", type);
            putValue(value);
            if (equals != null) {
                put(MetadataValidation.EQUALS, equals);
            }
        }

        protected void putValue(T value) {
            put("value", value);
        }
    }

    public static class IntGreaterThan extends ComparisonRule<Integer> {
        public IntGreaterThan(Integer value) {
            super(MetadataValidation.GREATER_THAN, value);
        }

        public IntGreaterThan(Integer value, Boolean equals) {
            super(MetadataValidation.GREATER_THAN, value, equals);
        }
    }

    public static class DateGreaterThan extends ComparisonRule<Date> {
        public DateGreaterThan(Date value) {
            super(MetadataValidation.GREATER_THAN, value);
        }

        public DateGreaterThan(Date value, Boolean equals) {
            super(MetadataValidation.GREATER_THAN, value, equals);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.cloudinary.metadata.MetadataValidation.ComparisonRule
        public void putValue(Date value) {
            put("value", ObjectUtils.toISO8601DateOnly(value));
        }
    }

    public static class IntLessThan extends ComparisonRule<Integer> {
        public IntLessThan(Integer value) {
            super(MetadataValidation.LESS_THAN, value);
        }

        public IntLessThan(Integer value, Boolean equals) {
            super(MetadataValidation.LESS_THAN, value, equals);
        }
    }

    public static class DateLessThan extends ComparisonRule<Date> {
        public DateLessThan(Date value) {
            super(MetadataValidation.LESS_THAN, value);
        }

        public DateLessThan(Date value, Boolean equals) {
            super(MetadataValidation.LESS_THAN, value, equals);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.cloudinary.metadata.MetadataValidation.ComparisonRule
        public void putValue(Date value) {
            put("value", ObjectUtils.toISO8601DateOnly(value));
        }
    }
}
