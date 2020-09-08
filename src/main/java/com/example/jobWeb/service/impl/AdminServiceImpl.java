package com.example.jobWeb.service.impl;

import com.example.jobWeb.domain.User;
import com.example.jobWeb.mapper.AdminMapper;
import com.example.jobWeb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired()
    private AdminMapper adminMapper;

    @Override
    public List<User> queryAllUser() {
        return adminMapper.queryAllUser();
    }

    @Override
    public int userRecharge(int money, int user_id) {
        return adminMapper.userRecharge(money,user_id);
    }

    @Override
    public int PassUserWithDrawal(int user_id) {
        return adminMapper.PassUserWithDrawal(user_id);
    }

    @Override
    public List<User> queryWithDrawalUser() {
        return adminMapper.queryWithDrawalUser();
    }

    @Override
    public int updateUserFrozenMoney(int money, int user_id) {
        return adminMapper.updateUserFrozenMoney(money,user_id);
    }

    @Override
    public int updateUserPwd(int user_id) {
        return adminMapper.updateUserPwd(user_id);
    }

    @Override
    public int pauseUser(int user_id) {
        return adminMapper.pauseUser(user_id);
    }

}
