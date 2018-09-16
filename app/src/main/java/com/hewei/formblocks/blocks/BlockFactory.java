package com.hewei.formblocks.blocks;

import com.hewei.formblocks.annotations.Block;

public interface BlockFactory<T extends BaseBlock> {
    T create();
}
