package com.icekredit.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icekredit.pojo.*;
import com.icekredit.pojo.bo.RequestBo;
import com.icekredit.pojo.bo.TestCaseBo;
import com.icekredit.pojo.po.*;
import com.icekredit.pojo.vo.*;

import java.util.ArrayList;
import java.util.List;

public class ConverterVoPo {

    public static TestCasePo testCaseVoToPo(TestCaseVo testCaseVo){
        TestCasePo testCasePo = new TestCasePo();
        if (MyString.isEmpty(testCaseVo.getId())){
            testCasePo.setId(IdGen.uuid());
            testCaseVo.setId(testCasePo.getId());
            testCasePo.setCreateBy(Tools.getUser().getId());
        }else {
            testCasePo.setId(testCaseVo.getId());
        }
        testCasePo.setProjectId(testCaseVo.getProjectId());
        testCasePo.setEnvironmentId(testCaseVo.getEnvironmentId());
        testCasePo.setRemarks(testCaseVo.getRemarks());
        testCasePo.setTestCaseName(testCaseVo.getTestCaseName());
        return testCasePo;
    }

    public static RequestVo requestPoToVo(RequestPo requestPo){
        RequestVo requestVo = new RequestVo();
        requestVo.setId(requestPo.getId());
        requestVo.setRequestType(requestPo.getRequestType());
        requestVo.setRequestName(requestPo.getRequestName());
        requestVo.setApi(requestPo.getApi());
        requestVo.setApiBodies(JSONObject.parseObject(requestPo.getApiBodies(), ApiBodies.class));
        requestVo.setApiAssertions(JSONArray.parseArray(requestPo.getApiAssertions(), Assertion.class));
        requestVo.setApiHeaders(JSONArray.parseArray(requestPo.getApiHeaders(), Header.class));
        requestVo.setApiUrlParameter(JSONArray.parseArray(requestPo.getApiUrlParameter(), UrlParameter.class));
        requestVo.setApiVariables(JSONArray.parseArray(requestPo.getApiVariables(), RequestVariable.class));
        return requestVo;
    }

    public static TestCaseVo testCasePoToVo(TestCasePo testCasePo, List<RequestVo> requestVo){
        TestCaseVo testCaseVo = new TestCaseVo();
        testCaseVo.setId(testCasePo.getId());
        testCaseVo.setEnvironmentId(testCasePo.getEnvironmentId());
        testCaseVo.setProjectId(testCasePo.getProjectId());
        testCaseVo.setRemarks(testCasePo.getRemarks());
        testCaseVo.setTestCaseName(testCasePo.getTestCaseName());
        testCaseVo.setRequests(requestVo);
        return testCaseVo;
    }

    public static RequestPo requestVoToPo(RequestVo requestVo) {
        RequestPo requestPo = new RequestPo();
        if (!MyString.isEmpty(requestVo.getId())){
            requestPo.setId(requestVo.getId());

        }else{
            String id = IdGen.uuid();
            requestPo.setId(id);
            requestVo.setId(id);
        }
        requestPo.setApi(requestVo.getApi());
        requestPo.setPreRequestScript(requestVo.getPreRequestScript());
        requestPo.setRequestName(requestVo.getRequestName());
        requestPo.setRequestType(requestVo.getRequestType());
        requestPo.setApiAssertions(JSON.toJSONString(requestVo.getApiAssertions()));
        requestPo.setApiBodies(JSON.toJSONString(requestVo.getApiBodies()));
        requestPo.setApiHeaders(JSON.toJSONString(requestVo.getApiHeaders()));
        requestPo.setApiUrlParameter(JSON.toJSONString(requestVo.getApiUrlParameter()));
        requestPo.setApiVariables(JSON.toJSONString(requestVo.getApiVariables()));
        return requestPo;
    }

    public static TestCaseResultPo testCaseResultVoToPo(TestCaseResultVo testCaseResultVo){
        TestCaseResultPo testCaseResultPo = new TestCaseResultPo();
        testCaseResultPo.setTestCaseId(testCaseResultVo.getTestCaseId());
        testCaseResultPo.setId(testCaseResultVo.getId());
        testCaseResultPo.setIsSuccess(testCaseResultVo.getIsSuccess());
        testCaseResultPo.setRunBy(testCaseResultVo.getRunBy());
        testCaseResultPo.setCollectionResultId(testCaseResultVo.getCollectionResultId());
        testCaseResultPo.setErrorInfo(testCaseResultVo.getErrorInfo());
        return testCaseResultPo;
    }

    public static TestCaseBo testCaseVoToBo(TestCaseVo testCaseVo, EnvironmentVo environment){
        TestCaseBo testCaseBo = new TestCaseBo();
        testCaseBo.setId(testCaseVo.getId());
        testCaseBo.setEnvironmentId(testCaseVo.getEnvironmentId());
        testCaseBo.setProjectId(testCaseVo.getProjectId());
        testCaseBo.setRemarks(testCaseVo.getRemarks());
        testCaseBo.setTestCaseName(testCaseVo.getTestCaseName());
        List<RequestBo> testRequestBoList = new ArrayList<>();
        testCaseVo.getRequests().stream().forEach(x->{
            testRequestBoList.add(new RequestBo(x, environment));
        });
        testCaseBo.setRequests(testRequestBoList);
        return testCaseBo;
    }

    public static ResultPo requestResultVToPo(ResultVo resultVo){
        ResultPo resultPo = new ResultPo();
        String id = IdGen.uuid();
        resultPo.setId(id);
        resultVo.setId(id);
        resultPo.setRequestId(resultVo.getRequestId());
        resultPo.setTestCaseResultId(resultVo.getTestCaseResultId());
        resultPo.setAssertions(JSON.toJSONString(resultVo.getAssertions()));
        resultPo.setBody(resultVo.getBody());
        resultPo.setBodySize(resultVo.getBodySize());
        resultPo.setBodyType(resultVo.getBodyType());
        resultPo.setHeaders(JSON.toJSONString(resultVo.getHeaders()));
        resultPo.setStatusCode(resultVo.getStatusCode());
        resultPo.setResponseTime(resultVo.getResponseTime());
        resultPo.setErrorInfo(resultVo.getErrorInfo());
        return resultPo;
    }

    public static List<ResultDetailVo> resultDetailPoToVo(List<ResultDetailPo> listResultDetailPo){
        List<ResultDetailVo> listResultDetailVo = new ArrayList<>();
        listResultDetailPo.stream().forEach(x->{
            ResultDetailVo resultDetailVo = new ResultDetailVo();
            resultDetailVo.setResponseTime(x.getResponseTime());
            resultDetailVo.setStatusCode(x.getStatusCode());
            resultDetailVo.setBodyType(x.getBodyType());
            resultDetailVo.setApi(x.getApi());
            resultDetailVo.setErrorInfo(x.getErrorInfo());
            resultDetailVo.setBodySize(x.getBodySize());
            resultDetailVo.setBody(x.getBody());
            resultDetailVo.setRequestResultId(x.getRequestResultId());
            resultDetailVo.setRequestId(x.getRequestId());
            resultDetailVo.setRequestType(x.getRequestType());
            resultDetailVo.setRequestName(x.getRequestName());
            resultDetailVo.setAssertions(JSONArray.parseArray(x.getAssertions(), Assertion.class));
            resultDetailVo.setHeaders(JSONArray.parseArray(x.getHeaders(), Header.class));
            listResultDetailVo.add(resultDetailVo);
        });
        return listResultDetailVo;
    }

    public static List<EnvironmentVo> environmentPoToVoList(List<EnvironmentPo> listPo){
        List<EnvironmentVo> listVo = new ArrayList<>();
        listPo.stream().forEach(x->{
            EnvironmentVo vo = new EnvironmentVo();
            vo.setId(x.getId());
            vo.setProjectId(x.getProjectId());
            vo.setEnvironmentName(x.getEnvironmentName());
            vo.setUpdateBy(x.getUpdateBy());
            vo.setCreateBy(x.getCreateBy());
            vo.setApiHeaders(JSONObject.parseArray(x.getApiHeaders(), Header.class));
            vo.setInitVariables(JSONObject.parseArray(x.getInitVariables(), Variable.class));
            vo.setApiUrlParameter(JSONObject.parseArray(x.getApiUrlParameter(), UrlParameter.class));
            vo.setAuthInfo(JSONObject.parseObject(x.getAuthInfo(), AuthInfo.class));
            listVo.add(vo);
        });
        return listVo;
    }
    public static EnvironmentVo environmentPoToVo(EnvironmentPo e){
        EnvironmentVo vo = new EnvironmentVo();
        vo.setId(e.getId());
        vo.setProjectId(e.getProjectId());
        vo.setEnvironmentName(e.getEnvironmentName());
        vo.setUpdateBy(e.getUpdateBy());
        vo.setCreateBy(e.getCreateBy());
        vo.setApiHeaders(JSONObject.parseArray(e.getApiHeaders(), Header.class));
        vo.setInitVariables(JSONObject.parseArray(e.getInitVariables(), Variable.class));
        vo.setApiUrlParameter(JSONObject.parseArray(e.getApiUrlParameter(), UrlParameter.class));
        vo.setAuthInfo(JSONObject.parseObject(e.getAuthInfo(), AuthInfo.class));
        return vo;
    }

    public static EnvironmentPo environmentVoToPo(EnvironmentVo environmentVo){
        EnvironmentPo environmentPo = new EnvironmentPo();
        JSONArray headerArray = new JSONArray();
        JSONArray variableArray = new JSONArray();
        JSONArray urlArray = new JSONArray();
        environmentPo.setId(environmentVo.getId());
        environmentPo.setProjectId(environmentVo.getProjectId());
        environmentPo.setEnvironmentName(environmentVo.getEnvironmentName());
        environmentPo.setCreateBy(environmentVo.getCreateBy());
        environmentPo.setCreateDate(environmentVo.getCreateDate());
        environmentPo.setUpdateBy(environmentVo.getUpdateBy());
        environmentPo.setUpdateDate(environmentVo.getUpdateDate());
        headerArray.addAll(environmentVo.getApiHeaders());
        variableArray.addAll(environmentVo.getInitVariables());
        urlArray.addAll(environmentVo.getApiUrlParameter());
        environmentPo.setApiHeaders(JSON.toJSONString(headerArray));
        environmentPo.setInitVariables(JSON.toJSONString(variableArray));
        environmentPo.setApiUrlParameter(JSON.toJSONString(urlArray));
        environmentPo.setAuthInfo(JSON.toJSONString(environmentVo.getAuthInfo()));
        return environmentPo;
    }
}
