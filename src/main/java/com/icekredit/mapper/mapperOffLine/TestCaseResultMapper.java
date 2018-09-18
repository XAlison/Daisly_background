package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.po.TestCaseResultPo;
import com.icekredit.pojo.po.TestCaseResultPoForTable;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TestCaseResultMapper {
    @Insert("INSERT INTO sys_test_case_result(id, test_case_id, collection_result_id, environment_id, run_by, run_date, error_info, is_success, total_assertions, success_assertions)" +
            "values(#{id}, #{testCaseId}, #{collectionResultId}, #{environmentId}, #{runBy}, now(), #{errorInfo}, #{isSuccess}, #{totalAssertions}, #{successAssertions})")
    void saveTestCaseResultMapper(TestCaseResultPo testCaseResultPo);

    @Update("UPDATE sys_test_case_result SET collection_result_id=#{collectionResultId} WHERE id=#{testCaseResultId}")
    void updateTestCaseResultCollectionResultId(@Param("testCaseResultId") String testCaseResultId, @Param("collectionResultId") String collectionResultId);

    @Select("SELECT t.id, t.test_case_id, t.run_date, t.error_info, t.total_assertions,s.remarks,s.test_case_name,\n" +
            "t.success_assertions, t.is_success, e.environment_name, c.collection_name FROM\n" +
            "((SELECT a.id, a.test_case_id, a.run_date, a.error_info, a.is_success, a.total_assertions,\n" +
            "a.success_assertions, a.environment_id, a.collection_result_id FROM sys_test_case_result a\n" +
            "WHERE a.test_case_id=#{testCaseId}) t LEFT JOIN sys_environments e ON e.id = t.environment_id)\n" +
            "LEFT JOIN sys_test_case s ON t.test_case_id=s.id\n" +
            "LEFT JOIN sys_collections c ON c.id = (SELECT collection_id FROM sys_collections_result WHERE id = t.collection_result_id)\n" +
            "ORDER BY t.run_date DESC")
    List<TestCaseResultPoForTable> queryTestCaseResultByTestCaseId(@Param("testCaseId") String testCaseId);

    @Select("SELECT t.id, t.test_case_id, b.test_case_name, b.remarks, t.run_date, t.error_info, t.total_assertions,\n" +
            "t.success_assertions, t.is_success, e.environment_name, c.collection_name FROM\n" +
            "((SELECT a.id, a.test_case_id, a.run_date, a.error_info, a.is_success, a.total_assertions,\n" +
            "a.success_assertions, a.environment_id, a.collection_result_id FROM sys_test_case_result a\n" +
            "WHERE a.collection_result_id=#{collectionResultId}) t LEFT JOIN sys_environments e ON e.id = t.environment_id)\n" +
            "LEFT JOIN sys_collections c ON c.id = (SELECT collection_id FROM sys_collections_result WHERE id = t.collection_result_id)\n" +
            "LEFT JOIN sys_test_case b ON b.id = t.test_case_id\n" +
            "ORDER BY t.run_date DESC;")
    List<TestCaseResultPoForTable> queryCollectionTestCaseResult(@Param("collectionResultId") String collectionResultId);

    @Select("SELECT is_success FROM sys_test_case_result WHERE collection_result_id=#{collectionResultId}")
    List<TestCaseResultPo> queryTestCaseResultByCollectionResultId(@Param("collectionResultId") String collectionResultId);

}
