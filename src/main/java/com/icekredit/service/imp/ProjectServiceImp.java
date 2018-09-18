package com.icekredit.service.imp;

import com.github.pagehelper.PageHelper;
import com.icekredit.mapper.mapperOffLine.ProjectMapper;
import com.icekredit.pojo.Project;
import com.icekredit.service.ProjectService;
import com.icekredit.utils.BeanUtil;
import com.icekredit.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    public Project queryProjectById(Project project){
        return projectMapper.getProjectsById(project);
    }

    public void update( Project project){
        projectMapper.update(project);
    }

    @Override
    public List<Project> queryProjectList(String uid) {
        return projectMapper.queryProjectLIst(uid);
    }

    public void save(Project project){
        projectMapper.save(project);
    }

    public int delProject(String id) {return projectMapper.delProject(id);}

    public PageResult<Project> queryByPage(Integer pageNo, Integer pageSize, String projectName, String uid){
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;

        PageHelper.startPage(pageNo, pageSize);

        return BeanUtil.toPagedResult(projectMapper.queryByPage(projectName, uid));
    }
}
