package com.icekredit.pojo.vo.statistic;

public class CollectionResultStatistic {
    private StatisticResultOverViewInfo CollectionResultOverViewInfo;
    private TimeSuccessFailedCount timeSuccessFailedCount;

    public StatisticResultOverViewInfo getCollectionResultOverViewInfo() {
        return CollectionResultOverViewInfo;
    }

    public void setCollectionResultOverViewInfo(StatisticResultOverViewInfo collectionResultOverViewInfo) {
        CollectionResultOverViewInfo = collectionResultOverViewInfo;
    }

    public TimeSuccessFailedCount getTimeSuccessFailedCount() {
        return timeSuccessFailedCount;
    }

    public void setTimeSuccessFailedCount(TimeSuccessFailedCount timeSuccessFailedCount) {
        this.timeSuccessFailedCount = timeSuccessFailedCount;
    }
}
