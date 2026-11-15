package androidx.compose.ui.autofill;

import android.os.Build;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import androidx.collection.ScatterMap;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import androidx.compose.ui.platform.SemanticsUtils_androidKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsInfo;
import androidx.compose.ui.semantics.SemanticsInfoKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.spatial.RectManager;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.media3.extractor.ts.TsExtractor;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PopulateViewStructure.android.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0001¨\u0006\u000b"}, d2 = {"populate", "", "Landroid/view/ViewStructure;", "semanticsInfo", "Landroidx/compose/ui/semantics/SemanticsInfo;", "rootAutofillId", "Landroid/view/autofill/AutofillId;", "packageName", "", "rectManager", "Landroidx/compose/ui/spatial/RectManager;", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PopulateViewStructure_androidKt {
    /* JADX WARN: Code duplicated, block: B:176:0x0434  */
    /* JADX WARN: Code duplicated, block: B:182:0x0445  */
    /* JADX WARN: Code duplicated, block: B:183:0x044e  */
    /* JADX WARN: Code duplicated, block: B:186:0x0457  */
    /* JADX WARN: Code duplicated, block: B:188:0x0478 A[LOOP:4: B:187:0x0476->B:188:0x0478, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:190:0x04c0  */
    /* JADX WARN: Code duplicated, block: B:198:0x04e1  */
    /* JADX WARN: Code duplicated, block: B:203:0x0500  */
    /* JADX WARN: Code duplicated, block: B:229:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:230:? A[RETURN, SYNTHETIC] */
    public static final void populate(final ViewStructure $this$populate, SemanticsInfo semanticsInfo, AutofillId rootAutofillId, String packageName, RectManager rectManager) {
        ContentType contentType;
        SemanticsProperties properties;
        boolean zM7095equalsimpl0;
        boolean isSensitive;
        boolean contentTypePassword;
        boolean isPassword;
        int i;
        String it;
        List $this$fastForEach$iv;
        int size;
        String str;
        int index$iv;
        String[] contentHints;
        String[] it2;
        ScatterMap props$ui;
        int j$iv$iv;
        ScatterMap props$ui2;
        boolean isPasswordProp;
        AnnotatedString annotatedString;
        ContentType contentType2;
        ContentDataType contentDataType;
        int j$iv$iv2;
        AndroidFillableData androidFillableData;
        final AutofillApi26Helper autofillApi = AutofillApi26Helper.INSTANCE;
        SemanticsProperties properties2 = SemanticsProperties.INSTANCE;
        SemanticsActions actions = SemanticsActions.INSTANCE;
        AndroidFillableData androidFillableData2 = null;
        boolean isSensitiveDataProp = true;
        Object maxTextLengthProp = null;
        Role role = null;
        Boolean bool = null;
        Object toggleableStateProp = null;
        List list = null;
        boolean hasSetTextAction = false;
        SemanticsConfiguration semanticsConfiguration = semanticsInfo.getSemanticsConfiguration();
        ContentDataType contentDataType2 = null;
        AnnotatedString annotatedString2 = null;
        boolean isPasswordProp2 = false;
        if (semanticsConfiguration == null || (props$ui2 = semanticsConfiguration.getProps$ui()) == null) {
            contentType = null;
            properties = properties2;
        } else {
            ScatterMap this_$iv = props$ui2;
            int i2 = 8;
            Object[] k$iv = this_$iv.keys;
            Object[] v$iv = this_$iv.values;
            ContentType contentType3 = null;
            long[] m$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    ScatterMap this_$iv2 = this_$iv;
                    Object[] v$iv2 = v$iv;
                    properties = properties2;
                    SemanticsActions actions2 = actions;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv3 = 0;
                        while (j$iv$iv3 < bitCount$iv$iv) {
                            long value$iv$iv$iv = slot$iv$iv & 255;
                            if (value$iv$iv$iv < 128) {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv3;
                                Object obj = k$iv[index$iv$iv];
                                j$iv$iv2 = j$iv$iv3;
                                Object value = v$iv2[index$iv$iv];
                                SemanticsPropertyKey property = (SemanticsPropertyKey) obj;
                                if (Intrinsics.areEqual(property, properties.getContentDataType())) {
                                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.ui.autofill.ContentDataType");
                                    contentDataType2 = (ContentDataType) value;
                                } else {
                                    if (Intrinsics.areEqual(property, properties.getContentDescription())) {
                                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
                                        String it3 = (String) CollectionsKt.firstOrNull((List) value);
                                        if (it3 != null) {
                                            autofillApi.setContentDescription($this$populate, it3);
                                        }
                                        androidFillableData = androidFillableData2;
                                    } else if (Intrinsics.areEqual(property, properties.getContentType())) {
                                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.ui.autofill.ContentType");
                                        contentType3 = (ContentType) value;
                                    } else if (Intrinsics.areEqual(property, properties.getFillableData())) {
                                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.ui.autofill.AndroidFillableData");
                                        androidFillableData2 = (AndroidFillableData) value;
                                    } else if (Intrinsics.areEqual(property, properties.getEditableText())) {
                                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.ui.text.AnnotatedString");
                                        annotatedString2 = (AnnotatedString) value;
                                    } else if (Intrinsics.areEqual(property, properties.getFocused())) {
                                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Boolean");
                                        autofillApi.setFocused($this$populate, ((Boolean) value).booleanValue());
                                        androidFillableData = androidFillableData2;
                                    } else {
                                        androidFillableData = androidFillableData2;
                                        Object fillableDataProp = properties.getMaxTextLength();
                                        if (Intrinsics.areEqual(property, fillableDataProp)) {
                                            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Int");
                                            maxTextLengthProp = (Integer) value;
                                            androidFillableData2 = androidFillableData;
                                        } else if (Intrinsics.areEqual(property, properties.getPassword())) {
                                            isPasswordProp2 = true;
                                            androidFillableData2 = androidFillableData;
                                        } else if (Intrinsics.areEqual(property, properties.getIsSensitiveData())) {
                                            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Boolean");
                                            isSensitiveDataProp = ((Boolean) value).booleanValue();
                                            androidFillableData2 = androidFillableData;
                                        } else if (Intrinsics.areEqual(property, properties.getRole())) {
                                            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.ui.semantics.Role");
                                            role = (Role) value;
                                            androidFillableData2 = androidFillableData;
                                        } else if (Intrinsics.areEqual(property, properties.getSelected())) {
                                            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Boolean");
                                            bool = (Boolean) value;
                                            androidFillableData2 = androidFillableData;
                                        } else if (Intrinsics.areEqual(property, properties.getToggleableState())) {
                                            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.ui.state.ToggleableState");
                                            toggleableStateProp = (ToggleableState) value;
                                            androidFillableData2 = androidFillableData;
                                        } else if (Intrinsics.areEqual(property, actions2.getOnClick())) {
                                            autofillApi.setClickable($this$populate, true);
                                        } else if (Intrinsics.areEqual(property, actions2.getOnLongClick())) {
                                            autofillApi.setLongClickable($this$populate, true);
                                        } else if (Intrinsics.areEqual(property, actions2.getRequestFocus())) {
                                            autofillApi.setFocusable($this$populate, true);
                                        } else if (Intrinsics.areEqual(property, actions2.getSetText())) {
                                            hasSetTextAction = true;
                                            androidFillableData2 = androidFillableData;
                                        }
                                    }
                                    androidFillableData2 = androidFillableData;
                                }
                            } else {
                                j$iv$iv2 = j$iv$iv3;
                            }
                            slot$iv$iv >>= i2;
                            j$iv$iv3 = j$iv$iv2 + 1;
                        }
                        AndroidFillableData androidFillableData3 = androidFillableData2;
                        int j$iv$iv4 = i2;
                        if (bitCount$iv$iv != j$iv$iv4) {
                            annotatedString = annotatedString2;
                            isPasswordProp = isPasswordProp2;
                            contentType2 = contentType3;
                            androidFillableData2 = androidFillableData3;
                            contentDataType = contentDataType2;
                            break;
                        }
                        androidFillableData2 = androidFillableData3;
                    }
                    if (i$iv$iv != lastIndex$iv$iv) {
                        i$iv$iv++;
                        properties2 = properties;
                        actions = actions2;
                        this_$iv = this_$iv2;
                        v$iv = v$iv2;
                        i2 = 8;
                    }
                }
                contentDataType2 = contentDataType;
                contentType = contentType2;
                annotatedString2 = annotatedString;
                isPasswordProp2 = isPasswordProp;
            } else {
                properties = properties2;
            }
            annotatedString = annotatedString2;
            isPasswordProp = isPasswordProp2;
            contentType2 = contentType3;
            contentDataType = contentDataType2;
            contentDataType2 = contentDataType;
            contentType = contentType2;
            annotatedString2 = annotatedString;
            isPasswordProp2 = isPasswordProp;
        }
        SemanticsConfiguration semanticsConfigurationMergedSemanticsConfiguration = SemanticsInfoKt.mergedSemanticsConfiguration(semanticsInfo);
        if (semanticsConfigurationMergedSemanticsConfiguration != null && (props$ui = semanticsConfigurationMergedSemanticsConfiguration.getProps$ui()) != null) {
            ScatterMap this_$iv3 = props$ui;
            Object[] k$iv2 = this_$iv3.keys;
            Object[] v$iv3 = this_$iv3.values;
            ScatterMap this_$iv$iv = this_$iv3;
            long[] m$iv$iv2 = this_$iv$iv.metadata;
            int lastIndex$iv$iv2 = m$iv$iv2.length - 2;
            int i$iv$iv2 = 0;
            if (0 <= lastIndex$iv$iv2) {
                while (true) {
                    long slot$iv$iv2 = m$iv$iv2[i$iv$iv2];
                    Object[] k$iv3 = k$iv2;
                    Object[] v$iv4 = v$iv3;
                    ScatterMap this_$iv$iv2 = this_$iv$iv;
                    long[] m$iv$iv3 = m$iv$iv2;
                    if ((((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                        int j$iv$iv5 = 0;
                        while (j$iv$iv5 < bitCount$iv$iv2) {
                            long value$iv$iv$iv2 = slot$iv$iv2 & 255;
                            if (value$iv$iv$iv2 < 128) {
                                int index$iv$iv2 = (i$iv$iv2 << 3) + j$iv$iv5;
                                Object obj2 = k$iv3[index$iv$iv2];
                                j$iv$iv = j$iv$iv5;
                                Object value2 = v$iv4[index$iv$iv2];
                                SemanticsPropertyKey property2 = (SemanticsPropertyKey) obj2;
                                if (Intrinsics.areEqual(property2, properties.getDisabled())) {
                                    autofillApi.setEnabled($this$populate, false);
                                } else if (Intrinsics.areEqual(property2, properties.getText())) {
                                    Intrinsics.checkNotNull(value2, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.ui.text.AnnotatedString>");
                                    list = (List) value2;
                                }
                            } else {
                                j$iv$iv = j$iv$iv5;
                            }
                            slot$iv$iv2 >>= 8;
                            j$iv$iv5 = j$iv$iv + 1;
                        }
                        if (bitCount$iv$iv2 != 8) {
                            break;
                        }
                    }
                    if (i$iv$iv2 == lastIndex$iv$iv2) {
                        break;
                    }
                    i$iv$iv2++;
                    this_$iv$iv = this_$iv$iv2;
                    m$iv$iv2 = m$iv$iv3;
                    k$iv2 = k$iv3;
                    v$iv3 = v$iv4;
                }
            }
        }
        Integer numValueOf = Integer.valueOf(semanticsInfo.getSemanticsId());
        numValueOf.intValue();
        int it4 = semanticsInfo.getParentInfo() == null ? 1 : 0;
        Integer autofillType = null;
        if (it4 != 0) {
            numValueOf = null;
        }
        int semanticsId = numValueOf != null ? numValueOf.intValue() : -1;
        autofillApi.setAutofillId($this$populate, rootAutofillId, semanticsId);
        autofillApi.setId($this$populate, semanticsId, packageName, null, null);
        if (contentDataType2 != null) {
            autofillType = Integer.valueOf(ContentDataType_androidKt.getDataType(contentDataType2));
        } else if (hasSetTextAction) {
            autofillType = 1;
        } else if (toggleableStateProp != null) {
            autofillType = 2;
        }
        if (autofillType != null) {
            int it5 = autofillType.intValue();
            autofillApi.setAutofillType($this$populate, it5);
        }
        if (annotatedString2 != null) {
            AnnotatedString textProp = annotatedString2;
            autofillApi.setAutofillValue($this$populate, autofillApi.getAutofillTextValue(textProp.getText()));
        }
        if (androidFillableData2 != null) {
            AndroidFillableData fillableData = androidFillableData2;
            AutofillValue it6 = fillableData.getAutofillValue();
            autofillApi.setAutofillValue($this$populate, it6);
        }
        if (contentType != null && (it2 = ContentType_androidKt.getContentHints(contentType)) != null) {
            autofillApi.setAutofillHints($this$populate, it2);
        }
        rectManager.getRects().withRect(semanticsInfo.getSemanticsId(), new Function4<Integer, Integer, Integer, Integer, Unit>() { // from class: androidx.compose.ui.autofill.PopulateViewStructure_androidKt.populate.7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2, Integer num3, Integer num4) {
                invoke(num.intValue(), num2.intValue(), num3.intValue(), num4.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int left, int top, int right, int bottom) {
                autofillApi.setDimens($this$populate, left, top, 0, 0, right - left, bottom - top);
            }
        });
        if (bool != null) {
            boolean it7 = bool.booleanValue();
            autofillApi.setSelected($this$populate, it7);
        }
        Object toggleableState = toggleableStateProp;
        Boolean selected = bool;
        if (toggleableState != null) {
            autofillApi.setCheckable($this$populate, true);
            autofillApi.setChecked($this$populate, toggleableState == ToggleableState.On);
        } else if (selected != null) {
            int iM7106getTabo7Vup1c = Role.INSTANCE.m7106getTabo7Vup1c();
            if (role == null) {
                zM7095equalsimpl0 = false;
            } else {
                zM7095equalsimpl0 = Role.m7095equalsimpl0(role.getValue(), iM7106getTabo7Vup1c);
            }
            if (!zM7095equalsimpl0) {
                autofillApi.setCheckable($this$populate, true);
                autofillApi.setChecked($this$populate, selected.booleanValue());
            }
        }
        String passwordHint = (String) ArraysKt.first(ContentType_androidKt.getContentHints(ContentType.INSTANCE.getPassword()));
        if (contentType != null && (contentHints = ContentType_androidKt.getContentHints(contentType)) != null) {
            boolean zContains = ArraysKt.contains(contentHints, passwordHint);
            isSensitive = true;
            if (zContains) {
                contentTypePassword = true;
            }
            if (!isPasswordProp2 || contentTypePassword) {
                isPassword = isSensitive;
            } else {
                isPassword = false;
            }
            if (!isPassword && !isSensitiveDataProp) {
                isSensitive = false;
            }
            autofillApi.setDataIsSensitive($this$populate, isSensitive);
            if (semanticsInfo.isTransparent()) {
                i = 4;
            } else {
                i = 0;
            }
            autofillApi.setVisibility($this$populate, i);
            if (list != null) {
                List it8 = list;
                $this$fastForEach$iv = it8;
                size = $this$fastForEach$iv.size();
                str = "";
                index$iv = 0;
                while (index$iv < size) {
                    int i3 = size;
                    List $this$fastForEach$iv2 = $this$fastForEach$iv;
                    Object item$iv = $this$fastForEach$iv2.get(index$iv);
                    AnnotatedString text = (AnnotatedString) item$iv;
                    str = str + text.getText() + '\n';
                    index$iv++;
                    size = i3;
                    $this$fastForEach$iv = $this$fastForEach$iv2;
                }
                Object concatenatedText = str;
                autofillApi.setText($this$populate, (CharSequence) concatenatedText);
                autofillApi.setClassName($this$populate, AndroidComposeViewAccessibilityDelegateCompat.TextClassName);
            }
            if (semanticsInfo.getChildrenInfo().isEmpty() && role != null && (it = SemanticsUtils_androidKt.m7075toLegacyClassNameV4PA4sw(role.getValue())) != null) {
                autofillApi.setClassName($this$populate, it);
            }
            if (hasSetTextAction) {
                autofillApi.setClassName($this$populate, AndroidComposeViewAccessibilityDelegateCompat.TextFieldClassName);
                if (Build.VERSION.SDK_INT >= 28 && maxTextLengthProp != null) {
                    int it9 = ((Number) maxTextLengthProp).intValue();
                    AutofillApi28Helper.INSTANCE.setMaxTextLength($this$populate, it9);
                }
                if (isPassword) {
                    autofillApi.setInputType($this$populate, TsExtractor.TS_STREAM_TYPE_AC3);
                }
            }
        }
        isSensitive = true;
        contentTypePassword = false;
        if (isPasswordProp2) {
            isPassword = isSensitive;
        } else {
            isPassword = isSensitive;
        }
        if (!isPassword) {
            isSensitive = false;
        }
        autofillApi.setDataIsSensitive($this$populate, isSensitive);
        if (semanticsInfo.isTransparent()) {
            i = 4;
        } else {
            i = 0;
        }
        autofillApi.setVisibility($this$populate, i);
        if (list != null) {
            List it10 = list;
            $this$fastForEach$iv = it10;
            size = $this$fastForEach$iv.size();
            str = "";
            index$iv = 0;
            while (index$iv < size) {
                int i4 = size;
                List $this$fastForEach$iv3 = $this$fastForEach$iv;
                Object item$iv2 = $this$fastForEach$iv3.get(index$iv);
                AnnotatedString text2 = (AnnotatedString) item$iv2;
                str = str + text2.getText() + '\n';
                index$iv++;
                size = i4;
                $this$fastForEach$iv = $this$fastForEach$iv3;
            }
            Object concatenatedText2 = str;
            autofillApi.setText($this$populate, (CharSequence) concatenatedText2);
            autofillApi.setClassName($this$populate, AndroidComposeViewAccessibilityDelegateCompat.TextClassName);
        }
        if (semanticsInfo.getChildrenInfo().isEmpty()) {
            autofillApi.setClassName($this$populate, it);
        }
        if (hasSetTextAction) {
            autofillApi.setClassName($this$populate, AndroidComposeViewAccessibilityDelegateCompat.TextFieldClassName);
            if (Build.VERSION.SDK_INT >= 28) {
                int it11 = ((Number) maxTextLengthProp).intValue();
                AutofillApi28Helper.INSTANCE.setMaxTextLength($this$populate, it11);
            }
            if (isPassword) {
                autofillApi.setInputType($this$populate, TsExtractor.TS_STREAM_TYPE_AC3);
            }
        }
    }
}
