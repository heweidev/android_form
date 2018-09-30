package com.hewei.formblocks.data;

import java.util.List;

public class ListArgProvider implements ArgsProvider {
    private final List<String> args;

    public ListArgProvider(List<String> args) {
        this.args  = args;
    }

    @Override
    public String getArg(String key) {
        return null;
    }

    @Override
    public String getArg(int index) {
        return args != null && index >= 0 && index < args.size() ? args.get(index) : null;
    }
}
