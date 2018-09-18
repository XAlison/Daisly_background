package com.icekredit.pojo;


public class Variable {
    /* id */
    private String id;
    /* 变量名 */
    private String name;

    /* 变量值 */
    private String value;
    public Variable (){

    }

    public Variable (String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
