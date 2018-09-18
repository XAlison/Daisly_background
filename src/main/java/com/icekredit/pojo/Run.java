package com.icekredit.pojo;

import java.io.Serializable;
import java.util.Date;

public class Run implements Serializable {
    private String id;
    private String testName;
    private Date runDate;
    private String runBy;
    private  char isSucess;
    private String resultInfo;
    private String collection;

    public String getId() {
        return id;
    }

    public String getTestName() {
        return testName;
    }

    public Date getRunDate() {
        return runDate;
    }

    public String getRunBy() {
        return runBy;
    }

    public char getIsSucess() {
        return isSucess;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public String getCollection() {
        return collection;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }

    public void setRunBy(String runBy) {
        this.runBy = runBy;
    }

    public void setIsSucess(char isSucess) {
        this.isSucess = isSucess;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
