package com.icekredit.controller;

import com.icekredit.enumeration.Status;
import com.icekredit.framework.JsonResult;
import com.icekredit.framework.base.BaseController;
import com.icekredit.pojo.vo.EnvironmentVo;
import com.icekredit.pojo.User;
import com.icekredit.service.*;
import com.icekredit.utils.IdGen;
import com.icekredit.utils.MyString;
import com.icekredit.utils.Tools;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/environment")
public class EnvironmentsController extends BaseController {
    @Autowired
    private EnvironmentsService environmentsService;

    Logger logger = LoggerFactory.getLogger(EnvironmentsController.class);

    @ApiOperation(value="获取环境变量列表", notes="根据page和key以及用户ID获取环境变量列表")
    @RequestMapping(value = "/list", produces="application/json; charset=utf-8")
    @ResponseBody
    public JsonResult getEnvironments(@RequestParam("projectId") String projectId) throws InterruptedException {
        User user = Tools.getUser();
        List<EnvironmentVo> listEnvironment;
        try{
            listEnvironment = environmentsService.queryEnvironments(user.getId(), projectId);
        }catch (Exception e){
            logger.error(e.toString());
            return new JsonResult("000001");
        }

        return new JsonResult(Status.SUCCESS, listEnvironment);
    }

    @RequestMapping(value = "addOrUpdate", produces="application/json; charset=utf-8")
    @ResponseBody
    public JsonResult addEnvironments(@RequestBody List<EnvironmentVo> environments){
        User user = Tools.getUser();
        environments.stream().forEach(x->{
            if (!MyString.isEmpty(x.getId())){
                x.setUpdateBy(user.getId());
                environmentsService.update(x);

            }
            else {
                x.setId(IdGen.uuid());
                x.setCreateBy(user.getId());
                x.setUpdateBy(user.getId());
                environmentsService.save(x);
            }
        });
        return new JsonResult(Status.SUCCESS);
    }

    @RequestMapping(value = "/remove" , produces="application/json; charset=utf-8")
    @ResponseBody
    public JsonResult delEnvironments(@RequestBody EnvironmentVo environment){
        User user = Tools.getUser();
        environment.setCreateBy(user.getId());
        try{
            int re = environmentsService.deleteById(environment);
            if (re == 0){
                return new JsonResult("000002");
            }
        }catch (Exception e){
            logger.error("删除环境失败：" + e.toString());
            return new JsonResult("000001");
        }
        return new JsonResult(Status.SUCCESS);
    }
}
