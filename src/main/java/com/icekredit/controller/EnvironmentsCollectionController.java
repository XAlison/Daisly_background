package com.icekredit.controller;

import com.alibaba.fastjson.JSONObject;
import com.icekredit.enumeration.Status;
import com.icekredit.framework.JsonResult;
import com.icekredit.pojo.EnvironmentCollection;
import com.icekredit.pojo.User;
import com.icekredit.service.EnvironmentsService;
import com.icekredit.utils.IdGen;
import com.icekredit.utils.MyString;
import com.icekredit.utils.PageResult;
import com.icekredit.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/environment_collection")
public class EnvironmentsCollectionController {
    @Autowired
    private EnvironmentsService environmentsService;

    Logger logger = LoggerFactory.getLogger(EnvironmentsCollectionController.class);

    @RequestMapping(value = "/listpage", produces="application/json; charset=utf-8")
    @ResponseBody
    public JsonResult getEnvironments(@RequestBody JSONObject obj) throws InterruptedException {
        User user = Tools.getUser();
        PageResult<EnvironmentCollection> p;
        try{
            p = environmentsService.queryEnvironmentCollectionByPage(obj.getIntValue("page"), 20, obj.getString("name"), user.getId());
        }catch (Exception e){
            logger.error(e.toString());
            return new JsonResult("000001");
        }

        return new JsonResult(Status.SUCCESS, p);
    }

    @RequestMapping(value = "addOrUpdate", produces="application/json; charset=utf-8")
    @ResponseBody
    public JsonResult addOrupdateEnvironmentCollection(@RequestBody EnvironmentCollection environmentCollection){
        User user = Tools.getUser();
        try {
            // 修改
            if(!MyString.isEmpty(environmentCollection.getId())){
                environmentCollection.setCreateBy(user.getId());
                environmentsService.updateEnvironmentCollection(environmentCollection);
            }
            // 新增
            else{
                environmentCollection.setId(IdGen.uuid());
                environmentCollection.setCreateBy(user.getId());
                environmentsService.saveEnvironmentCollection(environmentCollection);
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new JsonResult("000001");
        }
        return new JsonResult(Status.SUCCESS);
    }

    @RequestMapping(value = "/remove" , produces="application/json; charset=utf-8")
    @ResponseBody
    public JsonResult delEnvironments(@ModelAttribute EnvironmentCollection environmentCollection){
        User user = Tools.getUser();
        environmentCollection.setCreateBy(user.getId());
        try{
            int re = environmentsService.delEnvironmentCollectionById(environmentCollection);
            if (re == 0){
                return new JsonResult("000002");
            }
        }catch (Exception e){
            logger.error("删除用例失败：{}", e.toString());
            return new JsonResult("000001");
        }
        return new JsonResult(Status.SUCCESS);
    }
}
