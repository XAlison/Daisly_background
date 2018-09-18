package com.icekredit.pojo;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 17-1-9.
 */
public class Tests implements Serializable {

    private static final long serialVersionUID = 7730319940675784477L;
    private String id;
    private String testName;
    private int runNum;
    private char isSucess;
    private int passedNum;
    private int failedNum;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private String remarks;
    private String delFlag;
    private char belongFlag;
    private String apiInfo;
    private String lastResult;
    private int sequence;

    public String getId() {
        return id;
    }

    public String getTestName() {
        return testName;
    }

    public int getRunNum() {
        return runNum;
    }

    public char getIsSucess() {
        return isSucess;
    }

    public int getPassedNum() {
        return passedNum;
    }

    public int getFailedNum() {
        return failedNum;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public char getBelongFlag() {
        return belongFlag;
    }

    public String getApiInfo() {
        return apiInfo;
    }

    public int getSequence() {
        return sequence;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
    }

    public void setIsSucess(char isSucess) {
        this.isSucess = isSucess;
    }

    public void setPassedNum(int passedNum) {
        this.passedNum = passedNum;
    }

    public void setFailedNum(int failedNum) {
        this.failedNum = failedNum;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setBelongFlag(char belongFlag) {
        this.belongFlag = belongFlag;
    }

    public void setApiInfo(String apiInfo) {
        this.apiInfo = apiInfo;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }
}
