package com.hewei.formblocks.data;

/**
 * Created by fengyinpeng on 2018/9/17.
 */
public interface DataProvider<T> {
    T getData(String key);
}
