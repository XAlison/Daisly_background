package com.icekredit.service;


import com.icekredit.pojo.po.statistic.ResponseTimeInfoPo;
import com.icekredit.pojo.po.statistic.StatisticResultOverViewInfoPo;
import com.icekredit.pojo.po.statistic.TimeSuccessFailedCountPo;
import com.icekredit.pojo.vo.statistic.CollectionResultStatistic;
import com.icekredit.pojo.vo.statistic.TestCaseResultStatistic;

import java.util.List;

public interface StatisticsService {

    List<ResponseTimeInfoPo> queryResponseTimeIn30Days(String testCaseId);


    StatisticResultOverViewInfoPo queryTestCaseResultOverviewInfo(String testCaseId);

    StatisticResultOverViewInfoPo queryCollectionResultOverviewInfo(String collectionId);

    List<TimeSuccessFailedCountPo> queryTestCaseSuccessFailedCount(String testCaseId);

    List<TimeSuccessFailedCountPo> queryCollectionSuccessFailedCount(String collectionId);

    TestCaseResultStatistic getTestCaseResultStatistic(String testCaseId);

    CollectionResultStatistic getCollectionResultStatistic(String collectionId);

}
