package com.icekredit.mapper.mapperOffLine;

import com.icekredit.dto.LoginDto;
import com.icekredit.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据登录名称查询用户
     * @param user
     * @return
     */
    @Select("SELECT id, user_name, account, password, salt, locked FROM sys_user where user_name=#{userName} ")
    List<User> getByLoginName(LoginDto user);

    @Select("SELECT id, user_name, account, password, salt, locked,token FROM sys_user where token=#{token}")
    User getUserByToken(@Param("token") String token);

    @Update("UPDATE sys_user SET token=#{token} WHERE id = #{uid}")
    void updateToken(@Param("uid") String uid, @Param("token") String token);

    @Select("SELECT id, user_name, account, password, salt, locked FROM sys_user where user_name=#{userName} LIMIT 1")
    User getByUser(User user);

    @Update("UPDATE sys_user SET password=#{newPassWd} WHERE id = #{uid}")
    void updatePassword(@Param("uid") String uid, @Param("newPassWd") String newPassWd);
}
