package com.icekredit.pojo.vo;

import java.util.List;

public class TestCaseVo {
    private String id;
    private String projectId;
    private String remarks;
    private String environmentId;
    private String testCaseName;
    private List<RequestVo> requests;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
    }

    public List<RequestVo> getRequests() {
        return requests;
    }

    public void setRequests(List<RequestVo> requests) {
        this.requests = requests;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

}
