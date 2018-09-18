package com.icekredit.service;

import com.github.pagehelper.PageHelper;
import com.icekredit.mapper.mapperOffLine.InterfaceMapper;
import com.icekredit.pojo.Interface;
import com.icekredit.utils.BeanUtil;
import com.icekredit.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterfaceService {
    @Autowired
    InterfaceMapper interfaceMapper;

    public Interface getProjectById(Interface i){
        return interfaceMapper.getProjectsById(i);
    }

    public void update( Interface i){
        interfaceMapper.update(i);
    }

    public void save(Interface i){
        interfaceMapper.save(i);
    }
    public int delInterface(String id) {return interfaceMapper.delInterface(id);}
    public PageResult<Interface> queryByPage(Integer pageNo, Integer pageSize, String interfaceName, String projectId){
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;

        PageHelper.startPage(pageNo, pageSize);

        return BeanUtil.toPagedResult(interfaceMapper.queryByPage(interfaceName, projectId));
    }
}
