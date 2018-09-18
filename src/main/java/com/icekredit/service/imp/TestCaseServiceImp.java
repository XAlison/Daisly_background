package com.icekredit.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icekredit.apitest.AssertionExecutor;
import com.icekredit.apitest.RequestExecutor;
import com.icekredit.enumeration.TestCaseStatus;
import com.icekredit.mapper.mapperOffLine.RequestResultMapper;
import com.icekredit.mapper.mapperOffLine.TestCaseMapper;
import com.icekredit.mapper.mapperOffLine.TestCaseResultMapper;
import com.icekredit.pojo.*;
import com.icekredit.pojo.po.*;
import com.icekredit.pojo.vo.*;
import com.icekredit.pojo.bo.RequestBo;
import com.icekredit.pojo.bo.TestCaseBo;
import com.icekredit.service.TestCaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.icekredit.utils.ConverterVoPo.*;

@Service
public class TestCaseServiceImp implements TestCaseService{

    @Autowired
    TestCaseMapper testCaseMapper;

    @Autowired
    TestCaseResultMapper testCaseResultMapper;

    @Autowired
    RequestResultMapper requestResultMapper;

    @Override
    public List<TestCaseVoForTable> queryAllTestCase(String uid, String projectId) {
        return testCaseMapper.queryAllTestCase(uid, projectId);
    }

    @Override
    public List<TestCaseResultPoForTable> querryAllTestCaseResultByTestCaseId(String testCaseId) {
        List<TestCaseResultPoForTable> listTestCaseResult = testCaseResultMapper.queryTestCaseResultByTestCaseId(testCaseId);
        return listTestCaseResult;
    }

    @Override
    public TestCaseVo querryTestCaseById(String testCaseId) {
        TestCasePo testCasePo = testCaseMapper.queryTestCaseVoByTestCaseId(testCaseId);
        List<RequestPo> listRequestPo = testCaseMapper.queryRequestByTestCaseId(testCaseId);

        TestCaseVo testCaseVo = new TestCaseVo();
        List<RequestVo> listRequestVo = new ArrayList<>();
        listRequestPo.stream().forEach(x->{
            listRequestVo.add(requestPoToVo(x));
        });
        testCaseVo.setRequests(listRequestVo);
        testCaseVo.setId(testCasePo.getId());
        testCaseVo.setTestCaseName(testCasePo.getTestCaseName());
        testCaseVo.setRemarks(testCasePo.getRemarks());
        testCaseVo.setProjectId(testCasePo.getProjectId());
        testCaseVo.setEnvironmentId(testCasePo.getEnvironmentId());
        return testCaseVo;
    }

    @Override
    public void saveTestCase(TestCaseVo testCaseVo) {
        TestCasePo testCasePo = testCaseVoToPo(testCaseVo);
        testCaseMapper.saveTestCase(testCasePo);
    }

    @Override
    public void updateTestCase(TestCaseVo testCaseVo) {
        TestCasePo testCasePo = testCaseVoToPo(testCaseVo);
        testCaseMapper.updateTestCase(testCasePo);
    }

    @Override
    public void updateRequest(TestCaseVo testCaseVo) {
        testCaseVo.getRequests().stream().forEach(x->{
            RequestPo requestPo = requestVoToPo(x);
            requestPo.setTestCaseId(testCaseVo.getId());
            testCaseMapper.updateRequest(requestPo);
        });
    }


    @Override
    public void saveRequest(TestCaseVo testCaseVo) {
        testCaseVo.getRequests().stream().forEach(x->{
            RequestPo requestPo = requestVoToPo(x);
            requestPo.setTestCaseId(testCaseVo.getId());
            testCaseMapper.saveRequest(requestPo);
        });

    }

    @Override
    public void saveTestCaseResult(TestCaseResultPo testCaseResultPo) {
        testCaseResultMapper.saveTestCaseResultMapper(testCaseResultPo);
    }


    @Override
    public List<ResultDetailVo> queryCaseResultDetail(String testCaseId) {
        List<ResultDetailPo>  resultDetailPo =  requestResultMapper.queryRequestResultDetail(testCaseId);
        return resultDetailPoToVo(resultDetailPo);
    }

    @Override
    public void delRequest(RequestVo requestVo) {

    }

    @Override
    public void saveAndRun(TestCaseVo testCaseVo) {
        /** 保存测试用例 */
        saveTestCase(testCaseVo);
    }

}
