package com.icekredit.mapper.mapperOffLine;
import com.icekredit.pojo.TestSuit;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TestSuitMapper {
    @Update("UPDATE sys_testsuit SET testsuit_name=#{testSuitName},cases_id=#{casesId}, remarks=#{remarks}, project_name=#{projectName}")
    void update(TestSuit suit);

    @Delete("DELETE FROM sys_testsuit WHERE id=#{id} AND create_by=#{createBy}")
    int del(TestSuit testSuit);

    @Insert("INSERT INTO sys_testsuit(id, testsuit_name,create_by,remarks,cases_id,create_date, project_name)values(#{id},#{testSuitName},#{createBy},#{remarks}," +
            "#{casesId},now(), #{projectName})")
    void save(TestSuit suit);

    @Select("SELECT id, testsuit_name as testSuitName, project_name as projectName, create_by as createBy, cases_id as casesId, create_date as createDate, remarks FROM sys_testsuit WHERE create_by=#{createBy} AND testsuit_name like concat(concat('%',#{testSuitName}),'%')")
    List<TestSuit> queryByPage(@Param("testSuitName") String testSuitName, @Param("createBy") String createBy);

    @Select("SELECT id, testsuit_name as testSuitName, project_name as projectName, create_by as createBy, cases_id as casesId, create_date as createDate, remarks FROM sys_testsuit WHERE create_by=#{createBy} AND id = #{testSuitId}")
    TestSuit queryById(@Param("testSuitId") String testSuitId, @Param("createBy") String createBy);

//    @Select("")
//    Case queryById(@Param("id") String id);
//
//    @Select("")
//    List<Case> queryAll(String uid);
}