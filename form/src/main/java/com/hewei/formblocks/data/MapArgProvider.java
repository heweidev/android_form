package com.hewei.formblocks.data;

import java.util.Map;

public class MapArgProvider implements ArgsProvider {
    private final Map<String, String> map;

    public MapArgProvider(Map<String, String> args) {
        map = args;
    }

    @Override
    public String getArg(String key) {
        return map != null ? map.get(key) : null;
    }

    @Override
    public String getArg(int index) {
        return null;
    }
}
