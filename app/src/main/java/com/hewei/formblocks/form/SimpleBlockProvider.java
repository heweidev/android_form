package com.hewei.formblocks.form;

import com.hewei.formblocks.blocks.BlockFactory;

import java.util.List;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public class SimpleBlockProvider implements BlockProvider {
    private final String id;
    private final Class<?> type;
    private final Class<? extends BlockFactory<?>> factory;
    private final int size;
    private final List<String> args;

    public SimpleBlockProvider(final String id, final Class<?> type,
                               final Class<? extends BlockFactory<?>> factory,
                               final int size, final List<String> args) {
        this.id = id;
        this.type = type;
        this.factory = factory;
        this.size = size;
        this.args = args;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Class<?> getItemType() {
        return type;
    }

    @Override
    public Class<? extends BlockFactory<?>> getFactory() {
        return factory;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }
}
