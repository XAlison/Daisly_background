package com.icekredit.service;

import com.icekredit.pojo.TestCase;
import com.icekredit.pojo.po.TestCaseResultPo;
import com.icekredit.pojo.bo.RequestBo;
import com.icekredit.pojo.po.TestCaseResultPoForTable;
import com.icekredit.pojo.vo.*;

import java.util.List;

public interface TestCaseService {
    List<TestCaseVoForTable> queryAllTestCase(String uid, String projectId);

    List<TestCaseResultPoForTable> querryAllTestCaseResultByTestCaseId(String testCaseId);

    TestCaseVo querryTestCaseById(String testCaseId);

    void saveTestCase(TestCaseVo testCaseVo);

    void saveRequest(TestCaseVo testCaseVo);

    void saveTestCaseResult(TestCaseResultPo TestCaseResultPo);


    List<ResultDetailVo> queryCaseResultDetail(String testCaseId);

    void delRequest(RequestVo requestVo);

    void updateTestCase(TestCaseVo testCaseVo);

    void updateRequest(TestCaseVo testCaseVo);

    void saveAndRun(TestCaseVo testCaseVo);

}
