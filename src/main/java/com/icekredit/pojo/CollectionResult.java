package com.icekredit.pojo;

public class CollectionResult {
    private String id;
    private String collectionId;
    private String runBy;
    private String runDate;
    private String errorInfo;
    private char isSuccess;
    private int totalFailedTestCaseNum;
    private int totalSuccessTestCaseNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRunBy() {
        return runBy;
    }

    public void setRunBy(String runBy) {
        this.runBy = runBy;
    }

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
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

    public int getTotalFailedTestCaseNum() {
        return totalFailedTestCaseNum;
    }

    public void setTotalFailedTestCaseNum(int totalFailedTestCaseNum) {
        this.totalFailedTestCaseNum = totalFailedTestCaseNum;
    }

    public int getTotalSuccessTestCaseNum() {
        return totalSuccessTestCaseNum;
    }

    public void setTotalSuccessTestCaseNum(int totalSuccessTestCaseNum) {
        this.totalSuccessTestCaseNum = totalSuccessTestCaseNum;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }
}
