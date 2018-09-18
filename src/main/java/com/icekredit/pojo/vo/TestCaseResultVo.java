package com.icekredit.pojo.vo;


import java.util.List;

public class TestCaseResultVo {
    private String id;
    private String testCaseId;
    private String collectionResultId;
    private String runDate;
    private String runBy;
    private String errorInfo;
    private char isSuccess;
    List<ResultVo> resultInfo;

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

    public List<ResultVo> getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(List<ResultVo> resultInfo) {
        this.resultInfo = resultInfo;
    }
}
