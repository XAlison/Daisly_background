package com.icekredit.pojo;


public class Header {
    /* 头部key */
    private String key;

    /* 头部值 */
    private String value;

    public Header() {

    }

    public Header(String key, String value) {
        this.key = key;
        this.value = value;
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
