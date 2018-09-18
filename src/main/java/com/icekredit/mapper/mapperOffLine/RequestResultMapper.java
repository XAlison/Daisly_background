package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.po.ResultDetailPo;
import com.icekredit.pojo.po.ResultPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RequestResultMapper {

    @Insert("INSERT INTO sys_request_result(id, test_case_result_id, request_id, response_time, body_size," +
            "body_type, status_code, headers, assertions, body, error_info)" +
            "values(#{id}, #{testCaseResultId}, #{requestId}, #{responseTime}, #{bodySize}, #{bodyType}, #{statusCode}, #{headers}," +
            "#{assertions}, #{body}, #{errorInfo})")
    void saveRequestResult(ResultPo resultPo);

    @Select("SELECT sys_request_result.* FROM sys_request_result WHERE request_id=#{String requestId}")
    List<ResultPo> queryResultByRequestId(@Param("requestId") String requestId);

    @Select("SELECT t.*, r.api, r.request_name, r.request_type FROM ((SELECT s.id AS request_result_id, s.test_case_result_id, s.request_id, s.response_time, s.body_size, s.body_type, s.error_info, s.status_code,\n" +
            "  s.headers, s.assertions, s.body FROM sys_request_result s WHERE s.test_case_result_id=#{testCaseResultId}) t) LEFT JOIN sys_request r ON r.id = t.request_id;")
    List<ResultDetailPo> queryRequestResultDetail(@Param("testCaseResultId") String testCaseResultId);
}
