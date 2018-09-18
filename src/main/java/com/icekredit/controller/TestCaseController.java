package com.icekredit.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.examples.Utils;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.icekredit.enumeration.Status;
import com.icekredit.framework.JsonResult;
import com.icekredit.framework.auth.MyException;
import com.icekredit.framework.base.BaseController;
import com.icekredit.pojo.User;
import com.icekredit.pojo.bo.TestCaseBo;
import com.icekredit.pojo.po.TestCaseResultPoForTable;
import com.icekredit.pojo.vo.*;
import com.icekredit.service.RunTestCaseService;
import com.icekredit.service.TestCaseService;
import com.icekredit.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/case")
public class TestCaseController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private RunTestCaseService runTestCaseService;

    @GetMapping(value = "/allcases")
    @ResponseBody
    public JsonResult getAllCases(@RequestParam("projectId") String projectId){
        User user = Tools.getUser();
        List<TestCaseVoForTable> testCases = testCaseService.queryAllTestCase(user.getId(), projectId);
        return new JsonResult(Status.SUCCESS, testCases);
    }

    @GetMapping(value = "/get_testcase")
    @ResponseBody
    public JsonResult getTestCase(@RequestParam("testCaseId") String testCaseId){
        TestCaseVo testCaseVo = testCaseService.querryTestCaseById(testCaseId);
        return new JsonResult(Status.SUCCESS, testCaseVo);
    }


    @GetMapping(value = "/test_case_result")
    @ResponseBody
    public JsonResult getTestCaseResult(@RequestParam("testCaseId") String testCaseId){
        List<TestCaseResultPoForTable> testCases = testCaseService.querryAllTestCaseResultByTestCaseId(testCaseId);
        return new JsonResult(Status.SUCCESS, testCases);
    }

    @GetMapping(value = "/re_run_testcase")
    @ResponseBody
    public JsonResult reRunTestCase(@RequestParam("testCaseId") String testCaseId){
        runTestCaseService.reRunTestCase(testCaseId);
        return new JsonResult(Status.SUCCESS);
    }

    @GetMapping(value = "/result_detail")
    @ResponseBody
    public JsonResult getResultDetailByTestCaseResult(@RequestParam("testCaseResultId") String testCaseResultId){
        List<ResultDetailVo> listResultDetail = testCaseService.queryCaseResultDetail(testCaseResultId);
        return new JsonResult(Status.SUCCESS, listResultDetail);
    }

    @RequestMapping(value = "/addOrUpdate", produces="application/json; charset=utf-8")
    @ResponseBody
    public JsonResult saveTest(@RequestBody TestCaseVo data) throws InterruptedException {
        return new JsonResult(Status.SUCCESS);
    }

//    @PostMapping("/checkJsonSchema")
//    @RequestBody
//    public JsonResult checkJsonSchema(@RequestBody String jsonSchema){
//        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4).freeze()).freeze();
//    }

    @PostMapping("/saveandrun")
    @ResponseBody
    public JsonResult runAndSaveCase(@RequestBody TestCaseVo testCaseVo){
        if (MyString.isEmpty(testCaseVo.getId())){
            testCaseService.saveTestCase(testCaseVo);
            testCaseService.saveRequest(testCaseVo);
        }else {
            testCaseService.updateTestCase(testCaseVo);
            testCaseService.updateRequest(testCaseVo);
        }
        try {
            runTestCaseService.runTestCase(testCaseVo, Tools.getUser().getId());
        }catch (Exception e){
            JsonResult j = new JsonResult("000009");
            j.setData(testCaseVo);
            return j;
        }

        return new JsonResult(Status.SUCCESS, testCaseVo);
    }
}