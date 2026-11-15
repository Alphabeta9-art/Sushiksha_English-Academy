package com.cloudinary;

import androidx.compose.material.OutlinedTextFieldKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.Transformation;
import com.cloudinary.transformation.AbstractLayer;
import com.cloudinary.transformation.Condition;
import com.cloudinary.transformation.Expression;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import com.google.android.gms.common.Scopes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes11.dex */
public class Transformation<T extends Transformation> implements Serializable {
    public static final String VAR_NAME_RE = "^\\$[a-zA-Z][a-zA-Z0-9]+$";
    protected boolean hiDPI;
    protected String htmlHeight;
    protected String htmlWidth;
    protected boolean isResponsive;
    protected Map transformation;
    protected List<Map> transformations;
    protected static boolean defaultIsResponsive = false;
    protected static Object defaultDPR = null;
    private static final Map<String, String> DEFAULT_RESPONSIVE_WIDTH_TRANSFORMATION = ObjectUtils.asMap("width", "auto", "crop", "limit");
    protected static Map responsiveWidthTransformation = null;
    private static final Pattern RANGE_VALUE_RE = Pattern.compile("^((?:\\d+\\.)?\\d+)([%pP])?$");
    private static final Pattern RANGE_RE = Pattern.compile("^(\\d+\\.)?\\d+[%pP]?\\.\\.(\\d+\\.)?\\d+[%pP]?$");
    private static final String[] SIMPLE_PARAMS = {"ac", "audio_codec", "af", "audio_frequency", "bo", OutlinedTextFieldKt.BorderId, "br", "bit_rate", "cs", "color_space", "d", "default_image", "dl", "delay", "dn", "density", "f", "fetch_format", "fn", "custom_function", "fps", "fps", "g", "gravity", CmcdHeadersFactory.STREAM_TYPE_LIVE, "overlay", "p", "prefix", "pg", "page", "u", "underlay", "vs", "video_sampling", "sp", "streaming_profile", "ki", "keyframe_interval"};

    public Transformation(Transformation transformation) {
        this(dup(transformation.transformations));
        this.hiDPI = transformation.isHiDPI();
        this.isResponsive = transformation.isResponsive();
    }

    public Transformation(List<Map> transformations) {
        this.hiDPI = false;
        this.isResponsive = false;
        this.transformations = transformations;
        if (transformations.isEmpty()) {
            chain();
        } else {
            this.transformation = transformations.get(transformations.size() - 1);
        }
    }

    public Transformation() {
        this.hiDPI = false;
        this.isResponsive = false;
        this.transformations = new ArrayList();
        chain();
    }

    public T width(Object obj) {
        return (T) param("width", obj);
    }

    public T height(Object obj) {
        return (T) param("height", obj);
    }

    public T named(String... strArr) {
        return (T) param("transformation", strArr);
    }

    public T crop(String str) {
        return (T) param("crop", str);
    }

    public T background(String str) {
        return (T) param("background", str);
    }

    public T color(String str) {
        return (T) param("color", str);
    }

    public T effect(String str) {
        return (T) param("effect", str);
    }

    public T effect(String str, Object obj) {
        return (T) param("effect", str + ":" + obj);
    }

    public T angle(int i) {
        return (T) param("angle", Integer.valueOf(i));
    }

    public T angle(String... strArr) {
        return (T) param("angle", strArr);
    }

    public T border(String str) {
        return (T) param(OutlinedTextFieldKt.BorderId, str);
    }

    public T border(int i, String str) {
        return (T) param(OutlinedTextFieldKt.BorderId, "" + i + "px_solid_" + replaceColorPrefix(str));
    }

    public T x(Object obj) {
        return (T) param("x", obj);
    }

    public T y(Object obj) {
        return (T) param("y", obj);
    }

    public T radius(Object obj) {
        return (T) radius(new Object[]{obj});
    }

    public T radius(Object obj, Object obj2) {
        return (T) radius(new Object[]{obj, obj2});
    }

    public T radius(Object obj, Object obj2, Object obj3) {
        return (T) radius(new Object[]{obj, obj2, obj3});
    }

    public T radius(Object obj, Object obj2, Object obj3, Object obj4) {
        return (T) radius(new Object[]{obj, obj2, obj3, obj4});
    }

    public T radius(Object[] objArr) {
        return (T) param("radius", objArr);
    }

    public T quality(Object obj) {
        return (T) param("quality", obj);
    }

    public T defaultImage(String str) {
        return (T) param("default_image", str);
    }

    public T gravity(String str) {
        return (T) param("gravity", str);
    }

    public T keyframeInterval(float f) {
        return (T) param("keyframe_interval", Float.valueOf(f));
    }

    public T keyframeInterval(String str) {
        return (T) param("keyframe_interval", str);
    }

    public T colorSpace(String str) {
        return (T) param("color_space", str);
    }

    public T prefix(String str) {
        return (T) param("prefix", str);
    }

    public T overlay(String str) {
        return (T) param("overlay", str);
    }

    public T overlay(AbstractLayer<?> abstractLayer) {
        return (T) param("overlay", abstractLayer);
    }

    public T underlay(String str) {
        return (T) param("underlay", str);
    }

    public T underlay(AbstractLayer<?> abstractLayer) {
        return (T) param("underlay", abstractLayer);
    }

    public T fetchFormat(String str) {
        return (T) param("fetch_format", str);
    }

    public T density(Object obj) {
        return (T) param("density", obj);
    }

    public T page(Object obj) {
        return (T) param("page", obj);
    }

    public T delay(Object obj) {
        return (T) param("delay", obj);
    }

    public T opacity(Object obj) {
        return (T) param("opacity", obj);
    }

    public T rawTransformation(String str) {
        return (T) param("raw_transformation", str);
    }

    public T flags(String... strArr) {
        return (T) param("flags", strArr);
    }

    public T dpr(float f) {
        return (T) param("dpr", Float.valueOf(f));
    }

    public T dpr(int i) {
        return (T) param("dpr", Integer.valueOf(i));
    }

    public T dpr(String str) {
        return (T) param("dpr", str);
    }

    public T duration(String str) {
        return (T) param(TypedValues.TransitionType.S_DURATION, str);
    }

    public T duration(float f) {
        return (T) param(TypedValues.TransitionType.S_DURATION, new Float(f));
    }

    public T duration(double d) {
        return (T) param(TypedValues.TransitionType.S_DURATION, new Double(d));
    }

    public T durationPercent(float f) {
        return (T) param(TypedValues.TransitionType.S_DURATION, new Float(f).toString() + "p");
    }

    public T durationPercent(double d) {
        return (T) param(TypedValues.TransitionType.S_DURATION, new Double(d).toString() + "p");
    }

    public T startOffset(String str) {
        return (T) param("start_offset", str);
    }

    public T startOffset(float f) {
        return (T) param("start_offset", new Float(f));
    }

    public T startOffset(double d) {
        return (T) param("start_offset", new Double(d));
    }

    public T startOffsetPercent(float f) {
        return (T) param("start_offset", new Float(f).toString() + "p");
    }

    public T startOffsetPercent(double d) {
        return (T) param("start_offset", new Double(d).toString() + "p");
    }

    public T endOffset(String str) {
        return (T) param("end_offset", str);
    }

    public T endOffset(float f) {
        return (T) param("end_offset", new Float(f));
    }

    public T endOffset(double d) {
        return (T) param("end_offset", new Double(d));
    }

    public T endOffsetPercent(float f) {
        return (T) param("end_offset", new Float(f).toString() + "p");
    }

    public T endOffsetPercent(double d) {
        return (T) param("end_offset", new Double(d).toString() + "p");
    }

    public T offset(String str) {
        return (T) param(TypedValues.CycleType.S_WAVE_OFFSET, str);
    }

    public T offset(String[] strArr) {
        if (strArr.length < 2) {
            throw new IllegalArgumentException("Offset range must include at least 2 items");
        }
        return (T) param(TypedValues.CycleType.S_WAVE_OFFSET, strArr);
    }

    public T offset(float[] fArr) {
        if (fArr.length < 2) {
            throw new IllegalArgumentException("Offset range must include at least 2 items");
        }
        return (T) offset(new Number[]{Float.valueOf(fArr[0]), Float.valueOf(fArr[1])});
    }

    public T offset(double[] dArr) {
        if (dArr.length < 2) {
            throw new IllegalArgumentException("Offset range must include at least 2 items");
        }
        return (T) offset(new Number[]{Double.valueOf(dArr[0]), Double.valueOf(dArr[1])});
    }

    public T offset(Number[] numberArr) {
        if (numberArr.length < 2) {
            throw new IllegalArgumentException("Offset range must include at least 2 items");
        }
        return (T) param(TypedValues.CycleType.S_WAVE_OFFSET, numberArr);
    }

    public T videoCodec(String str) {
        return (T) param("video_codec", str);
    }

    public T videoCodec(Map<String, String> map) {
        return (T) param("video_codec", map);
    }

    public T audioCodec(String str) {
        return (T) param("audio_codec", str);
    }

    public T audioFrequency(String str) {
        return (T) param("audio_frequency", str);
    }

    public T audioFrequency(int i) {
        return (T) param("audio_frequency", Integer.valueOf(i));
    }

    public T bitRate(String str) {
        return (T) param("bit_rate", str);
    }

    public T bitRate(int i) {
        return (T) param("bit_rate", new Integer(i));
    }

    public T videoSampling(String str) {
        return (T) param("video_sampling", str);
    }

    public T videoSamplingFrames(int i) {
        return (T) param("video_sampling", Integer.valueOf(i));
    }

    public T videoSamplingSeconds(Number number) {
        return (T) param("video_sampling", number.toString() + CmcdHeadersFactory.STREAMING_FORMAT_SS);
    }

    public T videoSamplingSeconds(int i) {
        return (T) videoSamplingSeconds(new Integer(i));
    }

    public T videoSamplingSeconds(float f) {
        return (T) videoSamplingSeconds(new Float(f));
    }

    public T videoSamplingSeconds(double d) {
        return (T) videoSamplingSeconds(new Double(d));
    }

    public T zoom(String str) {
        return (T) param("zoom", str);
    }

    public T zoom(float f) {
        return (T) param("zoom", new Float(f));
    }

    public T zoom(double d) {
        return (T) param("zoom", new Double(d));
    }

    public T aspectRatio(double d) {
        return (T) param("aspect_ratio", new Double(d));
    }

    public T aspectRatio(String str) {
        return (T) param("aspect_ratio", str);
    }

    public T aspectRatio(int i, int i2) {
        return (T) aspectRatio(Integer.toString(i) + ":" + Integer.toString(i2));
    }

    public T responsiveWidth(boolean z) {
        return (T) param("responsive_width", Boolean.valueOf(z));
    }

    public Condition ifCondition() {
        return new Condition().setParent(this);
    }

    public T ifCondition(String str) {
        return (T) param("if", str);
    }

    public T ifCondition(Expression expression) {
        return (T) ifCondition(expression.toString());
    }

    public T ifCondition(Condition condition) {
        return (T) ifCondition(condition.toString());
    }

    public T ifElse() {
        chain();
        return (T) param("if", "else");
    }

    public T endIf() {
        chain();
        for (int size = this.transformations.size() - 1; size >= 0; size--) {
            Map map = this.transformations.get(size);
            Object obj = map.get("if");
            if (obj != null) {
                String string = obj.toString();
                if (string.equals(TtmlNode.END)) {
                    break;
                }
                if (map.size() > 1) {
                    map.remove("if");
                    this.transformations.set(size, map);
                    this.transformations.add(size, ObjectUtils.asMap("if", obj));
                }
                if (!"else".equals(string)) {
                    break;
                }
            }
        }
        param("if", TtmlNode.END);
        return (T) chain();
    }

    public T fps(String str) {
        return (T) param("fps", str);
    }

    public T fps(double d) {
        return (T) param("fps", new Float(d));
    }

    public T fps(int i) {
        return (T) param("fps", new Integer(i));
    }

    public T fps(Object obj, Object obj2) {
        if (obj2 == null && obj == null) {
            throw new IllegalArgumentException("At least one of [rangeStart, rangeEnd] must be provided");
        }
        StringBuilder sb = new StringBuilder();
        if (obj != null) {
            sb.append(obj);
        }
        sb.append("-");
        if (obj2 != null) {
            sb.append(obj2);
        }
        return (T) param("fps", sb.toString());
    }

    public T streamingProfile(String str) {
        return (T) param("streaming_profile", str);
    }

    public boolean isResponsive() {
        return this.isResponsive;
    }

    public boolean isHiDPI() {
        return this.hiDPI;
    }

    public T params(Map transformation) {
        this.transformation = transformation;
        this.transformations.add(transformation);
        return this;
    }

    public T chain() {
        return (T) params(new HashMap());
    }

    public T chainWith(Transformation transformation) {
        List<Map> listDup = dup(this.transformations);
        listDup.addAll(dup(transformation.transformations));
        return (T) new Transformation(listDup);
    }

    public T param(String key, Object value) {
        this.transformation.put(key, value);
        return this;
    }

    public String generate() {
        return generate(this.transformations);
    }

    public String toString() {
        return generate();
    }

    public String generate(Iterable<Map> optionsList) {
        List<String> components = new ArrayList<>();
        for (Map options : optionsList) {
            if (options.size() > 0) {
                components.add(generate(options));
            }
        }
        return StringUtils.join(components, DomExceptionUtils.SEPARATOR);
    }

    public String generate(Map options) {
        String height;
        List ts;
        String transformationString;
        List transformations;
        boolean isResponsive = ObjectUtils.asBoolean(options.get("responsive_width"), Boolean.valueOf(defaultIsResponsive)).booleanValue();
        String size = (String) options.get("size");
        String str = "x";
        if (size != null) {
            String[] size_components = size.split("x");
            options.put("width", size_components[0]);
            options.put("height", size_components[1]);
        }
        String width = ObjectUtils.asString(options.get("width"));
        this.htmlWidth = width;
        String height2 = ObjectUtils.asString(options.get("height"));
        this.htmlHeight = height2;
        boolean hasLayer = (options.get("overlay") != null && StringUtils.isNotBlank(options.get("overlay").toString())) || (options.get("underlay") != null && StringUtils.isNotBlank(options.get("underlay").toString()));
        String crop = (String) options.get("crop");
        String angle = StringUtils.join((List<String>) ObjectUtils.asArray(options.get("angle")), ".");
        boolean noHtmlSizes = hasLayer || StringUtils.isNotBlank(angle) || "fit".equals(crop) || "limit".equals(crop);
        if (width != null && (width.startsWith("auto") || !isValidAttrValue(width) || noHtmlSizes || isResponsive)) {
            this.htmlWidth = null;
        }
        if (height2 != null && (!isValidAttrValue(height2) || noHtmlSizes || isResponsive)) {
            this.htmlHeight = null;
        }
        String background = (String) options.get("background");
        if (background != null) {
            background = replaceColorPrefix(background);
        }
        String color = (String) options.get("color");
        if (color != null) {
            color = replaceColorPrefix(color);
        }
        List transformations2 = ObjectUtils.asArray(options.get("transformation"));
        boolean allNamed = true;
        int i = 0;
        while (true) {
            height = height2;
            if (i >= transformations2.size()) {
                break;
            }
            Object baseTransformation = transformations2.get(i);
            boolean hasLayer2 = hasLayer;
            if (baseTransformation instanceof Map) {
                allNamed = false;
                break;
            }
            if (baseTransformation instanceof String) {
                transformations2.set(i, ((String) baseTransformation).replaceAll(" ", "%20"));
            }
            i++;
            height2 = height;
            hasLayer = hasLayer2;
            angle = angle;
        }
        String namedTransformation = null;
        if (allNamed) {
            namedTransformation = StringUtils.join((List<String>) transformations2, ".");
            ts = new ArrayList();
        } else {
            List transformations3 = new ArrayList();
            Iterator it = transformations2.iterator();
            while (it.hasNext()) {
                Object baseTransformation2 = it.next();
                Iterator it2 = it;
                if (baseTransformation2 instanceof Map) {
                    transformationString = generate((Map) baseTransformation2);
                } else {
                    Map map = new HashMap();
                    map.put("transformation", baseTransformation2);
                    transformationString = generate(map);
                }
                transformations3.add(transformationString);
                it = it2;
            }
            ts = transformations3;
        }
        String flags = StringUtils.join((List<String>) ObjectUtils.asArray(options.get("flags")), ".");
        String duration = normRangeValue(options.get(TypedValues.TransitionType.S_DURATION));
        String startOffset = normAutoRangeValue(options.get("start_offset"));
        String endOffset = normRangeValue(options.get("end_offset"));
        String[] offset = splitRange(options.get(TypedValues.CycleType.S_WAVE_OFFSET));
        if (offset != null) {
            startOffset = normAutoRangeValue(offset[0]);
            endOffset = normRangeValue(offset[1]);
        }
        String videoCodec = processVideoCodecParam(options.get("video_codec"));
        String dpr = ObjectUtils.asString(options.get("dpr"), defaultDPR == null ? null : defaultDPR.toString());
        List<String> components = new ArrayList<>();
        String ifValue = (String) options.get("if");
        if (ifValue != null) {
            components.add(0, "if_" + Expression.normalize(ifValue));
        }
        SortedSet<String> varParams = new TreeSet<>();
        Iterator it3 = options.keySet().iterator();
        while (true) {
            Iterator it4 = it3;
            if (!it3.hasNext()) {
                break;
            }
            String str2 = str;
            Object k = it4.next();
            String videoCodec2 = videoCodec;
            String key = (String) k;
            if (StringUtils.isVariable(key)) {
                varParams.add(key + "_" + ObjectUtils.asString(options.get(k)));
            }
            str = str2;
            it3 = it4;
            videoCodec = videoCodec2;
            namedTransformation = namedTransformation;
        }
        String namedTransformation2 = namedTransformation;
        String str3 = str;
        String videoCodec3 = videoCodec;
        if (!varParams.isEmpty()) {
            components.add(StringUtils.join(varParams, ","));
        }
        String variables = processVar((Expression[]) options.get("variables"));
        if (variables != null) {
            components.add(variables);
        }
        Map<String, String> params = new HashMap<>(64);
        params.put(CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY, Expression.normalize(angle));
        params.put("ar", Expression.normalize(options.get("aspect_ratio")));
        params.put("b", background);
        params.put("c", crop);
        params.put("co", color);
        params.put("dpr", Expression.normalize(dpr));
        params.put("du", duration);
        params.put("e", Expression.normalize(options.get("effect")));
        params.put("eo", endOffset);
        params.put("fl", flags);
        params.put(CmcdHeadersFactory.STREAMING_FORMAT_HLS, Expression.normalize(height));
        params.put("o", Expression.normalize(options.get("opacity")));
        params.put("q", Expression.normalize(options.get("quality")));
        params.put("r", Expression.normalize(radiusToExpression((Object[]) options.get("radius"))));
        params.put("so", startOffset);
        params.put("t", namedTransformation2);
        params.put("vc", videoCodec3);
        String flags2 = Expression.normalize(width);
        params.put("w", flags2);
        params.put(str3, Expression.normalize(options.get(str3)));
        params.put("y", Expression.normalize(options.get("y")));
        params.put("z", Expression.normalize(options.get("zoom")));
        for (int i2 = 0; i2 < SIMPLE_PARAMS.length; i2 += 2) {
            params.put(SIMPLE_PARAMS[i2], ObjectUtils.asString(options.get(SIMPLE_PARAMS[i2 + 1])));
        }
        Map<String, String> params2 = new TreeMap<>(params);
        for (Iterator<Map.Entry<String, String>> it5 = params2.entrySet().iterator(); it5.hasNext(); it5 = it5) {
            Map.Entry<String, String> param = it5.next();
            if (StringUtils.isNotBlank(param.getValue())) {
                components.add(param.getKey() + "_" + param.getValue());
            }
            params2 = params2;
        }
        String raw_transformation = (String) options.get("raw_transformation");
        if (raw_transformation != null) {
            components.add(raw_transformation);
        }
        if (components.isEmpty()) {
            transformations = ts;
        } else {
            String joined = StringUtils.join(components, ",");
            transformations = ts;
            transformations.add(joined);
        }
        if (isResponsive) {
            transformations.add(generate(getResponsiveWidthTransformation()));
        }
        if ("auto".equals(width) || isResponsive) {
            this.isResponsive = true;
        }
        if ("auto".equals(dpr)) {
            this.hiDPI = true;
        }
        return StringUtils.join((List<String>) transformations, DomExceptionUtils.SEPARATOR);
    }

    private String replaceColorPrefix(String color) {
        return StringUtils.replaceIfFirstChar(color, '#', "rgb:");
    }

    private String processVar(Expression[] variables) {
        if (variables == null) {
            return null;
        }
        List<String> s = new ArrayList<>(variables.length);
        for (Expression variable : variables) {
            s.add(variable.toString());
        }
        return StringUtils.join(s, ",");
    }

    private boolean isValidAttrValue(String value) {
        try {
            float parseFloat = Float.parseFloat(value);
            return parseFloat >= 1.0f;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getHtmlWidth() {
        return this.htmlWidth;
    }

    public String getHtmlHeight() {
        return this.htmlHeight;
    }

    private static List<Map> dup(List<Map> transformations) {
        List<Map> result = new ArrayList<>();
        for (Map params : transformations) {
            result.add(new HashMap(params));
        }
        return result;
    }

    public static void setResponsiveWidthTransformation(Map transformation) {
        responsiveWidthTransformation = transformation;
    }

    private static Map getResponsiveWidthTransformation() {
        Map result = new HashMap();
        if (responsiveWidthTransformation == null) {
            result.putAll(DEFAULT_RESPONSIVE_WIDTH_TRANSFORMATION);
        } else {
            result.putAll(responsiveWidthTransformation);
        }
        return result;
    }

    public static void setDefaultIsResponsive(boolean isResponsive) {
        defaultIsResponsive = isResponsive;
    }

    public static void setDefaultDPR(Object dpr) {
        defaultDPR = dpr;
    }

    private static String[] splitRange(Object range) {
        if ((range instanceof String[]) && ((String[]) range).length >= 2) {
            String[] stringArrayRange = (String[]) range;
            return new String[]{stringArrayRange[0], stringArrayRange[1]};
        }
        if ((range instanceof Number[]) && ((Number[]) range).length >= 2) {
            Number[] numberArrayRange = (Number[]) range;
            return new String[]{numberArrayRange[0].toString(), numberArrayRange[1].toString()};
        }
        if ((range instanceof String) && RANGE_RE.matcher((String) range).matches()) {
            return ((String) range).split("\\.\\.", 2);
        }
        return null;
    }

    private static String normRangeValue(Object objectValue) {
        if (objectValue == null) {
            return null;
        }
        String value = objectValue.toString();
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        Matcher matcher = RANGE_VALUE_RE.matcher(value);
        if (!matcher.matches()) {
            return Expression.normalize(value);
        }
        String modifier = "";
        if (matcher.groupCount() == 2 && !StringUtils.isEmpty(matcher.group(2))) {
            modifier = "p";
        }
        return matcher.group(1) + modifier;
    }

    private static String normAutoRangeValue(Object objectValue) {
        if ("auto".equals(objectValue)) {
            return objectValue.toString();
        }
        return normRangeValue(objectValue);
    }

    private static String processVideoCodecParam(Object param) {
        StringBuilder outParam = new StringBuilder();
        if (param instanceof String) {
            outParam.append(param);
        }
        if (param instanceof Map) {
            Map<String, String> paramMap = (Map) param;
            outParam.append(paramMap.get("codec"));
            if (paramMap.containsKey(Scopes.PROFILE)) {
                outParam.append(":").append(paramMap.get(Scopes.PROFILE));
                if (paramMap.containsKey("level")) {
                    outParam.append(":").append(paramMap.get("level"));
                    if (paramMap.containsKey("b_frames") && paramMap.get("b_frames") == "false") {
                        outParam.append(":").append("bframes_no");
                    }
                }
            }
        }
        return outParam.toString();
    }

    public T variable(String str, Object obj) {
        return (T) param(str, obj);
    }

    public T variables(Expression... expressionArr) {
        return (T) param("variables", expressionArr);
    }

    public T customFunction(CustomFunction customFunction) {
        return (T) param("custom_function", customFunction.toString());
    }

    public T customPreFunction(CustomFunction customFunction) {
        return (T) param("custom_function", "pre:" + customFunction.toString());
    }

    private String radiusToExpression(Object[] radiusOption) {
        if (radiusOption == null) {
            return null;
        }
        if (radiusOption.length == 0 || radiusOption.length > 4) {
            throw new IllegalArgumentException("Radius array should contain between 1 and 4 values");
        }
        for (Object o : radiusOption) {
            if (o == null) {
                throw new IllegalArgumentException("Radius options array should not contain nulls");
            }
        }
        return StringUtils.join(radiusOption, ":");
    }
}
