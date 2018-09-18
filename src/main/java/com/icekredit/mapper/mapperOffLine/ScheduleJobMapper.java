package com.icekredit.mapper.mapperOffLine;


import com.icekredit.pojo.ScheduleJob;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScheduleJobMapper {
	int deleteByPrimaryKey(Long jobId);

	@Insert("INSERT INTO sys_task_schedule_job (id, create_by, update_by, create_date, update_date, next_time,\n" +
			"\t\tjob_name, job_group, job_status,\n" +
			"\t\tcron_expression, remarks, bean_class, method_name\n" +
			"\t\t)\n" +
			"\t\tvalues (#{id}, #{createBy},#{updateBy},#{createDate},#{updateDate}, #{nextTime},\n" +
			"\t\t#{jobName}, #{jobGroup}, #{jobStatus},#{cronExpression}, #{remarks},\n" +
			"\t\t#{beanClass}, #{methodName}\n" +
			"\t\t)")
	int insert(ScheduleJob record);

	int insertSelective(ScheduleJob record);

	ScheduleJob selectByPrimaryKey(Long jobId);

	int updateByPrimaryKeySelective(ScheduleJob record);

	int updateByPrimaryKey(ScheduleJob record);

	@Select("SELECT id, create_date as createDate, update_date as updateDate, update_by as updateBy, create_by as createBy, spring_id as springId, next_time as nextTime," +
			"job_name as jobName, job_group as jobGroup, job_status as jobStatus, cron_expression as cronExpression, remarks, bean_class as beanClass," +
			"method_name as methodName FROM sys_task_schedule_job")
	List<ScheduleJob> getAll();

	@Select("SELECT id, create_date as createDate, update_date as updateDate, update_by as updateBy, create_by as createBy," +
			"job_name as jobName, job_group as jobGroup, spring_id as springId, job_status as jobStatus, cron_expression as cronExpression, remarks, bean_class as beanClass," +
			"method_name as methodName FROM sys_task_schedule_job WHERE create_by=#{uid} AND job_name like concat(concat('%',#{jobName}),'%')")
	List<ScheduleJob> getAllByPage(@Param("jobName") String jobName, @Param("uid") String uid);
}