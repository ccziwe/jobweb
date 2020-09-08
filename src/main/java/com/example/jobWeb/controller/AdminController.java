package com.example.jobWeb.controller;

import com.auth0.jwt.JWT;
import com.example.jobWeb.config.UserLoginToken;
import com.example.jobWeb.domain.CommonResult;
import com.example.jobWeb.domain.User;
import com.example.jobWeb.service.impl.AdminServiceImpl;
import com.example.jobWeb.service.impl.TokenServiceImpl;
import com.example.jobWeb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理员
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TokenServiceImpl tokenService;

    /**
     * 查询所有用户的信息
     *
     * @return
     */
    @UserLoginToken
    @RequestMapping(value ="queryAllUser", method = RequestMethod.POST)
    public CommonResult queryAllUser(@RequestHeader("token") String token) {
        if (Integer.parseInt(JWT.decode(token).getAudience().get(0))==1){
            List<User> users = adminService.queryAllUser();
            // List中去掉管理员对象
            List<User> userList = users.stream().filter( s -> s.getUser_id() != 1).collect(Collectors.toList());
            return new CommonResult(200,userList);
        }
        return new CommonResult(400,"您不是管理员");
    }

    /**
     * 查询需要提现的用户
     * @param token
     * @return
     */
    @UserLoginToken
    @RequestMapping(value ="queryWithDrawalUser", method = RequestMethod.POST)
    public CommonResult queryWithDrawalUser(@RequestHeader("token") String token){
        if (Integer.parseInt(JWT.decode(token).getAudience().get(0))==1){
            List<User> users = adminService.queryWithDrawalUser();
            // List中去掉管理员对象
            List<User> userList = users.stream().filter( s -> s.getUser_id() != 1).collect(Collectors.toList());
            return new CommonResult(200,userList);
        }
        return new CommonResult(400,"您不是管理员");
    }

    /**
     * 给用户充值
     * @param token
     * @param money
     * @param user_id
     * @return
     */
    @UserLoginToken
    @RequestMapping("userRecharge")
    public CommonResult userRecharge(@RequestHeader("token") String token,
                                     @RequestParam(name = "money") int money,
                                     @RequestParam(name = "user_id") int user_id) {
        if (Integer.parseInt(JWT.decode(token).getAudience().get(0))==1){
            int i =adminService.userRecharge(money,user_id);
            return new CommonResult(200,"充值成功");
        }
        return new CommonResult(400,"您不是管理员");
    }

    /**
     * 确认用户提现
     * @param token
     * @param user_id
     * @return
     */
    @UserLoginToken
    @RequestMapping("PassUserWithDrawal")
    public CommonResult PassUserWithDrawal(@RequestHeader("token") String token,
                                           @RequestParam(name = "user_id") int user_id){
        if (Integer.parseInt(JWT.decode(token).getAudience().get(0))==1){
            int i =adminService.PassUserWithDrawal(user_id);
            return new CommonResult(200,"审核提现通过");
        }
        return new CommonResult(400,"您不是管理员");
    }

    /**
     * 修改提现金额
     * @param token
     * @param money
     * @param user_id
     * @return
     */
    @UserLoginToken
    @RequestMapping("updateUserFrozenMoney")
    public CommonResult updateUserFrozenMoney(@RequestHeader("token") String token,
                                              @RequestParam(name = "money") int money,
                                              @RequestParam(name = "user_id") int user_id){
        if (Integer.parseInt(JWT.decode(token).getAudience().get(0))==1 && money>=0){
            int i =adminService.updateUserFrozenMoney(money,user_id);
            return new CommonResult(200,"修改成功");
        }
        return new CommonResult(400,"您不是管理员");
    }

    // 重置用户密码
    @UserLoginToken
    @RequestMapping("updateUserPwd")
    public CommonResult updateUserPwd(@RequestHeader("token") String token,
                                              @RequestParam(name = "user_id") int user_id){
        if (Integer.parseInt(JWT.decode(token).getAudience().get(0))==1){
            int i =adminService.updateUserPwd(user_id);
            return new CommonResult(200,"重置成功,密码123456");
        }
        return new CommonResult(400,"您不是管理员");
    }

    // 封停账号
    @UserLoginToken
    @RequestMapping("pauseUser")
    public CommonResult pauseUser(@RequestHeader("token") String token,
                                      @RequestParam(name = "user_id") int user_id){
        if (Integer.parseInt(JWT.decode(token).getAudience().get(0))==1){
            int i =adminService.pauseUser(user_id);
            return new CommonResult(200,"封停成功");
        }
        return new CommonResult(400,"您不是管理员");
    }
}
