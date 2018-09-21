package com.hewei.formblocks.blocks;

public interface BlockListener<T> {
    String EVENT_DATA_CHANGE = "_b_event_changed_";
    void onEvent(String event, T data);
}
