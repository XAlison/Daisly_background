package com.icekredit.service;

import com.github.pagehelper.PageHelper;
import com.icekredit.pojo.Project;
import com.icekredit.utils.BeanUtil;
import com.icekredit.utils.PageResult;

import java.util.List;


public interface ProjectService {
    Project queryProjectById(Project project);

    void update( Project project);

    List<Project> queryProjectList(String uid);

    void save(Project project);

    int delProject(String id);

    PageResult<Project> queryByPage(Integer pageNo, Integer pageSize, String projectName, String uid);
}
