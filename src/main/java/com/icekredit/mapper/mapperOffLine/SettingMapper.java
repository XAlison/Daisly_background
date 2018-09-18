package com.icekredit.mapper.mapperOffLine;

import com.icekredit.pojo.Setting;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-12-14.
 */
public interface SettingMapper {

    /**
     * 查询设置
     * @return
     */
    public List<Setting> getSettings();

    public List<Setting> getSettingByMap(Map m);
}
