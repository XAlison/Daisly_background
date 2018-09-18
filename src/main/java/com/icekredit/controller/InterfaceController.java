package com.icekredit.controller;

import com.alibaba.fastjson.JSONObject;
import com.icekredit.enumeration.Status;
import com.icekredit.framework.JsonResult;
import com.icekredit.framework.auth.MyException;
import com.icekredit.pojo.Interface;
import com.icekredit.pojo.User;
import com.icekredit.service.InterfaceService;;
import com.icekredit.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class InterfaceController {

    private Logger logger = LoggerFactory.getLogger(InterfaceController.class);
    @Autowired
    private InterfaceService interfaceService;

//    @RequestMapping("interface/detail.do")
//    @ResponseBody
//    public JsonResult detail(@ModelAttribute Project project) throws MyException {
//        Project project1;
//        if(!project.getId().equals(Const.NULL_ID)){
//            project1= projectService.getProjectById(project);
//        }else{
//            project1=new Project();
//        }
//        return new JsonResult(Status.SUCCESS,project1);
//    }
//
    @RequestMapping(value = "/interface/addOrUpdate")
    public JsonResult addOrUpdate(@RequestBody Interface i){
        try {
            User user = Tools.getUser();
            // 修改
            if(!MyString.isEmpty(i.getId())){
                // 不允许转移项目
                //project.setUpdateBy(user.getUserName());
                interfaceService.update(i);
            }
            // 新增
            else{
                i.setId(IdGen.uuid());
                //project.setCreateBy(user.getUserName());
                //project.setUpdateBy(user.getUserName());
                interfaceService.save(i);
            }
        }catch (Exception e){
            logger.error(e.toString());
            return new JsonResult("000001");
        }

        return new JsonResult(Status.SUCCESS);
    }
//
    @RequestMapping(value = "/interface/remove", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delProject(@RequestBody JSONObject obj){
        try{
            int re = interfaceService.delInterface(obj.getString("id"));
            if (re == 0){
                return new JsonResult("000002");
            }
        }catch (Exception e){
            logger.error("删除项目失败：" + e.toString());
            return new JsonResult("000001");
        }
        return new JsonResult(Status.SUCCESS);
    }

    @RequestMapping(value = "/interface/listpage",  method = RequestMethod.POST)
    @ResponseBody
    public JsonResult list(@RequestBody JSONObject obj) throws MyException {
        PageResult<Interface> p;
        try{
            p = interfaceService.queryByPage(obj.getIntValue("page"), 20, obj.getString("interfaceName"),
                    obj.getString("projectId"));
        }catch (Exception e){
            logger.error("查询接口列表出错："+e.toString());
            return new JsonResult("000001");
        }
        return new JsonResult(Status.SUCCESS, p);
    }
}
