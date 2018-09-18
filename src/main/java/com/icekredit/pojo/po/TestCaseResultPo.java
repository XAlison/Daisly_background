package com.icekredit.pojo.po;

public class TestCaseResultPo {
    private String id;
    private String testCaseId;
    private String collectionResultId;
    private String runDate;
    private String runBy;
    private String errorInfo;
    private String environmentId;
    private int totalAssertions;
    private int successAssertions;
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

    public String getCollectionResultId() {
        return collectionResultId;
    }

    public void setCollectionResultId(String collectionResultId) {
        this.collectionResultId = collectionResultId;
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

    public char getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(char isSuccess) {
        this.isSuccess = isSuccess;
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

    public int getSuccessAssertions() {
        return successAssertions;
    }

    public void setSuccessAssertions(int successAssertions) {
        this.successAssertions = successAssertions;
    }


}
