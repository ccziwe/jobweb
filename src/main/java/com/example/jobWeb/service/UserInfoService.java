package com.example.jobWeb.service;

import com.example.jobWeb.domain.User;
import org.springframework.stereotype.Service;

/**
 * 用户信息修改
 */
@Service
public interface UserInfoService {
    int userNameUpdate(User user);
    int userPasswordUpdate(User user);
    User queryPwdById(int user_id);
    int updateZFBName(String zfb_name,int user_id);
    int updateZFBNum(String zfb_number,int user_id);
    int updateZFBTel(String user_tel,int user_id);
    User queryUserById(int user_id);

}
