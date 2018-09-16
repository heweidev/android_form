package com.hewei.formblocks.blocks.factory;

import com.hewei.formblocks.blocks.BlockFactory;
import com.hewei.formblocks.blocks.SpinnerLine;

public class SexBlockFactory implements BlockFactory<SpinnerLine> {
    public static SpinnerLine.SpinnerItem MALE = new SpinnerLine.SpinnerItem(1, "男");
    public static SpinnerLine.SpinnerItem FEMALE = new SpinnerLine.SpinnerItem(2, "女");

    @Override
    public SpinnerLine create() {
        return new SpinnerLine(new SpinnerLine.SpinnerItem[] {
                MALE, FEMALE,
        });
    }
}
