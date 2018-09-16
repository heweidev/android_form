package com.hewei.formblocks.blocks;

public class SpinnerFactory implements BlockFactory<SpinnerLine> {
    private static final SpinnerLine.SpinnerItem[] items = new SpinnerLine.SpinnerItem[] {
            new SpinnerLine.SpinnerItem(1, "北京"),
            new SpinnerLine.SpinnerItem(2, "上海"),
            new SpinnerLine.SpinnerItem(3, "广州"),
            new SpinnerLine.SpinnerItem(4, "深圳"),
    };

    @Override
    public SpinnerLine create() {
        return new SpinnerLine(items);
    }
}
