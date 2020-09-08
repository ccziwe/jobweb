package com.example.jobWeb.service;

import com.example.jobWeb.domain.Article;
import com.example.jobWeb.domain.Enroll;
import com.example.jobWeb.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccziwe
 * @date 2020-09-02 16:07
 */
@Service
public interface EnrollService {
    List<User> queryAllEnroll(Enroll enroll);
    int userEnroll(Enroll enroll);
    List<Enroll> checkEnroll(Enroll enroll);
    int passEnroll(Enroll enroll);
    List<Article> userQueryEnroll(Enroll enroll);
}
