package com.icekredit.pojo.vo.statistic;

public class TestCaseResultStatistic {
    private ResponseTimeInfo responseTimeInfo;
    private StatisticResultOverViewInfo testCaseResultOverViewInfo;
    private TimeSuccessFailedCount timeSuccessFailedCount;

    public ResponseTimeInfo getResponseTimeInfo() {
        return responseTimeInfo;
    }

    public void setResponseTimeInfo(ResponseTimeInfo responseTimeInfo) {
        this.responseTimeInfo = responseTimeInfo;
    }

    public StatisticResultOverViewInfo getTestCaseResultOverViewInfo() {
        return testCaseResultOverViewInfo;
    }

    public void setTestCaseResultOverViewInfo(StatisticResultOverViewInfo testCaseResultOverViewInfo) {
        this.testCaseResultOverViewInfo = testCaseResultOverViewInfo;
    }

    public TimeSuccessFailedCount getTimeSuccessFailedCount() {
        return timeSuccessFailedCount;
    }

    public void setTimeSuccessFailedCount(TimeSuccessFailedCount timeSuccessFailedCount) {
        this.timeSuccessFailedCount = timeSuccessFailedCount;
    }
}
