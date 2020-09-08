package com.example.jobWeb.mapper;

import com.example.jobWeb.domain.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminMapper {
    /**
     * 查询所有用户的信息
     *
     * @return
     */
    @Select("SELECT * FROM user")
    List<User> queryAllUser();

    /**
     * 给用户充值
     * @param money
     * @param user_id
     * @return
     */
    @Update("UPDATE user SET user_money = user_money + #{money} WHERE user_id = #{user_id}")
    int userRecharge(int money,int user_id);

    /**
     * 确认用户提现
     * @param user_id
     * @return
     */
    @Update("UPDATE user SET user_frozen_money = 0 WHERE user_id = #{user_id}")
    int PassUserWithDrawal(int user_id);

    /**
     * 查询所有需要提现的用户信息
     *
     * @return
     */
    @Select("SELECT * FROM user where user_frozen_money>0")
    List<User> queryWithDrawalUser();

    /**
     * 修改用户提现金额
     * @param money
     * @param user_id
     * @return
     */
    @Update("UPDATE user SET user_frozen_money = #{money} WHERE user_id = #{user_id}")
    int  updateUserFrozenMoney(int money,int user_id);

    /**
     * 重置用户密码
     * @param user_id
     * @return
     */
    @Update("UPDATE user SET user_password = '123456' WHERE user_id = #{user_id}")
    int updateUserPwd(int user_id);

    /**
     * 封停账号
     * @param user_id
     * @return
     */
    @Update("UPDATE user SET isused = 0 WHERE user_id = #{user_id}")
    int pauseUser(int user_id);
}
