package com.icekredit.utils;
import com.icekredit.framework.auth.MyException;
import com.icekredit.pojo.ScheduleJob;
//import com.icekredit.service.RunService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TaskUtils {
	public final static Logger logger = LoggerFactory.getLogger(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(ScheduleJob scheduleJob) throws MyException {
//		Object object = null;
//		Class clazz = null;
//		if (!MyString.isEmpty(scheduleJob.getSpringId())) {
//			if (null == SpringContextHolder.getApplicationContext()){
//				return;
//			}
//			object = SpringContextHolder.getBean(scheduleJob.getSpringId(), RunService.class);
//		}else if (!MyString.isEmpty(scheduleJob.getBeanClass())){
//			try {
//				clazz = Class.forName(scheduleJob.getBeanClass());
//				object = clazz.newInstance();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				logger.error("定时任务反射调用{}失败", scheduleJob.getBeanClass());
//			}
//		}
//		if (object == null) {
//			logger.error("任务名称 = [{}]---------------未启动成功，请检查是否配置正确！！！", scheduleJob.getJobName());
//			throw new MyException("000001");
//		}
//		clazz = object.getClass();
//		Method method = null;
//		try {
//			method = clazz.getDeclaredMethod(scheduleJob.getMethodName(), ScheduleJob.class);
//		} catch (NoSuchMethodException e) {
//			logger.error("任务名称 = [{}]---------------未启动成功，方法名设置错误！！！", scheduleJob.getJobName());
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			logger.error("任务名称 = [{}]---------------未启动成功，方法名设置错误！！！", scheduleJob.getJobName());
//		}
//		if (method != null) {
//			try {
//				method.invoke(object, scheduleJob);
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalArgumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}
