package com.icekredit.pojo.vo;

import com.icekredit.pojo.Assertion;
import com.icekredit.pojo.Header;

import java.util.List;

public class ResultDetailVo {
    private String requestName;
    private String requestType;
    private String api;
    private String errorInfo;
    private String requestResultId;
    private long responseTime;
    private String testCaseResultId;
    private String requestId;
    private int bodySize;
    private String bodyType;
    private int statusCode;
    private List<Header> headers;
    private List<Assertion> assertions;
    private String body;

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

    public String getRequestResultId() {
        return requestResultId;
    }

    public void setRequestResultId(String requestResultId) {
        this.requestResultId = requestResultId;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getTestCaseResultId() {
        return testCaseResultId;
    }

    public void setTestCaseResultId(String testCaseResultId) {
        this.testCaseResultId = testCaseResultId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getBodySize() {
        return bodySize;
    }

    public void setBodySize(int bodySize) {
        this.bodySize = bodySize;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public List<Assertion> getAssertions() {
        return assertions;
    }

    public void setAssertions(List<Assertion> assertions) {
        this.assertions = assertions;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
