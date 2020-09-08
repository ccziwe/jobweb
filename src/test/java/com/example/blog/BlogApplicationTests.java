package com.example.jobWeb;

import com.example.jobWeb.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private UserService userSerivce;

    @Test
    void contextLoads() {

    }


}
