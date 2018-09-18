package com.icekredit.service;

import com.github.pagehelper.PageHelper;
import com.icekredit.mapper.mapperOffLine.ModelAuthConfigMapper;
import com.icekredit.pojo.ModelAuthConfig;
import com.icekredit.utils.BeanUtil;
import com.icekredit.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 17-4-28.
 */
@Service
public class ModelAuthConfigService {
    @Autowired
    ModelAuthConfigMapper modelAuthConfigMapper;


    public void update(ModelAuthConfig  m){
        modelAuthConfigMapper.updateModelAuthConfig(m);
    }

    public void save(ModelAuthConfig m){
        modelAuthConfigMapper.saveModelAuthConfig(m);
    }

    public PageResult<ModelAuthConfig> queryByPage(Integer pageNo, Integer pageSize, String modeAuthlName, String uid){
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;

        PageHelper.startPage(pageNo, pageSize);

        return BeanUtil.toPagedResult(modelAuthConfigMapper.queryModelAuthConfig(modeAuthlName,uid));
    }
    public List<ModelAuthConfig> queryAll(String uid){
        return modelAuthConfigMapper.queryAll(uid);
    }

    public ModelAuthConfig queryByID(String authId){
        return modelAuthConfigMapper.queryById(authId);
    }
}
