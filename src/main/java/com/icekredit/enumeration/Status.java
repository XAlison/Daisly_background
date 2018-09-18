package com.icekredit.enumeration;

public enum Status {

    SUCCESS(1, "成功"), FAILED(0, "失败");

    private int value;
    private String desc;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private Status(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}