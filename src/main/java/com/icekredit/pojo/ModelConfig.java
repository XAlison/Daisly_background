package com.icekredit.pojo;

import java.io.Serializable;

public class ModelConfig implements Serializable {

    private static final long serialVersionUID = 7730319940675784477L;
    private String id;
    private String modelName;
    private String function;
    private String className;
    private String interface2model;
    private String scorePath;
    private String modelDataResource;
    private String uid;
    private String result;
    private String suggestionPath;
    private String modelAuthConfigId;
    private String assertions;
    private int serviceId;
    private int scoreGap;

    public String getModelAuthConfigId() {
        return modelAuthConfigId;
    }

    public void setModelAuthConfigId(String modelAuthConfigId) {
        this.modelAuthConfigId = modelAuthConfigId;
    }

    public String getAssertions() {
        return assertions;
    }

    public void setAssertions(String assertions) {
        this.assertions = assertions;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getModelDataResource() {
        return modelDataResource;
    }

    public void setModelDataResource(String modelDataResource) {
        this.modelDataResource = modelDataResource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getInterface2model() {
        return interface2model;
    }

    public void setInterface2model(String interface2model) {
        this.interface2model = interface2model;
    }

    public String getScorePath() {
        return scorePath;
    }

    public void setScorePath(String scorePath) {
        this.scorePath = scorePath;
    }

    public String getSuggestionPath() {
        return suggestionPath;
    }

    public void setSuggestionPath(String suggestionPath) {
        this.suggestionPath = suggestionPath;
    }

    public int getScoreGap() {
        return scoreGap;
    }

    public void setScoreGap(int scoreGap) {
        this.scoreGap = scoreGap;
    }
}
