package com.icekredit.pojo.po.statistic;

public class TimeSuccessFailedCountPo {
    private String runDate;
    private int failedCount;
    private int successCount;

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public int getSucessCount() {
        return successCount;
    }

    public void setSucessCount(int successCount) {
        this.successCount = successCount;
    }
}
