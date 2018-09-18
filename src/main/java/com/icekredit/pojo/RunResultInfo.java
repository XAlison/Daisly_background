package com.icekredit.pojo;

import com.alibaba.fastjson.JSONArray;

public class RunResultInfo {
    private String id;
    private String runModelId;
    private String resultOld;
    private String resultNew;
    private String scoreGap;
    private String oldSuggestion;
    private String newSuggestion;
    private String otherInfo;
    private String runDate;
    private String assertionsResult;
    private String modelData;
    private String oldModelScore;
    private String newModelScore;
    private char flag;

    public String getAssertionsResult() {
        return assertionsResult;
    }

    public void setAssertionsResult(String assertionsResult) {
        this.assertionsResult = assertionsResult;
    }

    public String getModelData() {
        return modelData;
    }

    public void setModelData(String modelData) {
        this.modelData = modelData;
    }

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public String getOldModelScore() {
        return oldModelScore;
    }

    public void setOldModelScore(String oldModelScore) {
        this.oldModelScore = oldModelScore;
    }

    public String getNewModelScore() {
        return newModelScore;
    }

    public void setNewModelScore(String newModelScore) {
        this.newModelScore = newModelScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRunModelId() {
        return runModelId;
    }

    public void setRunModelId(String runModelId) {
        this.runModelId = runModelId;
    }

    public String getResultOld() {
        return resultOld;
    }

    public void setResultOld(String resultOld) {
        this.resultOld = resultOld;
    }

    public String getResultNew() {
        return resultNew;
    }

    public void setResultNew(String resultNew) {
        this.resultNew = resultNew;
    }


    public String getOldSuggestion() {
        return oldSuggestion;
    }

    public void setOldSuggestion(String oldSuggestion) {
        this.oldSuggestion = oldSuggestion;
    }

    public String getNewSuggestion() {
        return newSuggestion;
    }

    public void setNewSuggestion(String newSuggestion) {
        this.newSuggestion = newSuggestion;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public char getFlag() {
        return flag;
    }

    public void setFlag(char flag) {
        this.flag = flag;
    }

    public String getScoreGap() {
        return scoreGap;
    }

    public void setScoreGap(String scoreGap) {
        this.scoreGap = scoreGap;
    }
}
