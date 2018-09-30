package com.hewei.formblocks.blocks.factory;

import com.hewei.formblocks.blocks.BlockFactory;
import com.hewei.formblocks.blocks.SpinnerLine;
import com.hewei.formblocks.data.ListItem;

public class SpinnerFactory implements BlockFactory<SpinnerLine> {
    private static final ListItem[] items = new ListItem[] {
            new SpinnerLine.SimpleListItem("1", "北京"),
            new SpinnerLine.SimpleListItem("2", "上海"),
            new SpinnerLine.SimpleListItem("3", "广州"),
            new SpinnerLine.SimpleListItem("4", "深圳"),
    };

    @Override
    public SpinnerLine create() {
        return new SpinnerLine(items);
    }
}
