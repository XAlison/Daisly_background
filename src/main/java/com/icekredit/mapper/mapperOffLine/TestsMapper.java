package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.Tests;

import java.util.List;

/**
 * Created by root on 17-1-9.
 */
public interface TestsMapper {

    public List<Tests> getByTestName(Tests test);
    public List<Tests> getAllTests();
    public void saveTest(Tests test);
    public void updateTest(Tests test);
    public void updateTestStatus(String name, char flag);
    public void upadteTestResult(String name, String result);
    public void runNumPlusPlus(String name);
    public void failNumPlusPlus(String name);
    public void passNumPlusPlus(String name);
    public void getCollectionByName(Tests test);
    public void deleteByName(String name);
    public int getTestCreateNumByDate(String date);
}
