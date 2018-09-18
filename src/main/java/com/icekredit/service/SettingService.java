package com.icekredit.service;

import com.icekredit.config.Config;
import com.icekredit.mapper.mapperOffLine.SettingMapper;
import com.icekredit.pojo.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class SettingService {

	@Autowired
	private SettingMapper settingMapper;
	@Autowired
	Config config;

	/**
	 *
	 * @param
	 * @return
	 */
	public List<Setting> getSeetings(){
		return settingMapper.getSettings();
	}

	public List<Setting> getSettingByMap(Map m){
		return settingMapper.getSettingByMap(m);
	}
}
