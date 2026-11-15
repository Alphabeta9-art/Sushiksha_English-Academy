package com.cloudinary.transformation;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.Transformation;
import com.cloudinary.metadata.MetadataValidation;
import com.cloudinary.transformation.BaseExpression;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes11.dex */
public abstract class BaseExpression<T extends BaseExpression> {
    protected List<String> expressions;
    protected Transformation parent = null;
    public static final Map<String, String> OPERATORS = ObjectUtils.asMap("=", "eq", "!=", "ne", "<", "lt", ">", "gt", "<=", "lte", ">=", "gte", "&&", MetadataValidation.AndValidator.AND, "||", "or", "*", "mul", DomExceptionUtils.SEPARATOR, TtmlNode.TAG_DIV, "+", "add", "-", "sub", "^", "pow");
    public static final Map<String, String> PREDEFINED_VARS = ObjectUtils.asMap("width", "w", "height", CmcdHeadersFactory.STREAMING_FORMAT_HLS, "initialWidth", "iw", "initialHeight", "ih", "aspect_ratio", "ar", "initial_aspect_ratio", "iar", "aspectRatio", "ar", "initialAspectRatio", "iar", "page_count", "pc", "pageCount", "pc", "face_count", "fc", "faceCount", "fc", "current_page", "cp", "currentPage", "cp", "tags", "tags", "pageX", "px", "pageY", "py", TypedValues.TransitionType.S_DURATION, "du", "initial_duration", "idu", "initialDuration", "idu");
    private static final Pattern PATTERN = getPattern();
    private static final Pattern USER_VARIABLE_PATTERN = Pattern.compile("\\$_*[^_]+");

    protected abstract T newInstance();

    protected BaseExpression() {
        this.expressions = null;
        this.expressions = new ArrayList();
    }

    public static String normalize(Object expression) {
        if (expression == null) {
            return null;
        }
        if (expression instanceof Number) {
            return String.valueOf(expression);
        }
        String conditionStr = StringUtils.mergeToSingleUnderscore(String.valueOf(expression));
        Matcher m = USER_VARIABLE_PATTERN.matcher(conditionStr);
        StringBuilder builder = new StringBuilder();
        int lastMatchEnd = 0;
        while (m.find()) {
            String beforeMatch = conditionStr.substring(lastMatchEnd, m.start());
            builder.append(normalizeBuiltins(beforeMatch));
            builder.append(m.group());
            lastMatchEnd = m.end();
        }
        builder.append(normalizeBuiltins(conditionStr.substring(lastMatchEnd)));
        return builder.toString();
    }

    private static String normalizeBuiltins(String input) {
        String replacement;
        Matcher matcher = PATTERN.matcher(input);
        StringBuffer result = new StringBuffer(input.length());
        while (matcher.find()) {
            if (OPERATORS.containsKey(matcher.group())) {
                replacement = OPERATORS.get(matcher.group());
            } else if (PREDEFINED_VARS.containsKey(matcher.group())) {
                replacement = PREDEFINED_VARS.get(matcher.group());
            } else {
                replacement = matcher.group();
            }
            matcher.appendReplacement(result, replacement);
        }
        matcher.appendTail(result);
        return result.toString();
    }

    private static Pattern getPattern() {
        ArrayList<String> operators = new ArrayList<>(OPERATORS.keySet());
        Collections.sort(operators, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder("((");
        for (String op : operators) {
            sb.append(Pattern.quote(op)).append("|");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")(?=[ _])|(?<![\\$:])(").append(StringUtils.join(PREDEFINED_VARS.keySet(), "|")).append("))");
        String pattern = sb.toString();
        return Pattern.compile(pattern);
    }

    public Transformation getParent() {
        return this.parent;
    }

    public T setParent(Transformation parent) {
        this.parent = parent;
        return this;
    }

    public String serialize() {
        return normalize(StringUtils.join(this.expressions, "_"));
    }

    public String toString() {
        return serialize();
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public T m8614clone() {
        T t = (T) newInstance();
        t.expressions.addAll(this.expressions);
        t.parent = this.parent;
        return t;
    }

    public T multiple(Object value) {
        this.expressions.add("mul");
        this.expressions.add(value.toString());
        return this;
    }

    public T gt(Object obj) {
        return (T) gt().value(obj);
    }

    public T gt() {
        this.expressions.add("gt");
        return this;
    }

    public T and(Object obj) {
        return (T) and().value(obj);
    }

    public T and() {
        this.expressions.add(MetadataValidation.AndValidator.AND);
        return this;
    }

    public T or(Object obj) {
        return (T) or().value(obj);
    }

    public T or() {
        this.expressions.add("or");
        return this;
    }

    public T eq(Object obj) {
        return (T) eq().value(obj);
    }

    public T eq() {
        this.expressions.add("eq");
        return this;
    }

    public T ne(Object obj) {
        return (T) ne().value(obj);
    }

    public T ne() {
        this.expressions.add("ne");
        return this;
    }

    public T lt(Object obj) {
        return (T) lt().value(obj);
    }

    public T lt() {
        this.expressions.add("lt");
        return this;
    }

    public T lte(Object obj) {
        return (T) lte().value(obj);
    }

    public T lte() {
        this.expressions.add("lte");
        return this;
    }

    public T gte(Object obj) {
        return (T) gte().value(obj);
    }

    public T gte() {
        this.expressions.add("gte");
        return this;
    }

    public T div(Object obj) {
        return (T) div().value(obj);
    }

    public T div() {
        this.expressions.add(TtmlNode.TAG_DIV);
        return this;
    }

    public T add(Object obj) {
        return (T) add().value(obj);
    }

    public T add() {
        this.expressions.add("add");
        return this;
    }

    public T sub(Object obj) {
        return (T) sub().value(obj);
    }

    public T sub() {
        this.expressions.add("sub");
        return this;
    }

    public T pow(Object obj) {
        return (T) pow().value(obj);
    }

    public T pow() {
        this.expressions.add("pow");
        return this;
    }

    public T value(Object value) {
        this.expressions.add(String.valueOf(value));
        return this;
    }
}
