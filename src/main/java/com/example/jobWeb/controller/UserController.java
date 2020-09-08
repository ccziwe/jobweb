package com.example.jobWeb.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.jobWeb.config.UserLoginToken;
import com.example.jobWeb.domain.CommonResult;
import com.example.jobWeb.domain.User;
import com.example.jobWeb.service.impl.TokenServiceImpl;
import com.example.jobWeb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TokenServiceImpl tokenService;


    /**
     * 用户首页注册
     *
     * @return
     */
    @RequestMapping("register")
    public CommonResult userRegister(User user) {
        if (userService.queryUserName(user.getUser_name()).size() == 0) {
            if (userService.userRegister(user) == 1) {
                // 注册成功返回用户对象
                return new CommonResult(200, user, "注册成功");
            } else {
                return new CommonResult(400, "注册失败");
            }
        } else {
            // 注册信息中某个字段与他人重复
            return new CommonResult(400, "用户名重复");
        }
    }

    /**
     * 查询用户账号是否重复
     *
     * @param user_name
     * @return
     */
    @RequestMapping("queryUserName")
    public CommonResult queryUserName(@RequestParam(name = "user_name") String user_name) {
        if (userService.queryUserName(user_name).size() == 0) {
            return new CommonResult(200, "昵称可用");
        } else {
            return new CommonResult(400, "昵称已被使用");
        }
    }


    /**
     * 用户登录
     *
     * @param user_name
     * @param user_password
     * @return
     */
    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    public CommonResult userLogin(@RequestParam(name = "user_name") String user_name,
                                  @RequestParam(name = "user_password") String user_password) {
        User user = userService.userLogin(new User(user_name, user_password));
        if (!StringUtils.isEmpty(user)) {
            if (user.getIsused() == 0) {
                // 账号被封停
                return new CommonResult(400, "账号被封停");
            }
            String token = tokenService.getToken(user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
            jsonObject.put("user", user);
            // 登录成功返回用户对象
            return new CommonResult(200, jsonObject, "登录成功");
        } else {
            return new CommonResult(400, "账号密码错误");
        }
    }

    /**
     * 用户信息修改
     *
     * @param token
     * @param user_name
     * @param user_password
     * @return
     */
    @UserLoginToken
    @RequestMapping("updateUserInfo")
    public CommonResult updateUserInfo(@RequestHeader("token") String token,
                                       @RequestParam(name = "user_name") String user_name,
                                       @RequestParam(name = "user_password") String user_password) {
        User u = userService.queryUserById(Integer.parseInt(JWT.decode(token).getAudience().get(0)));
        User old_user = userService.queryUserById(u.getUser_id());
        System.out.println("原来的USER：" + old_user);
        User user = new User();
        user.setUser_id(u.getUser_id());
        user.setUser_name(user_name);
        if (user_name.length() == 0) {
            user.setUser_name(old_user.getUser_name());
        }
        user.setUser_password(user_password);
        if (user_password.length() == 0) {
            user.setUser_password(old_user.getUser_password());
        }
        System.out.println("现在的USER：" + user);
        if (userService.queryUserName(user_name).size() == 0) {
            int i = userService.updateUserInfo(user);
            if (i == 1) {
                CommonResult cr = new CommonResult(200, user, "修改成功");
                return new CommonResult(200, cr);
            } else {
                return new CommonResult(400, "修改失败");
            }
        } else {
            // 某个字段与他人重复
            return new CommonResult(400, "请检查昵称");
        }
    }

    /**
     * 根据token查用户信息
     *
     * @param token
     * @return
     */
    @UserLoginToken
    @RequestMapping("queryUserById")
    public CommonResult queryUserById(@RequestHeader("token") String token) {
        User user = userService.queryUserById(Integer.parseInt(JWT.decode(token).getAudience().get(0)));
        if (!StringUtils.isEmpty(user)) {
            return new CommonResult(200, user, "查找成功");
        } else {
            return new CommonResult(400, "查找失败");
        }
    }

    /**
     * 用户申请提现金额
     *
     * @param token
     * @param withDrawalMoney
     * @return
     */
    @UserLoginToken
    @RequestMapping("userWithDrawal")
    public CommonResult userWithDrawal(@RequestHeader("token") String token,
                                       @RequestParam(name = "withDrawalMoney") int withDrawalMoney) {
        User user = userService.queryUserById(Integer.parseInt(JWT.decode(token).getAudience().get(0)));
        if (!StringUtils.isEmpty(user) && withDrawalMoney>0) {
            if (user.getUser_money() >= withDrawalMoney) {
                user.setUser_frozen_money(user.getUser_frozen_money() + withDrawalMoney);
                user.setUser_money(user.getUser_money() - withDrawalMoney);
                int i = userService.userWithDrawal(user);
                return new CommonResult(200, user, "申请提现成功");
            }
        }
        return new CommonResult(400, "失败");
    }

}
