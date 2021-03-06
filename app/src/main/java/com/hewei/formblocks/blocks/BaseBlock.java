package com.hewei.formblocks.blocks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hewei.formblocks.data.DataListener;

import java.util.List;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public abstract class BaseBlock<T> {
    private DataListener<T> dataListener;
    private T data;
    private List<String> args;

    public void setDataListener(DataListener<T> listener) {
        dataListener = listener;
    }

    public final void setData(T data) {
        this.data = data;
        if (dataListener != null) {
            dataListener.onDataChanged(data);
        }
        onData(data);
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
