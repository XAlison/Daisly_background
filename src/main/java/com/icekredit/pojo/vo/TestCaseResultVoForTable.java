package com.icekredit.pojo.vo;


import java.util.List;

public class TestCaseResultVoForTable {
    private String id;
    private String testCaseId;
    private String testCaseName;
    private String collectionIResultd;
    private String collectionName;
    private String runDate;
    private String runBy;
    private String errorInfo;
    private String environmentId;
    private String environmentName;
    private int totalAssertions;
    private int totalSuccessAssertions;
    private char isSuccess;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getCollectionIResultd() {
        return collectionIResultd;
    }

    public void setCollectionIResultd(String collectionIResultd) {
        this.collectionIResultd = collectionIResultd;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public String getRunBy() {
        return runBy;
    }

    public void setRunBy(String runBy) {
        this.runBy = runBy;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
    }

    public int getTotalAssertions() {
        return totalAssertions;
    }

    public void setTotalAssertions(int totalAssertions) {
        this.totalAssertions = totalAssertions;
    }

    public int getTotalSuccessAssertions() {
        return totalSuccessAssertions;
    }

    public void setTotalSuccessAssertions(int totalSuccessAssertions) {
        this.totalSuccessAssertions = totalSuccessAssertions;
    }

    public char getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(char isSuccess) {
        this.isSuccess = isSuccess;
    }


    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }
}
