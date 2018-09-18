package com.icekredit.mapper.mapperOffLine;
import com.icekredit.pojo.po.RequestPo;
import com.icekredit.pojo.po.TestCasePo;

import com.icekredit.pojo.vo.TestCaseVo;
import com.icekredit.pojo.vo.TestCaseVoForTable;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TestCaseMapper {
//    @Select("P_GET_PROJECTDETAIL_BY_ID(#{id})")
//    Interface getProjectsById(Interface i);
//

    @Insert("INSERT INTO sys_test_case(id, test_case_name, remarks, project_id, environment_id,create_by,create_date) values" +
            "(#{id}, #{testCaseName}, #{remarks}, #{projectId}, #{environmentId}, #{createBy}, date(now()))")
    void saveTestCase(TestCasePo testCasePo);

    @Update("UPDATE sys_test_case SET test_case_name=#{testCaseName}, remarks=#{remarks}, environment_id=#{environmentId} WHERE id=#{id}")
    void updateTestCase(TestCasePo testCasePo);

    @Insert("INSERT INTO sys_request(id, test_case_id, request_name, request_type, api, api_headers, api_url_parameter, api_assertions, api_variables, api_bodies, pre_request_script) values " +
            "(#{id}, #{testCaseId}, #{requestName}, #{requestType}, #{api}, #{apiHeaders}, #{apiUrlParameter}, #{apiAssertions}, #{apiVariables}, #{apiBodies} ,#{preRequestScript})")
    void saveRequest(RequestPo requestPo);

    @Update("UPDATE sys_request SET request_name=#{requestName}, request_type=#{requestType}, api=#{api}, api_headers=#{apiHeaders}, " +
            "api_url_parameter=#{apiUrlParameter}, pre_request_script=#{preRequestScript}, api_assertions=#{apiAssertions}, api_variables=#{apiVariables}, api_bodies=#{apiBodies} WHERE id=#{id}")
    void updateRequest(RequestPo requestPo);

    @Select("SELECT * FROM sys_request WHERE test_case_id=#{testCaseId}")
    List<RequestPo> queryRequestByTestCaseId(@Param("testCaseId") String testCaseId);
//    id, project_id, remarks, environment_id, test_case_name
    @Select("SELECT * FROM sys_test_case WHERE id=#{testCaseId}")
    TestCasePo queryTestCaseVoByTestCaseId(@Param("testCaseId") String testCaseId);

    @Update("UPDATE sys_test_case SET is_success=#{status} WHERE id=#{testCaseId}")
    void updateTestCaseStatus(@Param("testCaseId") String testCaseId, @Param("status") char status);

//     AND sys_environments.id=sys_test_case.environment_id;
    @Select("SELECT  o.id, o.remarks, o.create_by, o.create_date, o.test_case_name, o.environment_name,\n" +
            "o.project_id, o.environment_id, r.run_date, r.is_success FROM  (SELECT m.*, e.environment_name FROM\n" +
            "(SELECT t.*, u.account AS create_by FROM (SELECT sys_test_case.id,\n" +
            "sys_test_case.project_id, sys_test_case.environment_id, sys_test_case.test_case_name,\n" +
            "sys_test_case.remarks, sys_test_case.create_by AS user,sys_test_case.create_date\n" +
            "from sys_test_case WHERE  sys_test_case.project_id=#{projectId} AND sys_test_case.create_by=#{uid})\n" +
            "t LEFT JOIN sys_user u ON u.id=t.user) m LEFT JOIN sys_environments e ON m.environment_id= e.id) o\n" +
            "LEFT JOIN (SELECT any_value(is_success) AS is_success, any_value(test_case_id) AS test_case_id, any_value((run_date)) AS run_date\n" +
            "FROM sys_test_case_result as b where not exists(select 1 from sys_test_case_result where b.test_case_id= test_case_id\n" +
            "and b.run_date<run_date)) r ON o.id=r.test_case_id")
    List<TestCaseVoForTable> queryAllTestCase(@Param("uid") String uid, @Param("projectId") String projectId);

    @Update("update sys_cases set case_name\n=#{caseName},dep_cases=#{depCases}, update_date=now(),update_by=#{updateBy}," +
            "remarks=#{remarks},request_type=#{requestType},return_type=#{returnType}, " +
            " case_info=#{caseInfo}, project_name=#{projectName},interface_name=#{interfaceName}," +
            "api=#{api} where id=#{id}")
    void update(Case c);

    @Update("update sys_cases set del_flag='1' WHERE id=#{0}")
    int del(String id);

    @Insert("insert into sys_cases(id, dep_cases, request_type, return_type, case_name, api, project_name, interface_Name, case_info, create_by, create_date, " +
            "update_by, update_date, remarks, sequence) values(#{id},#{depCases}, #{requestType}, #{returnType}, #{caseName}, #{api}, #{projectName}, #{interfaceName}, #{caseInfo}," +
            "#{createBy}, now(), #{updateBy}," +
            "now(), #{remarks},#{sequence})")
    void save(Case c);

    @Select("SELECT id, api, remarks, dep_cases as depCases, request_type as requestType, return_type as returnType, project_name as projectName, case_name as caseName, case_info as caseInfo, \n" +
            "interface_name as interfaceName, create_by as createBy, \n" +
            "create_date as createDate, update_by as updateBy, \n" +
            "update_date as updateDate, run_num as runNum, is_sucess as isSucess,\n" +
            "passed_num as passedNum, failed_num as failedNum, last_result as lastResult\n" +
            " FROM sys_cases WHERE del_flag = '0' AND case_name like concat(concat('%',#{caseName}),'%') AND interface_name like concat(concat('%',#{interfaceName}),'%') AND create_by=#{uid} AND project_name like concat(concat('%',#{projectName}),'%')")
    List<Case> queryByPage(@Param("projectName") String projectName, @Param("interfaceName") String interfaceName, @Param("caseName")  String caseName, @Param("uid") String uid);

    @Select("SELECT id, api, remarks, dep_cases as depCases, request_type as requestType, return_type as returnType, project_name as projectName, case_name as caseName, case_info as caseInfo, \n" +
            "interface_name as interfaceName, create_by as createBy, \n" +
            "create_date as createDate, update_by as updateBy, \n" +
            "update_date as updateDate, run_num as runNum, is_sucess as isSucess,\n" +
            "passed_num as passedNum, failed_num as failedNum, last_result as lastResult\n" +
            " FROM sys_cases WHERE del_flag = '0' AND id=#{id}")
    Case queryById(@Param("id") String id);

    @Select("SELECT id, case_name as caseName FROM sys_cases WHERE create_by=#{0}")
    List<Case> queryAll(String uid);

    @Select("SELECT id, case_name as caseName FROM sys_cases WHERE create_by=#{uid} AND project_name=#{projectName}")
    List<Case> queryAllCasesByProjectName(@Param("projectName") String projectName, @Param("uid") String uid);

}