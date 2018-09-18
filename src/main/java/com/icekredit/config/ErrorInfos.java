package com.icekredit.config;

import com.icekredit.DaisyApplication;
import com.icekredit.service.TestsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component
public class ErrorInfos implements CommandLineRunner {
    public static Map<String,String> errMsgs = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(ErrorInfos.class);
    @Override
    public void run(String... strings) throws Exception {
        logger.info("初始话ErrorInfo...");
        Properties propFlies = new Properties();
        try {
            InputStreamReader instream=new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("errorinfo.properties"),"UTF-8");
            propFlies.load(instream);
            Set<String> sets = propFlies.stringPropertyNames();
            errMsgs = (Map) propFlies;
        }catch (Exception e){
            logger.error(e.toString());
        }
    }
    public static Map<String, String> getErrMsgs() {
        return errMsgs;
    }

    public static void setErrMsgs(Map<String, String> errMsgs) {
        errMsgs = errMsgs;
    }

    public Map<String, String> getErrMap(String code) {
        Map<String, String> returnMap = new HashMap<String, String>();
        if (errMsgs.get(code) != null) {
            returnMap.put("code", code);
            returnMap.put("msg", errMsgs.get(code));
        }
        return returnMap;
    }

    public static String getMessage(String code) {
        return errMsgs.get(code);
    }
    public Map<String, String> getErrMap(String code, String msg) {
        Map<String, String> returnMap = new HashMap<String, String>();
        if (errMsgs.get(code) != null) {
            returnMap.put("code", code);
            returnMap.put("msg", errMsgs.get(code) + msg);
        }
        return returnMap;
    }
}
