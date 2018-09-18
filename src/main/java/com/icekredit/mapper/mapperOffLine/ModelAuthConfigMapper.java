package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.ModelAuthConfig;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ModelAuthConfigMapper {

    @Insert("insert into sys_model_auth_config(id, auth_name, uid, auth_url, user_name, password)" +
            "values(#{id}, #{authName}, #{uid}, #{authUrl}, #{userName}, #{password})")
    void saveModelAuthConfig(ModelAuthConfig r);

    @Update("UPDATE sys_model_auth_config SET auth_name=#{authName}, auth_url=#{authUrl}, user_name=#{userName},password=#{password} WHERE id=#{id}")
    void updateModelAuthConfig(ModelAuthConfig r);

    @Select("SELECT id, auth_name as authName, uid, auth_url as authUrl, user_name as userName, password" +
            " FROM sys_model_auth_config WHERE uid=#{uid} and auth_name like concat('%', #{modelAuthName}, '%')")
    List<ModelAuthConfig> queryModelAuthConfig(@Param("modelAuthName") String modelAuthName, @Param("uid") String uid);

    @Select("SELECT id, auth_name as authName FROM sys_model_auth_config WHERE uid=#{uid}")
    List<ModelAuthConfig> queryAll(@Param("uid") String uid);

    @Select("SELECT  id, auth_name as authName, uid, auth_url as authUrl, user_name as userName, password FROM sys_model_auth_config WHERE  id=#{authId}")
    ModelAuthConfig queryById(@Param("authId")String authId);
}
