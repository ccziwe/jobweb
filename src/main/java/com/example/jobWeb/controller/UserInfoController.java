package com.example.jobWeb.controller;

import com.auth0.jwt.JWT;
import com.example.jobWeb.config.UserLoginToken;
import com.example.jobWeb.domain.CommonResult;
import com.example.jobWeb.domain.User;
import com.example.jobWeb.service.impl.UserInfoServiceImpl;
import com.example.jobWeb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoServiceImpl userInfoService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户修改昵称
     *
     * @return
     */
    @UserLoginToken
    @RequestMapping("userNameUpdate")
    public CommonResult userNameUpdate(HttpServletRequest httpServletRequest,
                                       @RequestParam(name = "user_new_name") String user_new_name) {
        if (userService.queryUserName(user_new_name).size() == 0) {
            // 昵称没有重复
            User user = new User();
            user.setUser_id(Integer.parseInt(JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0)));
            user.setUser_name(user_new_name);
            userInfoService.userNameUpdate(user);
            return new CommonResult(200, "昵称修改成功");
        } else {
            // 昵称重复
            return new CommonResult(400, "昵称重复，请重新修改");
        }
    }

    /**
     * 用户修改密码
     *
     * @return
     */
    @UserLoginToken
    @RequestMapping("userPasswordUpdate")
    public CommonResult userPasswordUpdate(HttpServletRequest httpServletRequest,
                                           @RequestParam(name = "user_old_password") String user_old_password,
                                           @RequestParam(name = "user_new_password") String user_new_password) {
        User old_user = userInfoService.queryPwdById(Integer.parseInt(JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0)));
        if (old_user.getUser_password().equals(user_old_password)) {
            // 原始密码校验通过
            User user = new User();
            user.setUser_id(old_user.getUser_id());
            user.setUser_password(user_new_password);
            userInfoService.userPasswordUpdate(user);
            return new CommonResult(200, "密码修改成功");
        } else {
            // 原始密码错误
            return new CommonResult(0, "原始密码错误");
        }
    }

    /**
     * 用户修改支付宝姓名账号手机号
     *
     * @param token
     * @param msg
     * @param updata
     * @return
     */
    @UserLoginToken
    @RequestMapping("userInfoUpdate")
    public CommonResult userInfoUpdate(@RequestHeader("token") String token,
                                       @RequestParam(name = "msg") String msg,
                                       @RequestParam(name = "updata") String updata) {
        int uid = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        if (msg.equals("tel")) {
            userInfoService.updateZFBTel(updata, uid);
            return new CommonResult(200, "修改成功");
        } else if (msg.equals("zfbname")) {
            userInfoService.updateZFBName(updata, uid);
            return new CommonResult(200, "修改成功");
        } else if (msg.equals("zfbnum")) {
            userInfoService.updateZFBNum(updata, uid);
            return new CommonResult(200, "修改成功");
        }
        return new CommonResult(400, "修改失败");
    }

    /**
     * 根据ID查询用户信息
     * @param token
     * @return
     */
    @UserLoginToken
    @RequestMapping("queryUserById")
    public CommonResult queryUserById(@RequestHeader("token") String token) {
        User user = userService.queryUserById(Integer.parseInt(JWT.decode(token).getAudience().get(0)));
        return new CommonResult(200, user);
    }
}
