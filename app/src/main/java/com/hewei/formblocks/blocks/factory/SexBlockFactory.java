package com.hewei.formblocks.blocks.factory;

import com.hewei.formblocks.blocks.BlockFactory;
import com.hewei.formblocks.blocks.SpinnerLine;
import com.hewei.formblocks.data.ListItem;

public class SexBlockFactory implements BlockFactory<SpinnerLine> {
    public static ListItem MALE = new SpinnerLine.SimpleListItem("male", "男");
    public static ListItem FEMALE = new SpinnerLine.SimpleListItem("female", "女");

    @Override
    public SpinnerLine create() {
        return new SpinnerLine(new ListItem[] {
                MALE, FEMALE,
        });
    }
}
