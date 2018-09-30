package com.hewei.formblocks.data;

import android.util.Pair;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public class KeyValueProvider implements DataProvider<Pair<String, Object>> {
    private final ObjectProvider mObjProvider;

    public KeyValueProvider(Object obj) {
        mObjProvider = new ObjectProvider(obj);
    }

    @Override
    public Pair<String, Object> getData(String key) {
        Object obj = mObjProvider.getData(key);
        return new Pair<>(key, obj);
    }
}
