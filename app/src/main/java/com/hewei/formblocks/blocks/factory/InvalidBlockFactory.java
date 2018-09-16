package com.hewei.formblocks.blocks.factory;

import com.hewei.formblocks.blocks.BaseBlock;
import com.hewei.formblocks.blocks.BlockFactory;

public class InvalidBlockFactory implements BlockFactory<BaseBlock<Object>> {
    @Override
    public BaseBlock create() {
        return null;
    }
}
