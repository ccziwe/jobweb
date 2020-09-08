package com.example.jobWeb.service.impl;

import com.example.jobWeb.domain.User;
import com.example.jobWeb.mapper.UserMapper;
import com.example.jobWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired()
    private UserMapper userMapper;

    @Override
    public int userRegister(User user) {
        return userMapper.userRegister(user);
    }

    @Override
    public List<User> queryUserName(String user_name) {
        return userMapper.queryUserName(user_name);
    }

    @Override
    public User userLogin(User user) {
        return userMapper.userLogin(user);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUserInfo(user);
    }

    @Override
    public User queryUserById(int user_id) {
        return userMapper.queryUserById(user_id);
    }

    @Override
    public int userWithDrawal(User user) {
        return userMapper.userWithDrawal(user);
    }

}
