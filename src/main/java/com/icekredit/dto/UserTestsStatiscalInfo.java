package com.icekredit.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 17-1-11.
 */
public class UserTestsStatiscalInfo {
    private int totalTest;
    private int runNum;
    private int failNum;
    private int passNum;

    private List<Map<String,Integer>> createTestsNumEveryDay;
    private List<Map<String,Integer>> runTestsNumEveryDay;
    private List<Map<String,Integer>> passTestsNumEveryDay;
    private List<Map<String,Integer>> failTestsNumEveryDay;

    public UserTestsStatiscalInfo(){
        createTestsNumEveryDay = new ArrayList<>();
        runTestsNumEveryDay = new ArrayList<>();
        passTestsNumEveryDay = new ArrayList<>();
        failTestsNumEveryDay = new ArrayList<>();
    }

    public int getTotalTest() {
        return totalTest;
    }

    public void setTotalTest(int totalTest) {
        this.totalTest = totalTest;
    }

    public int getRunNum() {
        return runNum;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }

    public int getPassNum() {
        return passNum;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public List<Map<String, Integer>> getCreateTestsNumEveryDay() {
        return createTestsNumEveryDay;
    }

    public void setCreateTestsNumEveryDay(List<Map<String, Integer>> createTestsNumEveryDay) {
        this.createTestsNumEveryDay = createTestsNumEveryDay;
    }

    public List<Map<String, Integer>> getRunTestsNumEveryDay() {
        return runTestsNumEveryDay;
    }

    public void setRunTestsNumEveryDay(List<Map<String, Integer>> runTestsNumEveryDay) {
        this.runTestsNumEveryDay = runTestsNumEveryDay;
    }

    public List<Map<String, Integer>> getPassTestsNumEveryDay() {
        return passTestsNumEveryDay;
    }

    public void setPassTestsNumEveryDay(List<Map<String, Integer>> passTestsNumEveryDay) {
        this.passTestsNumEveryDay = passTestsNumEveryDay;
    }

    public List<Map<String, Integer>> getFailTestsNumEveryDay() {
        return failTestsNumEveryDay;
    }

    public void setFailTestsNumEveryDay(List<Map<String, Integer>> failTestsNumEveryDay) {
        this.failTestsNumEveryDay = failTestsNumEveryDay;
    }
}
