package com.icekredit.pojo;

public class AssertionModel {
    private String name;
    private String onLineModelJsonPath;
    private String comparison;
    private String offLineModelJsonPath;
    private char isSuccess;
    private String failedReason;

    public AssertionModel(String name, String onLineModelJsonPath, String comparison, String offLineModelJsonPath){
        this.name = name;
        this.onLineModelJsonPath = onLineModelJsonPath;
        this.comparison = comparison;
        this.offLineModelJsonPath = offLineModelJsonPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnLineModelJsonPath() {
        return onLineModelJsonPath;
    }

    public void setOnLineModelJsonPath(String onLineModelJsonPath) {
        this.onLineModelJsonPath = onLineModelJsonPath;
    }

    public String getComparison() {
        return comparison;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    public String getOffLineModelJsonPath() {
        return offLineModelJsonPath;
    }

    public void setOffLineModelJsonPath(String offLineModelJsonPath) {
        this.offLineModelJsonPath = offLineModelJsonPath;
    }

    public char getIsSuccess() {
        return isSuccess;
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
}
