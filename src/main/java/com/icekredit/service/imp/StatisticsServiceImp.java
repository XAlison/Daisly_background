package com.icekredit.service.imp;

import com.icekredit.mapper.mapperOffLine.StatisticMapper;
import com.icekredit.pojo.po.statistic.ResponseTimeInfoPo;
import com.icekredit.pojo.po.statistic.StatisticResultOverViewInfoPo;
import com.icekredit.pojo.po.statistic.TimeSuccessFailedCountPo;
import com.icekredit.pojo.vo.statistic.*;
import com.icekredit.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StatisticsServiceImp implements StatisticsService{

    @Autowired
    StatisticMapper statisticMapper;

    @Override
    public List<ResponseTimeInfoPo> queryResponseTimeIn30Days(String testCaseId) {
        return statisticMapper.queryResponseTimeIn30Days(testCaseId);
    }

    @Override
    public StatisticResultOverViewInfoPo queryTestCaseResultOverviewInfo(String testCaseId) {
        StatisticResultOverViewInfoPo testCaseResultOverViewInfoPo =  statisticMapper.queryTestCaseResultOverviewInfo(testCaseId);
        testCaseResultOverViewInfoPo.setTotalFailedRunCount(testCaseResultOverViewInfoPo.getTotalRunCount() - testCaseResultOverViewInfoPo.getTotalSucessRunCount());
        return testCaseResultOverViewInfoPo;
    }

    @Override
    public StatisticResultOverViewInfoPo queryCollectionResultOverviewInfo(String collectionId) {
        StatisticResultOverViewInfoPo collectionResultOverViewInfoPo =  statisticMapper.queryCollectionResultOverviewInfo(collectionId);
        collectionResultOverViewInfoPo.setTotalFailedRunCount(collectionResultOverViewInfoPo.getTotalRunCount() - collectionResultOverViewInfoPo.getTotalSucessRunCount());
        return collectionResultOverViewInfoPo;
    }

    @Override
    public List<TimeSuccessFailedCountPo> queryTestCaseSuccessFailedCount(String testCaseId) {
        return statisticMapper.queryTestCaseSuccessFailedCount(testCaseId);
    }

    @Override
    public List<TimeSuccessFailedCountPo> queryCollectionSuccessFailedCount(String collectionId) {
        return statisticMapper.queryCollectionSuccessFailedCount(collectionId);
    }

    @Override
    public TestCaseResultStatistic getTestCaseResultStatistic(String testCaseId) {
        List<ResponseTimeInfoPo> listResponseTimeInfoPo = queryResponseTimeIn30Days(testCaseId);
        StatisticResultOverViewInfoPo testCaseResultOverViewInfoPo = queryTestCaseResultOverviewInfo(testCaseId);
        List<TimeSuccessFailedCountPo> listTimeSuccessFailedCountPo = queryTestCaseSuccessFailedCount(testCaseId);


        TestCaseResultStatistic testCaseResultStatistic = new TestCaseResultStatistic();
        ResponseTimeInfo responseTimeInfo = new ResponseTimeInfo();
        TimeSuccessFailedCount timeSuccessFailedCount = new TimeSuccessFailedCount();
        StatisticResultOverViewInfo testCaseResultOverViewInfo = new StatisticResultOverViewInfo();


        List<String> listRunDate = new ArrayList<>();
        List<Integer> listResponseTime = new ArrayList<>();
        listResponseTimeInfoPo.stream().forEach(x->{
            listRunDate.add(x.getRunDate());
            listResponseTime.add(x.getResponseTime());
        });
//        Collections.reverse(listRunDate);
//        Collections.reverse(listResponseTime);
        responseTimeInfo.setRunDate(listRunDate);
        responseTimeInfo.setResponseTime(listResponseTime);


        List<String> listDate = new ArrayList<>();
        List<Integer> successCount = new ArrayList<>();
        List<Integer> failedCount = new ArrayList<>();
        responseTimeInfo.setRunDate(listRunDate);
        listTimeSuccessFailedCountPo.stream().forEach(x->{
            listDate.add(x.getRunDate());
            failedCount.add(x.getFailedCount());
            successCount.add(x.getSucessCount());
        });
//        Collections.reverse(listDate);
//        Collections.reverse(successCount);
//        Collections.reverse(failedCount);
        timeSuccessFailedCount.setRunDate(listDate);
        timeSuccessFailedCount.setFailedCount(failedCount);
        timeSuccessFailedCount.setSucessCount(successCount);

        testCaseResultOverViewInfo.setTotalFailedRunCount(testCaseResultOverViewInfoPo.getTotalFailedRunCount());
        testCaseResultOverViewInfo.setTotalRunCount(testCaseResultOverViewInfoPo.getTotalRunCount());
        testCaseResultOverViewInfo.setTotalRunCountToday(testCaseResultOverViewInfoPo.getTotalRunCountToday());
        testCaseResultOverViewInfo.setTotalSuccessRunCount(testCaseResultOverViewInfoPo.getTotalSucessRunCount());

        testCaseResultStatistic.setResponseTimeInfo(responseTimeInfo);
        testCaseResultStatistic.setTestCaseResultOverViewInfo(testCaseResultOverViewInfo);
        testCaseResultStatistic.setTimeSuccessFailedCount(timeSuccessFailedCount);

        return testCaseResultStatistic;
    }

    @Override
    public CollectionResultStatistic getCollectionResultStatistic(String collectionId) {
        StatisticResultOverViewInfoPo collectionResultOverViewInfoPo = queryCollectionResultOverviewInfo(collectionId);
        List<TimeSuccessFailedCountPo> listTimeSuccessFailedCountPo = queryCollectionSuccessFailedCount(collectionId);


        CollectionResultStatistic collectionResultStatistic = new CollectionResultStatistic();
        TimeSuccessFailedCount timeSuccessFailedCount = new TimeSuccessFailedCount();
        StatisticResultOverViewInfo collectionResultOverViewInfo = new StatisticResultOverViewInfo();



        List<String> listDate = new ArrayList<>();
        List<Integer> successCount = new ArrayList<>();
        List<Integer> failedCount = new ArrayList<>();
        listTimeSuccessFailedCountPo.stream().forEach(x->{
            listDate.add(x.getRunDate());
            failedCount.add(x.getFailedCount());
            successCount.add(x.getSucessCount());
        });
//        Collections.reverse(listDate);
//        Collections.reverse(successCount);
//        Collections.reverse(failedCount);
        timeSuccessFailedCount.setRunDate(listDate);
        timeSuccessFailedCount.setFailedCount(failedCount);
        timeSuccessFailedCount.setSucessCount(successCount);

        collectionResultOverViewInfo.setTotalFailedRunCount(collectionResultOverViewInfoPo.getTotalFailedRunCount());
        collectionResultOverViewInfo.setTotalRunCount(collectionResultOverViewInfoPo.getTotalRunCount());
        collectionResultOverViewInfo.setTotalRunCountToday(collectionResultOverViewInfoPo.getTotalRunCountToday());
        collectionResultOverViewInfo.setTotalSuccessRunCount(collectionResultOverViewInfoPo.getTotalSucessRunCount());

        collectionResultStatistic.setCollectionResultOverViewInfo(collectionResultOverViewInfo);
        collectionResultStatistic.setTimeSuccessFailedCount(timeSuccessFailedCount);

        return collectionResultStatistic;
    }
}
