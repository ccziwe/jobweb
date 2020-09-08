package com.example.jobWeb.service;

import com.example.jobWeb.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface UserService {
    int userRegister(User user);
    List<User> queryUserName(String user_name);
    User userLogin(User user);
    int updateUserInfo(User user);
    User queryUserById(int user_id);
    int userWithDrawal(User user);
}
