package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.CollectionResult;
import com.icekredit.pojo.CollectionTestCase;
import com.icekredit.pojo.po.CollectionPo;
import com.icekredit.pojo.po.statistic.CollectionResultStatisticInfo;
import com.icekredit.pojo.vo.CollectionVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectionMapper {
    @Insert("INSERT sys_collections(id, project_id, environment_id, " +
            "remarks, collection_name, test_cases, test_case_num, create_by, create_date)values(#{id}, #{projectId}, #{environmentId}," +
            "#{remarks}, #{collectionName}, #{testCases}, #{testCaseNum}, #{createBy}, date(now()))")
    void add(CollectionPo collectionPo);

    @Update("UPDATE sys_collections SET environment_id=#{environmentId}, remarks=#{remarks}," +
            "collection_name=#{collectionName}, test_cases=#{testCases}, test_case_num=#{testCaseNum} WHERE id=#{id}")
    void update(CollectionPo collectionPo);

    @Select("SELECT a.id, a.create_date, a.remarks, a.create_by, a.environment_id, a.collection_name,\n" +
            "b.runDate, a.project_id, a.test_cases, b.isSuccess, a.test_case_num\n" +
            "FROM (SELECT t.id, sys_user.account AS create_by, t.collection_name, t.create_date, t.remarks,\n" +
            "t.project_id, t.test_case_num, t.environment_id, t.test_cases, t.is_success\n" +
            "FROM (SELECT id,collection_name,create_date,create_by,remarks,project_id,test_case_num,environment_id,test_cases,is_success   FROM sys_collections WHERE project_id=#{projectId}\n" +
            "AND create_by = #{uid}) t\n" +
            "LEFT JOIN sys_user ON t.create_by=sys_user.id) a LEFT JOIN (select any_value(c.run_date)\n" +
            "  AS runDate, any_value(c.collection_id) AS collectionId, any_value(c.is_success) AS isSuccess from sys_collections_result as c where not exists\n" +
            "(select 1 from sys_collections_result where c.collection_id= collection_id and c.run_date<run_date ) ) b ON a.id= b.collectionId")
    List<CollectionPo> queryAllCollections(@Param("projectId") String projectId, @Param("uid") String uid);

    @Select("SELECT * FROM sys_collections WHERE id = #{collectionId} AND create_by = #{uid}")
    CollectionPo  queryCollection(@Param("collectionId") String collectionId, @Param("uid") String uid);

    @Update("UPDATE sys_collections_result SET is_success=#{isSuccess}, total_failed_test_case_num=#{totalFailedTestCaseNum}, total_success_test_case_num=#{totalSuccessTestCaseNum} WHERE id=#{id}")
    void updateCollectionResultStatus(CollectionResult collectionResult);

    @Select("SELECT * FROM sys_collections_result WHERE collection_id=#{collectionId} ORDER BY run_date DESC")
    List<CollectionResult> queryCollectionResult(@Param("collectionId") String collectionId);

    @Insert("INSERT sys_collections_result(id, collection_id, run_date)" +
            "values(#{id}, #{collectionId}, now())")
    void addCollectionResult(CollectionResult collectionResult);

    @Select("<script> " +
            "SELECT t.test_case_name, t.id, t.remarks, t.environment_id, b.runDate, b.isSuccess FROM (SELECT * FROM sys_test_case WHERE id IN " +
             "<foreach item='item' index='index' collection='testCases' open='(' separator=',' close=')'>" +
             "#{item}" +
             "</foreach>" +
            ")t " +
            "LEFT JOIN " +
            "(SELECT any_value(d.test_case_id) AS test_case_id, ANY_VALUE(d.run_date) AS runDate, ANY_VALUE(d.is_success) AS isSuccess FROM (SELECT * FROM (SELECT sys_collections_result.id as result_id FROM sys_collections_result WHERE collection_id=#{id}) c LEFT JOIN sys_test_case_result r ON c.result_id=r.collection_result_id ORDER BY r.run_date ASC) d GROUP BY d.test_case_id) b " +
            "ON t.id = b.test_case_id" +
            "</script>"
    )
    List<CollectionTestCase> queryCollectionTestCase(CollectionVo collectionVo);

    @Select("SELECT a.totalFailedCase, b.totalSuccessCase FROM (SELECT count(1) AS totalFailedCase FROM sys_test_case_result WHERE collection_result_id=#{collectionResultId} AND is_success='0') a, (SELECT count(1) AS totalSuccessCase FROM sys_test_case_result WHERE collection_result_id=#{collectionResultId} AND is_success='1') b;")
    CollectionResultStatisticInfo queryCollectionResultInfo(@Param("collectionResultId") String collectionResultId);
}