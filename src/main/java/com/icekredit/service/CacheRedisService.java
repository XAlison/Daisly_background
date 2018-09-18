package com.icekredit.service;

import com.icekredit.config.Config;
import com.icekredit.dao.RedisCacheDao;
import com.icekredit.pojo.Setting;
import com.icekredit.utils.Const;
import com.icekredit.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cacheService")
public class CacheRedisService {

    @Autowired
    private RedisCacheDao redisCache;

    @Autowired
    private Config config;

    @Autowired
    private SettingService settingService;

    public Object getObj(String key){
        return redisCache.getObj(key);
    }

    public Object setObj(String key, Object value, int expireTime){
        return redisCache.setObj(key, value, expireTime);
    }

    public boolean delObj(String key){
        return redisCache.delObj(key);
    }


    public Setting getSetting(String key){
        Object obj = redisCache.getObj(Const.CACHE_AUTHORIZE , key);

        if(obj == null){
            List<Setting> settings = settingService.getSettingByMap(Tools.getMap("key",key));
            if(settings.size() > 0){
                redisCache.setObj(Const.CACHE_SETTING, key, settings.get(0), config.getCacheTime());
                return settings.get(0);
            }
        }else{
            return (Setting) obj;
        }
        return new Setting();
    }

    @SuppressWarnings("unchecked")
    public List<Setting> getSetting(){
        Object obj = redisCache.getObj(Const.CACHE_SETTINGLIST);

        if(obj == null){
            List<Setting> settings = settingService.getSeetings();
            redisCache.setObj(Const.CACHE_SETTINGLIST, settings, config.getCacheTime());
            return settings;
        }else{
            return (List<Setting>) obj;
        }
    }

    public boolean setStr(String key, String value, int expireTime) {
        return redisCache.setStr(key, value, expireTime);
    }

    public String getStr(String key) {
        return redisCache.getStr(key);
    }

    public void delStr(String key) {
        redisCache.delStr(key);
    }

}
