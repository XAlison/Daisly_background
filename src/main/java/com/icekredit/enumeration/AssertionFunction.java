package com.icekredit.enumeration;

public enum AssertionFunction {
    NONE("None"), EQUALS("equals"), DOESNOTEQUAL("does not equal"), ISEMPTY("is empty"), ISNOTEMPTY("is not empty"),
    CONTAINS("contains"), DOESNOTCONTAINS("does not contains"), ISANUM("is a num"), EQUALSNUM("equals(number)"), LESSTHAN("less than"),
    LESSTHANOREQUAL("less than or equal"), GREATERTHAN("greater than"), GREATERTHANOREQUAL("greater than or equal");

    private final String name;

    private AssertionFunction(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

}
