package com.icekredit.controller;

import com.icekredit.enumeration.Status;
import com.icekredit.framework.JsonResult;
import com.icekredit.framework.base.BaseController;
import com.icekredit.pojo.User;
import com.icekredit.service.RunTestCaseService;
import com.icekredit.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController extends BaseController{
    @Autowired
    RunTestCaseService runTestCaseService;

    @GetMapping(value = "/run_collection")
    @ResponseBody
    public JsonResult runCollection(@RequestParam("collectionId") String collectionId) {
        User user = Tools.getUserByToken();
        if (null == user){
            return new JsonResult("000013");
        }
        String uid = user.getId();
        Boolean successFlag = runTestCaseService.runCollectionId(collectionId, uid);
        if (!successFlag){
            return new JsonResult("000014");
        }
        return new JsonResult(Status.SUCCESS);
    }

}
