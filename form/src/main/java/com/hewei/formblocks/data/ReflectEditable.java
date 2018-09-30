package com.hewei.formblocks.data;

public class ReflectEditable implements Editable<String> {
    private final Object mObj;
    private final String mKey;

    public ReflectEditable(Object obj, String key) {
        mObj = obj;
        mKey = key;
    }

    @Override
    public void set(String s) {

    }

    @Override
    public String get() {
        return null;
    }
}
