package com.icekredit.controller;

import com.alibaba.fastjson.JSONObject;
import com.icekredit.enumeration.Status;
import com.icekredit.framework.JsonResult;
import com.icekredit.framework.auth.MyException;
import com.icekredit.framework.base.BaseController;
import com.icekredit.pojo.Project;
import com.icekredit.pojo.User;
import com.icekredit.service.ProjectService;
import com.icekredit.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private ProjectService projectService;

//    @RequestMapping("/detail.do")
//    @ResponseBody
//    public JsonResult detail(@ModelAttribute Project project) throws MyException {
////        Project project1;
////        if(!project.getId().equals(Const.NULL_ID)){
////            project1= projectService.getProjectById(project);
////        }else{
////            project1=new Project();
////        }
//        return new JsonResult(Status.SUCCESS,project1);
//    }

    @RequestMapping(value = "/addOrUpdate")
    @ResponseBody
    public JsonResult addOrUpdate(@RequestBody Project project) throws MyException {
        try {
            User user = Tools.getUser();
            // 修改
            if(!MyString.isEmpty(project.getId())){
                // 不允许转移项目
                projectService.update(project);
                return new JsonResult(Status.SUCCESS);
            }
            // 新增
            else{
                project.setId(IdGen.uuid());
                project.setCreateBy(user.getId());
                projectService.save(project);
                return new JsonResult(Status.SUCCESS);
            }
        }catch (Exception e){
            logger.error(e.toString());
            throw new MyException("000018");
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delProject(@RequestBody JSONObject obj) throws MyException {
        try{
            int re = projectService.delProject(obj.getString("id"));
            if (re == 0){
                return new JsonResult("000002");
            }
        }catch (Exception e){
            logger.error("删除项目失败：" + e.toString());
            throw new MyException("000012");
        }
        return new JsonResult(Status.SUCCESS);
    }

    @RequestMapping(value = "/list",  method = RequestMethod.GET)
    @ResponseBody
    public JsonResult list() throws MyException {
        List<Project> p = null;
        User user = Tools.getUser();
        try{
            p = projectService.queryProjectList(user.getId());
        }catch (Exception e){
            logger.error("获取项目列表失败:{}", e.toString());
            return new JsonResult("000001");
        }


        return new JsonResult(Status.SUCCESS, p);
    }
}
