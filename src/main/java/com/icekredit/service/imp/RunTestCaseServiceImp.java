package com.icekredit.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icekredit.DaisyApplication;
import com.icekredit.apitest.AssertionExecutor;
import com.icekredit.apitest.RequestExecutor;
import com.icekredit.enumeration.TestCaseStatus;
import com.icekredit.mapper.mapperOffLine.*;
import com.icekredit.pojo.*;
import com.icekredit.pojo.bo.RequestBo;
import com.icekredit.pojo.bo.TestCaseBo;
import com.icekredit.pojo.po.*;
import com.icekredit.pojo.vo.EnvironmentVo;
import com.icekredit.pojo.vo.RequestVo;
import com.icekredit.pojo.vo.ResultVo;
import com.icekredit.pojo.vo.TestCaseVo;
import com.icekredit.service.AuthService;
import com.icekredit.service.RunTestCaseService;
import com.icekredit.utils.IdGen;
import com.icekredit.utils.MyString;
import com.icekredit.utils.Tools;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.icekredit.utils.ConverterVoPo.*;

@Service
public class RunTestCaseServiceImp implements RunTestCaseService {

    @Autowired
    TestCaseMapper testCaseMapper;

    @Autowired
    CollectionMapper collectionMapper;

    @Autowired
    TestCaseResultMapper testCaseResultMapper;

    @Autowired
    RequestResultMapper requestResultMapper;

    @Autowired
    EnvironmentsMapper environmentsMapper;

    @Autowired
    AuthService authService;

    private Logger logger = LoggerFactory.getLogger(RunTestCaseServiceImp.class);

    @Override
    public void runTestCaseById(String testCaseId) {
        TestCaseVo testCaseVo = getTestVoById(testCaseId);
        runTestCase(testCaseVo, Tools.getUser().getId());
    }

    @Override
    public TestCaseResultPo runTestCaseByIds(String testCaseId, String environmentId, String uid) {
        TestCaseVo testCaseVo = getTestVoById(testCaseId);
        if (!MyString.isEmpty(environmentId)){
            testCaseVo.setEnvironmentId(environmentId);
        }
        return runTestCase(testCaseVo, uid);
    }

    @Override
    public TestCaseVo getTestVoById(String testCaseId) {
        TestCasePo testCasePo = testCaseMapper.queryTestCaseVoByTestCaseId(testCaseId);
        List<RequestPo> requestPo = testCaseMapper.queryRequestByTestCaseId(testCaseId);
        List<RequestVo> listRequestVo = new ArrayList<>();
        requestPo.stream().forEach(x->{
            listRequestVo.add(requestPoToVo(x));
        });
        TestCaseVo testCaseVo = testCasePoToVo(testCasePo, listRequestVo);
        return testCaseVo;
    }

    @Override
    public ResultVo runRequest(RequestBo requestBo, String testCaseResultId) {
        //
        RequestExecutor requestExecutor = new RequestExecutor(requestBo);
        Response response = null;
        ResultVo rr = new ResultVo();
        try {
            response = requestExecutor.executeHttpRequest();
            logger.info("返回的值为{}", response.body().prettyPeek());
            List<Header> listHeader = new ArrayList<>();
            response.getHeaders().asList().forEach(x->{
                listHeader.add(new Header(x.getName(), x.getValue()));
            });
            rr.setTestCaseResultId(testCaseResultId);
            rr.setRequestId(requestBo.getRequestVo().getId());
            rr.setBodyType(Tools.checkStringFormat( toString()));
            rr.setBodySize(response.getBody().asByteArray().length);
            rr.setStatusCode(response.getStatusCode());
            rr.setBody(response.getBody().prettyPrint());
            rr.setBodyType(Tools.checkStringFormat(rr.getBody()));
            rr.setHeaders(listHeader);
            rr.setResponseTime(response.getTime());
            AssertionExecutor assertionExecutor = new AssertionExecutor(requestBo, response);
            assertionExecutor.executeHttpAssert();
            rr.setAssertions(requestBo.getAssertionParams());
        }catch (Exception e){
            rr.setErrorInfo(e.toString());
            rr.setRequestId(requestBo.getRequestVo().getId());
            rr.setTestCaseResultId(testCaseResultId);
            rr.setAssertions(new ArrayList<>());
            if (rr.getHeaders() == null) {
                rr.setHeaders(new ArrayList<>());
            }
        }
        requestResultMapper.saveRequestResult(requestResultVToPo(rr));
        return rr;
    }
   private void getVariables(EnvironmentVo environment, String body, RequestBo requestBo){
       ArrayList<RequestVariable> requestVariables = (ArrayList<RequestVariable>) requestBo.getVariables();
       for (int i=0; i<requestVariables.size(); ++i){
           try {
               environment.getInitVariables().add(new Variable(requestVariables.get(i).getName(), JsonPath.from(body).get(requestVariables.get(i).getProperty())));
           }catch (Exception e){

           }
       }
   }
    @Override
    public TestCaseResultPo runTestCase(TestCaseVo testCaseVo, String uid) {
        EnvironmentPo environmentPo = environmentsMapper.findById(testCaseVo.getEnvironmentId());
        EnvironmentVo  environment = null;
        if (environmentPo != null){
            environment = environmentPoToVo(environmentPo);
            if (!MyString.isEmpty(environment.getAuthInfo().getMyAuth().getAuthUrl())){
                String authId = authService.getTokenId(environment.getAuthInfo().getMyAuth());
                environment.getInitVariables().add(new Variable(environment.getAuthInfo().getMyAuth().getTokenName(), authId));
            }
        }
        TestCaseBo testCaseBo = testCaseVoToBo(testCaseVo, environment);
        final boolean[] failedFlag = {false};
        final int[] totalAssertions = {0};
        final int[] sucessAssertions = {0};
        TestCaseResultPo testCaseResult = new TestCaseResultPo();
        testCaseResult.setId(IdGen.uuid());
        testCaseResult.setEnvironmentId(testCaseVo.getEnvironmentId());
        testCaseResult.setRunBy(uid);
        testCaseResult.setTestCaseId(testCaseBo.getId());
        testCaseResult.setIsSuccess(TestCaseStatus.SUCCESS.getValue());
        EnvironmentVo finalEnvironment = environment;
        testCaseBo.getRequests().stream().forEach(x->{
            ResultVo r = runRequest(x, testCaseResult.getId());
            try {
                getVariables(finalEnvironment, r.getBody(), x);
            }catch (Exception e){

            }
            if (!MyString.isEmpty(r.getErrorInfo())){
                failedFlag[0] = true;
                testCaseResult.setIsSuccess(TestCaseStatus.FAILED.getValue());
                return;
            }
            List<Assertion> assertions = r.getAssertions();
            totalAssertions[0] += assertions.size();
            for (int ii=0; ii<assertions.size(); ++ii){
                if (assertions.get(ii).getFailedReason() != null) {
                    testCaseResult.setIsSuccess(TestCaseStatus.FAILED.getValue());
                    failedFlag[0] = true;
                }else {
                    sucessAssertions[0] += 1;
                }
            }
        });
        testCaseResult.setTotalAssertions(totalAssertions[0]);
        testCaseResult.setSuccessAssertions(sucessAssertions[0]);
        /** 保存测试结果 */
        testCaseResultMapper.saveTestCaseResultMapper(testCaseResult);
        if (failedFlag[0]){
            testCaseMapper.updateTestCaseStatus(testCaseVo.getId(), TestCaseStatus.FAILED.getValue());
        }else {
            testCaseMapper.updateTestCaseStatus(testCaseVo.getId(), TestCaseStatus.SUCCESS.getValue());
        }
        return testCaseResult;
    }

    @Override
    public void reRunTestCase(String testCaseId) {
        TestCasePo testCasePo = testCaseMapper.queryTestCaseVoByTestCaseId(testCaseId);
        List<RequestPo> requestPo = testCaseMapper.queryRequestByTestCaseId(testCaseId);
        List<RequestVo> listRequestVo = new ArrayList<>();
        requestPo.stream().forEach(x->{
            listRequestVo.add(requestPoToVo(x));
        });
        TestCaseVo testCaseVo = testCasePoToVo(testCasePo, listRequestVo);
        runTestCase(testCaseVo, Tools.getUser().getId());
    }

    @Override
    public Boolean runCollectionId(String collectionId, String uid) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Boolean flagSuccess = true;
        CollectionPo collectionPo = collectionMapper.queryCollection(collectionId, uid);
        String environmentId = collectionPo.getEnvironmentId();
        JSONArray testCases = JSONArray.parseArray(collectionPo.getTestCases());
        CollectionResult collectionResult = new CollectionResult();
        collectionResult.setCollectionId(collectionId);
        collectionResult.setId(IdGen.uuid());
        collectionResult.setRunBy(uid);
        collectionMapper.addCollectionResult(collectionResult);
        testCases.stream().forEach(x->{
            executorService.execute(()->{
                TestCaseResultPo testCaseResult = runTestCaseByIds((String) x, environmentId, uid);
                testCaseResultMapper.updateTestCaseResultCollectionResultId(testCaseResult.getId(), collectionResult.getId());
            });
        });
        executorService.shutdown();
        while(true){
            if(executorService.isTerminated()){
                List<TestCaseResultPo> listTestCaseResult = testCaseResultMapper.queryTestCaseResultByCollectionResultId(collectionResult.getId());
                int totalSuccessTestCaseNum = 0;
                int totalFailedTestCaseNum = 0;
                for (int i=0; i<listTestCaseResult.size(); ++i){
                    if (listTestCaseResult.get(i).getIsSuccess() == TestCaseStatus.FAILED.getValue()){
                        flagSuccess = false;
                        totalFailedTestCaseNum++;
                    }else {
                        totalSuccessTestCaseNum++;
                    }
                }
                collectionResult.setTotalFailedTestCaseNum(totalFailedTestCaseNum);
                collectionResult.setTotalSuccessTestCaseNum(totalSuccessTestCaseNum);
                if (flagSuccess == true){
                    collectionResult.setIsSuccess(TestCaseStatus.SUCCESS.getValue());
                }else {
                    collectionResult.setIsSuccess(TestCaseStatus.FAILED.getValue());
                }
                collectionMapper.updateCollectionResultStatus(collectionResult);
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
        return flagSuccess;
    }
}
