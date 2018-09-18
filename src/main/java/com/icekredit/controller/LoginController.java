package com.icekredit.controller;

import java.io.IOException;

import java.util.List;

import com.icekredit.config.Config;
import com.icekredit.enumeration.Status;
import com.icekredit.pojo.vo.ChangePassWordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icekredit.dto.LoginDto;
import com.icekredit.framework.JsonResult;
import com.icekredit.framework.auth.MyException;
import com.icekredit.framework.base.BaseController;
import com.icekredit.pojo.User;
import com.icekredit.service.CacheRedisService;
import com.icekredit.service.UserService;
import com.icekredit.utils.*;

import javax.servlet.ServletOutputStream;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private Config config;

    @Autowired
    private CacheRedisService cacheRedisService;

    @Autowired
    private UserService userService;
    /**
     * Method description
     *
     *
     *
     * @return
     *
     * @throws IOException
     * @throws MyException
     */
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult JsonResult(@RequestBody LoginDto user) throws IOException, MyException {
        if (MyString.isEmpty(user.getVerificationCode()) || user.getVerificationCode().equals(Tools.getImgCode(request))) {
            throw  new MyException("000010");
        }
        List<User> users = null;

        users = userService.getUserByName(user);

        if (users.size() == 1) {
            User userDataBase = users.get(0);
            if (!MyString.isEmpty(user.getPassword())
                    && MD5.encrytMD5(user.getPassword()).equals(userDataBase.getPassword())
                    && (user.getUserName().equals(userDataBase.getUserName()))) {
                userService.login(userDataBase, request, response);

                return new JsonResult(Status.SUCCESS, userDataBase);
            }
            throw  new MyException("000004");
        } else {

            throw  new MyException("000005");
        }

    }
    @RequestMapping("/getImgCode")
    @ResponseBody
    public void getImgvcode() throws IOException {
        // 设置response，输出图片客户端不缓存
        response.setDateHeader("Expires", 0);
        response.addHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        ValidateCodeService vservice = new ValidateCodeService();
        String uuid = MyCookie.getCookie(Const.COOKIE_UUID, false, request);
        cacheRedisService.setStr(Const.CACHE_IMGCODE + uuid, vservice.getCode() , 10 * 60);
        cacheRedisService.setStr(Const.CACHE_IMGCODE_TIMES + uuid, String.valueOf(0) , 10 * 60);
        try {
            vservice.write(out);
            out.flush();
        } finally {
            out.close();
        }
    }
    /**
     * Method description
     *
     *
     * @return
     *
     * @throws IOException
     */
    @RequestMapping("/loginOut")
    @ResponseBody
    public JsonResult loginOut() throws MyException {
        String uid = MyCookie.getCookie(Const.COOKIE_USERID, false, request);
        cacheRedisService.delObj(Const.CACHE_USER + uid);
        MyCookie.deleteCookie(Const.COOKIE_TOKEN, request, response);
        return new JsonResult(Status.SUCCESS);
    }

    @RequestMapping("/change_password")
    @ResponseBody
    public JsonResult changePassWord(@RequestBody ChangePassWordVo passWordInfo) throws MyException {
        userService.changePassWord(passWordInfo);
        return new JsonResult(Status.SUCCESS);
    }

    /**
     * Method description
     *
     *
     * @return
     *
     * @throws IOException
     */
    @GetMapping("/createToken")
    @ResponseBody
    public JsonResult updateToken() throws MyException {
        String token = IdGen.randomBase62(64);
        userService.updateToken(token);
        return new JsonResult(Status.SUCCESS, token);
    }

    @RequestMapping("/checkLoginOrNot")
    @ResponseBody
    public JsonResult checkLoginOrNot() throws MyException{
        User user = Tools.getUser();
        if (null == user){
            return new JsonResult(Status.FAILED);
        }
        return new JsonResult(Status.SUCCESS);
    }
}

