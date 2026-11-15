package com.cloudinary.strategies;

import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public class StrategyLoader {
    public static <T> T load(String str) {
        try {
            return (T) Class.forName(str).newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T find(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            T t = (T) load(list.get(i));
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    public boolean exists(List<String> strategies) {
        return find(strategies) != null;
    }
}
