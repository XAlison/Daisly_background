package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.RunModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RunModelMapper {

    @Insert("insert into sys_run_model(id, run_model_name, model_config_id, data_num, flag, create_date, uid)" +
            "values(#{id}, #{runModelName}, #{modelConfigId}, #{dataNum}, #{flag}, now(), #{uid})")
    void saveRunModel(RunModel r);

    @Update("UPDATE sys_run_model SET flag=#{flag} WHERE id=#{runModelId}")
    void updateRunModelFlag(@Param("flag") char flag, @Param("runModelId")String runModelId);

    @Update("UPDATE sys_run_model SET data_actual_num=#{actualNum} WHERE id=#{runModelId}")
    void setActualNum(@Param("actualNum") int actualNum, @Param("runModelId")String runModelId);

    @Select("SELECT id, run_model_name as runModelName, model_config_id as modelConfigId, create_date as createDate, data_num as dataNum, flag" +
            " FROM sys_run_model WHERE run_model_name like concat('%', #{modelName}, '%') AND uid=#{uid}")
    List<RunModel> queryRunModelBypage(@Param("modelName") String runModelName, @Param("uid") String uid);

    @Select("SELECT id, run_model_name as runModelName, model_config_id as modelConfigId, create_date as createDate, data_num as dataNum, flag" +
            ",uid FROM sys_run_model WHERE uid=#{uid}")
    List<RunModel> queryAllRunModel(@Param("uid") String uid);
    @Select("SELECT data_actual_num FROM sys_run_model WHERE id=#{runModelId}")
    int getActualNum(@Param("runModelId")String runModelId);


    @Select("CALL P_DEL_RUN_MODEL(#{id}, #{uid})")
    void del(@Param("id") String id, @Param("uid") String uid);
}
