package com.icekredit.pojo;

public class Assertion {
    private String name;
    private String source;
    private String property;
    private String comparison;
    private String targetValue;
    private char isSuccess;
    private String failedReason;

    public Assertion(String name, String source, String property, String comparison, String targetValue){
        this.name = name;
        this.source = source;
        this.property = property;
        this.comparison = comparison;
        this.targetValue = targetValue;
    }
    public Assertion(){

    }
    public String getName() {
        return name;
    }

    public String getProperty() {
        return property;
    }

    public String getComparison() {
        return comparison;
    }

    public String getTargetValue() {
        return targetValue;
    }

    public char getIsSuccess() {
        return isSuccess;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public void setIsSuccess(char isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
