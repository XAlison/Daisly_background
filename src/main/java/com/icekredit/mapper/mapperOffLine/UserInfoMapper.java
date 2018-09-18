package com.icekredit.mapper.mapperOffLine;

import com.github.pagehelper.Page;
import com.icekredit.pojo.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    @Select("select * from user_info")
    Page<UserInfo> selectAll();
}