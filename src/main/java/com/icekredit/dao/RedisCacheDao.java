package com.icekredit.dao;

import com.icekredit.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheDao {
    @Autowired
    Config config;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name="redisTemplate")
    ValueOperations<Object, Object> valOps;
    @Resource(name="stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    public Object getObj(String key) {
        return valOps.get(config.getRedisKeyPrefix() + key);
    }

    public boolean setStr(String key, String value, int expireTime) {
        if (expireTime > 0){
            valOpsStr.set(config.getRedisKeyPrefix() + key, value, expireTime);
            valOps.getOperations().expire(config.getRedisKeyPrefix() + key,expireTime,TimeUnit.SECONDS);
        }else {
            valOpsStr.set(config.getRedisKeyPrefix() + key, value);
            valOps.getOperations().expire(config.getRedisKeyPrefix() + key,expireTime,TimeUnit.SECONDS);
        }
        return true;
    }

    public String getStr(String key) {
        return valOpsStr.get(config.getRedisKeyPrefix() + key);
    }

    public boolean setObj(String key, Object value, int expireTime) {
        if (expireTime > 0){
            valOps.set(config.getRedisKeyPrefix() + key, value);
            valOps.getOperations().expire(config.getRedisKeyPrefix() + key, expireTime, TimeUnit.SECONDS);
        }else {
            valOps.set(config.getRedisKeyPrefix() + key, value);
            valOps.getOperations().expire(config.getRedisKeyPrefix() + key,expireTime,TimeUnit.SECONDS);
        }
        return true;

    }

    public boolean delStr(String key) {
        valOpsStr.getOperations().delete(config.getRedisKeyPrefix() + key);
        return true;
    }

    public boolean delObj(String key) {
        valOps.getOperations().delete(config.getRedisKeyPrefix() + key);
        return true;
    }

    public boolean delObj(String key, String field) {
        valOps.getOperations().opsForHash().delete(key, field);
        return true;
    }

    public boolean setObj(String key, String field, Object value, int expireTime) {
        if(value == null)
            return false;
        valOps.getOperations().opsForHash().put(config.getRedisKeyPrefix() + key, field, value);
        if (expireTime > 0){
            valOps.getOperations().expire(config.getRedisKeyPrefix() + key, expireTime, TimeUnit.SECONDS);
        }
        return true;
    }

    public Object getObj(String key, String field) {
        return valOps.getOperations().opsForHash().get(config.getRedisKeyPrefix() + key, field);

    }

    public long flushDB() {
        return redisTemplate.execute((RedisCallback<Long>) c -> {
            c.flushDb();
            return 1L;
        });
    }


}
