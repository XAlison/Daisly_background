package com.icekredit.service;


import com.icekredit.pojo.bo.RequestBo;
import com.icekredit.pojo.po.TestCaseResultPo;
import com.icekredit.pojo.vo.ResultVo;
import com.icekredit.pojo.vo.TestCaseVo;

public interface RunTestCaseService {
    /** 运行测试用例by id */
    void runTestCaseById(String testCaseId);

    /** 运行测试用例by 测试用例id, 环境变量id */
    TestCaseResultPo runTestCaseByIds(String testCaseId, String environmentISd, String uid);

    TestCaseVo getTestVoById(String testCaseId);

    /** 运行测试集合by id */
    Boolean runCollectionId(String collectionId, String uid);

    /**  运行request */
    ResultVo runRequest(RequestBo requestBo, String testCaseResultId);

    /** 允许TestCase */
    TestCaseResultPo runTestCase(TestCaseVo testCaseVo, String uid);

    /** 重新允许测试用例 */
    void reRunTestCase(String testCaseId);
}
