package com.icekredit.pojo.po;

public class RequestPo {
    String id;
    String requestName;
    String requestType;
    String testCaseId;
    String api;
    String preRequestScript;
    String apiHeaders;
    String apiUrlParameter;
    String apiAssertions;
    String apiVariables;
    String apiBodies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getApiHeaders() {
        return apiHeaders;
    }

    public void setApiHeaders(String apiHeaders) {
        this.apiHeaders = apiHeaders;
    }

    public String getApiUrlParameter() {
        return apiUrlParameter;
    }

    public void setApiUrlParameter(String apiUrlParameter) {
        this.apiUrlParameter = apiUrlParameter;
    }

    public String getApiAssertions() {
        return apiAssertions;
    }

    public void setApiAssertions(String apiAssertions) {
        this.apiAssertions = apiAssertions;
    }

    public String getApiVariables() {
        return apiVariables;
    }

    public void setApiVariables(String apiVariables) {
        this.apiVariables = apiVariables;
    }

    public String getApiBodies() {
        return apiBodies;
    }

    public void setApiBodies(String apiBodies) {
        this.apiBodies = apiBodies;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getPreRequestScript() {
        return preRequestScript;
    }

    public void setPreRequestScript(String preRequestScript) {
        this.preRequestScript = preRequestScript;
    }
}
