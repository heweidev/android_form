package com.hewei.formblocks.blocks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseBlock<T> {
    T data;
    DataListener<T> dataListener;

    public T getData() {
        return data;
    }

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

    public abstract void onData(T data);
    public abstract View getView(Context context, ViewGroup parentView);
}
