package com.icekredit.enumeration;

public enum AssertionType {
    NONE("None"), HEADERS("Headers"), JSONBODY("JsonBody"), RESPONSESIZE("Response Size(bytes)"),
    RESPONSETIME("Response Time(ms)"), STATUSCODE("Status code"), TEXTBODY("Text Body"), XMLBODY("XML Body");
    private final String name;

    private AssertionType(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
