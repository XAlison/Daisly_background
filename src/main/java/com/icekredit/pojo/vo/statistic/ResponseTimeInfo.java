package com.icekredit.pojo.vo.statistic;


import java.util.List;

public class ResponseTimeInfo {
    private List<String> runDate;
    private List<Integer> responseTime;

    public List<String> getRunDate() {
        return runDate;
    }

    public void setRunDate(List<String> runDate) {
        this.runDate = runDate;
    }

    public List<Integer> getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(List<Integer> responseTime) {
        this.responseTime = responseTime;
    }
}
