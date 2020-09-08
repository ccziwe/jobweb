package com.example.jobWeb.mapper;

import com.example.jobWeb.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface UserMapper {

    /**
     * 用户首页注册
     *
     * @param user
     * @return
     */
    @Insert("insert into user(user_name,user_password,user_tel,zfb_name,zfb_number) values (#{user_name},#{user_password},#{user_tel},#{zfb_name},#{zfb_number})")
    int userRegister(User user);

    /**
     * 查询用户账号是否重复
     *
     * @param user_name
     * @return
     */
    @Select("SELECT * FROM user where user_name=#{user_name}")
    List<User> queryUserName(String user_name);


    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Select("SELECT * FROM user where user_name=#{user_name} and user_password=#{user_password}")
    User userLogin(User user);


    /**
     * 用户信息修改
     *
     * @param user
     * @return
     */
    @Update("update user set user_name = #{user_name} , user_password = #{user_password} where user_id = #{user_id}")
    int updateUserInfo(User user);

    /**
     * 根据ID查用户信息
     *
     * @param user_id
     * @return
     */
    @Select("SELECT * FROM user where user_id = #{user_id}")
    User queryUserById(int user_id);

    /**
     * 用户提现
     * @param user
     * @return
     */
    @Update("UPDATE user SET user_money = #{user_money}, user_frozen_money = #{user_frozen_money} WHERE user_id = #{user_id}")
    int userWithDrawal(User user);
}
