package com.icekredit.pojo.vo.statistic;


public class StatisticResultOverViewInfo {
    private int totalRunCount;
    private int totalFailedRunCount;
    private int totalSuccessRunCount;
    private int totalRunCountToday;

    public int getTotalRunCount() {
        return totalRunCount;
    }

    public void setTotalRunCount(int totalRunCount) {
        this.totalRunCount = totalRunCount;
    }

    public int getTotalFailedRunCount() {
        return totalFailedRunCount;
    }

    public void setTotalFailedRunCount(int totalFailedRunCount) {
        this.totalFailedRunCount = totalFailedRunCount;
    }

    public int getTotalSuccessRunCount() {
        return totalSuccessRunCount;
    }

    public void setTotalSuccessRunCount(int totalSuccessRunCount) {
        this.totalSuccessRunCount = totalSuccessRunCount;
    }

    public int getTotalRunCountToday() {
        return totalRunCountToday;
    }

    public void setTotalRunCountToday(int totalRunCountToday) {
        this.totalRunCountToday = totalRunCountToday;
    }
}
