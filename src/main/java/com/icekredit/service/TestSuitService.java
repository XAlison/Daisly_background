package com.icekredit.service;

import com.github.pagehelper.PageHelper;
import com.icekredit.mapper.mapperOffLine.TestSuitMapper;
import com.icekredit.pojo.TestSuit;
import com.icekredit.utils.BeanUtil;
import com.icekredit.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSuitService {
    @Autowired
    TestSuitMapper testSuitMapper;
//    public TestSuit getProjectById(TestSuit project){
//
//    }
//    public List<Project> queryAll() {return projectMapper.queryAll();}
    public void update( TestSuit testSuit){
        testSuitMapper.update(testSuit);
    }

    public void save(TestSuit testSuit){
        testSuitMapper.save(testSuit);
    }
    public int delTestSuit(TestSuit testSuit) {return testSuitMapper.del(testSuit);}
    public PageResult<TestSuit> queryByPage(Integer pageNo, Integer pageSize, String testSuitName, String uid){
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;

        PageHelper.startPage(pageNo, pageSize);

        return BeanUtil.toPagedResult(testSuitMapper.queryByPage(testSuitName, uid));
    }
}
