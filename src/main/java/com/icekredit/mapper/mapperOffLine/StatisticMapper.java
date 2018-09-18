package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.po.statistic.ResponseTimeInfoPo;
import com.icekredit.pojo.po.statistic.StatisticResultOverViewInfoPo;
import com.icekredit.pojo.po.statistic.TimeSuccessFailedCountPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StatisticMapper {
    @Select("SELECT t.run_date AS runDate, sys_request_result.response_time AS responseTime FROM\n" +
            "  (SELECT * FROM sys_test_case_result WHERE DATE_SUB(CURDATE(), INTERVAL 30 DAY)<= date(sys_test_case_result.run_date) AND test_case_id=#{testCaseId}) t LEFT JOIN sys_request_result ON sys_request_result.test_case_result_id = t.id ORDER BY runDate ASC")
    List<ResponseTimeInfoPo> queryResponseTimeIn30Days(@Param("testCaseId") String testCaseId);

    @Select("SELECT t.totalRunCountToday, a.totalRunCount, s.totalSuccessRunCount FROM (SELECT Count(*) AS totalRunCountToday\n" +
            "FROM sys_test_case_result WHERE test_case_id=#{testCaseId} AND DATE(run_date) = DATE(now())) t, (SELECT Count(*) AS totalRunCount\n" +
            "                                                                                      FROM sys_test_case_result WHERE test_case_id=#{testCaseId}) a, (SELECT Count(*) AS totalSuccessRunCount\n" +
            "                                                                                                                                           FROM sys_test_case_result WHERE test_case_id=#{testCaseId} AND is_success='1') s;")
    StatisticResultOverViewInfoPo queryTestCaseResultOverviewInfo(@Param("testCaseId") String testCaseId);

    @Select("SELECT t.totalRunCountToday, a.totalRunCount, s.totalSuccessRunCount FROM (SELECT Count(*) AS totalRunCountToday\n" +
            "FROM sys_collections_result WHERE collection_id=#{collectionId} AND DATE(run_date) = DATE(now())) t, (SELECT Count(*)\n" +
            "AS totalRunCount FROM sys_collections_result WHERE collection_id=#{collectionId}) a, (SELECT Count(*) AS totalSuccessRunCount\n" +
            "FROM sys_collections_result WHERE collection_id=#{collectionId} AND is_success='1') s")
    StatisticResultOverViewInfoPo queryCollectionResultOverviewInfo(@Param("collectionId") String collectionId);

    @Select("SELECT t.runDate, t.successCount, b.failedCount FROM \n" +
            "(SELECT\n" +
            "  date(a.dday) runDate,\n" +
            "  count(*) -1 as successCount\n" +
            "FROM\n" +
            "  (\n" +
            "    SELECT\n" +
            "      datelist as dday\n" +
            "    FROM\n" +
            "      calendar\n" +
            "    -- 这里是限制返回最近30天的数据\n" +
            "    where  DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(datelist)&&date(datelist)<=CURDATE()\n" +
            "    UNION ALL\n" +
            "    SELECT\n" +
            "      run_date\n" +
            "    FROM\n" +
            "      sys_test_case_result WHERE test_case_id=#{testCaseId} AND is_success='1'\n" +
            "  ) a\n" +
            "GROUP BY runDate) t\n" +
            "\n" +
            "LEFT JOIN \n" +
            "\n" +
            "(SELECT\n" +
            "date(a.dday) runDate,\n" +
            "count(*) -1 as failedCount\n" +
            "FROM\n" +
            "(\n" +
            "SELECT\n" +
            "datelist as dday\n" +
            "FROM\n" +
            "calendar\n" +
            "-- 这里是限制返回最近30天的数据\n" +
            "where  DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(datelist)&&date(datelist)<=CURDATE()\n" +
            "UNION ALL\n" +
            "SELECT\n" +
            "run_date\n" +
            "FROM\n" +
            "sys_test_case_result WHERE test_case_id=#{testCaseId} AND is_success='0'\n" +
            ") a\n" +
            "GROUP BY runDate) b ON t.runDate = b.runDate;")
    List<TimeSuccessFailedCountPo> queryTestCaseSuccessFailedCount(@Param("testCaseId") String testCaseId);

    @Select("SELECT t.runDate, t.successCount, b.failedCount FROM \n" +
            "(SELECT\n" +
            "  date(a.dday) runDate,\n" +
            "  count(*) -1 as successCount\n" +
            "FROM\n" +
            "  (\n" +
            "    SELECT\n" +
            "      datelist as dday\n" +
            "    FROM\n" +
            "      calendar\n" +
            "    -- 这里是限制返回最近30天的数据\n" +
            "    where  DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(datelist)&&date(datelist)<=CURDATE()\n" +
            "    UNION ALL\n" +
            "    SELECT\n" +
            "      run_date\n" +
            "    FROM\n" +
            "      sys_collections_result WHERE collection_id=#{collectionId} AND is_success='1'\n" +
            "  ) a\n" +
            "GROUP BY runDate) t\n" +
            "\n" +
            "LEFT JOIN \n" +
            "\n" +
            "(SELECT\n" +
            "date(a.dday) runDate,\n" +
            "count(*) -1 as failedCount\n" +
            "FROM\n" +
            "(\n" +
            "SELECT\n" +
            "datelist as dday\n" +
            "FROM\n" +
            "calendar\n" +
            "-- 这里是限制返回最近30天的数据\n" +
            "where  DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(datelist)&&date(datelist)<=CURDATE()\n" +
            "UNION ALL\n" +
            "SELECT\n" +
            "run_date\n" +
            "FROM\n" +
            "sys_collections_result WHERE collection_id=#{collectionId} AND is_success='0'\n" +
            ") a\n" +
            "GROUP BY runDate) b ON t.runDate = b.runDate;")
    List<TimeSuccessFailedCountPo> queryCollectionSuccessFailedCount(@Param("collectionId") String collectionId);
}
