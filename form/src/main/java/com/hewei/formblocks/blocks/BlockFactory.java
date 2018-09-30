package com.hewei.formblocks.blocks;

public interface BlockFactory<T extends BaseBlock> {
    T create();
}
