package com.example.jobWeb.service.impl;

import com.example.jobWeb.domain.User;
import com.example.jobWeb.mapper.UserInfoMapper;
import com.example.jobWeb.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired()
    private UserInfoMapper userInfoMapper;

    @Override
    public int userNameUpdate(User user) {
        return userInfoMapper.userNameUpdate(user);
    }

    @Override
    public int userPasswordUpdate(User user) {
        return userInfoMapper.userPasswordUpdate(user);
    }

    @Override
    public User queryPwdById(int user_id) {
        return userInfoMapper.queryPwdById(user_id);
    }

    @Override
    public int updateZFBName(String zfb_name, int user_id) {
        return userInfoMapper.updateZFBName(zfb_name,user_id);
    }

    @Override
    public int updateZFBNum(String zfb_number, int user_id) {
        return userInfoMapper.updateZFBNum(zfb_number,user_id);
    }

    @Override
    public int updateZFBTel(String user_tel, int user_id) {
        return userInfoMapper.updateZFBTel(user_tel,user_id);
    }

    @Override
    public User queryUserById(int user_id) {
        return userInfoMapper.queryUserById(user_id);
    }
}
