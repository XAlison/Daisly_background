package com.icekredit.enumeration;

public enum TestCaseStatus {

    SUCCESS('1', "成功"), FAILED('0', "失败");

    private char value;
    private String desc;

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private TestCaseStatus(char value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}