package com.icekredit.service;

import com.github.pagehelper.PageHelper;
import com.icekredit.dto.UserTestsStatiscalInfo;
import com.icekredit.framework.auth.AuthInterceptor;
import com.icekredit.mapper.mapperOffLine.EnvironmentsMapper;
import com.icekredit.mapper.mapperOffLine.RunMapper;
import com.icekredit.mapper.mapperOffLine.TestsMapper;
import com.icekredit.pojo.Tests;
import com.icekredit.utils.BeanUtil;
import com.icekredit.utils.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class TestsService {
    private Logger logger = LoggerFactory.getLogger(TestsService.class);
    @Autowired
    private TestsMapper testsMapper;
    @Autowired
    private RunMapper runMapper;

    @Autowired
    private EnvironmentsMapper environmentsMapper;

    public List<Tests> getByTestName(Tests test){
        return  testsMapper.getByTestName(test);
    }
    public void saveTest(Tests tests){
        testsMapper.saveTest(tests);
    }
    public void updateTest(Tests tests){
        testsMapper.updateTest(tests);
    }
    public void runNumPlusPlus(String name){testsMapper.runNumPlusPlus(name);}
    public void failNumPlusPlus(String name){testsMapper.failNumPlusPlus(name);}
    public void passNumPlusPlus(String name){testsMapper.passNumPlusPlus(name);}
    public void upadteTestResult(String name, String result){
        testsMapper.upadteTestResult(name, result);
    }
    public List<Tests> getAllTests(){return testsMapper.getAllTests();}
    public PageResult<Tests> queryByPage(Integer pageNo, Integer pageSize){
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;

        PageHelper.startPage(pageNo, pageSize);

        return BeanUtil.toPagedResult(testsMapper.getAllTests());
    }

    public void updateTestStatus(String name, char flag){
        testsMapper.updateTestStatus(name, flag);
    }

    public void deleteByName(String name){
        testsMapper.deleteByName(name);
    }
    //UserTestsStatiscalInfo
    public  UserTestsStatiscalInfo getStatiscalInfo() throws ParseException {
        List<Tests> testsList = testsMapper.getAllTests();
        UserTestsStatiscalInfo userTestsStatiscalInfo = new UserTestsStatiscalInfo();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //当前日期
        Calendar cal = Calendar.getInstance();
        for (int i=0; i<10; ++i){
            cal.add(Calendar.DAY_OF_MONTH, 0-i);
            String date = format.format(cal.getTime());
            int runNum = runMapper.getTestRunNumByDate(date);
            int passNum = runMapper.getTestPassNumByDate(date);
            int failNum = runMapper.getTestFailNumByDate(date);
            int createNUm = testsMapper.getTestCreateNumByDate(date);
            userTestsStatiscalInfo.getRunTestsNumEveryDay().add(
                    new HashMap<String, Integer>(){
                        {
                            put(date, runNum);
                        }

                });
            userTestsStatiscalInfo.getPassTestsNumEveryDay().add(
                    new HashMap<String, Integer>(){
                        {
                            put(date, passNum);
                        }

                    });
            userTestsStatiscalInfo.getFailTestsNumEveryDay().add(
                    new HashMap<String, Integer>(){
                        {
                            put(date, failNum);
                        }

                    });
            userTestsStatiscalInfo.getCreateTestsNumEveryDay().add(
                    new HashMap<String, Integer>(){
                        {
                            put(date, createNUm);
                        }

                    });
            logger.info(String.valueOf(runMapper.getTestRunNumByDate(date)));
        }
        userTestsStatiscalInfo.setTotalTest(testsList.size());
        userTestsStatiscalInfo.setRunNum(runMapper.getCountRun());
        userTestsStatiscalInfo.setFailNum(runMapper.getFailRun());
        userTestsStatiscalInfo.setPassNum(runMapper.getPassRun());
        return userTestsStatiscalInfo;
    }

    public static void main(String args[]) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //当前日期
        Calendar cal = Calendar.getInstance();

        System.out.println(Date.valueOf(format.format(cal.getTime())));
    }
}
