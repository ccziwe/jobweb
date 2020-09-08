package com.example.jobWeb.service.impl;

import com.example.jobWeb.domain.Article;
import com.example.jobWeb.domain.Enroll;
import com.example.jobWeb.domain.User;
import com.example.jobWeb.mapper.EnrollMapper;
import com.example.jobWeb.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccziwe
 * @date 2020-09-02 16:07
 */
@Service
public class EnrollServicelmpl implements EnrollService {
    @Autowired()
    private EnrollMapper enrollMapper;


    @Override
    public List<User> queryAllEnroll(Enroll enroll) {
        return enrollMapper.queryAllEnroll(enroll);
    }

    @Override
    public int userEnroll(Enroll enroll) {
        return enrollMapper.userEnroll(enroll);
    }

    @Override
    public List<Enroll> checkEnroll(Enroll enroll) {
        return enrollMapper.checkEnroll(enroll);
    }

    @Override
    public int passEnroll(Enroll enroll) {
        return enrollMapper.passEnroll(enroll);
    }

    @Override
    public List<Article> userQueryEnroll(Enroll enroll) {
        return enrollMapper.userQueryEnroll(enroll);
    }


}
