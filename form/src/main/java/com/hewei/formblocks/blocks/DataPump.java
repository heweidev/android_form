package com.hewei.formblocks.blocks;

public interface DataPump<T> {
    boolean accept(Class<? extends BaseBlock<?>> cls);
    T pump(BaseBlock<?> block);
}
