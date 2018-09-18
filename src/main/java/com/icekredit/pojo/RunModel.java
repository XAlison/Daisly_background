package com.icekredit.pojo;

public class RunModel {
    private String id;
    private String runModelName;
    private String dataNum;
    private String modelConfigId;
    private String createDate;
    private String uid;
    private char flag;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public char getFlag() {
        return flag;
    }

    public void setFlag(char flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRunModelName() {
        return runModelName;
    }

    public void setRunModelName(String runModelName) {
        this.runModelName = runModelName;
    }

    public String getDataNum() {
        return dataNum;
    }

    public void setDataNum(String dataNum) {
        this.dataNum = dataNum;
    }

    public String getModelConfigId() {
        return modelConfigId;
    }

    public void setModelConfigId(String modelConfigId) {
        this.modelConfigId = modelConfigId;
    }
}
