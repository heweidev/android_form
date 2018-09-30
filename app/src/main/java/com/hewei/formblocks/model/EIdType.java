package com.hewei.formblocks.model;

import com.hewei.formblocks.data.ListItem;

public enum EIdType implements ListItem {
    ID("身份证"), PASSPORT("护照");

    private String desc;

    EIdType(String desc) {
        this.desc = desc;
    }

    @Override
    public String getId() {
        return name();
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
