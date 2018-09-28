package com.hewei.formblocks.blocks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * touch the block, can start activity or dialog
 * @param <T>
 */
public class HandleBlock<T> extends BaseBlock<T> {
    @Override
    public void onData(T data) {

    }

    @Override
    public View getView(Context context, ViewGroup parentView) {
        return null;
    }

    public void queryData() {
        onEvent(BlockListener.EVENT_QUERY_DATA, getId());
    }
}
