package com.icekredit.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("config")
@ConfigurationProperties(prefix = "web", ignoreUnknownFields = false)
@PropertySource("classpath:/config.properties")
public class Config {
	private String redisKeyPrefix;
	private int loginInforTime;
	private int cacheTime;
	private String uploadPath;
	private String jarName;

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getJarName() {
		return jarName;
	}

	public void setJarName(String jarName) {
		this.jarName = jarName;
	}

	public String getRedisKeyPrefix() {
		return redisKeyPrefix;
	}

	public int getCacheTime() {
		return cacheTime;
	}

	public int getLoginInforTime() {
		return loginInforTime;
	}

	public void setRedisKeyPrefix(String redisKeyPrefix) {
		this.redisKeyPrefix = redisKeyPrefix;
	}

	public void setCacheTime(int cacheTime) {
		this.cacheTime = cacheTime;
	}

	public void setLoginInforTime(int loginInforTime) {
		this.loginInforTime = loginInforTime;
	}

}
