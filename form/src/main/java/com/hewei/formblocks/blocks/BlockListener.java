package com.hewei.formblocks.blocks;

public interface BlockListener<T> {
    String EVENT_DATA_CHANGE = "_b_event_data_changed_";
    String EVENT_QUERY_DATA = "_b_event_query_data_";

    void onEvent(String event, T data);
}
