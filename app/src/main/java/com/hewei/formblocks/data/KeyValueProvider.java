package com.hewei.formblocks.data;

import android.util.Pair;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public class KeyValueProvider implements DataProvider<Pair<String, Object>> {
    private Object mObj;

    public KeyValueProvider(Object obj) {
        mObj = obj;
    }

    @Override
    public Pair<String, Object> getData(String key) {
        Object obj = new ObjectProvider(mObj).getData(key);
        return new Pair<>(key, obj);
    }
}
