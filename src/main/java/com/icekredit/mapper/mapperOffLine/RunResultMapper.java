package com.icekredit.mapper.mapperOffLine;

import com.alibaba.fastjson.JSONArray;
import com.icekredit.pojo.RunResultInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface RunResultMapper {

    @Insert("Insert sys_run_result(id, assertions_result, run_model_id, result_old, result_new, score_gap, old_suggestion, new_suggestion, run_date, model_data, old_model_score, new_model_score, flag)" +
            "values(#{id}, #{assertionsResult}, #{runModelId}, #{resultOld}, #{resultNew}, #{scoreGap}, #{oldSuggestion},#{newSuggestion}, now(), #{modelData}, #{oldModelScore}, #{newModelScore}, #{flag})")
    void saveRunResult(RunResultInfo runResultInfo);

    @Select("Select id, assertions_result as assertionsResult,run_model_id as runModelId, result_old as resultOld, result_new as resultNew, score_gap as scoreGap, old_suggestion as oldSuggestion, " +
            "new_suggestion as newSuggestion, run_date as runDate, model_data as modelData, old_model_score as oldModelScore, new_model_score as newModelScore, flag FROM sys_run_result WHERE run_model_id=#{0}")
    List<RunResultInfo> queryRunResultByPage(String runModelId);

    @Select("SELECT COUNT(1) FROM sys_run_result WHERE run_model_id=#{0}")
    int getCountByRunModelId(String runModelId);
    @Select("SELECT flag FROM sys_run_result WHERE run_model_id=#{0}")
    List<String> getResultFlagByRunModelId(String runModelId);

    @Delete("DELETE from sys_run_result")
    void delAllRunModelResult();
}
