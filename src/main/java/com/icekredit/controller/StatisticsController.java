package com.icekredit.controller;

import com.alibaba.fastjson.JSONObject;
import com.icekredit.enumeration.Status;
import com.icekredit.framework.JsonResult;
import com.icekredit.framework.base.BaseController;
import com.icekredit.pojo.po.statistic.ResponseTimeInfoPo;
import com.icekredit.pojo.po.statistic.TimeSuccessFailedCountPo;
import com.icekredit.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticsController extends BaseController{

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/testcase_statistic")
    @ResponseBody
    JsonResult getTestCaseStatistic(@RequestParam("testCaseId") String testCaseId){
        return new JsonResult(Status.SUCCESS, statisticsService.getTestCaseResultStatistic(testCaseId));
    }

    @GetMapping("/collection_statistic")
    @ResponseBody
    JsonResult getCollectionStatistic(@RequestParam("collectionId") String collectionId){
        return new JsonResult(Status.SUCCESS, statisticsService.getCollectionResultStatistic(collectionId));
    }

}
