package com.icekredit.pojo.vo;


import com.icekredit.pojo.*;

import java.util.List;

public class RequestVo {
    String id;
    String requestName;
    String requestType;
    String preRequestScript;
    String api;
    List<Header> apiHeaders;
    List<UrlParameter> apiUrlParameter;
    List<Assertion>  apiAssertions;
    List<RequestVariable> apiVariables;
    ApiBodies apiBodies;

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

    public List<Header> getApiHeaders() {
        return apiHeaders;
    }

    public void setApiHeaders(List<Header> apiHeaders) {
        this.apiHeaders = apiHeaders;
    }

    public List<UrlParameter> getApiUrlParameter() {
        return apiUrlParameter;
    }

    public void setApiUrlParameter(List<UrlParameter> apiUrlParameter) {
        this.apiUrlParameter = apiUrlParameter;
    }

    public List<Assertion> getApiAssertions() {
        return apiAssertions;
    }

    public void setApiAssertions(List<Assertion> apiAssertions) {
        this.apiAssertions = apiAssertions;
    }

    public List<RequestVariable> getApiVariables() {
        return apiVariables;
    }

    public void setApiVariables(List<RequestVariable> apiVariables) {
        this.apiVariables = apiVariables;
    }

    public ApiBodies getApiBodies() {
        return apiBodies;
    }

    public void setApiBodies(ApiBodies apiBodies) {
        this.apiBodies = apiBodies;
    }

    public String getPreRequestScript() {
        return preRequestScript;
    }

    public void setPreRequestScript(String preRequestScript) {
        this.preRequestScript = preRequestScript;
    }
}
