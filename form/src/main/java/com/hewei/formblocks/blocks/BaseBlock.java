package com.hewei.formblocks.blocks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.hewei.formblocks.data.ArgsProvider;
import com.hewei.formblocks.form.BaseForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public abstract class BaseBlock<T> {
    private BlockListener listener;
    private T data;
    private ArgsProvider argProvider;
    private String id;

    public BaseBlock() {

    }

    public BaseBlock(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setEventListener(BlockListener listener) {
        this.listener = listener;
    }

    public final void setData(T data) {
        setData(data, true);
    }

    public final void setData(T data, boolean updateUI) {
        this.data = data;
        onEvent(BlockListener.EVENT_DATA_CHANGE, data);
        if (updateUI) {
            onData(data);
        }
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return false;
    }

    protected  <K> void onEvent(String event, K data) {
        if (listener != null) {
            listener.onEvent(event, data);
        }
    }

    public T getData() {
        return data;
    }

    public String getArg(String key) {
        return argProvider != null ? argProvider.getArg(key) : null;
    }

    public String getArg(int position) {
        return argProvider != null ? argProvider.getArg(position) : null;
    }

    public void setArgProvider(ArgsProvider provider) {
        argProvider = provider;
    }

    public abstract void onData(T data);
    public abstract View getView(Context context, ViewGroup parentView);
}
