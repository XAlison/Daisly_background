package com.icekredit.pojo.po.statistic;


public class CollectionResultStatisticInfo {
    private int totalSuccessCase;
    private int totalFailedCase;
    private int totalCase;

    public int getTotalSuccessCase() {
        return totalSuccessCase;
    }

    public void setTotalSuccessCase(int totalSuccessCase) {
        this.totalSuccessCase = totalSuccessCase;
    }

    public int getTotalFailedCase() {
        return totalFailedCase;
    }

    public void setTotalFailedCase(int totalFailedCase) {
        this.totalFailedCase = totalFailedCase;
    }

    public int getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(int totalCase) {
        this.totalCase = totalCase;
    }
}
