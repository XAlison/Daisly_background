package com.icekredit.utils;

import com.icekredit.pojo.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定时任务运行工厂类
 *
 * @author tq
 * @date 2016/5/1
 */
public class QuartzJobFactory implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        System.out.println("任务成功运行");
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
//        System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]");
    }
}
