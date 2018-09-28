package com.hewei.formblocks.model;

import com.hewei.formblocks.blocks.SpinnerLine;

public enum EIdType implements SpinnerLine.SpinnerItem {
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
