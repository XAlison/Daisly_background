package com.icekredit.pojo;

/**
 * Created by root on 17-8-29.
 */
public class FormData {
    /** 键 */
    private String key;

    /**  值 */
    private String value;

    public FormData(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public FormData() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
