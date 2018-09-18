package com.icekredit.service.imp;

import com.icekredit.pojo.vo.MyAuthVo;
import com.icekredit.service.AuthService;
import com.icekredit.utils.HttpHandler;
import com.jayway.restassured.path.json.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class AuthServiceImp implements AuthService{


    private static Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Override
    public String getTokenId(MyAuthVo myAuthVo) {
        Map<String, String> m = new HashMap();
        m.put("merchant_name", myAuthVo.getUserName());
        m.put("merchant_pwd", myAuthVo.getPassWord());
        String returnObj = null;
        try {
            returnObj = HttpHandler.post(myAuthVo.getAuthUrl(), m);
            return JsonPath.from(returnObj).getString(myAuthVo.getToken());
        } catch (IOException e) {
            logger.error("获取鉴权错误");
            return "";
        }
    }

    @Override
    public String CheckToken(String token) {
        return null;
    }

}
