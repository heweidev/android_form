package com.hewei.formblocks.data;

import java.lang.reflect.Field;

public class ReflactEditable implements Editable<String> {
    private final Object mObj;
    private final String mKey;

    public ReflactEditable(Object obj, String key) {
        mObj = obj;
        mKey = key;
    }

    @Override
    public void set(String s) {
        try {
            Class<?> cls = mObj.getClass();
            Field field = cls.getField(mKey);
            field.setAccessible(true);
            field.set(mObj, s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get() {
        try {
            Class<?> cls = mObj.getClass();
            Field field = cls.getField(mKey);
            field.setAccessible(true);
            Object obj = field.get(mObj);
            if (obj != null) {
                return obj.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
