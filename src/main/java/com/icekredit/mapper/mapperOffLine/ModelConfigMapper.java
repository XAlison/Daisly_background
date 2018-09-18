package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.ModelConfig;
import com.icekredit.pojo.RunModel;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface ModelConfigMapper {

    @Update("update sys_model_config set  model_auth_id=#{modelAuthConfigId}, assertions=#{assertions}, result=#{result}, model_name=#{modelName}, function=#{function}, class_name=#{className}, service_id=#{serviceId}, interface2model=#{interface2model}," +
            "score_path=#{scorePath}, suggestion_path=#{suggestionPath}, model_data_resource=#{modelDataResource} WHERE id=#{id} and uid=#{uid}")
    void update(ModelConfig m);

    @Insert("insert into sys_model_config(id, model_auth_id, assertions, result, model_name, function, class_name, service_id, interface2model, score_path, suggestion_path, model_data_resource, uid) " +
            "values(#{id}, #{modelAuthConfigId}, #{assertions}, #{result},#{modelName},#{function},#{className},#{serviceId},#{interface2model},#{scorePath},#{suggestionPath}, #{modelDataResource}, #{uid})")
    void save(ModelConfig m);

    @Delete("call P_DEL_RUN_MODEL_CONFIG(#{0})")
    int del(String id);

    @Select("SELECT id, model_name as modelName, model_auth_id as modelAuthConfigId, assertions, result, function, class_name as className," +
            "service_id as serviceId, interface2model, " +
            "score_path as scorePath, suggestion_path as suggestionPath, model_data_resource as modelDataResource from sys_model_config where model_name like concat('%', #{modelName}, '%') and uid=#{uid}")
    List<ModelConfig> queryByPage(@Param("modelName") String modelName, @Param("uid")String uid);

    @Select("SELECT id, model_auth_id as modelAuthConfigId, model_name as modelName, assertions, function, class_name as className," +
            "service_id as serviceId, result, interface2model, " +
            "score_path as scorePath, suggestion_path as suggestionPath, model_data_resource as modelDataResource from sys_model_config where id=#{0}")
    ModelConfig getById(String id);
}