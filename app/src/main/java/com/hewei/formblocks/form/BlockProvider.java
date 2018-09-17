package com.hewei.formblocks.form;

import com.hewei.formblocks.blocks.BlockFactory;

import java.util.List;

/**
 * Created by fengyinpeng on 2018/9/17.
 *
 */
public interface BlockProvider {
    String getId();

    Class<?> getItemType();

    Class<? extends BlockFactory<?>> getFactory();

    int getSize();

    List<String> getArgs();
}
