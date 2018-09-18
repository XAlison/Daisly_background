package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.EnvironmentCollection;
import com.icekredit.pojo.po.EnvironmentPo;
import com.icekredit.pojo.vo.EnvironmentVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EnvironmentsMapper {
    @Select("SELECT * FROM sys_environments where create_by=#{uid} and project_id=#{projectId} and del_flag='0'")
    List<EnvironmentPo> queryEnvironments(@Param("uid") String uid, @Param("projectId") String projectId);

    @Insert("INSERT INTO sys_environments(id, project_id, environment_name, create_by, create_date, api_headers, api_urlParameter, init_variables, auth_info) values " +
            "(#{id}, #{projectId} ,#{environmentName}, #{createBy}, now(), #{apiHeaders}, #{apiUrlParameter}, #{initVariables}, #{authInfo}) ")
    void save(EnvironmentPo environment);

    @Delete("DELETE FROM sys_environments WHERE id=#{id} AND create_by=#{createBy}")
    int deleteById(EnvironmentVo environments);

    @Update("UPDATE sys_environments SET environment_name=#{environmentName}, update_date=now(), update_by=#{updateBy}, api_headers=#{apiHeaders},api_urlParameter=#{apiUrlParameter},init_variables=#{initVariables}, auth_info=#{authInfo} WHERE id=#{id} AND create_by=#{createBy}")
    void update(EnvironmentPo environment);

    @Select("SELECT id, name, create_by as createBy, create_date as createDate, remarks FROM sys_environments_collection where del_flag='0' and create_by=#{uid} AND name like concat('%', #{name}, '%')")
    List<EnvironmentCollection> queryEnvironmentCollectionByPage(@Param("name") String name, @Param("uid") String uid);

    @Update("UPDATE sys_environments_collection SET del_flag='1' WHERE id=#{id} AND create_by=#{createBy}")
    int delEnvironmentCollection(EnvironmentCollection environmentCollection);

    @Insert("INSERT INTO sys_environments_collection(id, name, create_by,create_date,remarks) values " +
            "(#{id}, #{name}, #{createBy}, now(), #{remarks}) ")
    void saveEnvironmentCollection(EnvironmentCollection environmentsCollection);

    @Insert("UPDATE sys_environments_collection SET name=#{name},remarks=#{remarks} WHERE id=#{id} AND create_by=#{createBy}")
    void updateEnvironmentCollection(EnvironmentCollection environmentsCollection);

    @Select("SELECT * FROM sys_environments WHERE id=#{id}")
    EnvironmentPo findById(String id);
}
