package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.Project;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectMapper {

    @Select("P_GET_PROJECTDETAIL_BY_ID(#{id})")
    Project getProjectsById(Project project);

    @Select("select id, project_name as projectName from sys_projects WHERE create_by=#{uid} ")
    List<Project> queryAll(@Param("uid") String uid);

    @Select("SELECT sys_projects.id, sys_projects.project_name, sys_projects.create_date, sys_projects.remarks, sys_user.user_name as create_by FROM sys_projects, sys_user WHERE sys_projects.create_by=#{uid} and sys_user.id=#{uid}")
    List<Project> queryProjectLIst(@Param("uid") String uid);

    @Update("update sys_projects set project_name=#{projectName},update_date=now(),update_by=#{updateBy}," +
            "remarks=#{remarks} where id=#{id}")
    void update(Project project);

    @Insert("insert into sys_projects(id, project_name, create_by, create_date, " +
            "update_by, update_date, remarks, sequence) values(#{id},#{projectName}, #{createBy}, now(), #{updateBy}," +
            "now(), #{remarks},#{sequence})")
    void save(Project project);
//
    @Select("select id, project_name as projectName, create_by as createBy, create_date as createDate," +
            "update_by as updateBy, update_date as updateDate, remarks, sequence from sys_projects " +
            "where del_flag='0' and create_by=#{uid} AND project_name like concat('%', #{projectName}, '%')")
    List<Project> queryByPage(@Param("projectName") String projectName, @Param("uid") String uid);

    @Update("update sys_projects SET del_flag='1' where id=#{0}")
    int delProject(String id);

    @Select("select id, project_name as projectName, create_by as createBy, create_date as createDate," +
            "update_by as updateBy, update_date as updateDate, remarks, sequence from sys_projects where project_name like concat('%', #{name}, '%')")
    List<Project> search(Project project);
}