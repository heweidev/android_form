package com.hewei.formblocks.form;

import com.hewei.formblocks.annotations.Block;
import com.hewei.formblocks.blocks.BlockFactory;
import com.hewei.formblocks.blocks.factory.InvalidBlockFactory;
import com.hewei.formblocks.data.ArgsProvider;
import com.hewei.formblocks.data.ListArgProvider;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public class AnnotationProvider implements BlockProvider {
    private Block mAnnotation;
    private Class<?> mType;

    public AnnotationProvider(Block block, Class<?> type) {
        mAnnotation = block;
        mType = type;
    }

    @Override
    public String getId() {
        return mAnnotation.id();
    }

    @Override
    public Class<?> getItemType() {
        return mType;
    }

    @Override
    public Class<? extends BlockFactory<?>> getFactory() {
        Class<? extends BlockFactory<?>> factory = mAnnotation.factory();
        return InvalidBlockFactory.class.equals(factory) ? null : factory;
    }

    @Override
    public int getSize() {
        return mAnnotation.size();
    }

    @Override
    public ArgsProvider getArgs() {
        return new ListArgProvider(Arrays.asList(mAnnotation.args()));
    }
}
