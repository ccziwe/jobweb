package com.example.jobWeb.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jobWeb.domain.User;
import com.example.jobWeb.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    /**
     * token的生成方法
     *
     * @param user
     * @return
     */
    @Override
    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(String.valueOf(user.getUser_id()))
                .sign(Algorithm.HMAC256(user.getUser_password()));
        return token;
    }
}
