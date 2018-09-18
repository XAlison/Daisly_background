package com.icekredit.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icekredit.enumeration.Status;
import com.icekredit.framework.JsonResult;
import com.icekredit.pojo.TestSuit;
import com.icekredit.pojo.User;
import com.icekredit.service.TestSuitService;
import com.icekredit.utils.IdGen;
import com.icekredit.utils.MyString;
import com.icekredit.utils.PageResult;
import com.icekredit.utils.Tools;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test_suit")
public class TestSuitController {
    @Autowired
    private TestSuitService testSuitService;

    Logger logger = LoggerFactory.getLogger(TestSuitController.class);

    @ApiOperation(value="获取测试集合列表", notes="根据page和key以及用户ID获取环境变量列表")
    @RequestMapping(value = "/listpage", produces="application/json; charset=utf-8")
    @ResponseBody
    public JsonResult getTestSuits(@RequestBody JSONObject obj) throws InterruptedException {
        User user = Tools.getUser();
        PageResult<TestSuit> p;
        try{
            p = testSuitService.queryByPage(obj.getIntValue("page"), 20, obj.getString("testSuitName"), user.getId());
        }catch (Exception e){
            logger.error(e.toString());
            return new JsonResult("000001");
        }

        return new JsonResult(Status.SUCCESS, p);
    }

    @RequestMapping(value = "/addOrUpdate", produces="application/json; charset=utf-8")
    @ResponseBody
    public JsonResult addTestSuit(@RequestBody JSONObject obj){
        TestSuit testSuit = new TestSuit();
        testSuit.setProjectName(obj.getString("projectName"));
        testSuit.setCasesId(JSON.toJSONString(obj.getJSONArray("casesId")));
        testSuit.setTestSuitName(obj.getString("testSuitName"));
        testSuit.setRemarks(obj.getString("remarks"));
        User user = Tools.getUser();
        try {
            // 修改
            if(!MyString.isEmpty(testSuit.getId())){
                testSuit.setCreateBy(user.getId());
                testSuitService.update(testSuit);
            }
            // 新增
            else{
                testSuit.setId(IdGen.uuid());
                testSuit.setCreateBy(user.getId());
                testSuitService.save(testSuit);
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new JsonResult("000001");
        }
        return new JsonResult(Status.SUCCESS);
    }

    @RequestMapping(value = "/remove" , produces="application/json; charset=utf-8")
    @ResponseBody
    public JsonResult delEnvironments(@RequestBody TestSuit testSuit){
        User user = Tools.getUser();
        testSuit.setCreateBy(user.getId());
        try{
            int re = testSuitService.delTestSuit(testSuit);
            if (re == 0){
                return new JsonResult("000002");
            }
        }catch (Exception e){
            logger.error("删除用例失败：" + e.toString());
            return new JsonResult("000001");
        }
        return new JsonResult(Status.SUCCESS);
    }
}
