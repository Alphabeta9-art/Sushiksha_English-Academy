package com.google.android.gms.internal.p002firebaseauthapi;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzalh {
    private static final char[] zza;

    static String zza(zzalc zzalcVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(str);
        zza(zzalcVar, sb, 0);
        return sb.toString();
    }

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, ' ');
    }

    private static void zza(int i, StringBuilder sb) {
        int length;
        while (i > 0) {
            if (i <= zza.length) {
                length = i;
            } else {
                length = zza.length;
            }
            sb.append(zza, 0, length);
            i -= length;
        }
    }

    static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zza(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zza(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        zza(i, sb);
        if (!str.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Character.toLowerCase(str.charAt(0)));
            for (int i2 = 1; i2 < str.length(); i2++) {
                char cCharAt = str.charAt(i2);
                if (Character.isUpperCase(cCharAt)) {
                    sb2.append("_");
                }
                sb2.append(Character.toLowerCase(cCharAt));
            }
            str = sb2.toString();
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"").append(zzamh.zza(zzaip.zza((String) obj))).append('\"');
            return;
        }
        if (obj instanceof zzaip) {
            sb.append(": \"").append(zzamh.zza((zzaip) obj)).append('\"');
            return;
        }
        if (obj instanceof zzajy) {
            sb.append(" {");
            zza((zzajy) obj, sb, i + 2);
            sb.append("\n");
            zza(i, sb);
            sb.append("}");
            return;
        }
        if (obj instanceof Map.Entry) {
            sb.append(" {");
            Map.Entry entry = (Map.Entry) obj;
            int i3 = i + 2;
            zza(sb, i3, "key", entry.getKey());
            zza(sb, i3, "value", entry.getValue());
            sb.append("\n");
            zza(i, sb);
            sb.append("}");
            return;
        }
        sb.append(": ").append(obj);
    }

    private static void zza(zzalc zzalcVar, StringBuilder sb, int i) {
        int i2;
        int i3;
        boolean zBooleanValue;
        boolean zEquals;
        Method method;
        HashSet hashSet = new HashSet();
        HashMap map = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = zzalcVar.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i4 = 0;
        while (true) {
            i2 = 3;
            if (i4 >= length) {
                break;
            }
            Method method2 = declaredMethods[i4];
            if (!Modifier.isStatic(method2.getModifiers()) && method2.getName().length() >= 3) {
                if (method2.getName().startsWith("set")) {
                    hashSet.add(method2.getName());
                } else if (Modifier.isPublic(method2.getModifiers()) && method2.getParameterTypes().length == 0) {
                    if (method2.getName().startsWith("has")) {
                        map.put(method2.getName(), method2);
                    } else if (method2.getName().startsWith("get")) {
                        treeMap.put(method2.getName(), method2);
                    }
                }
            }
            i4++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String strSubstring = ((String) entry.getKey()).substring(i2);
            if (!strSubstring.endsWith("List")) {
                i3 = i2;
            } else if (strSubstring.endsWith("OrBuilderList")) {
                i3 = i2;
            } else if (strSubstring.equals("List")) {
                i3 = i2;
            } else {
                Method method3 = (Method) entry.getValue();
                if (method3 != null) {
                    i3 = i2;
                    if (method3.getReturnType().equals(List.class)) {
                        zza(sb, i, strSubstring.substring(0, strSubstring.length() - 4), zzajy.zza(method3, zzalcVar, new Object[0]));
                        i2 = i3;
                    }
                } else {
                    i3 = i2;
                }
            }
            if (strSubstring.endsWith("Map") && !strSubstring.equals("Map") && (method = (Method) entry.getValue()) != null && method.getReturnType().equals(Map.class) && !method.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method.getModifiers())) {
                zza(sb, i, strSubstring.substring(0, strSubstring.length() - 3), zzajy.zza(method, zzalcVar, new Object[0]));
                i2 = i3;
            } else if (!hashSet.contains("set" + strSubstring)) {
                i2 = i3;
            } else {
                if (strSubstring.endsWith("Bytes")) {
                    if (treeMap.containsKey("get" + strSubstring.substring(0, strSubstring.length() - 5))) {
                        i2 = i3;
                    }
                }
                Method method4 = (Method) entry.getValue();
                Method method5 = (Method) map.get("has" + strSubstring);
                if (method4 != null) {
                    Object objZza = zzajy.zza(method4, zzalcVar, new Object[0]);
                    if (method5 != null) {
                        zBooleanValue = ((Boolean) zzajy.zza(method5, zzalcVar, new Object[0])).booleanValue();
                    } else {
                        zBooleanValue = true;
                        if (objZza instanceof Boolean) {
                            zEquals = !((Boolean) objZza).booleanValue();
                        } else if (objZza instanceof Integer) {
                            zEquals = ((Integer) objZza).intValue() == 0;
                        } else if (objZza instanceof Float) {
                            zEquals = Float.floatToRawIntBits(((Float) objZza).floatValue()) == 0;
                        } else if (objZza instanceof Double) {
                            zEquals = Double.doubleToRawLongBits(((Double) objZza).doubleValue()) == 0;
                        } else if (objZza instanceof String) {
                            zEquals = objZza.equals("");
                        } else if (objZza instanceof zzaip) {
                            zEquals = objZza.equals(zzaip.zza);
                        } else if (objZza instanceof zzalc) {
                            zEquals = objZza == ((zzalc) objZza).zzg();
                        } else {
                            zEquals = (objZza instanceof Enum) && ((Enum) objZza).ordinal() == 0;
                        }
                        if (zEquals) {
                            zBooleanValue = false;
                        }
                    }
                    if (!zBooleanValue) {
                        i2 = i3;
                    } else {
                        zza(sb, i, strSubstring, objZza);
                        i2 = i3;
                    }
                } else {
                    i2 = i3;
                }
            }
        }
        if (zzalcVar instanceof zzajy.zzb) {
            Iterator<Map.Entry<T, Object>> itZzd = ((zzajy.zzb) zzalcVar).zzc.zzd();
            if (itZzd.hasNext()) {
                throw new NoSuchMethodError();
            }
        }
        zzajy zzajyVar = (zzajy) zzalcVar;
        if (zzajyVar.zzb != null) {
            zzajyVar.zzb.zza(sb, i);
        }
    }
}
