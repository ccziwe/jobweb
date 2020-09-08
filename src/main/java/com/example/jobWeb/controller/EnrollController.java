package com.example.jobWeb.controller;

import com.auth0.jwt.JWT;
import com.example.jobWeb.config.UserLoginToken;
import com.example.jobWeb.domain.Article;
import com.example.jobWeb.domain.CommonResult;
import com.example.jobWeb.domain.Enroll;
import com.example.jobWeb.domain.User;
import com.example.jobWeb.service.impl.EnrollServicelmpl;
import com.example.jobWeb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 报名
 *
 * @author ccziwe
 * @date 2020-09-03 11:24
 */
@RestController
@RequestMapping("enroll")
public class EnrollController {
    @Autowired
    private EnrollServicelmpl enrollService;

    /**
     * 用户报名任务
     * @param token
     * @param articleId
     * @return
     */
    @UserLoginToken
    @RequestMapping("userEnroll")
    public CommonResult userEnroll(@RequestHeader("token") String token,
                                   @RequestParam(name = "articleId") int articleId) {
        Enroll enroll = new Enroll(Integer.parseInt(JWT.decode(token).getAudience().get(0)), articleId);
        if (enrollService.checkEnroll(enroll).size() == 0){
            // 未报名
            int i = enrollService.userEnroll(enroll);
            return new CommonResult(200, "报名成功");
        }
        return new CommonResult(400, "请勿重复报名");
    }

    /**
     * 用户根据ID和状态查询自己已报名的所有任务
     * status 0未审核 | 1已通过
     * @param token
     * @param status
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "userQueryEnroll",method = RequestMethod.POST)
    public CommonResult userQueryEnroll(@RequestHeader("token") String token,
                                   @RequestParam(name = "status") int status) {
        Enroll enroll = new Enroll(Integer.parseInt(JWT.decode(token).getAudience().get(0)), 0,status);
        List<Article> articles = enrollService.userQueryEnroll(enroll);
            return new CommonResult(200, articles);
    }

    /**
     * 管理员根据任务ID和状态查询未审核或已通过的人员名单
     * status 0未审核 | 1已通过
     * @param token
     * @param enroll
     * @return
     */
    @UserLoginToken
    @RequestMapping(value ="queryAllEnroll", method = RequestMethod.POST)
    public CommonResult queryAllEnroll(@RequestHeader("token") String token,Enroll enroll){
        if (Integer.parseInt(JWT.decode(token).getAudience().get(0)) == 1){
            List<User> userList = enrollService.queryAllEnroll(enroll);
            return new CommonResult(200, userList);
        }
        return new CommonResult(400, "您不是管理员");
    }

    /**
     * 管理员通过审核
     * @param token
     * @param enroll
     * @return
     */
    @UserLoginToken
    @RequestMapping("passEnroll")
    public CommonResult passEnroll(@RequestHeader("token") String token,Enroll enroll){
        if (Integer.parseInt(JWT.decode(token).getAudience().get(0)) == 1){
            int i = enrollService.passEnroll(enroll);
            return new CommonResult(200, "审核通过");
        }
        return new CommonResult(400, "您不是管理员");
    }
}
