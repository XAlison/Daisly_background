package com.icekredit.pojo.vo.statistic;

import java.util.List;

public class TimeSuccessFailedCount {
    private List<String> runDate;
    private List<Integer> failedCount;
    private List<Integer> sucessCount;

    public List<String> getRunDate() {
        return runDate;
    }

    public void setRunDate(List<String> runDate) {
        this.runDate = runDate;
    }

    public List<Integer> getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(List<Integer> failedCount) {
        this.failedCount = failedCount;
    }

    public List<Integer> getSucessCount() {
        return sucessCount;
    }

    public void setSucessCount(List<Integer> sucessCount) {
        this.sucessCount = sucessCount;
    }
}
