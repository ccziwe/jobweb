package com.example.jobWeb.service;

import com.example.jobWeb.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    List<User> queryAllUser();
    int userRecharge(int money,int user_id);
    int PassUserWithDrawal(int user_id);
    List<User> queryWithDrawalUser();
    int  updateUserFrozenMoney(int money,int user_id);
    int updateUserPwd(int user_id);
    int pauseUser(int user_id);
}
