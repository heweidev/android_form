package com.hewei.formblocks.data;

public interface Editable<T> {
    void set(T t);
    T get();
}
