package com.hewei.formblocks.blocks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public abstract class BaseBlock<T> {
    private BlockListener listener;
    private T data;
    private List<String> args;

    public void setEventListener(BlockListener listener) {
        this.listener = listener;
    }

    public final void setData(T data) {
        this.data = data;
        onEvent(BlockListener.EVENT_DATA_CHANGE, data);
        onData(data);
    }

    public <K> void onEvent(String event, K data) {
        if (listener != null) {
            listener.onEvent(event, data);
        }
    }

    public T getData() {
        return data;
    }


    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public abstract void onData(T data);
    public abstract View getView(Context context, ViewGroup parentView);
}
