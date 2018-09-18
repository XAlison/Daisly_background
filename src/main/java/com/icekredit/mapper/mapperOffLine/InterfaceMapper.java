package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.Interface;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface InterfaceMapper {

    @Select("P_GET_PROJECTDETAIL_BY_ID(#{id})")
    Interface getProjectsById(Interface i);

    @Update("update sys_interfaces set interface_name=#{interfaceName},update_date=now(),update_by=#{updateBy}," +
            "remarks=#{remarks}, request_type=#{requestType}, api=#{api}, return_type=#{returnType} where id=#{id}")
    void update(Interface i);

    @Insert("insert into sys_interfaces(id, interface_name, project_id, request_type, api, return_type, create_by, create_date, " +
            "update_by, update_date, remarks, sequence) values(#{id}, #{interfaceName}, #{projectId}, #{requestType}," +
            "#{api}, #{returnType}, #{createBy}, now(), #{updateBy}," +
            "now(), #{remarks},#{sequence})")
    void save(Interface i);
//
    @Select("select id, interface_name as interfaceName, create_by as createBy, create_date as createDate," +
            "update_by as updateBy, update_date as updateDate, remarks, sequence, api," +
            "request_type as requestType, return_type as returnType from sys_interfaces " +
            "where del_flag='0' and project_id=#{projectId} and interface_name like concat('%', #{projectName}, '%')")
    List<Interface> queryByPage(@Param("projectName") String projectName, @Param("projectId") String projectId);

    @Update("update sys_interfaces SET del_flag='1' where id=#{0}")
    int delInterface(String id);

    @Select("select id, interface_name as interfaceName, api, request_type as requestType from sys_interfaces where project_id=#{0}")
    List<Interface> getQueryInterfaceValue(String id);

    @Select("select id, project_name as projectName, create_by as createBy, create_date as createDate," +
            "update_by as updateBy, update_date as updateDate, remarks, sequence from sys_projects where project_name like concat('%', #{name}, '%')")
    List<Interface> search(Interface i);
}