package com.icekredit.pojo.vo;


import com.icekredit.pojo.Assertion;
import com.icekredit.pojo.Header;

import java.util.Collection;
import java.util.List;


public class ResultVo {
    private String id;
    private long responseTime;
    private String testCaseResultId;
    private String errorInfo;
    private String requestId;
    private int bodySize;
    private String bodyType;
    private int statusCode;
    private List<Header> headers;
    private List<Assertion> assertions;
    private String body;

    public ResultVo(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Assertion> getAssertions() {
        return assertions;
    }

    public void setAssertions(Collection<Assertion> assertions) {
        this.assertions = (List<Assertion>) assertions;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setAssertions(List<Assertion> assertions) {
        this.assertions = assertions;
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

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
