package com.example.jobWeb.mapper;

import com.example.jobWeb.domain.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserInfoMapper {

    /**
     * 根据ID查询用户信息
     * @param user_id
     * @return
     */
    @Select("SELECT * FROM user where user_id = #{user_id}")
    User queryUserById(int user_id);

    /**
     * 用户修改昵称
     *
     * @param user
     * @return
     */
    @Update("update user set user_name=#{user_name} where user_id = #{user_id}")
    int userNameUpdate(User user);

    /**
     * 用户修改密码
     *
     * @param user
     * @return
     */
    @Update("update user set user_password = #{user_password} where user_id = #{user_id}")
    int userPasswordUpdate(User user);

    /**
     * 根据ID查询原来的密码
     *
     * @param user_id
     * @return
     */
    @Select("SELECT user_password FROM zj_users where user_id = #{user_id}")
    User queryPwdById(int user_id);

    /**
     * 修改支付宝姓名
     * @param zfb_name
     * @param user_id
     * @return
     */
    @Update("UPDATE user SET zfb_name = #{zfb_name} WHERE user_id = #{user_id}")
    int updateZFBName(String zfb_name,int user_id);

    /**
     * 修改支付宝账号
     * @param zfb_number
     * @param user_id
     * @return
     */
    @Update("UPDATE user SET zfb_number = #{zfb_number}  WHERE user_id = #{user_id}")
    int updateZFBNum(String zfb_number,int user_id);

    /**
     * 修改手机号
     * @param user_tel
     * @param user_id
     * @return
     */
    @Update("UPDATE user SET user_tel = #{user_tel}  WHERE user_id = #{user_id}")
    int updateZFBTel(String user_tel,int user_id);
}
