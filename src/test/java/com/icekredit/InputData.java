package com.icekredit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icekredit.pojo.ApiBodies;
import com.icekredit.pojo.FormData;
import com.icekredit.pojo.Raw;
import com.icekredit.pojo.vo.RequestVo;
import com.icekredit.pojo.vo.TestCaseVo;
import com.icekredit.utils.IdGen;
import com.icekredit.utils.MyString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InputData {
    @Test
    public void postmanToBugFree(){
        String txtFile = readTxtFile(new File("D://Pcredit-test .postman_collection.json"));
        JSONObject j = JSON.parseObject(txtFile);
        JSONArray itemArray = j.getJSONArray("item");
        for (int i=0; i<itemArray.size(); ++i){
            JSONObject itemJsonObject = (JSONObject) itemArray.get(i);
            JSONArray testCasesArray = itemJsonObject.getJSONArray("item");
            for (int ii=0; ii<testCasesArray.size(); ii++)
            if (true){
                String name = itemJsonObject.getString("name"); //collection name
                for (int iii=0; iii<testCasesArray.size(); ++iii){
                    JSONObject test = (JSONObject) testCasesArray.get(iii);
                    JSONObject request = test.getJSONObject("request");
                    JSONArray jsonArray = test.getJSONArray("event");
                    String requestName = test.getString("name");
                    TestCaseVo testCaseVo = new TestCaseVo();
                    testCaseVo.setId(IdGen.uuid());
                    testCaseVo.setEnvironmentId("4b8e633121ee4d02ae8ee181b4b593a7");
                    testCaseVo.setProjectId("fbd54774cfd54931af5aee334af45ccb");
                    testCaseVo.setRemarks(name);
                    RequestVo requestVo = new RequestVo();
                    requestVo.setId(IdGen.uuid());
                    try {
                        requestVo.setApi(request.getJSONObject("url").getString("raw"));
                    }catch (Exception e){
                        requestVo.setApi(request.getString("url"));
                    }
                    requestVo.setRequestName(requestName);
                    if (request.getString("method").equals("POST")){
                        requestVo.setRequestType("Post");
                    }else {
                        requestVo.setRequestType("Get");
                    }
                    ApiBodies apiBodies = new ApiBodies();
                    apiBodies.setFlag("raw");
                    Raw raw = new Raw();
                    raw.setKey("json");
                    raw.setValue(request.getJSONObject("body").getString("raw"));
                    apiBodies.setRaw(raw);
                    List formData = new ArrayList<FormData>();
                    apiBodies.setFormData(formData);
                    requestVo.setApiBodies(apiBodies);
                    if (jsonArray != null){
                        JSONObject jj = (JSONObject) jsonArray.get(0);
                        JSONObject jjj = jj.getJSONObject("script");
                        JSONArray asserts = jjj.getJSONArray("exec");
                        for (int p=0; p<asserts.size(); ++p){
                            String s = (String) asserts.get(p);
                            if (s.contains("tests")){

                            }
                        }
                    }
                }
            }
        }

        int i =0;
    }

    @Test
    public void inPutShenMaXinShenDai(){
        String txtFile = readTxtFile(new File("C:\\Users\\icekredit\\Desktop\\PostMan个人\\什马新生贷.postman_collection.json"));
        JSONObject j = JSON.parseObject(txtFile);
        String collectionName = j.getString("name");
        JSONArray arrayRequests = j.getJSONArray("requests");
        arrayRequests.stream().forEach(request->{
            JSONObject requestObject = (JSONObject) request;
            String testCaseName = requestObject.getString("name");
            TestCaseVo testCaseVo = new TestCaseVo();
            testCaseVo.setId(IdGen.uuid());
            testCaseVo.setEnvironmentId("4b8e633121ee4d02ae8ee181b4b593a7");
            testCaseVo.setProjectId("fbd54774cfd54931af5aee334af45ccb");
            testCaseVo.setRemarks(testCaseName);
            RequestVo requestVo = new RequestVo();
            requestVo.setId(IdGen.uuid());
            requestVo.setApi(requestObject.getString("url"));
            requestVo.setRequestName(testCaseName);
            if (requestObject.getString("method").equals("POST")){
                requestVo.setRequestType("Post");
            }else {
                requestVo.setRequestType("Get");
            }
            ApiBodies apiBodies = new ApiBodies();
            apiBodies.setFlag("raw");
            Raw raw = new Raw();
            raw.setKey("json");
            raw.setValue(requestObject.getString("rawModeData"));
            apiBodies.setRaw(raw);
            List formData = new ArrayList<FormData>();
            apiBodies.setFormData(formData);
            requestVo.setApiBodies(apiBodies);
            String asserts = requestObject.getString("tests");
            String[] tests = asserts.split("\\n");
            for (int ii=0; ii<tests.length; ++ii){

            }
            int i = 0;
        });
    }
    public static String readTxtFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String encoding = "UTF-8";
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    stringBuilder.append(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
