package com.hewei.formblocks.blocks.factory;

import com.hewei.formblocks.blocks.BlockFactory;
import com.hewei.formblocks.blocks.SpinnerLine;

public class IdTypeSpinnerFactory implements BlockFactory<SpinnerLine> {
    @Override
    public SpinnerLine create() {
        return new SpinnerLine(ItemsSet.ID_TYPE_ITEMS);
    }
}
